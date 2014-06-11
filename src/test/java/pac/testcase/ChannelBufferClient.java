package pac.testcase;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class ChannelBufferClient {
	public static void main(String[] args) {
		Socket client = new Socket();
		try {
//			client.bind(new InetSocketAddress("127.0.0.1",1234));
			
			SocketChannel channel = SocketChannel.open();
			channel.configureBlocking(false);
			channel.connect(new InetSocketAddress("127.0.0.1",8888));
			
			Selector sel = Selector.open();
			channel.register(sel, SelectionKey.OP_WRITE);
			
			while(true){
				sel.select();
				Iterator<SelectionKey> ite = sel.selectedKeys().iterator();
				while(ite.hasNext()){
					SelectionKey key = ite.next();
					sel.select();
					if(key.isConnectable()){
						System.out.println("client connectin...");
						SocketChannel schannel = (SocketChannel) key  .channel();
						if(schannel.isConnectionPending()){  
	                        schannel.finishConnect();  
	                    }  
	                    schannel.configureBlocking(false);  
	  
	                    schannel.write(ByteBuffer.wrap(new String("walawalawalawang").getBytes()));  
	                    schannel.register(sel, SelectionKey.OP_READ);
					}
					if(key.isReadable()){
						System.out.println("client readin...");
				        SocketChannel schannel = (SocketChannel) key.channel();  
				        ByteBuffer buffer = ByteBuffer.allocate(10);  
				        schannel.read(buffer);  
				        byte[] data = buffer.array();  
				        String msg = new String(data).trim();  
				        System.out.println("walawalawalawang"+msg);  
				        ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes());  
				        schannel.write(outBuffer);
					}
				}
			}
			
			
			
//			ByteBuffer buffer = ByteBuffer.allocate(1024);
//			buffer.putInt(123);
//			channel.write(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
