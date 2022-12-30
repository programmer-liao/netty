package nio.zerocopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @author liaodezhi
 * @date 2022/12/30
 * 零拷贝客户端
 */
public class NIOClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 6666));
        String fileName = "file01.txt";
        FileChannel channel = new FileInputStream(fileName).getChannel();
        long startTime = System.currentTimeMillis();
        // 在linux下一个transferTo就可以完成传输
        // 在windows下一次调用transferTo只能传输8m，需要分段传输
        long transferCount = channel.transferTo(0, channel.size(), socketChannel);
        long endTime = System.currentTimeMillis();
        System.out.println("发送的总字节数 = " + transferCount + "耗时 = " + (endTime - startTime));
    }
}
