package cn.liborange.service;

import junit.framework.TestCase;

/**
 * Created by liborange on 15/10/7.
 */
public class DispatcherTest extends TestCase {

    private  String xml ="<xml><ToUserName><![CDATA[gh_d90d54fe170e]]></ToUserName><FromUserName><![CDATA[o5U5ttxXEI2laC9ePD10_eEvzRuw]]></FromUserName><CreateTime>1444193534</CreateTime><MsgType><![CDATA[image]]></MsgType><PicUrl><![CDATA[http://mmbiz.qpic.cn/mmbiz/6MiaXJ2qMl5jQ6k9v5eibKaAnFHg1y2JIUuNXtvvpNVrRdIyplhlmWxH3TUEicEAxicic1b06rfZ6w1ESebGXyticttQ/0]]></PicUrl><MsgId>6202763997838343150</MsgId><MediaId><![CDATA[1t1BlbU1o1owyplrYV8_kFYA5hcme6TmUuXtGIY_SFcy335ReLekQfcmm4D9lFSV]]></MediaId></xml> ";
    String event = "<xml><ToUserName><![CDATA[gh_d90d54fe170e]]></ToUserName><FromUserName><![CDATA[o5U5ttxXEI2laC9ePD10_eEvzRuw]]></FromUserName><CreateTime>1444198292</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[subscribe]]></Event><EventKey><![CDATA[]]></EventKey></xml>";
    public void testProcess() throws Exception {
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.process(event);
    }
}