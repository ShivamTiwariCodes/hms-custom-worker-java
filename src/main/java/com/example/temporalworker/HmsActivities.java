package com.example.temporalworker;


import com.hms.sdk.core.CustomArgs;
import com.hms.sdk.core.RoutineResponse;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface HmsActivities {
    @ActivityMethod
    RoutineResponse processEvent(CustomArgs customArgs);
}
