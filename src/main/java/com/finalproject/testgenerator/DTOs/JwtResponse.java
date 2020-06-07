package com.finalproject.testgenerator.DTOs;

import java.io.Serializable;

/**
 * This class represents a response for jwt request
 * The jwttoken will be used for C, U, D operations from CRUD
 */
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;

    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return this.jwttoken;
    }
}