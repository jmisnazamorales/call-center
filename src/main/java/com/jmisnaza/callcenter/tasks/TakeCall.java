package com.jmisnaza.callcenter.tasks;

import com.jmisnaza.callcenter.entities.Call;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.Callable;

@Slf4j
public class TakeCall implements Callable<Boolean> {

    private final Random random = new Random();

    private Call call;

    public TakeCall (Call call){
        this.call = call;
    }

    @Override
    public Boolean call() throws Exception {
        try {
            long timeCall = calculateTimeCall();
            log.info("Time´s Call {}", timeCall);
            Thread.sleep(calculateTimeCall());
        }
        catch (InterruptedException e){
            log.error("Error to process thread, {}", e.getMessage());
        }
        catch (Exception e){
            log.error("Error, {}", e.getMessage());
        }
        return true;
    }

    private long calculateTimeCall(){
        return random.nextInt(10 - 5 + 1) + 5;
    }
}
