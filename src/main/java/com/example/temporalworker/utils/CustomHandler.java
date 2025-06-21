package com.example.temporalworker.utils;

import com.hms.sdk.core.CustomArgs;
import com.hms.sdk.core.RoutineResponse;


@FunctionalInterface
public interface CustomHandler {
    Object handle(CustomArgs customArgs);
}
