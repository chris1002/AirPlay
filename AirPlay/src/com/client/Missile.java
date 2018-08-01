package com.client;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

//�ӵ���
public class Missile {
	//����
	int x,y;
	//ͼƬ
	BufferedImage showImage;
	//�ƶ��ٶ�
	int speed;
	//���
	int w,h;
	//������ս��
	PanelClient pc;
	
	public Missile(int x, int y, int w, int h, PanelClient pc) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.pc=pc;
		showImage=Tool.getImage("fire.png");
		//��ʼ���ٶ�
		speed=10;
	}
	
	//�ƶ�
	public void move(){
		y-=speed;
		if(y<=0){
			pc.missiles.remove(this);
		}
	}
	
	//һ���ӵ������ĳһ�����˵�Ѫ��
	public boolean hitEnemy(Enemy e){
		if(getRect().intersects(e.getRect())){
			if(--e.live == 0){
				//��������
				//���յ���
				pc.allEnemy.remove(e);
			}
			//ֱ�ӻ���
			pc.missiles.remove(this);
			return true;//������
		}
		return false;//Ĭ��û�д���
	}
	//���е��ӵ�������е���
	public void hitEnemys(List<Enemy> enemys){
//		for(int i=0;i<enemys.size();i++){
//			if(hitEnemy(enemys.get(i)))return;
//		}
		for(Enemy e:enemys){
			if(hitEnemy(e))return;
		}
	}
	
	
	//�õ������ı��εķ���
	public Rectangle getRect(){
		return new Rectangle(x,y,w,h);
	}

}
