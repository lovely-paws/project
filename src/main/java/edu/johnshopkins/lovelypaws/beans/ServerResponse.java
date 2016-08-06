package edu.johnshopkins.lovelypaws.beans;

public class ServerResponse<T> {

    private boolean success;
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    private String message;
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    private T result;
    public T getResult() { return result; }
    public void setResult(T result) { this.result = result; }

    public ServerResponse() { }
    public ServerResponse(boolean success, String message, T result) {
        this.success = success;
        this.message = message;
        this.result = result;
    }
}
