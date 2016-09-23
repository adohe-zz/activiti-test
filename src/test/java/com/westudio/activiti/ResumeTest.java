package com.westudio.activiti;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.ServiceTask;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.westudio.activiti.builder.ModelBuilder;

/**
 * @author YourName
 * @version $Id: ResumeTest.java, v 0.1 16/9/23 下午5:10 YourName Exp $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/activiti-context.xml" })
public class ResumeTest {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService    runtimeService;

    @Test
    public void testNormalServiceTask() throws Exception {
        BpmnModel model = new BpmnModel();
        Process process = new Process();
        model.addProcess(process);
        process.setId("test_normal_tasks");

        final String startId = "start";
        final String endId = "end";
        final String taskId = "task";
        final String taskIdTwo = "taskTwo";
        process.addFlowElement(ModelBuilder.noneStartEvent(startId));
        process.addFlowElement(ModelBuilder.sequenceFlow(startId, taskId));
        ServiceTask serviceTask = new ServiceTask();
        serviceTask.setId(taskId);
        serviceTask.setAsynchronous(true);
        serviceTask.setImplementationType("class");
        serviceTask.setImplementation("com.westudio.activiti.ShortSleepTask");
        process.addFlowElement(serviceTask);
        process.addFlowElement(ModelBuilder.sequenceFlow(taskId, taskIdTwo));
        ServiceTask serviceTaskTwo = new ServiceTask();
        serviceTaskTwo.setId(taskIdTwo);
        serviceTaskTwo.setAsynchronous(true);
        serviceTaskTwo.setImplementationType("class");
        serviceTaskTwo.setImplementation("com.westudio.activiti.LongSleepTask");
        process.addFlowElement(serviceTaskTwo);
        process.addFlowElement(ModelBuilder.sequenceFlow(taskIdTwo, endId));
        process.addFlowElement(ModelBuilder.noneEndEvent(endId));

        // deploy process definition to process engine
        repositoryService.createDeployment().addBpmnModel("test.bpmn", model).name("Simple Test").deploy();

        // start process instance
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("test_normal_tasks");
        System.out.println("pi id: " + pi.getId());

        while (true) {
            ProcessInstance pp = runtimeService.createProcessInstanceQuery().processInstanceId(pi.getId()).singleResult();
            if (!pp.isEnded()) {
                Thread.sleep(10 * 1000);
                System.out.println("sleep for a while");
            } else {
                break;
            }
        }
    }
}
