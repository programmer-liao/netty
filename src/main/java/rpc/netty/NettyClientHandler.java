package rpc.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

/**
 * @author liaodezhi
 * @date 2023/1/5
 * NettyClientHandler
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable<String> {

    /**
     * 上下文
     */
    private ChannelHandlerContext context;

    /**
     * 结果
     */
    private String result;

    /**
     * 参数
     */
    private String param;

    public NettyClientHandler() {
    }

    /**
     * 被代理对象调用, 发送数据给服务器, 然后wait, 等待被唤醒, 唤醒后返回结果
     */
    @Override
    public synchronized String call() throws Exception {
        context.writeAndFlush(param);
        // 等待channelRead方法获取到服务器的结果后
        wait();
        return result;
    }

    /**
     * 与服务器连接创建后就会被调用
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        // 因为在其他方法中也要使用到ctx
        this.context = ctx;
    }

    /**
     * 收到服务器数据后, 调用方法
     */
    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) {
        // 因为在其他方法中也要用到result
        result = msg.toString();
        // 唤醒等待的线程
        notify();
    }

    /**
     * 发生异常时调用
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    public void setParam(String param) {
        this.param = param;
    }
}
