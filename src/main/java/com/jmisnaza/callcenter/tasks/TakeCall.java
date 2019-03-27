package com.jmisnaza.callcenter.tasks;

import com.jmisnaza.callcenter.entities.Call;
import com.jmisnaza.callcenter.enums.CallStatusEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.Callable;

@Slf4j
public class TakeCall implements Callable<Call> {

    private final Random random = new Random();

    private Call call;

    public TakeCall (Call call){
        this.call = call;
    }

    @Override
    public Call call() {
        try {
            long timeCall = calculateTimeCall();
            log.info("Time´s Call {}", timeCall);
            Thread.sleep(calculateTimeCall());
            this.call.setStatus(CallStatusEnum.SUCCESS);
        }
        catch (InterruptedException e){
            this.call.setStatus(CallStatusEnum.FAIL);
            log.error("Error to process thread, {}", e.getMessage());
        }
        catch (Exception e){
            this.call.setStatus(CallStatusEnum.FAIL);
            log.error("Error, {}", e.getMessage());
        }
        return this.call;
    }

    private long calculateTimeCall(){
        return random.nextInt(10 - 5 + 1) + 5;
    }
}
