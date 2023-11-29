package com.alice.sjqy.task;

import com.alice.sjqy.exception.sytem.asserts.TaskAssert;
import com.alice.sjqy.log.LogCache;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @ClassName: BaseTask
 * @Description:
 * @Application:
 * @author: 86180
 * @date: 2022/10/27 14:25
 * @version: 1.0
 * @Copyright:
 */
public abstract class BaseTask {

    /**
     * 1待运行、2运行、3运行结束
     */
    private int runStatus = 1;

    private int progress = 0;

    /**
     * 1待运行、2运行、3运行结束
     */
    public int getRunStatus() {
        return runStatus;
    }

    /**
     * 任务进度
     * @return
     */
    public int getProgress() {
        return progress;
    }

    public void setProgress(int i) {
        TaskAssert.progressLimit0To100(i);
        progress = i;
    }

    /**
     * 设置被迁移的数据库的分组名称
     * @return
     */
    public abstract String getSourceDbGroup();

    /**
     * 设置目标数据库的分组名称
     * @return
     */
    public abstract String getTargetDbGroup();

    /**
     * 设置执行任务的分组名称
     * @return
     */
    public abstract String getTaskName();


    /**
     * 自定义执行任务的逻辑
     */
    public abstract void task() throws Exception;

    /**
     * 执行任务的方法
     */
    public void run() {
        synchronized (this) {
            if(runStatus == 2) {
                LogCache.infoLog(this,"已经运行！");
                return;
            }
            runStatus = 2;
        }
        progress = 0;
        LogCache.infoLog(this,"开始运行！");
        try {
            task();
            progress = 100;
        }catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            LogCache.putErrorStackCache(getTaskName(),sw.toString());
            String errorInfo = "运行中报错！<a href='/alice/errorInfo?taskName="+getTaskName()+"'>点击查看</a>";
            LogCache.errorLog(this, errorInfo,e);
        }
        LogCache.infoLog(this,"运行结束！");
        runStatus = 3;
    }
}
