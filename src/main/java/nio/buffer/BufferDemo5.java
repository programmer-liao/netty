package nio.buffer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @author liaodezhi
 * @date 2022/12/28
 * 演示Buffer的分散与聚集
 */
public class BufferDemo5 {
    public static void main(String[] args) throws IOException {
        // 获取ServerSocketChannel对象
        ServerSocketChannel channel = ServerSocketChannel.open();
        // 监听6666端口
        InetSocketAddress inetSocketAddress = new InetSocketAddress(6666);
        channel.socket().bind(inetSocketAddress);
        // 初始化ByteBuffer数组
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);
        // 阻塞，等待客户端连接
        SocketChannel socketChannel = channel.accept();
        // 假定从客户端读取8个字节
        int messageLength = 8;
        while (true) {
            // 用于记录已读取的byte字节数
            int byteRead = 0;
            while (byteRead < messageLength) {
                // 本次读取的字节数
                long read = socketChannel.read(byteBuffers);
                byteRead += read;
                System.out.println("byteRead = " + byteRead);
                Arrays.stream(byteBuffers)
                        .map(buffer -> "position = " + buffer.position() + ", limit = " + buffer.limit())
                        .forEach(System.out::println);
                Arrays.stream(byteBuffers)
                        .forEach(Buffer::flip);
            }
            long byteWrite = 0;
            while (byteWrite < messageLength) {
                long write = socketChannel.write(byteBuffers);
                byteWrite += write;
            }
            Arrays.stream(byteBuffers).forEach(Buffer::clear);
            System.out.println("byteRead = " + byteRead + ", byteWrite = " + byteWrite + ", messageLength = " + messageLength);
        }
    }
}
