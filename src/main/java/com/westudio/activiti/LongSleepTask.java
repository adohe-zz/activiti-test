package com.westudio.activiti;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class LongSleepTask implements JavaDelegate {
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("LongSleepTask get executed");
        Thread.sleep(5 * 60 * 1000);
    }
}
