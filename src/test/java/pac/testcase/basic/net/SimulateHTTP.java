package pac.testcase.basic.net;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

/**
 * Created by Alvez on 14-9-23.
 */
public class SimulateHTTP {

    enum OPERTYPE {
        NORMAL {
            @Override
            public void resolveRequest() {
                this.resolveRequest("HTTP/1.1 200 OK\r\n");
            }
        }, REDIRECT {
            @Override
            public void resolveRequest() {
                this.resolveRequest("HTTP/1.1 302 FOUND\r\n");
            }
        };

        OPERTYPE() {
        }

        void resolveRequest(String headLine){

            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(4646);
                while (true) {
                    Socket conn = serverSocket.accept();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String readLn = reader.readLine();
                    if (StringUtils.contains(readLn, "HTTP")) {
                        System.out.println("accept::" + readLn);
                        BufferedOutputStream outputStream = new BufferedOutputStream(conn.getOutputStream());
                        StringBuilder headerStr = new StringBuilder();
                        //outputStream.write("<html><body><table border=\"1\"><tr><td>abc</td></tr></table></body></html>".getBytes());
                        headerStr.append(headLine);
                        headerStr.append("Server: Alvez 1.0\r\n");
                        headerStr.append("Content-Type: text/html\r\n");
                        headerStr.append("Content-Length: ").append(readLn.length()).append("\r\n");
                        headerStr.append("Location: http://www.51cto.com");

                        outputStream.write(headerStr.toString().getBytes());
                        outputStream.flush();
                    }

                    conn.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                assert serverSocket != null;
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public abstract void resolveRequest();
    }

    public static void main(String[] args) {

        Socket socket = null;
        try {
            socket = new Socket("www.twitter.com", 80);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert socket != null;
        System.out.println(socket.isConnected());
        System.out.println(socket.getPort());
        System.out.println(socket.getInetAddress().getHostAddress());
        System.out.println(socket.getInetAddress().getHostName());
        System.out.println(socket.getInetAddress());
        System.out.println(socket.getRemoteSocketAddress());
        System.out.println("---------local info---------");
        System.out.println(socket.getLocalPort());
        System.out.println(socket.getLocalAddress());
        System.out.println(socket.getLocalAddress().getHostAddress());
        System.out.println(socket.getLocalAddress().getHostName());
        System.out.println(socket.getLocalSocketAddress());


        StringTokenizer tokenizer = new StringTokenizer("alvez","a",true);
        System.out.println(tokenizer.countTokens());
        while(tokenizer.hasMoreTokens()){
            System.out.println(tokenizer.nextToken());
        }
        OPERTYPE.NORMAL.resolveRequest();
    }

}
