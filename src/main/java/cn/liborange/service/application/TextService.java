package cn.liborange.service.application;

import cn.liborange.global.Config;
import cn.liborange.global.WXUtil;
import cn.liborange.message.InMessage;
import cn.liborange.message.OutMessage;
import cn.liborange.message.receive.ImageIn;
import cn.liborange.message.receive.TextIn;
import cn.liborange.message.transmit.TextOut;
import cn.liborange.service.AppService;
import cn.liborange.service.accessAPI.tuRing.Tuling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by liborange on 15/10/2.
 */
public class TextService extends AppService{
    private Logger logger = LoggerFactory.getLogger(TextService.class);

    public OutMessage process(InMessage inMessage) {

        if(inMessage instanceof TextIn){
            logger.debug("TextService开始接手处理请求");
            TextIn message = (TextIn)inMessage;
            String info = message.getContent();
            logger.debug("请求文本内容：{}",info);
            TextOut result = new TextOut();
            result.setFromUserName(message.getToUserName());
            result.setToUserName(message.getFromUserName());
            result.setContent(Tuling.chatWithTuling(info));
            result.setCreateTime(message.getCreateTime());
            result.setMsgType(message.getMsgType());
            logger.info("处理后的结果：\t{}",result.getContent());
            return  result;
        }else {
            logger.debug("接入类型不是Text类型，拒绝处理，返回null；");
        }


        return null;
    }
}
