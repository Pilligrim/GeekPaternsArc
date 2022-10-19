import domain.HttpRequest;

import java.util.List;
import java.util.Optional;

public class RequestParserImpl implements RequestParser {
    @Override
    public HttpRequest parse(List<String> rawRequest) {
        HttpRequest request = new HttpRequest();
        Optional.ofNullable(rawRequest).orElseThrow(() -> new RuntimeException("Request is empty"));
        Boolean isHeader = false;
        Boolean hasBody = false;
        StringBuilder body = new StringBuilder();
        String line;
        for (int i = 0; i < rawRequest.size(); i++) {
            line = rawRequest.get(i);
            if (i == 0) {
                String[] firstLine = line.split(" ");
                request.setMethod(firstLine[0]);
                request.setUrl(firstLine[1]);
                isHeader = true;
                continue;
            }
            if (isHeader) {
                if (line.isBlank()) {
                    hasBody = true;
                    continue;
                }
                String[] header = line.split(": ");
                request.addHeader(header[0], header[1]);
            }
            if (hasBody) {
                body.append(line);
            }
        }
        if (hasBody) {
            request.setBody(body.toString());
        }
        return request;
    }
}
