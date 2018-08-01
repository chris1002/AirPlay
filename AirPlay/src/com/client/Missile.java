package com.client;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

//子弹类
public class Missile {
	//坐标
	int x,y;
	//图片
	BufferedImage showImage;
	//移动速度
	int speed;
	//宽高
	int w,h;
	//持有主战场
	PanelClient pc;
	
	public Missile(int x, int y, int w, int h, PanelClient pc) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.pc=pc;
		showImage=Tool.getImage("fire.png");
		//初始化速度
		speed=10;
	}
	
	//移动
	public void move(){
		y-=speed;
		if(y<=0){
			pc.missiles.remove(this);
		}
	}
	
	//一个子弹打击掉某一个敌人的血量
	public boolean hitEnemy(Enemy e){
		if(getRect().intersects(e.getRect())){
			if(--e.live == 0){
				//敌人死亡
				//回收敌人
				pc.allEnemy.remove(e);
			}
			//直接回收
			pc.missiles.remove(this);
			return true;//打中了
		}
		return false;//默认没有打中
	}
	//所有的子弹打击所有敌人
	public void hitEnemys(List<Enemy> enemys){
//		for(int i=0;i<enemys.size();i++){
//			if(hitEnemy(enemys.get(i)))return;
//		}
		for(Enemy e:enemys){
			if(hitEnemy(e))return;
		}
	}
	
	
	//得到外切四边形的方法
	public Rectangle getRect(){
		return new Rectangle(x,y,w,h);
	}

}
