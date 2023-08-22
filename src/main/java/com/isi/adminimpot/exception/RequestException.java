package com.isi.adminimpot.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestException extends RuntimeException{
    String message;

    public RequestException(String message, HttpStatus httpStatus) {
    }
}
