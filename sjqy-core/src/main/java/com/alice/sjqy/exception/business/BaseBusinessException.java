package com.alice.sjqy.exception.business;

import com.alice.sjqy.exception.BusinessException;
import com.alice.sjqy.exception.ExplicableErrorCode;
import java.util.StringJoiner;

/**
 * @ClassName: BaseBusinessException
 * @Description: 通用业务异常
 * @Application:
 * @author: 86180
 * @date: 2022/10/31 13:24
 * @version: 1.0
 * @Copyright:
 */
public class BaseBusinessException extends BusinessException {

    /**
     * 异常信息
     */
    protected ExplicableErrorCode errorCode;

    /**
     * 显示参数
     */
    protected Object[] args;

    public BaseBusinessException() {

    }

    public BaseBusinessException(ExplicableErrorCode errorCode, Object... args) {
        this.args = args;
        this.errorCode = errorCode;
    }

    public BaseBusinessException(ExplicableErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        if(errorCode == null) {
            return null;
        }
        return String.format(errorCode.getMsg(),this.args);
    }

    @Override
    public String toString() {
        return new StringJoiner(" , ")
            .add("["+this.getClass().getSimpleName()+"]")
            .add("ErrorCode=[" + errorCode + "]")
            .add("Msg=[" + String.format(errorCode.getMsg(),this.args)+"]")
            .toString();
    }

    @Override
    public String getMessage() {
        return toString();
    }
}
