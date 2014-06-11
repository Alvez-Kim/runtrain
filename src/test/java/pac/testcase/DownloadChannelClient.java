package pac.testcase;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Iterator;

public class DownloadChannelClient extends Thread{
	
	
	public static void main(String[] args){
		new DownloadChannelClient(0).start();
		new DownloadChannelClient(1).start();
		new DownloadChannelClient(2).start();
	}
	
	private FileOutputStream fos;
	private FileChannel fc;

	public DownloadChannelClient(int i) {
		super();
		try {
			this.fos = new FileOutputStream("E:/testfolder/channelTest"+i+".rar");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.fc = fos.getChannel();
	}




	@Override
	public void run() {
		try {
			System.out.println("client...");
			SocketAddress addr = new InetSocketAddress(8989);

			SocketChannel client = SocketChannel.open();
			client.configureBlocking(false);
			Selector sel = Selector.open();
			client.register(sel, SelectionKey.OP_CONNECT);
			CharsetEncoder encoder = Charset.forName("UTF-8").newEncoder();
			ByteBuffer buffer= ByteBuffer.allocate(1024*500);
			client.connect(addr);
			
			while (true) {
				sel.select();
				Iterator<SelectionKey> selKeyItr = sel.selectedKeys().iterator();
				while (selKeyItr.hasNext()) {
					SelectionKey key = selKeyItr.next();
					selKeyItr.remove();
					if (key.isConnectable()) {
						System.out.println("client connectble");
						SocketChannel channel = (SocketChannel) key.channel();

						channel.configureBlocking(false);
						channel.finishConnect();
						channel.write(encoder.encode(CharBuffer.wrap("start download")));
						channel.register(sel, SelectionKey.OP_READ);
					} else if (key.isReadable()) {
						SocketChannel channel = (SocketChannel) key.channel();
						if(channel.read(buffer)>0){
							buffer.flip();
							fc.write(buffer);
							buffer.clear();
						}else{
							channel.close();
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
