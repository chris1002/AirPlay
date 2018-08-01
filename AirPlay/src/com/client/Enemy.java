package com.client;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy {
	//坐标
	int x,y;
	//宽高
	int w,h;
	//图片
	BufferedImage showImage;
	//速度
	int speed;
	//生命值
	int live;
	//随机类
	Random r = new Random();
	
	//不能有参数（多个类）
	public Enemy(){
		//拿到随机的图片
		int i = r.nextInt(15)+1;
		showImage=Tool.getImage("ep"+(i<10?"0"+i:i)+".png");
		//拿到图片宽高
		w=showImage.getWidth();
		h=showImage.getHeight();
		//随机的横坐标
		x=r.nextInt(512-w);
		//定义在屏幕上面
		y=-200;
		//根据i初始化速度和生命值
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
	//移动
	public void move(){
		y+=speed;
		if(y>=PanelClient.GAME_H){
			live=0;
		}
	}
	//得到外切四边形的方法
	public Rectangle getRect(){
		return new Rectangle(x,y,w,h);
	}

}
