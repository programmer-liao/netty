package netty.tcp.demo2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @author liaodezhi
 * @date 2023/1/5
 */
public class MessageProtocolDecoder extends ReplayingDecoder<Void> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        System.out.println("MessageProtocolDecoder decode 被调用");
        int len = in.readInt();
        byte[] bytes = new byte[len];
        in.readBytes(bytes);
        MessageProtocol messageProtocol = new MessageProtocol(len, bytes);
        out.add(messageProtocol);
    }
}
