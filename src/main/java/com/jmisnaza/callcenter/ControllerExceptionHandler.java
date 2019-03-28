package com.jmisnaza.callcenter;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler ({Exception.class})
    public String callCenterException(Exception e){
        return e.getMessage();
    }

    @ExceptionHandler ({NullPointerException.class})
    public String callCenterNullPointerException(NullPointerException e){
        return e.getMessage();
    }
}
