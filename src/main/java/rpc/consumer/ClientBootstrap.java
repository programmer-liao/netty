package rpc.consumer;

import rpc.HelloService;
import rpc.netty.NettyClient;

/**
 * @author liaodezhi
 * @date 2023/1/5
 * ClientBootstrap
 */
public class ClientBootstrap {

    /**
     * 协议头
     */
    public static final String PROVIDER_NAME = "HelloService#hello#";
    public static void main(String[] args) {
        NettyClient customer = new NettyClient();
        // 获得代理对象
        HelloService helloService = (HelloService) customer.getBean(HelloService.class, PROVIDER_NAME);
        // 通过代理对象调用服务提供者的方法
        String result = helloService.hello("你好, dubbo");
        System.out.println("调用结果 = " + result);
    }
}
