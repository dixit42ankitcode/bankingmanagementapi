package com.bankingmanagement.exception;

import com.bankingmanagement.controller.BankController;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

import java.util.logging.Logger;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(BankDetailsNotFound.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handleBankException(BankDetailsNotFound ex, HttpServletRequest request){
        log.error("exception while processing bank details",ex);
        ExceptionResponse exceptionResponse=new ExceptionResponse();
        exceptionResponse.setErrorMessage(ex.getMessage());
        exceptionResponse.setRequestedURI(request.getRequestURI());
        return exceptionResponse;
    }
    @ExceptionHandler(Branchdetailsnotfound.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handleBankException(final  Branchdetailsnotfound ex, final  HttpServletRequest request) {
        log.error("exception while processsing branch details",ex);
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setErrorMessage(ex.getMessage());
        exceptionResponse.setRequestedURI(request.getRequestURI());
        return exceptionResponse;
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handleBankException(final  Exception ex, final  HttpServletRequest request){
        log.error("ecxeption while processing  any details",ex);
        ExceptionResponse exceptionResponse=new ExceptionResponse();
        exceptionResponse.setErrorMessage(ex.getMessage());
        exceptionResponse.setRequestedURI(request.getRequestURI());
        return exceptionResponse;
    }
}
