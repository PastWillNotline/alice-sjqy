package com.alice.sjqy.exception.sytem.asserts;

import com.alice.sjqy.exception.sytem.TaskException;
import com.alice.sjqy.task.TaskCache;
import com.alice.sjqy.exception.sytem.TaskErrorCode;

/**
 * @ClassName: TaskAssert
 * @Description: 断言
 * @Application:
 * @author: 86180
 * @date: 2022/10/31 14:10
 * @version: 1.0
 * @Copyright:
 */
public class TaskAssert {
    public static void taskNameIsNotExist(String taskName) {
        if(TaskCache.taskIsExist(taskName)) {
            throw new TaskException(TaskErrorCode.TASK_NAME_REPEAT,taskName);
        }
    }

    public static void progressLimit0To100(Integer progress) {
        if(progress<0 || progress>100) {
            throw new TaskException(TaskErrorCode.TASK_PROGRESS_LIMIT,progress);
        }
    }
}
