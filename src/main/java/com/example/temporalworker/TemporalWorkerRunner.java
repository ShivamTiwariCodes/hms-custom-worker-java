package com.example.temporalworker;

import com.example.temporalworker.config.SpringContext;
import com.example.temporalworker.factory.HmsWorkflowFactory;
import com.example.temporalworker.utils.HandlerManager;
import com.hms.sdk.core.IHmsWorkflow;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

@Component
public class TemporalWorkerRunner {


    private final HandlerManager handlerManager;

    @Autowired
    public TemporalWorkerRunner(HandlerManager handlerManager) {
        this.handlerManager = handlerManager;
    }

    @Value("${temporal.host}")
    private String temporalHost;

    @Value("${temporal.taskQueue}")
    private String taskQueue;

    @PostConstruct
    public void startWorker() {
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance(
                WorkflowServiceStubsOptions.newBuilder().setTarget(temporalHost).build()
        );

        WorkflowClient client = WorkflowClient.newInstance(service);
        WorkerFactory factory = WorkerFactory.newInstance(client);
        Worker worker = factory.newWorker(taskQueue);

        worker.addWorkflowImplementationFactory(IHmsWorkflow.class, () ->
                SpringContext.getBean(HmsWorkflowFactory.class).newInstance()
        );

//        worker.registerWorkflowImplementationTypes(HmsWorkflowImpl.class);
        worker.registerActivitiesImplementations(new HmsActivitiesImpl(handlerManager));

        factory.start();
        System.out.println("Worker started on queue: " + taskQueue);
    }
}
