package cn.liborange.service;

import cn.liborange.global.Config;
import cn.liborange.global.HttpsUtil.HttpsClient;
import com.alibaba.fastjson.JSON;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 用于自动登录的中心控制中心（CCU：central control unit）
 * Created by liborange on 15/10/4.
 */
public class LoginCCU {

    private static long RETRY_INTERVAL = 5000;  //  重新登录时间为5s
    private static long EXPIRE_DISCOUNT = 300;
    private volatile AccessToken accessToken;
    private ScheduledExecutorService executor;
    {
        login();
    }
    public void start() {
        if (executor == null)
            executor = Executors.newScheduledThreadPool(1);
        long interval = accessToken.expires_in - EXPIRE_DISCOUNT;

        executor.scheduleAtFixedRate(new EnsureLogin(), 0, interval, TimeUnit.SECONDS);
    }

    public void stop() {
        if (executor != null)
            executor.shutdownNow();
    }

    public synchronized AccessToken getAccessToken() {
        return accessToken;
    }

    private synchronized void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    private boolean login() {
        String url = Config.ACCESS_TOKEN_URL.replaceAll("APPID", Config.APPID);
        url = url.replaceAll("APPSECRET", Config.APPSECRET);

        String json = HttpsClient.get(url);
        AccessToken accessToken = JSON.parseObject(json, AccessToken.class);

        if (accessToken.success()) {
            setAccessToken(accessToken);
            return true;
        }
        return false;
    }

    public static class AccessToken {
        String access_token;     //获取到的凭证
        long expires_in;       //凭证有效时间，单位：秒
        String errcode;
        String errmsg;

        boolean success() {
            return errcode == null && errmsg == null;
        }

        public String getErrcode() {
            return errcode;
        }

        public void setErrcode(String errcode) {
            this.errcode = errcode;
        }

        public String getAccess_token() {

            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public String getErrmsg() {
            return errmsg;
        }

        public void setErrmsg(String errmsg) {
            this.errmsg = errmsg;
        }

        public long getExpires_in() {

            return expires_in;
        }

        public void setExpires_in(long expires_in) {
            this.expires_in = expires_in;
        }


    }

    private class EnsureLogin implements Runnable {
        int tryTimes = 0;
        boolean loginSucs = false;

        @Override
        public void run() {
            while (loginSucs) {
                loginSucs = login();

                if (!loginSucs) {
                    tryTimes++;
                    try {
                        Thread.sleep(tryTimes * RETRY_INTERVAL);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
