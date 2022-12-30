package nio.zerocopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author liaodezhi
 * @date 2022/12/30
 * 零拷贝服务端
 */
public class NIOServer {
    public static void main(String[] args) throws IOException {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(6666);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(inetSocketAddress);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (true) {
            SocketChannel channel = serverSocketChannel.accept();
            int readCount = 0;
            while (readCount != -1) {
                try {
                    readCount = channel.read(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                buffer.rewind();
            }
        }
    }
}
