package it.polimi.se2018.controller.services;

public class ErrorService {

    /* Error Mapping:
     *
     * 404 - Resource not found
     * 500 - Server error
     */

    private final int errorId;
    private final String message;

    public ErrorService(int errorId, String message) {
        this.errorId = errorId;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getErrorId() {
        return errorId;
    }
}
