package netty.protobuf.demo2;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import netty.protobuf.demo1.StudentPOJO;

/**
 * @author liaodezhi
 * @date 2022/1/4
 * NettyServerHandler
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) {
        MyDataInfo.MyMessage.DataType dataType = msg.getDataType();
        if (dataType == MyDataInfo.MyMessage.DataType.studentType) {
            MyDataInfo.MyMessage.Student student = msg.getStudent();
            System.out.println("学生姓名 = " + student.getName());
            System.out.println("学生id = " + student.getId());
        } else if (dataType == MyDataInfo.MyMessage.DataType.workerType) {
            MyDataInfo.MyMessage.Worker worker  = msg.getWorker();
            System.out.println("工人姓名 = " + worker.getName());
            System.out.println("工人年龄 = " + worker.getAge());
        } else {
            System.out.println("信息输入不正确");
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(Unpooled.copiedBuffer("Hello, 客户端", CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }

}
