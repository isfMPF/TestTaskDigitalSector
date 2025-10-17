package com.dsacademy.exception;

/**
 * Кастомное исключение для ошибок загрузки файлов
 * Наследуется от RuntimeException (непроверяемое исключение)
 */
public class FileLoadException extends Exception{


    public FileLoadException(String message) {
        super(message);
    }

    public FileLoadException(String message, Throwable cause) {
        super(message, cause);
    }
}
