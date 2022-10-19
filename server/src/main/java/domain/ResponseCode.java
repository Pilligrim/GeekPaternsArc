package domain;

public enum ResponseCode {
    OK(200, "OK"),
    BAD_REQUEST(400, "BAD REQUEST"),
    UNAUTHORIZED(401,"UNAUTHORIZED"),
    PAYMENT_REQUIRED(402,"PAYMENT_REQUIRED"),
    FORBIDDEN(403, "FORBIDDEN"),
    NOT_FOUND(404, "NOT FOUND"),
    METHOD_NOT_ALLOWED(405, "METHOD NOT ALLOWED");

    final int status;

    final String name;

    ResponseCode(int status, String name) {
        this.status = status;
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }
}
