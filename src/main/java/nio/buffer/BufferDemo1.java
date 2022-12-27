package nio.buffer;

import java.nio.IntBuffer;

/**
 * @author liaodezhi
 * @date 2022/12/27
 * 举例说明Buffer在NIO中是什么东西
 */
public class BufferDemo1 {
    public static void main(String[] args) {
        // 创建一个buffer，大小为5，可以存放int
        IntBuffer buffer = IntBuffer.allocate(10);
        // 放满buffer
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put(i);
        }
        // 读写切换
        buffer.flip();
        // 取出数据
        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
    }
}
