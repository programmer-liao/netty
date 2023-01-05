package netty.tcp.demo2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;


/**
 * @author liaodezhi
 * @date 2022/1/5
 * NettyClientHandler
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<MessageProtocol> {
    private int count = 0;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) {
        byte[] bytes = msg.getContent();
        int len = msg.getLen();
        String message = new String(bytes, CharsetUtil.UTF_8);
        System.out.println("客户端接收消息内容: " + message);
        System.out.println("客户端接收消息长度: " + len);
        System.out.println("客户端接收消息数量 = " + (++count));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        for (int i = 0; i < 5; i++) {
            String message = "今天天气冷, 吃火锅";
            byte[] bytes = message.getBytes(CharsetUtil.UTF_8);
            int length = message.getBytes(CharsetUtil.UTF_8).length;
            MessageProtocol messageProtocol = new MessageProtocol(length, bytes);
            ctx.writeAndFlush(messageProtocol);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
