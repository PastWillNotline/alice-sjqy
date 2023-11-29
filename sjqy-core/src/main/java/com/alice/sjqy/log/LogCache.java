package com.alice.sjqy.log;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import com.alice.sjqy.task.BaseTask;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: LogCache
 * @Description: 日志缓存
 * @Application:
 * @author: 86180
 * @date: 2022/10/27 14:47
 * @version: 1.0
 * @Copyright:
 */
public class LogCache {

    private static final int logMaxSize = 1000;

    private static Map<String, Queue<AfSjqyLog>> logCache = new HashMap<>();

    private static Map<String,String> errorCache = new ConcurrentHashMap<>();

    public static Queue<AfSjqyLog> getLogs(String taskName) {
        return logCache.get(taskName);
    }

    private static synchronized void putLogCache(BaseTask baseTask,String logLevel,String logInfo) {
        AfSjqyLog afSjqyLog = new AfSjqyLog();
        afSjqyLog.setLogLevel(logLevel);
        afSjqyLog.setTaskName(baseTask.getTaskName());
        afSjqyLog.setLogInfo(logInfo);
        afSjqyLog.setSourceDbGroup(baseTask.getSourceDbGroup());
        afSjqyLog.setTargetDbGroup(baseTask.getTargetDbGroup());
        String createTime = DateUtil.formatDateTime(new Date());
        afSjqyLog.setCreateTime(createTime);
        String taskName = afSjqyLog.getTaskName();
        Queue<AfSjqyLog> afSjqyLogs = logCache.get(taskName);
        if(afSjqyLogs == null) {
            Queue<AfSjqyLog> sjqyLogs = new LinkedList<>();
            sjqyLogs.add(afSjqyLog);
            logCache.put(taskName,sjqyLogs);
        }else {
            if(afSjqyLogs.size() >= logMaxSize) {
                afSjqyLogs.poll();
            }
            afSjqyLogs.add(afSjqyLog);
        }
    }

    public static void infoLog(BaseTask baseTask,String logInfo) {
        String baseLogStart = String
            .format("【%s】【%s-%s】", baseTask.getTaskName(), baseTask.getSourceDbGroup(), baseTask.getTargetDbGroup());
        logInfo = baseLogStart + logInfo;
        System.out.println(logInfo);
        putLogCache(baseTask,"INFO",logInfo);
    }

    public static void errorLog(BaseTask baseTask,String errorInfo) {
        String baseLogStart = String
            .format("【%s】【%s-%s】", baseTask.getTaskName(), baseTask.getSourceDbGroup(), baseTask.getTargetDbGroup());
        errorInfo = baseLogStart + errorInfo;
        System.err.println(errorInfo);
        putLogCache(baseTask,"ERROR",errorInfo);
    }

    public static void errorLog(BaseTask baseTask,String errorInfo,Exception e) {
        String baseLogStart = String
            .format("【%s】【%s-%s】", baseTask.getTaskName(), baseTask.getSourceDbGroup(), baseTask.getTargetDbGroup());
        errorInfo = baseLogStart + errorInfo;
        System.err.println(errorInfo);
        putLogCache(baseTask,"ERROR",errorInfo);
    }

    public static void putErrorStackCache(String taskName,String errorInfo) {
        errorCache.put(taskName,errorInfo);
    }

    public static String getErrorStackCache(String taskName) {
        return errorCache.get(taskName);
    }

    public static String getErrorStackHtmlCache(String taskName) {
        String stackInfo = errorCache.get(taskName);
        Assert.notBlank(stackInfo);
        return stackInfo.replace("\n","<br>").replace(" ","&nbsp;");
    }
}
