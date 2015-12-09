package cn.liborange.message.transmit;

import cn.liborange.message.OutMessage;

/**
 * Created by liborange on 15/9/30.
 */
public class NewsOut extends OutMessage {
    private String ArticleCount;    //	是	图文消息个数，限制为10条以内
    private String Articles;        //	是	多条图文消息信息，默认第一个item为大图,注意，如果图文数超过10，则将会无响应
    private String Title;           //	否	图文消息标题
    private String Description;     //	否	图文消息描述
    private String PicUrl;          //	否	图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
    private String Url;             //	否	点击图文消息跳转链接

}
