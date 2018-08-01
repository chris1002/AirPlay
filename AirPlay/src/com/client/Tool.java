package com.client;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * @author pc
 * 帮助类
 *
 */
public class Tool {
	public static BufferedImage getImage(String name) {
		URL url = //地址  网址  实质就是字符串
			Tool.class.getResource("../img/"+name);
		try {
			//
			BufferedImage image = ImageIO.read(url);
			//验证输出
			//System.out.println(image);
			return image;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
