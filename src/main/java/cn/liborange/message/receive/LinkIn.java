package cn.liborange.message.receive;

import cn.liborange.message.InMessage;

/**
 * Created by liborange on 15/9/30.
 */
public class LinkIn extends InMessage {
    private String title;       //	消息标题
    private String description; //	消息描述
    private String url;         //	消息链接



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
