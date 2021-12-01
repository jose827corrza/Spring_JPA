package com.jose.crudspring;



import java.io.Serializable;

public enum Gender implements Serializable {
    MALE,
    FEMALE;


    public String getGender(){
        return this.name();
    }
}
