package com.yjc.system.commen.common.utils.excel;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.yjc.system.commen.common.annotation.ExcelField;
import com.yjc.system.commen.dto.base.ResultEntity;
import com.yjc.system.commen.common.annotation.ExcelField;
import com.yjc.system.commen.dto.base.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Excel相关处理 Excel2003
 *
 * @author lm
 * @description
 **/
@Slf4j
public class HSSFExcelUtil<T> {
    private HSSFWorkbook workbook;

    /** excel2003中每个sheet中最多有65536行**/
    private final int sheetSize=65536;

    public Class <T> clazz;

    public HSSFExcelUtil(Class <T> clazz) {
        this.clazz=clazz;
        workbook=new HSSFWorkbook();
    }

    /**
     * 导入excel
     *
     * @param sheetName
     * @param input
     * @return
     * @throws Exception
     */
    public List <T> importExcel(String sheetName, InputStream input) throws Exception {
        List <T> list=new ArrayList <T>();
        Workbook workbook=WorkbookFactory.create(input);
        Sheet sheet=workbook.getSheet(sheetName);
        if (StrUtil.isNotEmpty(sheetName)) {
            sheet=workbook.getSheet(sheetName);
        }
        if (sheet == null) {
            sheet=workbook.getSheetAt(0);
        }
        int rows=sheet.getPhysicalNumberOfRows();

        if (rows > 0) {
            Field[] allFields=getAllFields(clazz);
            Map <Integer, Field> fieldsMap=new HashMap <Integer, Field>();
            for (int col=0; col < allFields.length; col++) {
                Field field=allFields[col];
                if (field.isAnnotationPresent(ExcelField.class)) {
                    field.setAccessible(true);
                    fieldsMap.put(col, field);
                }
            }
            for (int i=1; i < rows; i++) {
                // 从第2行开始取数据,默认第一行是表头.
                Row row=sheet.getRow(i);
                int cellNum=sheet.getRow(0).getPhysicalNumberOfCells();
                T entity=null;
                for (int j=0; j < cellNum; j++) {
                    Cell cell=row.getCell(j);
                    if (cell == null) {
                        continue;
                    } else {
                        // 先设置Cell的类型，然后就可以把纯数字作为String类型读进来了
                        row.getCell(j).setCellType(CellType.STRING);
                        cell=row.getCell(j);
                    }
                    String c=cell.getStringCellValue();
                    if (StrUtil.isEmpty(c)) {
                        continue;
                    }

                    entity=(entity == null ? clazz.newInstance() : entity);
                    Field field=fieldsMap.get(j);
                    Class <?> fieldType=field.getType();
                    if (String.class == fieldType) {
                        field.set(entity, String.valueOf(c));
                    } else if ((Integer.TYPE == fieldType) || (Integer.class == fieldType)) {
                        field.set(entity, Integer.parseInt(c));
                    } else if ((Long.TYPE == fieldType) || (Long.class == fieldType)) {
                        field.set(entity, Long.valueOf(c));
                    } else if ((Float.TYPE == fieldType) || (Float.class == fieldType)) {
                        field.set(entity, Float.valueOf(c));
                    } else if ((Short.TYPE == fieldType) || (Short.class == fieldType)) {
                        field.set(entity, Short.valueOf(c));
                    } else if ((Double.TYPE == fieldType) || (Double.class == fieldType)) {
                        field.set(entity, Double.valueOf(c));
                    } else if (Character.TYPE == fieldType) {
                        if ((c != null) && (c.length() > 0)) {
                            field.set(entity, Character.valueOf(c.charAt(0)));
                        }
                    } else if (Date.class == fieldType) {
                        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            cell.setCellValue(sdf.format(cell.getNumericCellValue()));
                            c=sdf.format(cell.getNumericCellValue());
                        } else {
                            c=cell.getStringCellValue();
                        }
                    }else if (BigDecimal.class == fieldType) {
                        c=cell.getStringCellValue();
                    }
                }
                if (entity != null) {
                    list.add(entity);
                }
            }
        }

