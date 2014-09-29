package pac.testcase.basic.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;


public class TestFileChannelRW {
    static final Charset charset = Charset.forName("GBK");

    public static void main(String[] args) throws IOException {
        printContent(generateFile());

    }

    private static final String DEFAULT_PATH = "E:/test.txt";
    private static final String DEFAULT_CONTENT = "\"自幼曾攻经史，\\r\\n长成亦有权谋。\\r\\n恰如猛虎卧荒丘，\\r\\n潜伏爪牙忍受。\\r\\n不幸刺文双颊，\\r\\n那堪配在江州。\\r\\n他年若得报冤仇，\\r\\n血染浔阳江口!\"";
    static File generateFile() {
        return generateFile(DEFAULT_PATH,DEFAULT_CONTENT);
    }

    static File generateFile(String filePath,String content){
        File file = new File(filePath);
        try {
            if (!file.exists())
                while (!file.createNewFile()) ;
            else return file;

            FileChannel channel = new RandomAccessFile(file, "rw").getChannel();

            CharsetEncoder encoder = charset.newEncoder();
            CharBuffer charBuffer = CharBuffer.wrap(content);
            ByteBuffer byteBuffer = encoder.encode(charBuffer);

            channel.write(byteBuffer);
            channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    static void printContent(File file) {
        try {
            RandomAccessFile tempFile = new RandomAccessFile(file, "rw");
            FileChannel channel = tempFile.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(30);
            CharsetDecoder decoder = charset.newDecoder();

            while (channel.read(buffer) != -1) {
                buffer.flip();
                CharBuffer charBuffer = decoder.decode(buffer);
                while (charBuffer.hasRemaining()) {
                    System.out.print(charBuffer.get());
                }
                buffer.flip();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
