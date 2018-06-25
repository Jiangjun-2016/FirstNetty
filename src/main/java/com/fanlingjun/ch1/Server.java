package com.fanlingjun.ch1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author fxf
 * @create 2018-06-22
 **/
public class Server {

    private ServerSocket serverSocket;

    public Server(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
            System.out.println("服务端启动成功，端口:" + port);
        } catch (IOException exception) {
            System.out.println("服务端启动失败");
        }
    }

    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                doStart();
            }
        }).start();
    }

    /**
     * while (true) 循环监听新用户的连接
     * 对应的就是netty中 NioEventLoop(Nio事件的循环)
     * 事件循环主要包括：1 新连接的接入 2 连接当前存在的一个连接上面的数据流的读写
     *
     * 新连接：Channel 其实是对socket的一个抽象
     *
     * 服务端 接收数据：ByteBuf 封装了一些API
     *
     * 服务端 业务处理逻辑： ChannelHandler.  业务逻辑处理链 抽象出  Pipeline
     *
     * 服务端 写回数据：ByteBuf
     */
    private void doStart() {
        while (true) {
            try {
                Socket client = serverSocket.accept();
                new ClientHandler(client).start();//里面也有一个while (true)，对数据进行读写 对应的就是netty中 NioEventLoop
            } catch (IOException e) {
                System.out.println("服务端异常");
            }
        }
    }

}
