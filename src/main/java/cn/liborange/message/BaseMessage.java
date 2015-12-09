package cn.liborange.message;

/**
 * Created by liborange on 15/9/30.
 */
public abstract class BaseMessage {
    private String toUserName;      //	接收信息时为开发者微信号，发送信息时为接收方微信号
    private String fromUserName;    //	发送方帐号（一个OpenID）
    private int createTime;         //	消息创建时间 （整型）
    private String msgType;         //	信息格式

    public BaseMessage() {

    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("toUserName: ").append(toUserName).append("\n");
        sb.append("fromUserName: ").append(fromUserName).append("\n");
        sb.append("createTime: ").append(createTime).append("\n");
        sb.append("msgType: ").append(msgType).append("\n");
        return sb.toString();
    }

    //下面是getter setter
    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public int getCreateTime() {

        return createTime;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }

    public String getFromUserName() {

        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getToUserName() {

        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }
}
