package rpc;

/**
 * @author liaodezhi
 * @date 2023/1/5
 * 服务接口--服务提供方和服务消费方都需要
 */
public interface HelloService {
    String hello(String msg);
}
