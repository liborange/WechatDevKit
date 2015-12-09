package cn.liborange.message.receive;

import cn.liborange.message.InMessage;

/**
 * Created by liborange on 15/9/30.
 */
public class TextIn extends InMessage {
    private String content;

    public TextIn() {

    }

    public TextIn(int msgId) {
       setMsgId(msgId);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
