package com.example.temporalworker.utils;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class HandlerManager {

    private final Map<String, CustomHandler> handlers = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        List<String> hospitalsList = new ArrayList<>();
        hospitalsList.add("hospital1");
        loadCustomHandlers(hospitalsList);
    }

    public void loadCustomHandlers(List<String> hospitalCodeList) {
        for (String hospitalCode : hospitalCodeList) {
            try {
                // Example JAR file path per hospital
                String jarPath = "libs/custom-" + hospitalCode + ".jar";

                String className = "com.custom.hosp1.CustomHandlerImpl";

                URL[] urls = {new File(jarPath).toURI().toURL()};
//                URLClassLoader classLoader = new URLClassLoader(urls, this.getClass().getClassLoader());

                try (URLClassLoader loader = new URLClassLoader(urls)) {
                    Class<?> clazz = Class.forName(className, true, loader);
                    Object targetInstance = clazz.getDeclaredConstructor().newInstance();

                    System.out.println("Methods in " + className + ":");
                    for (Method method : clazz.getDeclaredMethods()) {
                        String methodName = method.getName();
                        String handlerCode = getCustomHandlerCode(hospitalCode, methodName);
                        handlers.put(handlerCode, (customArgs) -> {
                            try {
                                return method.invoke(targetInstance, customArgs);
                            } catch (Exception e) {
                                throw new RuntimeException("Error invoking method " + methodName, e);
                            }
                        });
                    }
                }
            } catch (Exception e) {
                System.err.println("Error loading handler for " + hospitalCode + ": " + e.getMessage());
            }
        }

        System.out.println("Resgistered methods are : " + handlers.keySet().toString());
    }

    public String getCustomHandlerCode(String hospitalCode, String handlerCode) {
        return hospitalCode + "__" + handlerCode;
    }

    public CustomHandler getCustomHandler(String customHandlerCode) {
        return handlers.get(customHandlerCode);
    }
}
