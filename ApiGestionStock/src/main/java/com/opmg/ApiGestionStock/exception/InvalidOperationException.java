package com.opmg.ApiGestionStock.exception;

import com.opmg.ApiGestionStock.handler.BusinessErrorCodes;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class InvalidOperationException extends RuntimeException {
    private HttpStatus httpStatus;
    private int code;

    public InvalidOperationException(BusinessErrorCodes code, Throwable cause) {
        super(code.getDescription(), cause);
        this.code = code.getCode();
        this.httpStatus = code.getHttpStatus();
    }

    public InvalidOperationException(BusinessErrorCodes code) {
        super(code.getDescription());
        this.code = code.getCode();
        this.httpStatus = code.getHttpStatus();
    }
}
