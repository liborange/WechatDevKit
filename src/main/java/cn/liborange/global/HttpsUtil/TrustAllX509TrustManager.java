package cn.liborange.global.HttpsUtil;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * 证书信任管理器,信任所有的签发机构。适用于自签名证书确保通讯安全的场合。
 * 
 * @author rikky.cai
 * @qq:6687523
 * @Email:6687523@qq.com
 *
 */
public class TrustAllX509TrustManager implements X509TrustManager
{

	@Override
	public void checkClientTrusted(X509Certificate[] arg0, String arg1)
			throws CertificateException
	{
	}

	@Override
	public void checkServerTrusted(X509Certificate[] arg0, String arg1)
			throws CertificateException
	{
	}

	@Override
	public X509Certificate[] getAcceptedIssuers()
	{
		return null;
	}

}
