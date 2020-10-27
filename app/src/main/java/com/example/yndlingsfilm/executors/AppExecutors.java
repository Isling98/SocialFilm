package com.example.yndlingsfilm.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class AppExecutors {

    private static AppExecutors instance;

    public static AppExecutors getInstance() {
        if(instance == null){
            instance = new AppExecutors();
        }
        return instance;
    }
    // bruger scheduled frem for alm. executor så vi senere kan behandle for lange svartider og bruges cached svar i stedet
    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);

    public ScheduledExecutorService getExecutorService() {
        return executorService;
    }
}
