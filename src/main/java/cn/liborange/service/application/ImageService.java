package cn.liborange.service.application;

import cn.liborange.message.InMessage;
import cn.liborange.message.OutMessage;
import cn.liborange.message.receive.ImageIn;
import cn.liborange.message.transmit.TextOut;
import cn.liborange.service.AppService;
import cn.liborange.service.accessAPI.youtu.YoutuHandler;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by liborange on 15/10/6.
 */
public class ImageService extends AppService{
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(ImageService.class);

    @Override
    protected OutMessage process(InMessage inMessage) {
        if(inMessage instanceof ImageIn){
            ImageIn image = (ImageIn)inMessage;
            String url  = image.getPicUrl();
            System.out.println(image);
            TextOut result = new TextOut();

            result.setFromUserName(image.getToUserName());
            result.setToUserName(inMessage.getFromUserName());
            result.setContent(YoutuHandler.analyzeImg(url));
            result.setCreateTime(image.getCreateTime());
            result.setMsgType("text");
            logger.info("处理后的结果：\t{}",result.getContent());
            return result;
        }

        return null;
    }
}
