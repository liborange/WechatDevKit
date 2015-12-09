package cn.liborange.servlet;

import cn.liborange.example.servlet.WeXServlet;
import cn.liborange.global.Config;
import cn.liborange.global.WXUtil;
import cn.liborange.service.WexService;
import com.google.common.base.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 处理来自微信信息的基类，主要用来响应get和post请求
 * <p/>
 * Created by liborange on 15/9/30.
 */
public abstract class WeXServletBase extends HttpServlet {
    private Logger logger = LoggerFactory.getLogger(WeXServletBase.class);
    protected WexService wexService;

    protected abstract void setService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("进入doGET");
        String sign = verify(request);
        logger.debug("start to verify the id of request(get) on {}", WXUtil.getData());
        String result = "";
        if (sign != null) {
            result = sign;
        } else {
            result = wexService.process(parseReq2XML(request));
        }
        try {
            response.getWriter().print(sign);
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
            logger.debug("verify failed cause: {} on {}", e.getMessage(), WXUtil.getData());
        } finally {
            response.getWriter().close();
            logger.info("verify success on {}", WXUtil.getData());
        }

    }

    /**
     * 这是一个哈哈响应服务器response的处理函数，需要调用Dispatcher的方法进行处理。
     * <p/>
     * note：第一次在这个项目中用到了guava工具包。还好写的代码不多的时候想到了guava，多熟悉guava尽量用它来写。
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("post请求{}", request.getRequestURL());
        String xml = parseReq2XML(request);
        logger.debug("请求内容：{}", xml);

        String result = wexService.process(xml);
        //Optional optional = Optional.of(wexService.process(xml));
        /*if(optional.isPresent()) {
            logger.debug("请求内容通过response返回，返回内容：{}",optional.get());
            response.setCharacterEncoding(Config.UTF);
            response.getWriter().print(optional.get());
            response.getWriter().flush();
            response.getWriter().close();
        }else
            logger.debug("无返回内容");*/
        if (result == null)
            result = "我不懂你说啥";
        logger.debug("请求内容通过response返回，返回内容：{}", result);
        response.setCharacterEncoding(Config.UTF);
        try {
            response.getWriter().print(result);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            logger.debug("出错了：{}", e);
        }


    }

    /**
     * 功能很简单，将request中的信息解析出来，微信服务器发送来的是一个xml格式的文本，先把xml文件接收并存储到成一个string返回，等待后续处理
     *
     * @param request
     * @return
     */
    private String parseReq2XML(HttpServletRequest request) {
        String charset = request.getCharacterEncoding();
        StringBuffer sb = new StringBuffer();
        if (charset == null)
            charset = Config.UTF;
        try {
            BufferedReader bReader = new BufferedReader(new InputStreamReader(request.getInputStream(), charset));
            String line = "";
            while ((line = bReader.readLine()) != null)
                sb.append(line);
            bReader.close();
        } catch (IOException e) {
            logger.debug("request转成xml出错，出错原因：{}", e.toString());
        }
        return sb.toString();
    }

    /**
     * 子类需要重写verify()方法，用来验证请求是否来源于微信。
     * 当前版本微信验证方法可以通过this的signCheck(...)来验证，防止后续验证方法改变，故单独列出来。
     *
     * @return
     */
    public abstract String verify(HttpServletRequest request);


    /**
     * 通过get请求获得的四个变量来检查是否为合法请求。
     * <p/>
     * 加密/校验流程如下：
     * 1. 将token、timestamp、nonce三个参数进行字典序排序
     * 2. 将三个参数字符串拼接成一个字符串进行sha1加密
     * 3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    protected String signCheck(String signature, String timestamp, String nonce, String echostr) {
        String[] str = new String[]{timestamp, nonce, Config.TOKEN};
        Arrays.sort(str);
        String mess = new String(str[0] + str[1] + str[2]);
        logger.debug("验证token，获得字串排序后长度{}", mess.length());
        try {
            MessageDigest MD = MessageDigest.getInstance("SHA-1");
            byte[] bytes = MD.digest(mess.getBytes());
            String temp = byteToStr(bytes);
            logger.debug("计算后结果:{}", temp);
            if (signature.toUpperCase().equals(temp))
                return echostr;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected String signCheck(HttpServletRequest request) {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        if (logger.isDebugEnabled()) {
            logger.debug("请求信息来自：{}", request.getRequestURL());
            logger.debug("signature:{}", signature);
            logger.debug("timestamp:{}", timestamp);
            logger.debug("nonce:{}", nonce);
            logger.debug("echostr:{}", echostr);
        }
        return signCheck(signature, timestamp, nonce, echostr);
    }

    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串
     *
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];

        String s = new String(tempArr);
        return s;
    }
}
