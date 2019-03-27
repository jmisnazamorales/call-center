package com.jmisnaza.callcenter;

import com.jmisnaza.callcenter.entities.Call;
import com.jmisnaza.callcenter.entities.builder.CallFactory;
import com.jmisnaza.callcenter.enums.CallStatusEnum;
import com.jmisnaza.callcenter.tasks.TakeCall;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@RestController
@RequestMapping("/dispatcher")
public class Dispatcher {


    /**
     * Esta implementación de <code>ExecutorService</code> permite fijar una cantidad de hilos
     * que se pueden procesar de manera concurrente. Para este caso se fija en 10 que corresponde
     * a la cantidad de personal atentiendo llamadas en el call-center.
     * Ademas por defecto el ejecutor trae una <code>LinkedBlockingQueue</code>, lo que permite
     * encolar las solicitudes que vayan llegando y que seran procesadas según disponibilidad,
     * luego de que alguno de los hilos se desocupe.
     */
    private static final ExecutorService executor =  Executors.newFixedThreadPool(10);

    @GetMapping
    public void dispatchCall() {
        Call call = CallFactory.buildCall();
        TakeCall called = new TakeCall(call);
        CompletableFuture<Call> completable = CompletableFuture.supplyAsync(called::call, executor);
        completable.whenCompleteAsync((status, exception) -> {
            if (status.getStatus().equals(CallStatusEnum.SUCCESS)) {
                log.info("Finish call id {}, Rol {}", call.getIdCall(), call.getTakenBy().getRolEmployed());
            } else {
                log.error("Finish call. Fail to remove rol {}", call.getTakenBy().getRolEmployed());
            }
        });
    }

}
