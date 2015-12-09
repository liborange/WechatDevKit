package cn.liborange.example.servlet;

import cn.liborange.service.Dispatcher;
import cn.liborange.servlet.WeXServletBase;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liborange on 15/10/2.
 */
public class WeXServlet extends WeXServletBase {

    public WeXServlet(){
        this.setService();
    }
    @Override
    protected void setService() {
        wexService = new Dispatcher();
    }

    @Override
    public String verify(HttpServletRequest request) {
        return signCheck(request);
    }
}
