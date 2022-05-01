package main.http;

import threads.Handler;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    private int port;
    private String directory;

    //модель сервера
    public Server(int port, String directory) {
        this.port = port;
        this.directory = directory;
    }

    //запуск сервер-сокета
    void start(){
        try (var server = new ServerSocket(this.port)){
            while (true){
                //созадли сокет
                var socket = server.accept();
                //екземляр классе Handler - поток
                var thread = new Handler(socket, this.directory);
                //запуск потока
                thread.run();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //по примеру https://github.com/EgorRepnikov/java-file-http-server

    public static void main(String[] args) {
        //в первой строке номер порта
        var port  = Integer.parseInt(args[0]);
        //директория во второй строке
        var directory = args[1];
        new Server(port, directory).start();


    }

}
