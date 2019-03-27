package com.jmisnaza.callcenter;

import com.jmisnaza.callcenter.entities.Call;
import com.jmisnaza.callcenter.entities.Employee;
import com.jmisnaza.callcenter.enums.CallStatusEnum;
import com.jmisnaza.callcenter.logic.EmployedServiceImpl;
import com.jmisnaza.callcenter.logic.services.EmployedServices;
import com.jmisnaza.callcenter.tasks.TakeCall;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class Dispatcher {

    private final EmployedServices employedServices = new EmployedServiceImpl();

    private static ExecutorService executor;

    public Dispatcher(){
        executor =  Executors.newFixedThreadPool(10);
    }

    public void processCalls(Call call) {
        Employee employed = new Employee();
        try {
            employed.setRolEmployed(employedServices.assignEmployed());
            call.setTakenBy(employed);
            call.setStatus(CallStatusEnum.IN_PROGRESS);
            TakeCall called = new TakeCall(call);
            CompletableFuture<Call> completable = CompletableFuture.supplyAsync(called::call, executor);
            completable.whenCompleteAsync((status, exception) -> {
                if (status.getStatus().equals(CallStatusEnum.SUCCESS)) {
                    log.info("Finish call. Rol {}", call.getTakenBy().getRolEmployed());
                    employedServices.enableEmployed(call.getTakenBy().getRolEmployed());
                } else {
                    log.info("Finish call. Fail to remove rol {}", call.getTakenBy().getRolEmployed());
                }
            });
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
        }
    }

}
