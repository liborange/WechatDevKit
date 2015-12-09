package cn.liborange.backstage;

import cn.liborange.message.InMessage;
import cn.liborange.message.info.UserInfo;

/**
 * Created by liborange on 15/10/4.
 */
public interface GetUserInfo {

    /**
     * 获取用户信息
     *
     * 用户关注公众号，公众号可获得关注者的OpenID
     * （加密后的微信号，每个用户对每个公众号的OpenID是唯一的。对于不同公众号，同一用户的openid不同）。
     * 公众号可通过本接口来根据OpenID获取用户基本信息，包括昵称、头像、性别、所在城市、语言和关注时间。
     * @param message
     * @return
     */
    UserInfo getUserInfo(InMessage message);
}
