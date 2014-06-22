package pac.testcase;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class ChannelBuffer {
	static final String CRLF = "\r\n\r\n";
	public static void main(String[] args) {
		RandomAccessFile file=null;
		Charset charset = Charset.forName("UTF-8");
		CharsetDecoder decoder = charset.newDecoder();
		/*
		try {
			file = new RandomAccessFile(new File("E:/test.txt"), "rw");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		FileChannel channel = file.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(1024*500);
		try {
//			decoder.decode(buffer);
			while(channel.read(buffer)!=-1){
				buffer.flip();
				while(buffer.hasRemaining()){
//					System.out.println();
					System.out.println(buffer.get());
				}
				buffer.clear();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
        //Thread t = new Thread(() -> {System.out.print("");});
		try {
			SocketChannel socketChannel =SocketChannel.open(new InetSocketAddress("118.85.207.95", 8082));
			String content = "GET /dpf/login!login.html HTTP/1.1"+CRLF
					+"Host: 118.85.207.95:8082"+CRLF
					+"Connection: keep-alive"+CRLF
					+"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8"+CRLF
					+"User-Agent: Mozilla/5.0 (Windows NT 5.2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.154 Safari/537.36"+CRLF
					+"Accept-Encoding: gzip,deflate,sdch"+CRLF
					+"Accept-Language: zh-CN,zh;q=0.8,en;q=0.6,ja;q=0.4,ko;q=0.2,zh-TW;q=0.2"+CRLF;
			socketChannel.write(charset.encode(content));
//			socketChannel.write(charset.encode("GET / HTTP/1.1"+CRLF));
//			socketChannel.write(charset.encode("GET /document"+CRLF));
			
//			socketChannel.write(charset.encode("GET "+"/ document"+"\r\n\r\n"));
			ByteBuffer socketBuffer = ByteBuffer.allocate(1024);
			while(socketChannel.read(socketBuffer)!=-1){
				socketBuffer.flip();
				System.out.println(decoder.decode(socketBuffer));
				socketBuffer.clear();
			}
			socketChannel.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}