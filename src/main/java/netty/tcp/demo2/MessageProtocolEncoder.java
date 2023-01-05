package netty.tcp.demo2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author liaodezhi
 * @date 2023/1/5
 */
public class MessageProtocolEncoder extends MessageToByteEncoder<MessageProtocol> {

    @Override
    protected void encode(ChannelHandlerContext ctx, MessageProtocol msg, ByteBuf out) {
        System.out.println("MessageProtocolEncoder encode 被调用");
        out.writeInt(msg.getLen());
        out.writeBytes(msg.getContent());
    }
}
