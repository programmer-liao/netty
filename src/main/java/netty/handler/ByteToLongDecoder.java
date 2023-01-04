package netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author liaodezhi
 * @date 2023/1/4
 * ByteToLongDecoder
 */
public class ByteToLongDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        System.out.println("ByteToLongDecode decode 被调用");
        if (in.readableBytes() >= 8) {
            out.add(in.readLong());
        }
    }
}
