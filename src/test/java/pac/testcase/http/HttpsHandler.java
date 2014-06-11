package pac.testcase.http;

import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HttpsHandler {
	public void trustAllHttpsCertificates() throws Exception {
		TrustManager[] tm_array = new TrustManager[1];
		TrustManager tm = new MyTrustManager();
		tm_array[0] = tm;
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, tm_array, null);
		
//		sc.getSocketFactory().createSocket();
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	}
	private class MyTrustManager implements TrustManager, X509TrustManager {
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
		public boolean isServerTrusted(X509Certificate[] certs) {
			return true;
		}
		public boolean isClientTrusted(X509Certificate[] certs) {
			return true;
		}
		public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
			return;
		}
		public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
			return;
		}
	}
	
	@SuppressWarnings("static-access")
	public boolean open(String method,String url,String charset)
	{

		try{
			URL __url = new URL(url);
			if(url.toLowerCase().startsWith("https://"))
			{
				HttpsHandler handler = new HttpsHandler();
				handler.trustAllHttpsCertificates();
				HostnameVerifier hv = new HostnameVerifier() {
					
					public boolean verify(String urlHostName, SSLSession session) {
						return true;
					}
				};
				HttpsURLConnection.setDefaultHostnameVerifier(hv);
				HttpsURLConnection https;
				https = (HttpsURLConnection)__url.openConnection();
				https.setDoInput(true);
				https.setDoOutput(true);
				https.setRequestMethod(method);
				https.setFollowRedirects(true);
//				https.setRequestProperty("Cookie",cookie);
			}
			else
			{
				HttpURLConnection http = null;
				http = (HttpURLConnection)__url.openConnection();
				http.setDoInput(true);
				http.setDoOutput(true);
				http.setRequestMethod(method);
				http.setFollowRedirects(true);
//				http.setRequestProperty("Cookie",cookie);
			}
		}catch(Exception e){return false;}
		return true;
	}
	
	public static void main(String[] args) {
		
	}
}