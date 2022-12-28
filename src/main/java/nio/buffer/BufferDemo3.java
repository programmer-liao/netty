package nio.buffer;

import java.nio.ByteBuffer;

/**
 * @author liaodezhi
 * @date 2022/12/28
 * 演示只读Buffer
 */
public class BufferDemo3 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 将ByteBuffer转换为ReadonlyBuffer
        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
        // 此时写操作就会抛出异常ReadOnlyBufferException
        readOnlyBuffer.put((byte) 1);
    }
}
