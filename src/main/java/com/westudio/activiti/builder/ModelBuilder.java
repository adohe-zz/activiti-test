package com.westudio.activiti.builder;

import java.util.ArrayList;
import java.util.List;

import org.activiti.bpmn.model.*;

public class ModelBuilder {

    /**
     * Creates a bpmn none start event model.
     * @param id id of the node
     * @return a NoneStartEvent model
     */
    public static StartEvent noneStartEvent(String id) {
        StartEvent startEvent = new StartEvent();
        startEvent.setId(id);
        return startEvent;
    }

    /**
     * Creates a bpmn signal start event model.
     * @param id id of the node.
     * @param signalRef reference of the signal define
     * @return a SignalStartEvent
     */
    public static StartEvent signalStartEvent(String id, String signalRef) {
        List<EventDefinition> eventDefinitions = new ArrayList<EventDefinition>();
        SignalEventDefinition signal = new SignalEventDefinition();
        signal.setSignalRef(signalRef);
        signal.setAsync(false);
        eventDefinitions.add(signal);
        StartEvent startEvent = new StartEvent();
        startEvent.setId(id);
        startEvent.setEventDefinitions(eventDefinitions);
        return startEvent;
    }

    /**
     * Creates a bpmn end event model.
     * @param id id of the node.
     * @return a NoneEndEvent
     */
    public static EndEvent noneEndEvent(String id) {
        EndEvent endEvent = new EndEvent();
        endEvent.setId(id);
        return endEvent;
    }

    /**
     * Creates a bpmn parallel gateway model.
     * @param id id of the node.
     * @return a ParallelGateway
     */
    public static ParallelGateway parallelGateway(String id) {
        ParallelGateway parallelGateway = new ParallelGateway();
        parallelGateway.setId(id);
        return parallelGateway;
    }

    /**
     * Creates a bpmn exclusive gateway model.
     * @param id id of the node.
     * @return a ExclusiveGateway
     */
    public static ExclusiveGateway exclusiveGateway(String id) {
        ExclusiveGateway exclusiveGateway = new ExclusiveGateway();
        exclusiveGateway.setId(id);
        return exclusiveGateway;
    }

    /**
     * Creates a bpmn java service task model.
     * @param id id of the node.
     * @param clazz the external java class name.
     * @return a ServiceTask
     */
    public static ServiceTask serviceTask(String id, String clazz) {
        ServiceTask serviceTask = new ServiceTask();
        serviceTask.setId(id);
        serviceTask.setImplementation(clazz);
        serviceTask.setImplementationType("class");
        return serviceTask;
    }

    /**
     * Creates a bpmn receive task model.
     * @param id id of the node.
     * @return a ReceiveTask
     */
    public static ReceiveTask receiveTask(String id) {
        ReceiveTask receiveTask = new ReceiveTask();
        receiveTask.setId(id);
        return receiveTask;
    }

    /**
     * Creates a bpmn sub-process model.
     * @param id id of the node.
     * @return a SubProcess
     */
    public static SubProcess subProcess(String id, String name) {
        SubProcess subProcess = new SubProcess();
        subProcess.setId(id);
        subProcess.setName(name);
        return subProcess;
    }

    /**
     * Creates a bpmn sequence flow model.
     * @param sourceRef Source element ref of this sequence flow
     * @param targetRef Target element ref of this sequence flow
     * @return a SequenceFlow
     */
    public static SequenceFlow sequenceFlow(String sourceRef, String targetRef) {
        SequenceFlow sequenceFlow = new SequenceFlow();
        sequenceFlow.setSourceRef(sourceRef);
        sequenceFlow.setTargetRef(targetRef);
        return sequenceFlow;
    }

    public static SequenceFlow conditionFlow(String sourceRef, String targetRef, String condition) {
        SequenceFlow sequenceFlow = new SequenceFlow();
        sequenceFlow.setSourceRef(sourceRef);
        sequenceFlow.setTargetRef(targetRef);
        sequenceFlow.setConditionExpression(condition);
        return sequenceFlow;
    }
}
