package cn.liborange.message.receive;

import cn.liborange.message.InMessage;

/**
 * Created by liborange on 15/9/30.
 */
public class ImageIn extends InMessage {
    private String picUrl;  //	图片链接
    private String mediaId; //	图片消息媒体id，可以调用多媒体文件下载接口拉取数据。


    public String toString(){
        return super.toString()+"\npicUrl: "+picUrl;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getPicUrl() {

        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
