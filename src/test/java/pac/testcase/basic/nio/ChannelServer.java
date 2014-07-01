package pac.testcase.basic.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class ChannelServer {
	public static void main(String[] args) {
		try {
			ServerSocketChannel channel = ServerSocketChannel.open();
			channel.bind(new InetSocketAddress("127.0.0.1",8888));
			channel.configureBlocking(false);
			Selector selector = Selector.open();;
			selector.open();
			channel.register(selector, SelectionKey.OP_ACCEPT);
			
			while(true){
				channel.accept();
				selector.select();
				Iterator<SelectionKey> itr = selector.selectedKeys().iterator();
				while(itr.hasNext()){
					SelectionKey key = itr.next();
					itr.remove();
					if(key.isAcceptable()){
						System.out.println("accept...");
						ServerSocketChannel server = (ServerSocketChannel) key  
	                            .channel();  
	                    SocketChannel schannel = server.accept();  
	                    schannel.configureBlocking(false);  
	                    ByteBuffer bb = ByteBuffer.allocate(1024);
	                    schannel.read(bb);
	                    System.out.println(bb.getChar());
	                    
	                    schannel.write(ByteBuffer.wrap(new String("hamahamaxiongxiong").getBytes()));
	                    schannel.register(selector, SelectionKey.OP_READ);  
					}
					if(key.isReadable()){
						System.out.println("readin...");
						 // 服务器可读取消息:得到事件发生的Socket通道  
				        SocketChannel schannel = (SocketChannel) key.channel();  
				        // 创建读取的缓冲区  
				        ByteBuffer buffer = ByteBuffer.allocate(10);  
				        schannel.read(buffer);  
				        byte[] data = buffer.array();  
				        String msg = new String(data).trim();  
				        System.out.println("hamahamaxiongxiong"+msg);  
				        ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes());  
				        schannel.write(outBuffer);// 将消息回送给客户端  
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
