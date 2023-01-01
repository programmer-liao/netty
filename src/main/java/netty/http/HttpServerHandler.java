package netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author liaodezhi
 * @date 2023/1/1
 * HttpServerHandler
 */
public class HttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws URISyntaxException {
        if (msg instanceof HttpRequest) {
            System.out.println("msg类型 = " + msg.getClass());
            System.out.println("客户端地址 = " + ctx.channel().remoteAddress());
            HttpRequest httpRequest = (HttpRequest) msg;
            URI uri = new URI(httpRequest.uri());
            if ("/favicon.ico".equals(uri.getPath())) {
                System.out.println("请求了/favicon.ico, 不做响应");
                return;
            }
            ByteBuf content = Unpooled.copiedBuffer("hello, 我是服务器", CharsetUtil.UTF_8);
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            response.headers()
                    .set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=utf-8")
                    .set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
            ctx.writeAndFlush(response);
        }
    }
}
