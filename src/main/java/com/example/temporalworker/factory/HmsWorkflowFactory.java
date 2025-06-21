package com.example.temporalworker.factory;

import com.example.temporalworker.HmsWorkflowImpl;
import com.example.temporalworker.utils.HandlerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HmsWorkflowFactory implements WorkflowFactory<HmsWorkflowImpl> {

    @Autowired
    private HandlerManager handlerManager;

    @Override
    public HmsWorkflowImpl newInstance() {
        return new HmsWorkflowImpl();
    }
}

