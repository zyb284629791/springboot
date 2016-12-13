package com.sccl.YbZ.springboot.common;

import org.apache.commons.lang.exception.NestableRuntimeException;

/**
 * 代码生成器异常
 * Created by zyb on 2016/12/13.
 */
public class CodeBuliderException extends NestableRuntimeException {


    public CodeBuliderException(String errorCode) {
        super(errorCode);
    }

    public CodeBuliderException(Throwable cause) {
        super(cause);
    }
}
