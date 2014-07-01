package pac.testcase.basic.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;


public class DownloadChannelServer extends Thread{
	
	@Override
	public void run() {
		System.out.println("server start...");
		
		ServerSocketChannel serverChannel;
		try {
			serverChannel = ServerSocketChannel.open();
			serverChannel.bind(new InetSocketAddress(8989));
			serverChannel.configureBlocking(false);
			Selector sel = Selector.open();
			serverChannel.register(sel, SelectionKey.OP_ACCEPT);
			ByteBuffer buffer = ByteBuffer.allocate(100*1024);
			
			CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
			
			while(true){
				sel.select();
				Iterator<SelectionKey> selKeyItr = sel.selectedKeys().iterator();
				while(selKeyItr.hasNext()){
					SelectionKey key = selKeyItr.next();
					selKeyItr.remove();
					
					if(key.isAcceptable()){
						System.out.println("server acceptable");
						SocketChannel channel = ((ServerSocketChannel)key.channel()).accept();
						channel.configureBlocking(false);
						channel.register(sel, SelectionKey.OP_READ);
					}else if(key.isReadable()){
						System.out.println("server readable"+Thread.currentThread().getName());
						SocketChannel channel = (SocketChannel) key.channel();
						if(channel.read(buffer)>0){
							buffer.flip();
							CharBuffer clientBuffer = decoder.decode(buffer);
							System.out.println("from client::"+clientBuffer.toString());
							buffer.clear();	
						}
						
						channel.register(sel, SelectionKey.OP_WRITE).attach(new ChannelResolver("D:/doc_backup.rar"));
					}else if(key.isWritable()){
						SocketChannel channel =(SocketChannel) key.channel();
						
						if(key.attachment()!=null){
							ChannelResolver resolver = (ChannelResolver)key.attachment();
							buffer = resolver.readInto(); 
							if(buffer!=null){
								channel.write(buffer);	
							}
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		new DownloadChannelServer().start();
	}
}

class ChannelResolver{
	private FileChannel channel;
	private ByteBuffer buffer;
	private FileInputStream fis;
	
	public ChannelResolver(String filePath){
		try {
			this.fis = new FileInputStream(filePath);
			this.channel = this.fis.getChannel();
			buffer = ByteBuffer.allocate(1024*100);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	ByteBuffer readInto(){
		try {
			buffer.clear();
			int i = channel.read(buffer);
			buffer.flip();
			if(i<0){
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer;
	}
	
	public FileChannel getChannel() {
		return channel;
	}

	public ByteBuffer getBuffer() {
		return buffer;
	}
}