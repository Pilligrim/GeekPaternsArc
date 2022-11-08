package service;

import domain.HttpResponse;

public class ResponseSerializerImpl implements ResponseSerializer {
    @Override
    public String serialize(HttpResponse httpResponse) {
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 ").append(httpResponse.getStatusCode().getStatus()).append(" ").append(httpResponse.getStatusCode().getName()).append("\n");
        httpResponse.getHeaders().forEach((header, value) -> sb.append(header).append(": ").append(value).append("\n"));
        sb.append("\n");
        sb.append(httpResponse.getBody());
        return sb.toString();
    }
}
