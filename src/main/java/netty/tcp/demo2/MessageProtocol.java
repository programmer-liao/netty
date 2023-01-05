package netty.tcp.demo2;

/**
 * @author liaodezhi
 * @date 2023/1/5
 * 自定义协议
 */
public class MessageProtocol {
    /**
     * 长度
     */
    private int len;
    /**
     * 字节数据
     */
    private byte[] content;

    public MessageProtocol() {
    }

    public MessageProtocol(int len, byte[] content) {
        this.len = len;
        this.content = content;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
