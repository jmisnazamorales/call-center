package com.jmisnaza.callcenter;

import com.jmisnaza.callcenter.entities.Call;
import com.jmisnaza.callcenter.logic.services.EmployedServices;
import com.jmisnaza.callcenter.tasks.TakeCall;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class Dispatcher {

    private final EmployedServices employedServices;

    private final static ExecutorService executor =  Executors.newFixedThreadPool(10);

    public Dispatcher(EmployedServices employedServices){
        this.employedServices = employedServices;
    }

    private void processCalls(Call call){
        call.setTakenBy(employedServices.assignEmployed());
        TakeCall called = new TakeCall(call);
        executor.submit(called);
    }

}
