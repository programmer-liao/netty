package nio.channel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author liaodezhi
 * @date 2022/12/27
 * Channel案例1：将“hello, world”写入到file01.txt中
 */
public class ChannelDemo1 {
    public static void main(String[] args) throws IOException {
        String str = "hello, world";
        FileOutputStream fileOutputStream = new FileOutputStream("file01.txt");
        // 获取FileChannel对象
        FileChannel fileChannel = fileOutputStream.getChannel();
        // 获取ByteBuffer对象
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        // 将str的字节数组放入ByteChannel里面
        byteBuffer.put(str.getBytes());
        // 读写切换
        byteBuffer.flip();
        // 将ByteBuffer里面的内容写入到file01.txt中
        fileChannel.write(byteBuffer);
        fileChannel.close();
        fileOutputStream.close();
    }
}
