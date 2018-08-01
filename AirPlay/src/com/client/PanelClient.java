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

import javax.swing.JFrame;//���

//��ս��
public class PanelClient extends JFrame implements Runnable{
	//�ֱ���
	public static final int GAME_W=512;
	public static final int GAME_H=768;
	//�ж���Ϸ�Ƿ����,Ĭ��û�н���
	boolean isGameOver=false;
	//����background
	BackGround bg;
	//�������˹�
	Hero hero;
	//�����ӵ�����
	List<Missile> missiles = new ArrayList<Missile>();
	//���е��˼���
	List<Enemy> allEnemy = new ArrayList<Enemy>();
	
	public List<Missile> getMissiles() {
		return missiles;
	}
	
	public List<Enemy> getAllEnemy() {
		return allEnemy;
	}

	public  PanelClient() {
		//��ʼ������
		bg=new BackGround();
		//��ʼ��hero
		hero=new Hero(150,660,150,50,100,this);
		//GUI
		this.setTitle("�쾯����");
		this.setSize(GAME_W, GAME_H);
		//this.setIconImage(Toolkit.getDefaultToolkit().getImage("../img/w_zdd.jpg"));
		//this.setIconImage(Toolkit.getDefaultToolkit().getImage("D:/Workspaces/MyEclipse8/�쾯����3.0/src/com/sybc/img/w_zdd.jpg"));
		//logo
		this.setIconImage(Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("../img/w_zdd.jpg")));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		//�������߳�
		new Thread(this).start();
		//ʵ�ּ��̵ļ���
		this.addKeyListener(new MyListener());
		//ʹ���ڲ��ഴ������ĵ���
		new Thread(new Runnable() {
			public void run() {
				while(true){
					try {
						//��������
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
		//������˸
		BufferedImage image = 
			new BufferedImage(512, 768, BufferedImage.TYPE_3BYTE_BGR);
		Graphics g2 = image.getGraphics();
		if(!isGameOver){
			//��������
			g2.drawImage(bg.getShowImge(), bg.getX(), bg.getY(), this);
			g2.drawImage(bg.getShowImge(), bg.getX2(), bg.getY2(), this);
			
			g2.setColor(Color.RED);
			g2.setFont(new Font("����", Font.BOLD, 15));
			g2.drawString("���˸�����"+getAllEnemy().size(), 10, 60);
			g2.drawString("heroѪ��ֵ��"+hero.getBlood(), 10, 80);
			g2.drawString("�ӵ��ĸ�����"+getMissiles().size(), 10, 100);
			//�������˹�
			g2.drawImage(hero.showImage, hero.x, hero.y, this);
			
			//���ӵ�
			for(int i=0;i<missiles.size();i++){
				Missile m = missiles.get(i);
				g2.drawImage(m.showImage, m.x, m.y,m.w,m.h, this);
				m.move();
				//
				m.hitEnemys(allEnemy);
			}
			//������
			for(int i=0;i<allEnemy.size();i++){
				Enemy e = allEnemy.get(i);
				if(e.live==0){
					this.allEnemy.remove(i);
				}
				g2.drawImage(e.showImage, e.x, e.y,e.w, e.h, this);
				//�����ƶ�
				e.move();
				
			}
		}else{
			g2.drawImage(bg.getShowImge(), 0, 0, this);		
			g2.drawImage(hero.showImage, hero.x, hero.y, this);
			g2.setColor(Color.RED);
			g2.setFont(new Font("����", Font.BOLD, 30));
			g2.drawString("��Ϸ����", hero.x-10, hero.y-30);
		}
		//����image
		g.drawImage(image, 0, 0, this);
	}
	
	//��Ա�ڲ���
	public class MyListener extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			int code = e.getKeyCode();
			//����hero���ƶ�����
			if(!isGameOver){
				hero.move(code);
			}
		}
		public void keyReleased(KeyEvent e) {
			int code = e.getKeyCode();
			//����hero��ֹͣ����
			hero.stop(code);
		}
	}

	public void run() {
		while(true){
			if(!isGameOver){
				//�������л�
				bg.move1();
				bg.move2();
				//�ж���Ϸ�Ƿ����
				if(!hero.isLive()){
					isGameOver=true;
				}
			}
			//�������ػ�
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
