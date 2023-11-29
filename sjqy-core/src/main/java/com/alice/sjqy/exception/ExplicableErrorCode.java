package com.alice.sjqy.exception;

/**
 * @ClassName: ExplicableErrorCode
 * @Description: TODO
 * @Application:
 * @author: 86180
 * @date: 2022/10/31 13:11
 * @version: 1.0
 * @Copyright:
 */
public interface ExplicableErrorCode {

    /**
     * 获取描述信息
     * @return
     */
    String getMsg();

    /**
     * 返回错误码
     * @return
     */
    String getCode();
}
