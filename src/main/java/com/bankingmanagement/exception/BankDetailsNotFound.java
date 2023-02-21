package com.bankingmanagement.exception;

public class BankDetailsNotFound extends Exception{
     public BankDetailsNotFound(String message){
        super(message);
    }
    public BankDetailsNotFound()
    {
        super();
    }
}
