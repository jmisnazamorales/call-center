package com.jmisnaza.callcenter;

import com.jmisnaza.callcenter.entities.Call;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DispatcherTest {

    private List<Call> callList;

    /**
     * To set up need objects per each test
     */
    @Before
    public void before(){

    }


    @Test()
    public void test10ConcurrentCalls(){
        log.info("Processing call");
        Dispatcher d = new Dispatcher();
        for (int i = 0; i <= 20; i++) {
            Call call = new Call();
            call.setIdCall(i);
            d.processCalls(call);
        }


    }
}
