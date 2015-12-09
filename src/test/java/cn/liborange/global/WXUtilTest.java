package cn.liborange.global;

import cn.liborange.message.InMessage;
import cn.liborange.message.OutMessage;
import cn.liborange.message.receive.TextIn;
import cn.liborange.message.transmit.Image;
import cn.liborange.message.transmit.ImageOut;
import cn.liborange.message.transmit.TextOut;
import junit.framework.TestCase;
import org.apache.commons.beanutils.BeanUtils;

/**
 * Created by liborange on 15/10/1.
 */
public class WXUtilTest extends TestCase {

    private String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xml>\n" +
            " <ToUserName><![CDATA[toUser]]></ToUserName>" +
            " <FromUserName><![CDATA[fromUser]]></FromUserName> " +
            " <CreateTime>1348831860</CreateTime>" +
            " <MsgType><![CDATA[text]]></MsgType>" +
            " <Content><![CDATA[this <is> </a> test]]></Content>" +
            " <MsgId>1234567890123456</MsgId>" +
            " </xml>";

    private String xml2 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<xml><FromUserName>&lt;![CDATA[herry]]&gt;</FromUserName><Image>&lt;MediaId&gt;&lt;![CDATA[1234321]]&gt;&lt;/MediaId&gt;</Image><MsgType><![CDATA[image]]></MsgType><CreateTime>123456</CreateTime><ToUserName>&lt;![CDATA[123456]]&gt;</ToUserName></xml>";
    public void testGetData() throws Exception {
        System.out.println(WXUtil.getData());
        InMessage mes = WXUtil.xml2bean(xml2);
        System.out.println(mes.toString());
        ;
    }

    public void testGetData1() throws Exception {
        System.out.println("\n");
        InMessage mes = new TextIn(34);
        System.out.println(mes.getMsgId());
        BeanUtils.setProperty(mes, "MsgId", 12);
        System.out.println(mes.getMsgId());
    }

    public void testXml2bean() throws Exception {

        Object obj = WXUtil.xml2bean(xml);

    }

    /**
     *
     <xml>
     <ToUserName><![CDATA[toUser]]></ToUserName>
     <FromUserName><![CDATA[fromUser]]></FromUserName>
     <CreateTime>12345678</CreateTime>
     <MsgType><![CDATA[image]]></MsgType>
     <Image>
     <MediaId><![CDATA[media_id]]></MediaId>
     </Image>
     </xml>
     * @throws Exception
     */
    public void testBean2xml() throws Exception {
        ImageOut message = new ImageOut();
        message.setMsgType("image");
        message.setCreateTime(123456);
        message.setFromUserName("herry");
        message.setToUserName("123456");
        Image image = new Image();
        image.setMediaId("1234321");
        message.setImage(image);
        String string = WXUtil.bean2xml(message);
        System.out.println(string);

        InMessage mes = WXUtil.xml2bean(string);
        System.out.println(mes.toString());
    }
}