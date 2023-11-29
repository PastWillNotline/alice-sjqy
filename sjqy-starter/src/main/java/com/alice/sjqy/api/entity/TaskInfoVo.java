package com.alice.sjqy.api.entity;

import lombok.Data;

/**
 * @ClassName: TaskVo
 * @Description: 任务信息
 * @Application:
 * @author: 86180
 * @date: 2022/10/27 16:17
 * @version: 1.0
 * @Copyright:
 */
@Data
public class TaskInfoVo {
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
     * 1待运行、2运行、3运行结束
     */
    private int runStatus;

    /**
     * 任务进度
     */
    private int progress;
}
