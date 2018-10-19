package com.cn.sysManager.toolbox.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Random;

/**图片验证码
 * Created by lijm on 2018-03-08.
 */
public class ImageCodeUtils {

    //使用到Algerian字体，系统里没有的话需要安装字体，字体只显示大写，去掉了1,0,i,o几个容易混淆的字符
    static final String VERIFY_CODES = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
    static Random random = new Random();

    /**
     * 使用默认字符源码生成验证码
     * @param verifySize
     * @return
     */
     public static String generateVerifyCode(int verifySize){
       return generateVerifyCode(verifySize,VERIFY_CODES);
     }

    /**
     * 使用指定源生成验证码
     * @param verifySize
     * @param sources
     * @return
     */
     public static String generateVerifyCode(int verifySize,String sources){
         if(sources == null || sources.length() == 0){
             sources = VERIFY_CODES;
         }

         int codesLen = sources.length();
         Random rand = new Random(System.currentTimeMillis());
         StringBuilder verifyCode = new StringBuilder(verifySize);
         for(Integer i=0;i<verifySize;i++){
             verifyCode.append(sources.charAt(rand.nextInt(codesLen-1)));
         }
         return verifyCode.toString();
     }

    /**
     * 输出置顶验证码图片流
     * @param w
     * @param h
     * @param os
     * @param code
     * @throws Exception
     */
     public static void outputImage(int w, int h, OutputStream os,String code)throws Exception{

         int verifySize = code.length();
         BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
         Random rand = new Random();
         Graphics2D gd = image.createGraphics();
         gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
         Color[] colors = new Color[5];
         Color[] colorSpaces = new Color[] { Color.WHITE, Color.CYAN,
                 Color.GRAY, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE,
                 Color.PINK, Color.YELLOW };
         float[] fractions = new float[colors.length];
         for(int i = 0; i < colors.length; i++){
             colors[i] = colorSpaces[rand.nextInt(colorSpaces.length)];
             fractions[i] = rand.nextFloat();
         }
         Arrays.sort(fractions);

         gd.setColor(new Color(250, 252, 253));// 设置边框色
         gd.fillRect(0, 0, w, h);
         gd.setColor(Color.WHITE);// 设置背景色
         gd.fillRect(0, 0, w, h);

         // 添加噪点
         float yawpRate = 0.1f;// 噪声率
         int area = (int) (yawpRate * w * h);
         for (int i = 0; i < area; i++) {
             int x = random.nextInt(w);
             int y = random.nextInt(h);
             int rgb = getRandomIntColor();
             image.setRGB(x, y, rgb);
         }
         //绘制干扰线
         Random random = new Random();
         gd.setColor(Color.gray);// 设置线条的颜色
         for (int i = 0; i < 16; i++) {
             int x = random.nextInt(w);
             int y = random.nextInt(h);
             int xl = random.nextInt(12);
             int yl = random.nextInt(12);
             gd.drawLine(x, y, x + xl, y + yl);
         }

         //shear(gd, w, h, Color.WHITE);// 使图片扭曲

         gd.setColor(getRandColor(100, 160));
         int fontSize = h-35;
         Font font = new Font("Fixedsys", Font.PLAIN,fontSize);
         gd.setFont(font);
         char[] chars = code.toCharArray();
         for(Integer i = 0; i < verifySize; i++){
             AffineTransform affine = new AffineTransform();
             affine.setToRotation(Math.PI / 4 * rand.nextDouble() * (rand.nextBoolean() ? 1 : -1), (w / verifySize) * i + fontSize/2, h/2);
             gd.setTransform(affine);
             gd.setColor(new Color(20 + random.nextInt(110),20 + random.nextInt(110), 20 + random.nextInt(110)));
             gd.drawChars(chars, i, 1, (((w-10) / verifySize) * i + 5), h/2 + fontSize/2 - 10);
         }

         gd.dispose();
         ImageIO.write(image, "jpg", os);
     }

    private static int getRandomIntColor() {
        int[] rgb = getRandomRgb();
        int color = 0;
        for (int c : rgb) {
            color = color << 8;
            color = color | c;
        }
        return color;
    }

    private static int[] getRandomRgb() {
        int[] rgb = new int[3];
        for (int i = 0; i < 3; i++) {
            rgb[i] = random.nextInt(255);
        }
        return rgb;
    }

    public static  Color getRandColor(int fc,int bc){
         Random random = new Random();
         if(fc>255){
             fc=255;
         }
         if(bc>255){
             bc=255;
         }
         int r = fc + random.nextInt(bc-fc);
         int g = fc + random.nextInt(bc-fc);
         int b = fc + random.nextInt(bc-fc);
         return new Color(r,g,b);
     }
}