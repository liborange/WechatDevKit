package cn.liborange.service;

import cn.liborange.service.application.ImageService;
import cn.liborange.service.application.EventService;
import cn.liborange.service.application.TextService;

/**
 * 基于工厂模式的service控制类
 * 这个应该可以改进……想到好的设计模式再改吧……
 *
 * Created by liborange on 15/10/2.
 */
public class FactoryService {

    public AppService createService(String type){
        AppService service =null ;
        switch (type) {
            case "text":
                System.out.println("text");
                service = new TextService();
                break;
            case "image":
                System.out.println("image");
                service = new ImageService();
                break;
            case "event":
                service = new EventService();
                break;
            default:
                System.out.println("defalut");
                service = new TextService();
                break;
        }
        return service;
    }
}
