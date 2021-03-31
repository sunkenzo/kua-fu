package com.mini.kuafu.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 验证码图片
 *
 * @author kenzo
 * @create 2021-03-30 13:49
 */
public class ValidateCodeUtil2 {
    // 验证码字符集
    private static final int[] chars = {
            1, 2, 3, 4, 5, 6, 7, 8, 9,
            1, 2, 3, 4, 5, 6, 7, 8, 9};
    // 字符数量
    private static final int SIZE = 2;
    // 干扰线数量
    private static final int LINES = 2;
    // 宽度
    private static final int WIDTH = 100;
    // 高度
    private static final int HEIGHT = 32;
    // 字体大小
    private static final int FONT_SIZE = 18;

    /**
     * 生成随机验证码及图片
     */
    public static ValidateCodeUtil2.ValidCode createImage() {
        StringBuffer sb = new StringBuffer();
        int result = 0;
        // 1.创建空白图片
        BufferedImage image = new BufferedImage(
                WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        // 2.获取图片画笔
        Graphics graphic = image.getGraphics();
        // 3.设置画笔颜色
        graphic.setColor(Color.LIGHT_GRAY);
        // 4.绘制矩形背景
        graphic.fillRect(0, 0, WIDTH, HEIGHT);
        // 设置随机颜色
        graphic.setColor(getRandomColor());
        // 设置字体大小
        graphic.setFont(new Font(
                null, Font.BOLD + Font.ITALIC, FONT_SIZE));
        // 5.画随机字符
        Random ran = new Random();
        int poolCount = 2 * SIZE - 1;
        for (int i = 0; i < poolCount; i++) {
            // 取随机字符索引
            int n = ran.nextInt(chars.length);
            // 画字符

            // 记录字符
            if (i % 2 != 0) {
                graphic.drawString(
                        "+", i * WIDTH / 3, HEIGHT * 2 / 3);
                sb.append('+');
            } else {
                graphic.drawString(
                        chars[n] + "", i * WIDTH / 3, HEIGHT * 2 / 3);
                sb.append(chars[n]);
                result += chars[n];
            }
        }
        // 6.画干扰线
        for (int i = 0; i < LINES; i++) {
            // 设置随机颜色
            graphic.setColor(getRandomColor());
            // 随机画线
            graphic.drawLine(ran.nextInt(WIDTH), ran.nextInt(HEIGHT),
                    ran.nextInt(WIDTH), ran.nextInt(HEIGHT));
        }
        // 7.返回验证码和图片
        return new ValidateCodeUtil2.ValidCode(sb.toString(), result, image);
    }

    /**
     * 随机取色
     */
    public static Color getRandomColor() {
        Random ran = new Random();
        Color color = new Color(ran.nextInt(255),
                0, 0);
        return color;
    }

    public static class ValidCode {
        private String code; // 验证码字符串
        private int result; // 运算结果
        private BufferedImage image; // 验证码图片

        public ValidCode(String code, int result, BufferedImage image) {
            this.code = code;
            this.result = result;
            this.image = image;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }

        public BufferedImage getImage() {
            return image;
        }

        public void setImage(BufferedImage image) {
            this.image = image;
        }
    }
}
