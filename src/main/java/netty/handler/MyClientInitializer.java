package netty.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author liaodezhi
 * @date 2023/1/4
 */
public class MyClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new LongToByteEncoder())
                .addLast(new ByteToLongDecoder())
                .addLast(new MyClientHandler());
    }
}
