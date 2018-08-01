package com.client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;//拆解

//主战场
public class PanelClient extends JFrame implements Runnable{
	//分辨率
	public static final int GAME_W=512;
	public static final int GAME_H=768;
	//判断游戏是否结束,默认没有结束
	boolean isGameOver=false;
	//持有background
	BackGround bg;
	//持有主人公
	Hero hero;
	//持有子弹集合
	List<Missile> missiles = new ArrayList<Missile>();
	//持有敌人集合
	List<Enemy> allEnemy = new ArrayList<Enemy>();
	
	public List<Missile> getMissiles() {
		return missiles;
	}
	
	public List<Enemy> getAllEnemy() {
		return allEnemy;
	}

	public  PanelClient() {
		//初始化背景
		bg=new BackGround();
		//初始化hero
		hero=new Hero(150,660,150,50,100,this);
		//GUI
		this.setTitle("红警敌人");
		this.setSize(GAME_W, GAME_H);
		//this.setIconImage(Toolkit.getDefaultToolkit().getImage("../img/w_zdd.jpg"));
		//this.setIconImage(Toolkit.getDefaultToolkit().getImage("D:/Workspaces/MyEclipse8/红警敌人3.0/src/com/sybc/img/w_zdd.jpg"));
		//logo
		this.setIconImage(Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("../img/w_zdd.jpg")));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		//开启多线程
		new Thread(this).start();
		//实现键盘的监听
		this.addKeyListener(new MyListener());
		//使用内部类创建随机的敌人
		new Thread(new Runnable() {
			public void run() {
				while(true){
					try {
						//创建敌人
						allEnemy.add(new Enemy());
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	@Override
	public void paint(Graphics g) {
		//处理闪烁
		BufferedImage image = 
			new BufferedImage(512, 768, BufferedImage.TYPE_3BYTE_BGR);
		Graphics g2 = image.getGraphics();
		if(!isGameOver){
			//画出背景
			g2.drawImage(bg.getShowImge(), bg.getX(), bg.getY(), this);
			g2.drawImage(bg.getShowImge(), bg.getX2(), bg.getY2(), this);
			
			g2.setColor(Color.RED);
			g2.setFont(new Font("宋体", Font.BOLD, 15));
			g2.drawString("敌人个数："+getAllEnemy().size(), 10, 60);
			g2.drawString("hero血量值："+hero.getBlood(), 10, 80);
			g2.drawString("子弹的个数："+getMissiles().size(), 10, 100);
			//画出主人公
			g2.drawImage(hero.showImage, hero.x, hero.y, this);
			
			//画子弹
			for(int i=0;i<missiles.size();i++){
				Missile m = missiles.get(i);
				g2.drawImage(m.showImage, m.x, m.y,m.w,m.h, this);
				m.move();
				//
				m.hitEnemys(allEnemy);
			}
			//画敌人
			for(int i=0;i<allEnemy.size();i++){
				Enemy e = allEnemy.get(i);
				if(e.live==0){
					this.allEnemy.remove(i);
				}
				g2.drawImage(e.showImage, e.x, e.y,e.w, e.h, this);
				//调用移动
				e.move();
				
			}
		}else{
			g2.drawImage(bg.getShowImge(), 0, 0, this);		
			g2.drawImage(hero.showImage, hero.x, hero.y, this);
			g2.setColor(Color.RED);
			g2.setFont(new Font("宋体", Font.BOLD, 30));
			g2.drawString("游戏结束", hero.x-10, hero.y-30);
		}
		//绘制image
		g.drawImage(image, 0, 0, this);
	}
	
	//成员内部类
	public class MyListener extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			int code = e.getKeyCode();
			//调用hero的移动方法
			if(!isGameOver){
				hero.move(code);
			}
		}
		public void keyReleased(KeyEvent e) {
			int code = e.getKeyCode();
			//调用hero的停止方法
			hero.stop(code);
		}
	}

	public void run() {
		while(true){
			if(!isGameOver){
				//处理背景切换
				bg.move1();
				bg.move2();
				//判断游戏是否结束
				if(!hero.isLive()){
					isGameOver=true;
				}
			}
			//处理背景重绘
			repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
	
	public static void main(String[] args) {
		new PanelClient();
	}

}
