package cn.liborange.message.receive;

import cn.liborange.example.learning.Person;
import cn.liborange.message.InMessage;

/**
 * Created by liborange on 15/9/30.
 */
public class VideoIn extends InMessage {
    private String mediaId;         //	视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
    private String thumbMediaId;    //	视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。

    //getter setter
    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getThumbMediaId() {

        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }


}

