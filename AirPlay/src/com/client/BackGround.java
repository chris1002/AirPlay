package com.client;

import java.awt.image.BufferedImage;
import java.util.Random;

public class BackGround {
	//接受图片变量
	private BufferedImage showImge;
	//坐标
	private int x,y;
	//移动的另一份坐标
	private int x2,y2;
	//定义随机对象
	Random r = new Random();
	
	public BackGround(){
		//初始化坐标
		x=y=0;
		x2=0;
		y2=-768;
		//拿到背景的随机的范围
		int i = r.nextInt(5)+1;
		//初始化图片
		showImge=Tool.getImage("bg"+i+".jpg");
	}
	
	//当前背景的移动
	public void move1(){
		y+=3;
		if(y>=768){
			y=-768;
			//拿到背景的随机的范围
			int i = r.nextInt(5)+1;
			//初始化图片
			showImge=Tool.getImage("bg"+i+".jpg");
		}
	}
	//下一个背景的移动
	public void move2(){
		y2+=3;
		if(y2>=768){
			y2=-768;
			//拿到背景的随机的范围
			int i = r.nextInt(5)+1;
			//初始化图片
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
