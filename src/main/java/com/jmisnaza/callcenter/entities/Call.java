package com.jmisnaza.callcenter.entities;

import com.jmisnaza.callcenter.enums.CallStatusEnum;


public class Call {

    private int idCall;

    private double lengthCall;

    private Employee takenBy;

    private CallStatusEnum status;

    public int getIdCall() {
        return idCall;
    }

    public void setIdCall(int idCall) {
        this.idCall = idCall;
    }

    public double getLengthCall() {
        return lengthCall;
    }

    public void setLengthCall(double lengthCall) {
        this.lengthCall = lengthCall;
    }

    public Employee getTakenBy() {
        return takenBy;
    }

    public void setTakenBy(Employee takenBy) {
        this.takenBy = takenBy;
    }

    public CallStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CallStatusEnum status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Call{" +
                "idCall=" + idCall +
                ", lengthCall=" + lengthCall +
                ", takenBy=" + takenBy +
                ", status=" + status +
                '}';
    }


}
