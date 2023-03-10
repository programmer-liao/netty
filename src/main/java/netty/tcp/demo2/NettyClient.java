package netty.tcp.demo2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author liaodezhi
 * @date 2022/1/5
 * NettyClient
 */
public class NettyClient {
    public static void main(String[] args) throws InterruptedException {

        // 客户端只需要一个事件循环组
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            // 客户端需要的是Bootstrap, 而不是ServerBootstrap
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(new MessageProtocolEncoder())
                                    .addLast(new MessageProtocolDecoder())
                                    .addLast(new NettyClientHandler());
                        }
                    });
            System.out.println("客户端已经准备好了");
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 6666).sync();
            // 对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }

    }
}
