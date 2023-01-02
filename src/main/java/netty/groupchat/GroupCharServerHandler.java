package netty.groupchat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.net.SocketAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author liaodezhi
 * @date 2023/1/2
 * GroupCharServerHandler
 */
public class GroupCharServerHandler extends SimpleChannelInboundHandler<String> {
    private static final ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        Channel channel = ctx.channel();
        channelGroup.forEach(ch -> {
            if (ch != channel) {
                ch.writeAndFlush(formatter.format(LocalDateTime.now()) + "[客户端]" + channel.remoteAddress() + ": " + msg + "\n");
            }
        });
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        SocketAddress address = channel.remoteAddress();
        channelGroup.add(channel);
        System.out.println(formatter.format(LocalDateTime.now()) + "[服务端]" + address + ": 加入");
        channelGroup.writeAndFlush(formatter.format(LocalDateTime.now()) + "[服务端]" + address + ": 加入");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        SocketAddress address = ctx.channel().remoteAddress();
        System.out.println(formatter.format(LocalDateTime.now()) + "[服务端]" + address + ": 离开");
        channelGroup.writeAndFlush(formatter.format(LocalDateTime.now()) + "[服务端]" + address + ": 离开");
        int size = channelGroup.size();
        System.out.println("当前客户端个数: " + size);
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }
}
