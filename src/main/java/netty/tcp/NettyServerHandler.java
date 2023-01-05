package netty.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.UUID;

/**
 * @author liaodezhi
 * @date 2022/1/5
 * NettyServerHandler
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private int count = 0;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) {
        byte[] bytes = new byte[msg.readableBytes()];
        msg.readBytes(bytes);
        String message = new String(bytes, CharsetUtil.UTF_8);
        System.out.println("服务器接收到数据 " + message);
        System.out.println("服务器接收到消息量 = " + (++count));
        ByteBuf buf = Unpooled.copiedBuffer(UUID.randomUUID().toString().replace("-", ""), CharsetUtil.UTF_8);
        ctx.writeAndFlush(buf);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
