package com.client;

import java.awt.image.BufferedImage;
import java.util.Random;

public class BackGround {
	//����ͼƬ����
	private BufferedImage showImge;
	//����
	private int x,y;
	//�ƶ�����һ������
	private int x2,y2;
	//�����������
	Random r = new Random();
	
	public BackGround(){
		//��ʼ������
		x=y=0;
		x2=0;
		y2=-768;
		//�õ�����������ķ�Χ
		int i = r.nextInt(5)+1;
		//��ʼ��ͼƬ
		showImge=Tool.getImage("bg"+i+".jpg");
	}
	
	//��ǰ�������ƶ�
	public void move1(){
		y+=3;
		if(y>=768){
			y=-768;
			//�õ�����������ķ�Χ
			int i = r.nextInt(5)+1;
			//��ʼ��ͼƬ
			showImge=Tool.getImage("bg"+i+".jpg");
		}
	}
	//��һ���������ƶ�
	public void move2(){
		y2+=3;
		if(y2>=768){
			y2=-768;
			//�õ�����������ķ�Χ
			int i = r.nextInt(5)+1;
			//��ʼ��ͼƬ
			showImge=Tool.getImage("bg"+i+".jpg");
		}
	}

	public int getX2() {
		return x2;
	}

	public int getY2() {
		return y2;
	}

	public BufferedImage getShowImge() {
		return showImge;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
