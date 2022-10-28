package service;

import domain.HttpRequest;

import java.util.List;

public interface RequestParser {
    HttpRequest parse(List<String> rawRequest);
}
