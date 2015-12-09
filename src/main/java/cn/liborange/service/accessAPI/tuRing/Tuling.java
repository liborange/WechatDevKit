package cn.liborange.service.accessAPI.tuRing;

import cn.liborange.global.Config;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

/**
 * Created by liborange on 15/10/2.
 */
public class Tuling {


    public static String chatWithTuling(String info) {
        try {
            info = URLEncoder.encode(info, "utf-8");
            String getURL = Config.TULING_ADDRESS+"?key=" + Config.TULING_API_KEY + "&info=" + info;
            URL getUrl = new URL(getURL);
            HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
            connection.connect();

            // 取得输入流，并使用Reader读取
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            StringBuffer sb = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            // 断开连接
            connection.disconnect();
            JSONObject json = JSON.parseObject(sb.toString());
            if(json.get("code").toString().equals("100000"))
                return json.get("text").toString();

            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("开启人机对话模式~打他骂他都快说 ~ 。~");
        while (true) {

            String me = in.nextLine();
            String chat = Tuling.chatWithTuling(me.trim());
            System.out.println(chat);
        }
    }
}
