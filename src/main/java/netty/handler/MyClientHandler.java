package netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.channels.SocketChannel;

/**
 * @author liaodezhi
 * @date 2023/1/4
 */
public class MyClientHandler extends SimpleChannelInboundHandler<SocketChannel> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketChannel msg) {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("MyClientHandler 发送数据");
        ctx.writeAndFlush(123456L);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
