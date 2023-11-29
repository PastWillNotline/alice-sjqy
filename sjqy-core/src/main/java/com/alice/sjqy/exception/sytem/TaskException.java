package com.alice.sjqy.exception.sytem;

import com.alice.sjqy.exception.ExplicableErrorCode;

/**
 * @ClassName: TaskException
 * @Description: 任务运行异常
 * @Application:
 * @author: 86180
 * @date: 2022/10/31 14:07
 * @version: 1.0
 * @Copyright:
 */
public class TaskException extends BaseSystemException{
    public TaskException() {
        super();
    }

    public TaskException(ExplicableErrorCode errorCode, Object... args) {
        super(errorCode,args);
    }

    public TaskException(ExplicableErrorCode errorCode) {
        super(errorCode);
    }
}
