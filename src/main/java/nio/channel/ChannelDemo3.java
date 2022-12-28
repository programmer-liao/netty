package nio.channel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author liaodezhi
 * @date 2022/12/28
 * Channel案例3：将拷贝file01.txt中的内容到file02.txt中去
 */
public class ChannelDemo3 {
    public static void main(String[] args) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream("file01.txt");
            fileOutputStream = new FileOutputStream("file02.txt");
            FileChannel inputChannel = fileInputStream.getChannel();
            FileChannel outputChannel = fileOutputStream.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while (true) {
                // 此处必须clear复位，否则会出现readLen = 0的情况，造成死循环
                byteBuffer.clear();
                int readLen = inputChannel.read(byteBuffer);
                if (readLen == -1) {
                    break;
                }
                // 读写切换
                byteBuffer.flip();
                outputChannel.write(byteBuffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
