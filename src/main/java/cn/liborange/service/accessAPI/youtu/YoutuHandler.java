package cn.liborange.service.accessAPI.youtu;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by liborange on 15/10/6.
 */
public class YoutuHandler {
    private static Logger logger = LoggerFactory.getLogger(YoutuHandler.class);
    private static final String APP_ID = "1001338";
    private static final String SECRET_ID = "AKIDjtSyeOtmc2iIVMCgOTQDkyxIKgklReyI";
    private static final String SECRET_KEY = "KizH2aSjkAar7kWwpNAt2NRov698Cz0g";
    //优图初始化方式
    private static Youtu faceYoutu = new Youtu(APP_ID, SECRET_ID, SECRET_KEY,Youtu.API_YOUTU_END_POINT);

    public static String analyzeImg(String url) {
        logger.debug("开始优图处理图片");
        StringBuffer sb = new StringBuffer();
        String tag = imageTag(url);
        String face = detectFace(url);
        if(tag.length()+face.length()<2)
            sb.append("呀呀呀我是近视眼，照片有点模糊，换张清晰点得~");
        else
            sb.append("我看了一下照片，").append(tag).append(face);
        logger.debug("处理完成，处理结果：{}",sb.toString());
        return sb.toString();
    }
    private static String imageTag(String url){

        try {
            JSONObject json = faceYoutu.ImageTagUrl(url);
            if(!json.get("errormsg").equals("OK"))
                return "";
            JSONArray array = json.getJSONArray("tags");
            StringBuffer sb = new StringBuffer();
            sb.append("照片里有 ");
            for (int i=0;i<array.length();i++){
                JSONObject ob = (JSONObject)array.get(i);
                if(ob.getString("tag_name").equals("合影"))
                    continue;
                sb.append(ob.getString("tag_name")).append("、");
            }
            sb.replace(sb.length()-1,sb.length(),"。");
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    private static String detectFace(String url){

        try {
            JSONObject json = faceYoutu.DetectFaceUrl(url,1);
            if(!json.get("errormsg").equals("OK")) {
                json = faceYoutu.DetectFaceUrl(url, 0);
                if(!json.get("errormsg").equals("OK"))
                    return "";
            }
            JSONObject face  = (JSONObject) json.getJSONArray("face").get(0);
            StringBuffer sb = new StringBuffer();
            sb.append("");
            sb.append("图片里的").append((int)face.get("gender")>50?"男人":"女人").append(",");
            sb.append("年龄大约").append(face.get("age").toString()).append("岁,");
            if(face.getBoolean("glass") == true)
                sb.append("带的眼镜不错不错。");
            sb.append("看起来").append(faceEmotion(face.getInt("expression"))).append("给心情打个分: ").append(face.getInt("expression")).append("\n");
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    private static String faceEmotion(int expression){
        if(expression<10)
            return "有点不开心。";
        else if(expression<30)
            return "似笑非笑。";
        else if (expression<50)
            return "喜上眉梢。";
        else if (expression<80)
            return "心花怒放。";
        else if (expression<90)
            return "很开心呀。";
        else
            return "漂亮极了。";
    }

    public static void main(String[] args) {
        YoutuHandler youtuHandler = new YoutuHandler();
        String url = "http://mmbiz.qpic.cn/mmbiz/6MiaXJ2qMl5jQ6k9v5eibKaAnFHg1y2JIUp573ia33bTR5GlSZlCH5nmN90FYzaY5xYork3HSiaLTfIcj84rGRN20Q/0";
        String url2 = "http://imgcache.mysodao.com/img1/M02/05/38/CgAPC0-55KqGC3VOACoAAA14DEI344-f652ec30.JPG";
        System.out.println(youtuHandler.analyzeImg(url));
    }
}
