package cn.liborange.service;

import cn.liborange.backstage.WeXClient;
import cn.liborange.global.WXUtil;
import cn.liborange.message.InMessage;
import cn.liborange.message.OutMessage;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by liborange on 15/10/2.
 */
public class Dispatcher implements WexService{
    private org.slf4j.Logger logger = LoggerFactory.getLogger(Dispatcher.class);
    private FactoryService factory;
    private WeXClient client;

    public Dispatcher(){
        logger.debug("初始化Dispatcher");
        factory = new FactoryService();
        //client = new WeXClient();
        //client.startAutoCheck();
    }
    public String toString() {
        return "我是Dispatcher";
    }
    /**
     * 1,将xml信息解析成inmessage类
     * 2,获得相应的service
     * 3,service处理并返回结果集
     * @param req：request解析后的信息。
     * @return
     */
    @Override
    public String process(String req) {
        logger.debug("Dispatcher开始处理");
        InMessage message = WXUtil.xml2bean(req);
        logger.info("请求数据转成类：\n{}",message.toString());
        AppService service = factory.createService(message.getMsgType());
        logger.debug("使用{}服务类处理",service.getClass());
        service.setClient(client);
        OutMessage out = service.process(message);
        logger.info("请求处理完成");
        return WXUtil.bean2xml(out);
    }
}
