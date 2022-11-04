package service;

import domain.HttpResponse;

public interface ResponseSerializer {

    String serialize(HttpResponse httpResponse);
}
