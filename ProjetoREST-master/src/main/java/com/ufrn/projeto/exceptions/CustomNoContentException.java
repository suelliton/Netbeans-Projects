/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufrn.projeto.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Taniro
 */
public class CustomNoContentException extends WebApplicationException {

    public CustomNoContentException() {
        super(Response
                    .status(Response.Status.NO_CONTENT)
                    .type(MediaType.APPLICATION_JSON)
                    .build());
    }
    
}
