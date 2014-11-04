package pac.testcase.utils;

import android.graphics.*;
import com.sun.imageio.plugins.common.ImageUtil;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.Path;

import javax.imageio.ImageIO;

/**
 * Created by Alvez on 14-10-27.
 */
public class ImgUtils {
    public static BufferedImage makeRoundedCorner(BufferedImage image,
                                                  int cornerRadius) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage output = new BufferedImage(w, h,
                BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = output.createGraphics();

        // This is what we want, but it only does hard-clipping, i.e. aliasing
        // g2.setClip(new RoundRectangle2D ...)

        // so instead fake soft-clipping by first drawing the desired clip shape
        // in fully opaque white with antialiasing enabled...
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setComposite(AlphaComposite.Src);
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius,
                cornerRadius));

        // ... then compositing the image on top,
        // using the white shape from above as alpha source
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);

        g2.dispose();

        return output;
    }

    public static BufferedImage createResizedCopy(Image originalImage, int scaledWidth,
                                                  int scaledHeight, boolean preserveAlpha) {
        int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB
                : BufferedImage.TYPE_INT_ARGB;
        BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight,
                imageType);
        Graphics2D g = scaledBI.createGraphics();
        if (preserveAlpha) {
            g.setComposite(AlphaComposite.Src);
        }
        g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        g.dispose();
        return scaledBI;
    }

    public static Bitmap getRoundCornerBitmap(Bitmap bitmap, float roundPX){
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Bitmap bitmap2 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap2);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, width, height);
        final RectF rectF = new RectF(rect);

        paint.setColor(color);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawRoundRect(rectF, roundPX, roundPX, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return bitmap2;
    }

    public static void main(String[] args) throws IOException {
//        BufferedImage icon = ImageIO.read(new File("D:/Wallpaper/p1358080806.jpg"));
//        BufferedImage rounded = makeRoundedCorner(icon, 60);
//        ImageIO.write(rounded, "png", new File("D:/Wallpaper/gen.png"));

        Files.copy(new File("D:/Wallpaper/000.jpg").toPath(),new File("D:/Wallpaper/111.png").toPath());
        BufferedImage pic = ImageIO.read(new File("D:/Wallpaper/111.png"));

        BufferedImage resized = makeRoundedCorner(createResizedCopy(pic, 520, 520, true), 70);
        ImageIO.write(resized, "png", new File("D:/Wallpaper/gen.png"));

//        BitmapFactory.decodeStream(new FileInputStream("D:/Wallpaper/p1358080806.jpg"));
//        Bitmap bitmap = getRoundCornerBitmap(BitmapFactory.decodeStream(new FileInputStream("D:/Wallpaper/p1358080806.jpg")),30);

    }
}
