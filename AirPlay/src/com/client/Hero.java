package com.client;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

//���˹�
public class Hero implements Runnable{
	private int xmove;//��������ƶ���ƫ����
	private int ymove;	//���������ƶ���ƫ����
	//����
	int x,y;
	//ͼƬ
	BufferedImage showImage;
	//���
	int w,h;
	//���
	boolean live;
	//Ѫ��
	int blood;
	//������ս��
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
		//��ʼ��ͼƬ
		showImage=Tool.getImage("My_plane.png");
		//�õ�ͼƬ
		w=showImage.getWidth();
		h=showImage.getHeight();
		//�������߳�
		new Thread(this).start();
	}
	//�ƶ�
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
	//����
	private void fire() {
		//missiles-->��ս����Ա����-->��Ա����������
		//-->pc����-->���ô���
		Missile m = new Missile(x+10, y, 80, 80, pc);
		pc.missiles.add(m);
	}
	
	//�õ������ı��εķ���
	public Rectangle getRect(){
		return new Rectangle(x,y,w,h);
	}
	
	public void run() {
		while(true){
			//����߽�����
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
			//���������л�������
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