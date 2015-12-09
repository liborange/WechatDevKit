package cn.liborange.service.application;

import cn.liborange.message.InMessage;
import cn.liborange.message.OutMessage;
import cn.liborange.message.info.UserInfo;
import cn.liborange.message.receive.EventIn;
import cn.liborange.message.transmit.TextOut;
import cn.liborange.service.AppService;

/**
 * Created by liborange on 15/10/7.
 */
public class EventService extends AppService{

    public static final String Welcome = "非常非常感谢您的关注，您的关注即是最大的鼓励。说下本公众号的三个准则：不打扰，有意思，正能量。" +
            "不打扰是因为每天没有那么多话想说；有意思是怕你忘记我；正能量是要杜绝响亮但空洞的鸡汤，传递行动加坚持的力量。" +
            "安利一下我目前能做的两件事：聊天，最聊得来可能是机器人；识图，给我一张风景照自拍照，还你一个机器人眼里的世界。" +
            "最最最最最后告诉你，拉屎无聊的时候一定要找到我。";
    @Override
    protected OutMessage process(InMessage inMessage) {
        EventIn message = (EventIn)inMessage;
        String info = message.getEvent();
        //logger.debug("请求文本内容：{}",info);

        TextOut result = new TextOut();

        if(info.equals("subscribe")) {
            //UserInfo user = client.getUserInfo(inMessage);
            //System.out.println(user.toString());
            result.setFromUserName(message.getToUserName());
            result.setToUserName(message.getFromUserName());
            result.setContent(Welcome);
            result.setCreateTime(message.getCreateTime());
            result.setMsgType("text");
        }
        return  result;
    }
}
