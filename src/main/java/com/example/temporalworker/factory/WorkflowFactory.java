package com.example.temporalworker.factory;

public interface WorkflowFactory<T> {
    T newInstance();
}
