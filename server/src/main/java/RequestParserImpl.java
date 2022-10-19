import domain.HttpRequest;

import java.util.List;
import java.util.Optional;

public class RequestParserImpl implements RequestParser {
    @Override
    public HttpRequest parse(List<String> rawRequest) {
        HttpRequest.Builder builder = HttpRequest.createBuilder();
        Optional.ofNullable(rawRequest).orElseThrow(() -> new RuntimeException("Request is empty"));
        Boolean isHeader = false;
        Boolean hasBody = false;
        StringBuilder body = new StringBuilder();
        String line;
        for (int i = 0; i < rawRequest.size(); i++) {
            line = rawRequest.get(i);
            if (i == 0) {
                String[] firstLine = line.split(" ");
                builder.withMethod(firstLine[0]);
                builder.withUrl(firstLine[1]);
                isHeader = true;
                continue;
            }
            if (isHeader) {
                if (line.isBlank()) {
                    hasBody = true;
                    continue;
                }
                String[] header = line.split(": ");
                builder.withHeader(header[0], header[1]);
            }
            if (hasBody) {
                body.append(line);
            }
        }
        if (hasBody) {
            builder.withBody(body.toString());
        }
        return builder.build();
    }
}
