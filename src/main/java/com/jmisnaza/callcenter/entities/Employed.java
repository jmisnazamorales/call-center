package com.jmisnaza.callcenter.entities;

import com.jmisnaza.callcenter.enums.RolEnum;

public class Employed{

    private int idEmployed;

    private String nameEmployed;

    private RolEnum rolEmployed;

    public int getIdEmployed() {
        return idEmployed;
    }

    public void setIdEmployed(int idEmployed) {
        this.idEmployed = idEmployed;
    }

    public String getNameEmployed() {
        return nameEmployed;
    }

    public void setNameEmployed(String nameEmployed) {
        this.nameEmployed = nameEmployed;
    }

    public RolEnum getRolEmployed() {
        return rolEmployed;
    }

    public void setRolEmployed(RolEnum rolEmployed) {
        this.rolEmployed = rolEmployed;
    }
}
