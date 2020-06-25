package com.payday.authenticationgservice.models;

import com.sun.istack.internal.NotNull;

public class CustomerLogin {
    @NotNull
    private String nationalId;

    @NotNull
    private String password;

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return "Customer(NationalId: " + this.nationalId + ", Password: " + this.password + ")";
    }
}
