package netty.tcp.demo2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.UUID;

/**
 * @author liaodezhi
 * @date 2022/1/5
 * NettyServerHandler
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<MessageProtocol> {
    private int count = 0;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) {
        int len = msg.getLen();
        byte[] content = msg.getContent();
        System.out.println("服务器接收信息如下");
        System.out.println("len = " + len);
        System.out.println("content = " + new String(content, CharsetUtil.UTF_8));
        System.out.println("服务器接收到消息包数量 = " + (++count));
        String message = UUID.randomUUID().toString().replace("-", "");
        int length = message.getBytes(CharsetUtil.UTF_8).length;
        byte[] bytes = message.getBytes(CharsetUtil.UTF_8);
        MessageProtocol messageProtocol = new MessageProtocol(length, bytes);
        ctx.writeAndFlush(messageProtocol);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
