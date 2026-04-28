package main_exam.http;

public class HttpMessage {
    private String sender;
    private String message;
    private long receivedAt;

    public HttpMessage() {
    }

    public HttpMessage(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(long receivedAt) {
        this.receivedAt = receivedAt;
    }

    @Override
    public String toString() {
        return "HttpMessage{sender='" + sender + "', message='" + message + "', receivedAt=" + receivedAt + "}";
    }
}
