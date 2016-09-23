package com.westudio.activiti;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class ShortSleepTask implements JavaDelegate {
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("ShortSleepTask get executed");
        Thread.sleep(60 * 1000);
        System.out.println("ShortSleepTask finish execute");
    }
}
