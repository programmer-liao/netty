package netty.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author liaodezhi
 * @date 2023/1/4
 * MyServerInitializer
 */
public class MyServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline()
                .addLast(new ByteToLongDecoder())
                .addLast(new MyServerHandler());
    }
}
