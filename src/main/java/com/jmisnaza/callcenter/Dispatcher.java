package com.jmisnaza.callcenter;

import com.jmisnaza.callcenter.entities.Call;
import com.jmisnaza.callcenter.entities.builder.CallFactory;
import com.jmisnaza.callcenter.enums.CallStatusEnum;
import com.jmisnaza.callcenter.tasks.TakeCall;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@RestController
@RequestMapping("/dispatcher")
public class Dispatcher {


    private static final ExecutorService executor =  Executors.newFixedThreadPool(10);

    @PostMapping
    public void dispatchCall() {
        Call call = CallFactory.buildCall();
        TakeCall called = new TakeCall(call);
        try {
            CompletableFuture<Call> completable = CompletableFuture.supplyAsync(called::call, executor);
            completable.whenCompleteAsync((status, exception) -> {
                if (status.getStatus().equals(CallStatusEnum.SUCCESS)) {
                    log.info("Finish call id . Rol {}", call.getIdCall(), call.getTakenBy().getRolEmployed());
                } else {
                    log.error("Finish call. Fail to remove rol {}", call.getTakenBy().getRolEmployed());
                }
            });
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
        }
    }

}
