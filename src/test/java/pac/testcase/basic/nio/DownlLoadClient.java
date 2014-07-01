package pac.testcase.basic.nio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class DownlLoadClient implements Runnable {

	@SuppressWarnings("resource")
	public void run() {
		try {
			Socket client = new Socket("127.0.0.1", 8989);
			InputStream is = client.getInputStream();
			FileOutputStream fos = new FileOutputStream(
					"E:/testfolder/langchao" + Thread.currentThread().getId()
							+ ".txt");

			byte[] fromServer = new byte[1024];
			while (is.read(fromServer) > -1) {
				fos.write(fromServer);
			}
			client.close();
		} catch (IOException e) {
			System.out.println("client线程我的天~");
		}
	}

	public static void main(String[] args) throws IOException,
			InterruptedException {
		new Thread(new DownLoadServer()).start();
		Thread.sleep(1000);
		new Thread(new DownlLoadClient()).start();
		new Thread(new DownlLoadClient()).start();
	}
}

class DownLoadServer implements Runnable {

	public void run() {
		try {
			@SuppressWarnings("resource")
			final ServerSocket ss = new ServerSocket(8989);
			while (true) {
				final Socket server = ss.accept();
				Thread t = new Thread() {

					@Override
					public void run() {
						super.run();
						byte[] bfile = new byte[1024];
						try {
							FileInputStream fis = new FileInputStream("D:/doc_backup.rar");
							OutputStream os = server.getOutputStream();
							while (fis.read(bfile) > -1) {
								os.write(bfile);
							}
							fis.close();
							server.close();
						} catch (IOException e) {
							System.out.println("server线程输出流我的天");
						}
					}
				};
				t.start();
			}
		} catch (Exception e) {
			System.out.println("server线程 我的天~");
		}
	}
	
	/*
	@Override
	public void run() {
		try {
			@SuppressWarnings("resource")
			final ServerSocket ss = new ServerSocket(8989);
			while (true) {
				Socket server = ss.accept();
				byte[] bfile = new byte[1024];
				try {
					FileInputStream fis = new FileInputStream("D:/doc_backup.rar");
					OutputStream os = server.getOutputStream();
					while (fis.read(bfile) > -1) {
						os.write(bfile);
					}
					fis.close();
					server.close();
				} catch (IOException e) {
					System.out.println("server线程输出流我的天");
				}
			}
		} catch (Exception e) {
			System.out.println("server线程 我的天~");
		}
	}
*/
}
