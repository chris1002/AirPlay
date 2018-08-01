package com.client;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

//主人公
public class Hero implements Runnable{
	private int xmove;//定义横向移动的偏移量
	private int ymove;	//定义纵向移动的偏移量
	//坐标
	int x,y;
	//图片
	BufferedImage showImage;
	//宽高
	int w,h;
	//存活
	boolean live;
	//血量
	int blood;
	//持有主战场
	PanelClient pc;
	
	public int getBlood() {
		return blood;
	}
	
	public boolean isLive() {
		return live;
	}

	public Hero(int x, int y, int w, int h, int blood, PanelClient pc) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.blood=blood;
		this.pc = pc;
		live=true;
		//初始化图片
		showImage=Tool.getImage("My_plane.png");
		//拿到图片
		w=showImage.getWidth();
		h=showImage.getHeight();
		//开启多线程
		new Thread(this).start();
	}
	//移动
	public void move(int code) {
		switch(code){
			case KeyEvent.VK_UP:ymove=-10;break;
			case KeyEvent.VK_DOWN:ymove=+10;break;
			case KeyEvent.VK_LEFT:xmove=-10;break;
			case KeyEvent.VK_RIGHT:xmove=+10;break;
			case KeyEvent.VK_SPACE:fire();break;
			default:break;
		}
	}
	public void stop(int code) {
		switch(code){
			case KeyEvent.VK_UP:ymove=0;break;
			case KeyEvent.VK_DOWN:ymove=0;break;
			case KeyEvent.VK_LEFT:xmove=0;break;
			case KeyEvent.VK_RIGHT:xmove=0;break;
			default:break;
		}
	}
	//开火
	private void fire() {
		//missiles-->主战场成员变量-->成员对象所调用
		//-->pc对象-->引用传递
		Missile m = new Missile(x+10, y, 80, 80, pc);
		pc.missiles.add(m);
	}
	
	//得到外切四边形的方法
	public Rectangle getRect(){
		return new Rectangle(x,y,w,h);
	}
	
	public void run() {
		while(true){
			//处理边界问题
			if(this.x<=10){
				this.x=10;
			}
			if(this.x>=PanelClient.GAME_W-showImage.getWidth()-10){
				this.x=PanelClient.GAME_W-showImage.getWidth()-10;
			}
			if(this.y>=PanelClient.GAME_H-showImage.getHeight()-10){
				this.y=PanelClient.GAME_H-showImage.getHeight()-10;
			}
			if(this.y<=0){
				this.y=0;
			}
			//处理遇到敌机的问题
			for(int i=0;i<pc.getAllEnemy().size();i++){
				Enemy e = pc.getAllEnemy().get(i);
				if(this.getRect().intersects(e.getRect())){
					blood--;
				}
			}
			if(blood<=0){
				live=false;
			}
			x+=xmove;
			y+=ymove;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}