package com.yjc.system.commen.common.exception.base;

import com.yjc.system.commen.common.utils.MessageUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
* 基础异常
* @author lm
* @description
**/
@Slf4j
@AllArgsConstructor
public class BaseException extends RuntimeException
{

    private static final long serialVersionUID = 1L;

    /**
     * 所属模块
     */
    @Getter
    private String module;

    /**
     * 错误码
     */
    @Getter
    private String code;

    /**
     * 错误码对应的参数
     */
    @Getter
    private Object[] args;

    /**
     * 错误消息
     */
    @Getter
    private String defaultMessage;


    public BaseException(String module, String code, Object[] args)
    {
        this(module, code, args, null);
    }

    public BaseException(String module, String defaultMessage)
    {
        this(module, null, null, defaultMessage);
    }

    public BaseException(String code, Object[] args)
    {
        this(null, code, args, null);
    }

    public BaseException(String defaultMessage)
    {
        this(null, null, null, defaultMessage);
    }

    @Override
    public String getMessage()
    {
        String message = null;
        if (!StringUtils.isEmpty(code))
        {
            message = MessageUtils.message(code, args);
        }
        if (message == null)
        {
            message = defaultMessage;
        }
        return message;
    }


    @Override
    public String toString()
    {
        return this.getClass() + "{" + "module='" + module + '\'' + ", message='" + getMessage() + '\'' + '}';
    }
}
