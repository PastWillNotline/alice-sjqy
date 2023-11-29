package com.alice.sjqy.log;

import lombok.Data;

/**
 * @ClassName: AfSjqyLog
 * @Description: 数据迁移日志
 * @Application:
 * @author: 86180
 * @date: 2022/10/27 14:47
 * @version: 1.0
 * @Copyright:
 */
@Data
public class AfSjqyLog {

    /**
     * 目标数据库组
     */
    private String targetDbGroup;

    /**
     * 源数据库组
     */
    private String sourceDbGroup;
    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 日志级别（INFO、ERROR）
     */
    private String logLevel;

    /**
     * 日志内容
     */
    private String logInfo;

    /**
     * 日志执行时间
     * eg:yyyy-MM-dd HH:mm:ss
     */
    private String createTime;
}