        return list;
    }

    /**
     * 对list数据源将其里面的数据导入到excel表单
     *
     * @param filename 工作表的名称
     */
    public ResultEntity exportExcel(List <T> list, String filename) {
        generate(list,filename);
        try {
            filename=encodingFilename(filename);
            String path=getfile();
            File file=new File(path), fl=new File(path, filename);
            if (!file.exists()) {
                file.mkdirs();
            }
            if (!fl.exists()) {
                fl.createNewFile();
            }
            OutputStream out=new FileOutputStream(path + filename);
            workbook.write(out);
            out.close();
            return new ResultEntity(filename, "success");
        } catch (Exception e) {
            log.error("导出失败：{}", e.getMessage());
            return new ResultEntity("导出Excel失败！");
        }
    }

    /**
     * 对list数据源将其里面的数据导入到excel表单
     *
     * @param filename 工作表的名称
     */
    public void exportExcel(HttpServletResponse response,List <T> list, String filename) {
        generate(list,filename);
        try {
            filename=encodingFilename(filename);
            response.setContentType("application/octet-stream");
            response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            log.error("导出Excel失败：{}", e.getMessage());
        }
        try {
            response.flushBuffer();
        } catch (IOException var5) {
            log.error("异常：{}", var5.getMessage());
        }
    }

    /**
     *
     * @param list
     * @param sheetName
     */
    private void generate(List <T> list, String sheetName){
        // 得到所有定义字段
        Field[] allFields=getAllFields(clazz);
        List <Field> fields=new ArrayList <Field>();
        // 得到所有field并存放到一个list中.
        for (Field field : allFields) {
            if (field.isAnnotationPresent(ExcelField.class)) {
                fields.add(field);
            }
        }
        // 取出一共有多少个sheet.
        double sheetNo=Math.ceil(list.size() / sheetSize);
        for (int index=0; index <= sheetNo; index++) {
            // 产生工作表对象
            HSSFSheet sheet=workbook.createSheet();
            if (sheetNo == 0) {
                workbook.setSheetName(index, sheetName);
            } else {
                // 设置工作表的名称.
                workbook.setSheetName(index, sheetName + index);
            }
            HSSFRow row;
            HSSFCell cell=null;
            row=sheet.createRow(0);
            /**设置表格样式**/
            setHSSFCellStyle(fields,row,cell,sheet);
            /**填充数据**/
            setData(index,list,fields,row,cell,sheet);

        }
    }
    /**
     * 设置表格样式
     * @param fields
     * @param row
     * @param cell
     * @param sheet
     */
    private void setHSSFCellStyle(List <Field> fields,HSSFRow row,HSSFCell cell,HSSFSheet sheet){
        // 写入各个字段的列头名称
        for (int i=0; i < fields.size(); i++) {
            Field field=fields.get(i);
            ExcelField attr=field.getAnnotation(ExcelField.class);
            cell=row.createCell(i);
            cell.setCellType(CellType.STRING);
            HSSFCellStyle cellStyle=workbook.createCellStyle();
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            if (attr.name().indexOf("注：") >= 0) {
                HSSFFont font=workbook.createFont();
                font.setColor(HSSFFont.COLOR_RED);
                cellStyle.setFont(font);
                sheet.setColumnWidth(i, 6000);
            } else {
                HSSFFont font=workbook.createFont();
                font.setBold(true);
                cellStyle.setFont(font);
                sheet.setColumnWidth(i, 3766);
            }
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cellStyle.setWrapText(true);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(attr.name());

            /**如果设置了提示信息则鼠标放上去提示.**/
            if (StrUtil.isNotEmpty(attr.prompt())) {
                /**这里默认设了2-101列提示.**/
                setHSSFPrompt(sheet, "", attr.prompt(), 1, 100, i, i);
            }
            /**如果设置了combo属性则本列只能选择不能输入**/
            if (attr.combo().length > 0) {
                /**这里默认设了2-101列只能选择不能输入**/
                setHSSFValidation(sheet, attr.combo(), 1, 100, i, i);
            }
        }

    }

    /**
     * 填充数据
     * @param index
     * @param list
     * @param fields
     * @param row
     * @param cell
     * @param sheet
     */
    private void setData(int index,List<T> list,List<Field> fields,HSSFRow row,HSSFCell cell,HSSFSheet sheet){
        HSSFCellStyle cs=workbook.createCellStyle();
        cs.setAlignment(HorizontalAlignment.CENTER);
        cs.setVerticalAlignment(VerticalAlignment.CENTER);
        int startNo=index * sheetSize;
        int endNo=Math.min(startNo + sheetSize, list.size());
        for (int i=startNo; i < endNo; i++) {
            row=sheet.createRow(i + 1 - startNo);
            T vo=(T) list.get(i);
            for (int j=0; j < fields.size(); j++) {
                Field field=fields.get(j);
                field.setAccessible(true);
                ExcelField attr=field.getAnnotation(ExcelField.class);
                try {
                    /**根据Excel中设置情况决定是否导出,有些情况需要保持为空,希望用户填写这一列.**/
                    if (attr.isExport()) {
                        cell=row.createCell(j);
                        cell.setCellStyle(cs);
                        try {
                            if (String.valueOf(field.get(vo)).length() > 10) {
                                throw new Exception("长度超过10位就不用转数字了");
                            }
                            // 如果可以转成数字则导出为数字类型
                            BigDecimal bc=new BigDecimal(String.valueOf(field.get(vo)));
                            cell.setCellType(CellType.NUMERIC);
                            cell.setCellValue(bc.doubleValue());
                        } catch (Exception e) {
                            cell.setCellType(CellType.STRING);
                            if (vo == null) {
                                cell.setCellValue("");
                            } else {
                                cell.setCellValue(field.get(vo) == null ? "" : String.valueOf(field.get(vo)));
                            }
                        }
                    }
                } catch (Exception e) {
                    log.error("导出Excel失败{}", e.getMessage());
                }
            }
        }
    }
    /**
     * 设置单元格上提示
     *
     * @param sheet         要设置的sheet.
     * @param promptTitle   标题
     * @param promptContent 内容
     * @param firstRow      开始行
     * @param endRow        结束行
     * @param firstCol      开始列
     * @param endCol        结束列
     * @return 设置好的sheet.
     */
    public static HSSFSheet setHSSFPrompt(HSSFSheet sheet, String promptTitle, String promptContent, int firstRow, int endRow, int firstCol, int endCol) {
        // 构造constraint对象
        DVConstraint constraint=DVConstraint.createCustomFormulaConstraint("DD1");
        // 四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions=new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
        // 数据有效性对象
        HSSFDataValidation dataValidationView=new HSSFDataValidation(regions, constraint);
        dataValidationView.createPromptBox(promptTitle, promptContent);
        sheet.addValidationData(dataValidationView);
        return sheet;
    }

    /**
     * 设置某些列的值只能输入预制的数据,显示下拉框.
     *
     * @param sheet    要设置的sheet.
     * @param textlist 下拉框显示的内容
     * @param firstRow 开始行
     * @param endRow   结束行
     * @param firstCol 开始列
     * @param endCol   结束列
     * @return 设置好的sheet.
     */
    public static HSSFSheet setHSSFValidation(HSSFSheet sheet, String[] textlist, int firstRow, int endRow, int firstCol, int endCol) {
        // 加载下拉列表内容
        DVConstraint constraint=DVConstraint.createExplicitListConstraint(textlist);
        // 设置数据有效性加载在哪个单元格上,四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions=new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
        // 数据有效性对象
        HSSFDataValidation dataValidationList=new HSSFDataValidation(regions, constraint);
        sheet.addValidationData(dataValidationList);
        return sheet;
    }

    /**
     * 获取所有属性 含父类属性
     *
     * @param clazz
     * @return
     */
    public static Field[] getAllFields(Class clazz) {
        List <Field> fieldList=new ArrayList <>();
        while (clazz != null) {
            fieldList.addAll(new ArrayList <>(Arrays.asList(clazz.getDeclaredFields())));
            clazz=clazz.getSuperclass();
        }
        Field[] fields=new Field[fieldList.size()];
        fieldList.toArray(fields);
        return fields;
    }

    /**
     * 编码文件名
     */
    public String encodingFilename(String filename) {
        filename=filename+"_"+DateUtil.format(new Date(),DatePattern.PURE_DATETIME_MS_PATTERN) + ".xls";
        return filename;
    }

    /**
     * 临时文件路径
     * @return
     * @throws FileNotFoundException
     */
    public String getfile() throws FileNotFoundException {
        return ResourceUtils.getURL("classpath:").getPath() + "static/file/";
    }

}