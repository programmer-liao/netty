package bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liaodezhi
 * @date 2022/12/27
 * BIO实例演示
 */
public class BIOServer {
    public static void main(String[] args) {
        // 思路
        // 1. 创建一个线程池
        // 2. 如果有一个客户端连接，就创建一个线程，并与之通讯（单独写一个方法）
        ExecutorService threadPool = Executors.newCachedThreadPool();
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(6666);
            System.out.println("服务器启动");
            while (true) {
                // 监听，等待客户端连接，会阻塞
                Socket socket = serverSocket.accept();
                threadPool.execute(() -> handler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private static void handler(Socket socket) {
        System.out.println("连接到一个客户端, 线程id: " + Thread.currentThread().getId() + ", 线程名称: " + Thread.currentThread().getName());
        byte[] bytes = new byte[1024];
        try {
            InputStream inputStream = socket.getInputStream();
            while (true) {
                // inputStream中没有内容时，read会阻塞
                int read = inputStream.read(bytes);
                if (read != -1) {
                    System.out.println(new String(bytes, 0, read));
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("关闭和client的连接");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
