package nio.buffer;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author liaodezhi
 * @date 2022/12/28
 * 演示MappedByteBuffer的使用
 */
public class BufferDemo4 {
    public static void main(String[] args) throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile("file01.txt", "rw");
        FileChannel channel = randomAccessFile.getChannel();
        // 获得MappedByteBuffer对象
        // 参数说明
        // position: 修改起始位置
        // size: 修改区域的大小
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        // 修改文件索引0的位置为'H'
        mappedByteBuffer.put(0, (byte)'H');
        randomAccessFile.close();
    }
}
