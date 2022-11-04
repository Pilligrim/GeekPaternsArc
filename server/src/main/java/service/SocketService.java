package service;

import java.io.Closeable;
import java.util.List;

public interface SocketService extends Closeable {

    List<String> readRequest();

    void writeResponse(String rawResponse);
}
