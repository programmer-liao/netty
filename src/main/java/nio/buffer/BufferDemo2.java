package nio.buffer;

import java.nio.ByteBuffer;

/**
 * @author liaodezhi
 * @date 2022/12/28
 * 演示BufferUnderflowException
 */
public class BufferDemo2 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.putInt(100);
        buffer.putLong(9L);
        buffer.putChar( 's');
        buffer.putShort((short)3);


        // 读写切换
        buffer.flip();
        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getChar());
        // 存进去什么类型，就要用什么类型取，否则可能会抛出BufferUnderflowException
        System.out.println(buffer.getLong());
    }
}
