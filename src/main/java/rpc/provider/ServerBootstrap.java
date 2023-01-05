package rpc.provider;

import rpc.netty.NettyServer;

/**
 * @author liaodezhi
 * @date 2023/1/5
 * 服务启动类
 */
public class ServerBootstrap {
    public static void main(String[] args) {
        NettyServer.startServer("127.0.0.1", 7000);
    }
}
