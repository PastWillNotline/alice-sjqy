package com.alice.sjqy.api;

import cn.hutool.core.thread.ThreadUtil;
import com.alice.sjqy.api.entity.AjaxResult;
import com.alice.sjqy.api.entity.TaskInfoVo;
import com.alice.sjqy.log.AfSjqyLog;
import com.alice.sjqy.log.LogCache;
import com.alice.sjqy.task.BaseTask;
import com.alice.sjqy.task.TaskCache;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: TaskController
 * @Description: 迁移任务
 * @Application:
 * @author: 86180
 * @date: 2022/10/27 16:16
 * @version: 1.0
 * @Copyright:
 */
@RestController
@RequestMapping("/alice/api")
public class TaskController {

    @GetMapping("/tasks")
    public AjaxResult<List<TaskInfoVo>> getTasks() {
        Collection<BaseTask> baseTasks = TaskCache.getAll();
        List<TaskInfoVo> taskInfoVos = new ArrayList<>();
        for (BaseTask baseTask : baseTasks) {
            TaskInfoVo taskInfoVo = new TaskInfoVo();
            taskInfoVo.setTargetDbGroup(baseTask.getTargetDbGroup());
            taskInfoVo.setSourceDbGroup(baseTask.getSourceDbGroup());
            taskInfoVo.setTaskName(baseTask.getTaskName());
            taskInfoVo.setRunStatus(baseTask.getRunStatus());
            taskInfoVo.setProgress(baseTask.getProgress());
            taskInfoVos.add(taskInfoVo);
        }
        return AjaxResult.success(taskInfoVos);
    }

    @GetMapping("/task/start")
    public AjaxResult startTask(@RequestParam("taskName") String taskName) {
        BaseTask task = TaskCache.getTask(taskName);
        ThreadUtil.execute(()->{
            task.run();
        });
        return AjaxResult.success(taskName+"已执行！");
    }

    @GetMapping("/logs")
    public AjaxResult getLogs(@RequestParam("taskName") String taskName) {
        Queue<AfSjqyLog> logs = LogCache.getLogs(taskName);
        return AjaxResult.success(logs);
    }
}
