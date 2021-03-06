package com.jmisnaza.callcenter.entities;

import com.jmisnaza.callcenter.enums.RolEnum;

/**
 * POJO de un empleado
 */
public class Employee {

    private RolEnum rolEmployed;

    public RolEnum getRolEmployed() {
        return rolEmployed;
    }

    public void setRolEmployed(RolEnum rolEmployed) {
        this.rolEmployed = rolEmployed;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "rolEmployed=" + rolEmployed +
                '}';
    }
}
