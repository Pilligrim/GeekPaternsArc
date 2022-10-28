

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Deque;
import java.util.LinkedList;


public class RxWebServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);

        Observable.<Socket>create(emitter -> {
                    try {
                        while (true) {
                            Socket socket = serverSocket.accept();
                            System.out.println("New connection");
                            emitter.onNext(socket);
                        }
                    } catch (Exception ex) {
                        emitter.onError(ex);
                    }
                })
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .map(socket -> {
                    BufferedReader input = new BufferedReader(
                            new InputStreamReader(
                                    socket.getInputStream(), StandardCharsets.UTF_8));

                    while (!input.ready()) ;

                    Deque<String> request = new LinkedList<>();
                    while (input.ready()) {
                        String line = input.readLine();
                        System.out.println(line);
                        request.add(line);
                    }
                    return request;
                }).subscribe(request -> {
                    request.forEach(System.out::println);
                });

        System.out.println("Press any key");
        System.in.read();
    }
}
