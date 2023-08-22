package com.isi.adminimpot.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * classe d'exception si on fait une réquete au niveau de la BD
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntityNotFoundException extends RuntimeException{
    String message;
    HttpStatus status;

    public EntityNotFoundException(String message) {
    }
}
