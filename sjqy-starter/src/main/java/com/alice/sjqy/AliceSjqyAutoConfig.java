package com.alice.sjqy;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import com.alice.sjqy.exception.sytem.asserts.TaskAssert;
import com.alice.sjqy.task.BaseTask;
import com.alice.sjqy.task.TaskCache;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: AliceSjqyAutoConfig
 * @Description:
 * @Application:
 * @author: 86180
 * @date: 2023/11/28 16:05
 * @version: 1.0
 * @Copyright:
 */
@Slf4j
@Configuration
@ComponentScan
public class AliceSjqyAutoConfig implements CommandLineRunner {

    @Value("${sjqy.baseTaskPackage}")
    private String basePackage;

    @Override
    public void run(String... args) throws Exception {
        if(StrUtil.isBlank(basePackage)) {
            log.info("必要配置：sjqy.baseTaskPackage");
            return;
        }
        Set<Class<?>> classes = ClassUtil.scanPackage(basePackage);
        for (Class<?> aClass : classes) {
            Class<?> superclass = aClass.getSuperclass();
            if(superclass != null && superclass == BaseTask.class) {
                BaseTask baseTask = (BaseTask) aClass.newInstance();
                TaskAssert.taskNameIsNotExist(baseTask.getTaskName());
                TaskCache.addTask(baseTask);
            }
        }
    }
}
