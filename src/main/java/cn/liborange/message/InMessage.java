package cn.liborange.message;

/**
 * Created by liborange on 15/9/30.
 */
public abstract class InMessage extends BaseMessage {
    private Long msgId;



    //下面是getter setter
    public String toString(){
        return  super.toString()+"MsgId: "+msgId;
    }

    public long getMsgId() {
        return msgId;
    }

    public void setMsgId(long msgId) {
        this.msgId = msgId;
    }
}
