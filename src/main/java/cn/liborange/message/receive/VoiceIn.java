package cn.liborange.message.receive;

import cn.liborange.message.InMessage;

/**
 * Created by liborange on 15/9/30.
 */
public class VoiceIn extends InMessage {
    private String mediaId; //	语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
    private String format;  //	语音格式，如amr，speex等

    //getter setter
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getMediaId() {

        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
