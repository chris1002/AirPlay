package com.client;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * @author pc
 * ������
 *
 */
public class Tool {
	public static BufferedImage getImage(String name) {
		URL url = //��ַ  ��ַ  ʵ�ʾ����ַ���
			Tool.class.getResource("../img/"+name);
		try {
			//
			BufferedImage image = ImageIO.read(url);
			//��֤���
			//System.out.println(image);
			return image;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
