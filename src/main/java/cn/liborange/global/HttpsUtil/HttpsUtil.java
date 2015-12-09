package cn.liborange.global.HttpsUtil;


import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.net.URL;

/**
 * Https的支持工具，主要对自签名证书进行支持。
 * 
 * @author rikky.cai
 * @qq:6687523
 * @Email:6687523@qq.com
 *
 */
public class HttpsUtil
{
	private static SSLSocketFactory TRUST_ALL_FACTORY;
	
	private static SSLSocketFactory createTrustAllSocketFactory()
	{
		try
		{
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");  
			sslContext.init(null, new TrustManager[]{new TrustAllX509TrustManager()}, new java.security.SecureRandom());  
			return sslContext.getSocketFactory();
		}
		catch (Exception e) 
		{
			throw new RuntimeException(e);
		}		
	}
	
	public static SSLSocketFactory getTrustAllSocketFactory()
	{
		synchronized (HttpsUtil.class)
		{
			if(TRUST_ALL_FACTORY == null)
			{
				TRUST_ALL_FACTORY = createTrustAllSocketFactory();
			}
		}
		
		return TRUST_ALL_FACTORY;
	}
	
	public static HttpsURLConnection openHttpsURLConnection(String httpsURL)
	{
		try
		{
			URL url = new URL(httpsURL);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(getTrustAllSocketFactory());
			
			return conn;
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	

}
