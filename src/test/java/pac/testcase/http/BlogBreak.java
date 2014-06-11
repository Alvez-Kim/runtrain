package pac.testcase.http;

import java.io.IOException;

//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.HttpException;
//import org.apache.commons.httpclient.methods.GetMethod;
//import org.apache.commons.httpclient.methods.PostMethod;

public class BlogBreak {
	public static void main(String[] args) throws IOException {
//		HttpClient client = new HttpClient();
////		PostMethod method = new PostMethod("");
//		GetMethod method0 = new GetMethod("http://runtime.blog.51cto.com/7711135/1419377");
//		GetMethod method1 = new GetMethod("http://log.51cto.com/pageview.php?frompos=blog_art");
//		GetMethod method2 = new GetMethod("http://new.51cto.com/iw.php");
//		method0.setRequestHeader("Host", "runtime.blog.51cto.com");
//		method0.setRequestHeader("Connection", "keep-alive");
//		method0.setRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
//		method0.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.114 Safari/537.36");
//		method0.setRequestHeader("Referer", "http://runtime.blog.51cto.com/");
//		method0.setRequestHeader("Accept-Encoding", "gzip,deflate,sdch");
//		method0.setRequestHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6,ja;q=0.4,ko;q=0.2,zh-TW;q=0.2");
////		method0.setRequestHeader("Cookie", "PHPSESSID=0ec388ef9f9ee6653ec20c77a04c3964; bdshare_firstime=1400815134971; _ourplusFirstTime=114-5-23-11-18-55; pub_cookietime=0; pub_sid=mxSVZR; pub_smile=1DD0D9; pub_visitedfid=338; Hm_lvt_5c45148fa0fb203598b3551b22ff4a23=1400822406; Hm_lpvt_5c45148fa0fb203598b3551b22ff4a23=1400901587; Hm_lvt_f77ea1ecd95cb2a1bc65cbcb3aaba7d4=1401005252; Hm_lpvt_f77ea1ecd95cb2a1bc65cbcb3aaba7d4=1401005252; Hm_lvt_6b547ce36c1316c810810a799b07236f=1401247250; Hm_lpvt_6b547ce36c1316c810810a799b07236f=1401247270; Hm_lvt_b415ab0b61d6ef0fd39070a1cc06ec69=1401247279; Hm_lpvt_b415ab0b61d6ef0fd39070a1cc06ec69=1401247279; Hm_lvt_f5127c6793d40d199f68042b8a63e725=1401261898; Hm_lpvt_f5127c6793d40d199f68042b8a63e725=1401276279; __utma=211587014.1566409824.1400821725.1401328747.1401355127.17; __utmc=211587014; __utmz=211587014.1401328747.16.10.utmcsr=runtime.blog.51cto.com|utmccn=(referral)|utmcmd=referral|utmcct=/; refreshlimit=1401358131%09%2Fuser_index.php%3Faction%3Daddblog_new; blog_top=yes; pub_sauth1=ExFXEAwIUidBRjsGVAhXBQcGPVVSAgUJUFIAVQE; pub_sauth2=f1ce8d651bc8a71045f265d0b4d388d5; lastvisit=0%091401417584%09%2Fjs%2Fblog_top_list.php%3F; _ourplusReturnCount=395; _ourplusReturnTime=114-5-30-10-39-45; CNZZDATA4274540=cnzz_eid%3D1289472516-1400814113-%26ntime%3D1401414984%26cnzz_a%3D16%26sin%3Dnone%26ltime%3D1401409349745%26rtime%3D7; __utma=61931183.50517459.1400815135.1401412741.1401417216.45; __utmb=61931183.6.10.1401417216; __utmc=61931183; __utmz=61931183.1401355130.42.9.utmcsr=blog.51cto.com|utmccn=(referral)|utmcmd=referral|utmcct=/user_index.php");
//		
//		method1.setRequestHeader("Host", "runtime.blog.51cto.com");
//		method1.setRequestHeader("Connection", "keep-alive");
//		method1.setRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
//		method1.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.114 Safari/537.36");
//		method1.setRequestHeader("Referer", "http://runtime.blog.51cto.com/");
//		method1.setRequestHeader("Accept-Encoding", "gzip,deflate,sdch");
//		method1.setRequestHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6,ja;q=0.4,ko;q=0.2,zh-TW;q=0.2");
////		method1.setRequestHeader("Cookie", "PHPSESSID=0ec388ef9f9ee6653ec20c77a04c3964; bdshare_firstime=1400815134971; _ourplusFirstTime=114-5-23-11-18-55; pub_cookietime=0; pub_sid=mxSVZR; pub_smile=1DD0D9; pub_visitedfid=338; Hm_lvt_5c45148fa0fb203598b3551b22ff4a23=1400822406; Hm_lpvt_5c45148fa0fb203598b3551b22ff4a23=1400901587; Hm_lvt_f77ea1ecd95cb2a1bc65cbcb3aaba7d4=1401005252; Hm_lpvt_f77ea1ecd95cb2a1bc65cbcb3aaba7d4=1401005252; Hm_lvt_6b547ce36c1316c810810a799b07236f=1401247250; Hm_lpvt_6b547ce36c1316c810810a799b07236f=1401247270; Hm_lvt_b415ab0b61d6ef0fd39070a1cc06ec69=1401247279; Hm_lpvt_b415ab0b61d6ef0fd39070a1cc06ec69=1401247279; Hm_lvt_f5127c6793d40d199f68042b8a63e725=1401261898; Hm_lpvt_f5127c6793d40d199f68042b8a63e725=1401276279; __utma=211587014.1566409824.1400821725.1401328747.1401355127.17; __utmc=211587014; __utmz=211587014.1401328747.16.10.utmcsr=runtime.blog.51cto.com|utmccn=(referral)|utmcmd=referral|utmcct=/; refreshlimit=1401358131%09%2Fuser_index.php%3Faction%3Daddblog_new; blog_top=yes; pub_sauth1=ExFXEAwIUidBRjsGVAhXBQcGPVVSAgUJUFIAVQE; pub_sauth2=f1ce8d651bc8a71045f265d0b4d388d5; lastvisit=0%091401417584%09%2Fjs%2Fblog_top_list.php%3F; _ourplusReturnCount=395; _ourplusReturnTime=114-5-30-10-39-45; CNZZDATA4274540=cnzz_eid%3D1289472516-1400814113-%26ntime%3D1401414984%26cnzz_a%3D16%26sin%3Dnone%26ltime%3D1401409349745%26rtime%3D7; __utma=61931183.50517459.1400815135.1401412741.1401417216.45; __utmb=61931183.6.10.1401417216; __utmc=61931183; __utmz=61931183.1401355130.42.9.utmcsr=blog.51cto.com|utmccn=(referral)|utmcmd=referral|utmcct=/user_index.php");
//		
//		method2.setRequestHeader("Host", "runtime.blog.51cto.com");
//		method2.setRequestHeader("Connection", "keep-alive");
//		method2.setRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
//		method2.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.114 Safari/537.36");
//		method2.setRequestHeader("Referer", "http://runtime.blog.51cto.com/");
//		method2.setRequestHeader("Accept-Encoding", "gzip,deflate,sdch");
//		method2.setRequestHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6,ja;q=0.4,ko;q=0.2,zh-TW;q=0.2");
////		method2.setRequestHeader("Cookie", "PHPSESSID=0ec388ef9f9ee6653ec20c77a04c3964; bdshare_firstime=1400815134971; _ourplusFirstTime=114-5-23-11-18-55; pub_cookietime=0; pub_sid=mxSVZR; pub_smile=1DD0D9; pub_visitedfid=338; Hm_lvt_5c45148fa0fb203598b3551b22ff4a23=1400822406; Hm_lpvt_5c45148fa0fb203598b3551b22ff4a23=1400901587; Hm_lvt_f77ea1ecd95cb2a1bc65cbcb3aaba7d4=1401005252; Hm_lpvt_f77ea1ecd95cb2a1bc65cbcb3aaba7d4=1401005252; Hm_lvt_6b547ce36c1316c810810a799b07236f=1401247250; Hm_lpvt_6b547ce36c1316c810810a799b07236f=1401247270; Hm_lvt_b415ab0b61d6ef0fd39070a1cc06ec69=1401247279; Hm_lpvt_b415ab0b61d6ef0fd39070a1cc06ec69=1401247279; Hm_lvt_f5127c6793d40d199f68042b8a63e725=1401261898; Hm_lpvt_f5127c6793d40d199f68042b8a63e725=1401276279; __utma=211587014.1566409824.1400821725.1401328747.1401355127.17; __utmc=211587014; __utmz=211587014.1401328747.16.10.utmcsr=runtime.blog.51cto.com|utmccn=(referral)|utmcmd=referral|utmcct=/; refreshlimit=1401358131%09%2Fuser_index.php%3Faction%3Daddblog_new; blog_top=yes; pub_sauth1=ExFXEAwIUidBRjsGVAhXBQcGPVVSAgUJUFIAVQE; pub_sauth2=f1ce8d651bc8a71045f265d0b4d388d5; lastvisit=0%091401417584%09%2Fjs%2Fblog_top_list.php%3F; _ourplusReturnCount=395; _ourplusReturnTime=114-5-30-10-39-45; CNZZDATA4274540=cnzz_eid%3D1289472516-1400814113-%26ntime%3D1401414984%26cnzz_a%3D16%26sin%3Dnone%26ltime%3D1401409349745%26rtime%3D7; __utma=61931183.50517459.1400815135.1401412741.1401417216.45; __utmb=61931183.6.10.1401417216; __utmc=61931183; __utmz=61931183.1401355130.42.9.utmcsr=blog.51cto.com|utmccn=(referral)|utmcmd=referral|utmcct=/user_index.php");
//		
//		try {
//			client.executeMethod(method0);
//			client.executeMethod(method1);
//			client.executeMethod(method2);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
////		System.out.println(method.getResponseBodyAsString());
	}
}
