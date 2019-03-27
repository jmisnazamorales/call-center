package com.jmisnaza.callcenter.logic;

import com.jmisnaza.callcenter.enums.RolEnum;
import com.jmisnaza.callcenter.logic.services.EmployedServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class EmployedServiceImpl implements EmployedServices {

    @Override
    public RolEnum assignEmployed() throws RuntimeException{
        if (OPERATORS_QUANTITY.get() > 0) {
            OPERATORS_QUANTITY.getAndDecrement();
            log.info("Call will be taken by {}", RolEnum.OPERATOR);
            return RolEnum.OPERATOR;
        } else if (SUPERVISORS_QUANTITY.get() > 0) {
            SUPERVISORS_QUANTITY.getAndDecrement();
            log.info("Call will be taken by {}", RolEnum.SUPERVISOR);
            return RolEnum.SUPERVISOR;
        } else if (DIRECTORS_QUANTITY.get() > 0) {
            DIRECTORS_QUANTITY.getAndDecrement();
            log.info("Call will be taken by {}", RolEnum.DIRECTOR);
            return RolEnum.DIRECTOR;
        }
        throw new RuntimeException("Ups!!! There are not employees");
    }

    @Override
    public void enableEmployed(RolEnum rol) {
        log.info("Enable {}", rol);
        switch(rol) {
            case OPERATOR:
                OPERATORS_QUANTITY.incrementAndGet();
                break;
            case SUPERVISOR:
                SUPERVISORS_QUANTITY.incrementAndGet();
                break;
            case DIRECTOR:
                DIRECTORS_QUANTITY.incrementAndGet();
                break;
        }
    }
}
