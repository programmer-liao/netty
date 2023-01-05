package rpc.provider;

import rpc.HelloService;

/**
 * @author liaodezhi
 * @date 2023/1/5
 * 提供者实现接口
 */
public class HelloServiceImpl implements HelloService {

    /**
     * 当消费方调用该方法时，就返回一个结果
     */
    @Override
    public String hello(String msg) {
        System.out.println("收到客户端消息: " + msg);
        return "你好客户端, 服务端已经收到你的消息: [ " + msg + " ]";
    }
}
