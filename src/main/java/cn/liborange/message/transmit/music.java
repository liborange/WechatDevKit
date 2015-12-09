package cn.liborange.message.transmit;

/**
 * Created by liborange on 15/10/2.
 */
public class music {
    private String Title;       //	否	音乐标题
    private String Description; //	否	音乐描述
    private String MusicURL;    //	否	音乐链接
    private String HQMusicUrl;  //	否	高质量音乐链接，WIFI环境优先使用该链接播放音乐
    private String ThumbMediaId;//  否	缩略图的媒体id，通过素材管理接口上传多媒体文件，得到的id
}
