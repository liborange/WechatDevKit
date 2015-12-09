package cn.liborange.message.transmit;

import cn.liborange.message.OutMessage;

/**
 * Created by liborange on 15/9/30.
 */
public class MusicOut extends OutMessage {
    private String title;           //	否	音乐标题
    private String description;     //	否	音乐描述
    private String musicURL;        //	否	音乐链接
    private String qMusicUrl;      //	否	高质量音乐链接，WIFI环境优先使用该链接播放音乐
    private String thumbMediaId;    //	否	缩略图的媒体id，通过素材管理接口上传多媒体文件，得到的id

    public String toString(){
        //TODO
        return null;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    public String getqMusicUrl() {

        return qMusicUrl;
    }

    public void setqMusicUrl(String qMusicUrl) {
        this.qMusicUrl = qMusicUrl;
    }

    public String getMusicURL() {

        return musicURL;
    }

    public void setMusicURL(String musicURL) {
        this.musicURL = musicURL;
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
