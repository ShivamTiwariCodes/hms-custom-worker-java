package com.example.temporalworker;

import com.example.temporalworker.utils.CustomHandler;
import com.example.temporalworker.utils.HandlerManager;
import com.hms.sdk.core.CustomArgs;
import com.hms.sdk.core.IHmsWorkflow;
import com.hms.sdk.core.RoutineResponse;
import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;


public class HmsWorkflowImpl implements IHmsWorkflow {


//    private final HandlerManager handlerManager;
//
//    public HmsWorkflowImpl(HandlerManager handlerManager) {
//        this.handlerManager = handlerManager;
//    }

    private final HmsActivities activities = Workflow.newActivityStub(
            HmsActivities.class,
            ActivityOptions.newBuilder()
                    .setStartToCloseTimeout(Duration.ofMinutes(1))
                    .build());

    @Override
    public RoutineResponse execute(CustomArgs customArgs) {
        try {
            RoutineResponse result = activities.processEvent(customArgs);
            return result;
        } catch (Exception e) {
            Workflow.getLogger(HmsWorkflowImpl.class).error("Error in workflow execution", e);
            throw e;
        }
    }
}
