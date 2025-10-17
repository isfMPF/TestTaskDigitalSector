package com.dsacademy.exception;

/**
 * Исключение выбрасывается, если сотрудник с указанным ID не найден.
 */
public class EmployeeNotFoundException extends Exception{

    public EmployeeNotFoundException() {
    }

    public EmployeeNotFoundException(String message) {
        super(message);
    }

}
