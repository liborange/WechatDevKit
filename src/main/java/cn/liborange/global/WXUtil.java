package cn.liborange.global;

import cn.liborange.message.InMessage;
import cn.liborange.message.OutMessage;
import org.apache.commons.beanutils.BeanUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.util.*;

/**
 * Created by liborange on 15/10/1.
 */
public class WXUtil {

    public static String getData() {
        Date date = new Date();
        DateFormat format = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
        return format.format(date);
    }

    /**
     * 将xml文件解析成bean返回
     *
     * @param xml
     * @return
     */
    public static InMessage xml2bean(String xml) {
        Map<String, String> map = xml2map(xml);
        InMessage result = null;
        try {
            Class cls = Config.ReqEnum.valueOf(map.get("msgType")).getCls();
            Object obj = cls.newInstance();
            BeanUtils.populate(obj,map);
            result = (InMessage)obj;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static Map xml2map(String xml) {
        Map<String, String> map = new HashMap<>();
        try {
            SAXBuilder builder = new SAXBuilder();
            Document document = builder.build(new StringReader(xml));
            Element root = document.getRootElement();
            List<Element> elements = root.getChildren();
            for (Element element : elements) {
                String name = element.getName();
                map.put(name.substring(0, 1).toLowerCase() + name.substring(1), element.getValue());
            }
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;

    }

    public static String bean2xml(OutMessage out) {
        try {
            Map map = BeanUtils.describe(out);
            SAXBuilder builder = new SAXBuilder();
            Document document = new Document();
            Element root = new Element("xml");
            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                String name = (String) entry.getKey();
                name = name.substring(0, 1).toUpperCase() + name.substring(1);
                Element element;
                switch (name){
                    case "CreateTime":
                    case "ArticleCount":
                    case "Image":
                    case "Music":
                    case "Video":
                    case "Voice":

                         element = new Element(name);

                        element.setText((String) entry.getValue());
                        root.addContent(element);
                        break;
                    case "Class":
                        break;
                    default:
                        element = new Element(name);
                        String str = new String(("<![CDATA["+entry.getValue()+"]]>").getBytes(),Config.UTF);
                        element.setText(str);
                        root.addContent(element);
                }


            }
            document.addContent(root);
            ByteArrayOutputStream byteRsp=new ByteArrayOutputStream();
            XMLOutputter xmlOut=new XMLOutputter();
            try {
                xmlOut.output(document, byteRsp);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String str = byteRsp.toString();
            str = str.replaceAll("&lt;","<");
            str = str.replaceAll("&gt;",">");
            return str;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


}
