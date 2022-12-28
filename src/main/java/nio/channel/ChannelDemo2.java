package nio.channel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author liaodezhi
 * @date 2022/12/27
 * Channel案例2：将file01.txt中的内容打印到控制台
 */
public class ChannelDemo2 {
    public static void main(String[] args) {
        FileInputStream fileInputStream = null;
        try {
            File file = new File("file01.txt");
            fileInputStream = new FileInputStream(file);
            // 获取FileChannel对象
            FileChannel channel = fileInputStream.getChannel();
            // 获取ByteBuffer对象
            ByteBuffer byteBuffer = ByteBuffer.allocate((int)file.length());
            // 读取Channel中的内容到ByteBuffer中去
            channel.read(byteBuffer);
            // 字节转字符
            String str = new String(byteBuffer.array());
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
