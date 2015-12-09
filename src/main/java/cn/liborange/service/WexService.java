package cn.liborange.service;

/**
 * Created by liborange on 15/9/30.
 */
public interface WexService {

    /**
     * 核心逻辑处理接口
     *
     * 处理微信发送来的用户请求，req为已经经过处理的request信息
     *
     * @param req：request解析后的信息。
     * @return 返回给WeXServlet的doPost()用来响应微信服务器
     */
    String process(String req);
}
