package hr.academy.sightseeingapp.model;

import java.sql.Timestamp;
import java.util.UUID;
import jakarta.persistence.*;

@Entity
@Table(name = "HTTP_ERRORS", schema = "SIGHTSEEING_APP")
public class HTTPError {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column private UUID uuid;
    @Column private Timestamp timestamp;
    @Column private String message;
    @Column private String type;
    @Column private int httpCode;
    @Column(columnDefinition = "TEXT") private String requestHeaders;
    @Column(columnDefinition = "TEXT") private String requestBody;

    public HTTPError(Timestamp timestamp, String message, String type, int httpCode, String requestHeaders, String requestBody) {
        this.timestamp = timestamp;
        this.message = message;
        this.type = type;
        this.httpCode = httpCode;
        this.requestHeaders = requestHeaders;
        this.requestBody = requestBody;
    }

    public HTTPError() {

    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public String getRequestHeaders() {
        return requestHeaders;
    }

    public void setRequestHeaders(String requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }
}
