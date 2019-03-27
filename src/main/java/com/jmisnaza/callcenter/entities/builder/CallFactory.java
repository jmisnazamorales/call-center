package com.jmisnaza.callcenter.entities.builder;

import com.jmisnaza.callcenter.entities.Call;
import com.jmisnaza.callcenter.entities.Employee;
import com.jmisnaza.callcenter.enums.CallStatusEnum;

import java.util.Random;

public class CallFactory {

    private static int idCall = 0;

    private static final Random random = new Random();

    public static Call buildCall(){
        Call call = new Call();
        call.setIdCall(idCall++);
        Employee employed = new Employee();
        call.setTakenBy(employed);
        call.setStatus(CallStatusEnum.IN_PROGRESS);
        call.setLengthCall(calculateTimeCall());
        return call;
    }

    public static long calculateTimeCall(){
        return random.nextInt(20000 - 5000 + 1) + 5000;
    }
}
