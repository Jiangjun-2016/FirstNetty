package com.fanlingjun.ch1;

/**
 * @author fxf
 * @create 2018-06-22
 **/
public class ServerBoot {

    private static final int PORT = 8000;

    public static void main(String[] args) {
        Server server = new Server(PORT);
        server.start();
    }
}
