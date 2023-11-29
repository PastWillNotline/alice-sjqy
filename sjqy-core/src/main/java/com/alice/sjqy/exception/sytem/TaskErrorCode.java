package com.alice.sjqy.exception.sytem;

import com.alice.sjqy.exception.ExplicableErrorCode;

/**
 * @ClassName: TaskErrorCode
 * @Description: 任务errorCode
 * @Application:
 * @author: 86180
 * @date: 2022/10/31 13:18
 * @version: 1.0
 * @Copyright:
 */
public enum TaskErrorCode implements ExplicableErrorCode {
    TASK_NAME_REPEAT("%s-任务名称重复"),
    TASK_PROGRESS_LIMIT("%d-progress的设置范围在1~100之间");

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public String getCode() {
        return this.name();
    }

    private String msg;

    TaskErrorCode(String msg) {
        this.msg = msg;
    }
}
