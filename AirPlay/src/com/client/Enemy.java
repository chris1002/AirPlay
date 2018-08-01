package com.client;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy {
	//����
	int x,y;
	//���
	int w,h;
	//ͼƬ
	BufferedImage showImage;
	//�ٶ�
	int speed;
	//����ֵ
	int live;
	//�����
	Random r = new Random();
	
	//�����в���������ࣩ
	public Enemy(){
		//�õ������ͼƬ
		int i = r.nextInt(15)+1;
		showImage=Tool.getImage("ep"+(i<10?"0"+i:i)+".png");
		//�õ�ͼƬ���
		w=showImage.getWidth();
		h=showImage.getHeight();
		//����ĺ�����
		x=r.nextInt(512-w);
		//��������Ļ����
		y=-200;
		//����i��ʼ���ٶȺ�����ֵ
		if(i<=5){
			speed=5;
			live=1;
		}else if(i>=6&&i<=14){
			speed=15;
			live=2;
		}else if(i==15){
			speed=30;
			live=3;
		}
	}
	//�ƶ�
	public void move(){
		y+=speed;
		if(y>=PanelClient.GAME_H){
			live=0;
		}
	}
	//�õ������ı��εķ���
	public Rectangle getRect(){
		return new Rectangle(x,y,w,h);
	}

}
