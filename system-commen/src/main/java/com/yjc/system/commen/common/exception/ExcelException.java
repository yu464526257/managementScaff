package com.yjc.system.commen.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author dck
 * @Date 2019/9/26
 * @Version V1.0
 **/
@Data
@AllArgsConstructor
public class ExcelException extends RuntimeException {
    private String message;

}
