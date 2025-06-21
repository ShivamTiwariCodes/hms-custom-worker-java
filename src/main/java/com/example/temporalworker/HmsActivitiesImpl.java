package com.example.temporalworker;

import com.example.temporalworker.utils.CustomHandler;
import com.example.temporalworker.utils.HandlerManager;
import com.hms.sdk.core.CustomArgs;
import com.hms.sdk.core.RoutineResponse;
import io.temporal.workflow.Workflow;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class HmsActivitiesImpl implements HmsActivities {

    private HandlerManager handlerManager;

    HmsActivitiesImpl(HandlerManager handlerManager) {
        this.handlerManager = handlerManager;
    }

    @Override
    public RoutineResponse processEvent(CustomArgs customArgs) {

        String hospitalCode = customArgs.getSource();
        String handlerKey = handlerManager.getCustomHandlerCode(hospitalCode, "handle");
        CustomHandler handler = handlerManager.getCustomHandler(handlerKey);

        Object complex = handler.handle(customArgs);

        Workflow.getLogger(HmsWorkflowImpl.class).info("HMS workflow received args: {}", customArgs);

        Map<String, Object> data = new HashMap<>();
        data.put("response", complex);
        return new RoutineResponse(customArgs.getSource(), data);
    }
}
