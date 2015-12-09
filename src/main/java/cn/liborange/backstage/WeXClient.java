package cn.liborange.backstage;

import cn.liborange.global.Config;
import cn.liborange.global.HttpsUtil.HttpsClient;
import cn.liborange.message.InMessage;
import cn.liborange.message.OutMessage;
import cn.liborange.message.info.UserInfo;
import cn.liborange.service.LoginCCU;
import com.alibaba.fastjson.JSON;


/**
 * Created by liborange on 15/10/6.
 */
public class WeXClient implements SendMessg,GetUserInfo {
    LoginCCU ccu = new LoginCCU();
    public void startAutoCheck(){
        ccu.start();
    }

    public void stopAutoCheck() {
        ccu.start();
    }
    @Override
    public UserInfo getUserInfo(InMessage message) {
        String openId = message.getFromUserName();
        String url = Config.GET_USER_INFO;
        url = url.replaceAll("ACCESS_TOKEN",ccu.getAccessToken().getAccess_token());
        url = url.replaceAll("OPENID",openId);

        String content = HttpsClient.get(url);
        System.out.println("请求用户信息url："+url+"\n"+"服务器返回用户信息："+content);
        UserInfo info = JSON.parseObject(content,UserInfo.class);

        return info;
    }

    @Override
    public void sendToUser(OutMessage message) {
        String json = JSON.toJSONString(message);
        String url = Config.SEND_TO_USER;
        url = url.replaceAll("ACCESS_TOKEN",ccu.getAccessToken().getAccess_token());
        HttpsClient.post(url, json);
    }
}
