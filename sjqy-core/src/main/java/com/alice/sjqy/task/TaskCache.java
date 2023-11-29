package com.alice.sjqy.task;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName: TaskCache
 * @Description:
 * @Application:
 * @author: 86180
 * @date: 2022/10/27 15:26
 * @version: 1.0
 * @Copyright:
 */
public class TaskCache {
    private static final Map<String,BaseTask> tasks = new LinkedHashMap<>();

    public static void addTask(BaseTask baseTask) {
        tasks.put(baseTask.getTaskName(),baseTask);
        System.out.println(baseTask.getTaskName()+"-已完成初始化！");
    }

    public static Collection<BaseTask> getAll() {
        return tasks.values();
    }

    public static BaseTask getTask(String taskName) {
        return tasks.get(taskName);
    }

    public static boolean taskIsExist(String taskName) {
        return tasks.containsKey(taskName);
    }
}
