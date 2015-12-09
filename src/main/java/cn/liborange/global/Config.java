package cn.liborange.global;

import cn.liborange.message.receive.*;

/**
 * 全局信息。一些敏感信息需要在配置文件中。
 * Created by liborange on 15/9/30.
 */
public class Config {
    public static final String TYPE_IMG = "image";
    public static final String TYPE_TEXT = "text";
    public static final String TYPE_VIDEO = "video";
    public static final String TYPE_VOICE = "voice";
    public static final String TYPE_NEWS = "news";
    public static final String TYPE_MUSIC = "music";
    public static final String TYPE_LINK = "link";
    public static final String TYPE_SHORTVIDEO = "shortvideo";
    public static final String TYPE_LOCATION = "location";
    public static final String UTF = "utf-8";
    public static final String GBK = "gbk";

    //⬇️开发的用户名和密码……妥善保存，后期考虑将敏感信息放在全局配置文件中。
    public static final String APPID = "wx299a56ca54458a1f";
    public static final String APPSECRET = "275daa062af61d42e459ec3b33785238";
    public static final String TOKEN = "liborange";

    //⬇️获取用户信息的地址
    public static final String GET_USER_INFO = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    //⬇️向用户发送信息地址
    public static final String SEND_TO_USER = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";

    //⬇️图灵机器人接入口
    public static final String DEV_WECHAT_ID = "SevenDream";
    public static final String TULING_API_KEY = "59310cb174e40ad5c7319ed168d718e2";
    public static final String TULING_ADDRESS = "http://www.tuling123.com/openapi/api";

    //⬇️百度人脸识别接口地址
    public static final String FACE_RECOGNIZE_ADDRESS = "http://apis.baidu.com/idl_baidu/faceverifyservice/face_recognition";

    //⬇️用于获取微信服务器的access_token地址，GET方式请求。
    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    public static enum ReqEnum {
        text(TextIn.class),
        image(ImageIn.class),
        voice(VoiceIn.class),
        video(VideoIn.class),
        shortvideo(ShortVideoIn.class),
        location(LocationIn.class),
        link(LinkIn.class),
        event(EventIn.class);

        Class<?> cls;
        ReqEnum(Class<?> cls) {
            this.cls = cls;
        }
        public Class getCls() {
            return this.cls;
        }
    }
}
