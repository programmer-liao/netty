package rpc.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.lang.reflect.Proxy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liaodezhi
 * @date 2023/1/5
 * NettyClient
 */
public class NettyClient {

    /**
     * 线程池, 线程数与CPU核心线程数一致
     */
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    /**
     * NettyClientHandler
     */
    private static NettyClientHandler nettyClientHandler;


    private void init() {
        nettyClientHandler = new NettyClientHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap().group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true) // TCP不延时
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        ch.pipeline().addLast(new StringDecoder())
                                .addLast(new StringEncoder())
                                .addLast(nettyClientHandler);
                    }
                });
        try {
            bootstrap.connect("127.0.0.1", 7000).sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得代理对象
     * @param serviceClass 服务类对象
     * @param provideName 协议头
     */
    public Object getBean(final Class<?> serviceClass, final String provideName) {
        return Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class<?>[]{serviceClass},
                (proxy, method, args) -> {
                    if (nettyClientHandler == null) {
                        init();
                    }
                    // 设置要发给服务器端的信息
                    nettyClientHandler.setParam(provideName + args[0]);
                    return threadPool.submit(nettyClientHandler).get();
                });
    }
}
