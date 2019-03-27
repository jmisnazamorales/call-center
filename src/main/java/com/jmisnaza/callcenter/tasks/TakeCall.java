package com.jmisnaza.callcenter.tasks;

import com.jmisnaza.callcenter.entities.Call;
import com.jmisnaza.callcenter.enums.CallStatusEnum;
import com.jmisnaza.callcenter.logic.EmployedServiceImpl;
import com.jmisnaza.callcenter.logic.services.EmployedServices;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

@Slf4j
public class TakeCall implements Callable<Call> {

    private Call call;

    private EmployedServices employedServices = new EmployedServiceImpl();

    public TakeCall (Call call){
        log.info("Call {}", call.getIdCall());
        this.call = call;
    }

    @Override
    public Call call() {
        try {
            call.getTakenBy().setRolEmployed(employedServices.assignEmployed());
            log.info("Time´s Call {} {}", call.getIdCall(), call.getLengthCall());
            try {
                Thread.sleep( new Double(call.getLengthCall()).longValue());
            } catch (InterruptedException e) {
                log.error("Error Thread {}", e.getMessage());
            }
            employedServices.enableEmployed(call.getTakenBy().getRolEmployed());
            this.call.setStatus(CallStatusEnum.SUCCESS);
        }
        catch (Exception e){
            this.call.setStatus(CallStatusEnum.FAIL);
            log.error("Error, {}", e.getMessage());
        }
        return call;
    }
}
