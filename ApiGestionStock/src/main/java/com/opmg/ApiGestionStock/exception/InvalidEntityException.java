package com.opmg.ApiGestionStock.exception;

import com.opmg.ApiGestionStock.handler.BusinessErrorCodes;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class InvalidEntityException extends RuntimeException {
    private HttpStatus httpStatus;
    private int code;

    public InvalidEntityException(Throwable cause, BusinessErrorCodes code) {
        super(code.getDescription(), cause);
        this.httpStatus = code.getHttpStatus();
        this.code = code.getCode();
    }


    public InvalidEntityException(BusinessErrorCodes code) {
        super(code.getDescription());
        this.httpStatus = code.getHttpStatus();
        this.code = code.getCode();
    }
}
