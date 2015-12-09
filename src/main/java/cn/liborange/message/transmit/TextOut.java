package cn.liborange.message.transmit;

import cn.liborange.message.OutMessage;

/**
 * Created by liborange on 15/9/30.
 */
public class TextOut extends OutMessage {
    private String content ;     //	是	回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
