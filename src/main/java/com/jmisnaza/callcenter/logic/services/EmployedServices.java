package com.jmisnaza.callcenter.logic.services;

import com.jmisnaza.callcenter.enums.RolEnum;

import java.util.concurrent.atomic.AtomicInteger;

public interface EmployedServices {

    AtomicInteger OPERATORS_QUANTITY = new AtomicInteger(7);
    AtomicInteger SUPERVISORS_QUANTITY = new AtomicInteger(2);
    AtomicInteger DIRECTORS_QUANTITY = new AtomicInteger(1);

    RolEnum assignEmployed();

    void enableEmployed(RolEnum rol);
}
