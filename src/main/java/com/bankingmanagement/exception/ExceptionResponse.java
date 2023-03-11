package com.bankingmanagement.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponse {
    private String ErrorMessage;
    private String RequestedURI;

}
