package PJ;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;

import PJ.ChatMessage;

public class FriendsMable extends JPanel {
	BGM GAME_BGM;
	BGM MABLE_BGM =new BGM("MABLE_BGM.mp3",true);
	BGM SELECTED_BGM;
	int whatPanel=0; //패널바뀔때 쓰이는 변수
	int dice,dice1,dice2,turn=15;
	int tmp_x, tmp_y ,motion_tmp=0;//주사위 모션 x,y
	int touch=0;//대화창 열고닫고할수있게하는변수
	int bx,by; //건물 build의 x,y좌표 
	Timer diceMotion;

	Image StartBackground=new ImageIcon("src/Image/StartBackground_Title.png").getImage();
	Image MainBackground=new ImageIcon("src/Image/MainBackground.png").getImage();
	Image SelectBackground=new ImageIcon("src/Image/SelectBackground.png").getImage();
	boolean startcheck=false;
	boolean myturn=true;
	boolean colorblue=false;
	boolean finish =false;
	//startPanel 이미지-------------------------------------------------------------------------
	JButton loginButton=new JButton(new ImageIcon("src/Image/loginButton.png"));
	JButton startButton=new JButton(new ImageIcon("src/Image/StartButton.png"));
	JButton quitButton=new JButton(new ImageIcon("src/Image/QuitButton.png"));

	ImageIcon LoginButton=new ImageIcon("src/Image/loginButton.png");
	ImageIcon EnteredLoginButton=new ImageIcon("src/Image/EnteredLoginButton.png");
	ImageIcon StartButton=new ImageIcon("src/Image/StartButton.png");
	ImageIcon EnteredStartButton=new ImageIcon("src/Image/EnteredStartButton.png");
	ImageIcon QuitButton=new ImageIcon("src/Image/QuitButton.png");
	ImageIcon EnteredQuitButton=new ImageIcon("src/Image/EnteredQuitButton.png");

	//selectPanel이미지-------------------------------------------------------------------------
	ImageIcon RightButton=new ImageIcon("src/Image/rightButton.PNG");
	ImageIcon EnteredRightButton=new ImageIcon("src/Image/EnteredRightButton.jpg");
	ImageIcon LeftButton=new ImageIcon("src/Image/leftButton.png");
	ImageIcon EnteredLeftButton=new ImageIcon("src/Image/EnteredLeftButton.jpg");
	ImageIcon NextButton=new ImageIcon("src/Image/nextButton.png");
	ImageIcon EnteredNextButton=new ImageIcon("src/Image/EnteredNextButton.png");

	JButton rightButton=new JButton(RightButton);
	JButton leftButton=new JButton(LeftButton);
	JButton characterButton=new JButton(new ImageIcon("src/Image/lion.PNG"));
	JButton nextButton=new JButton(new ImageIcon("src/Image/nextButton.png"));

	ArrayList<Characters> charactersList=new ArrayList<Characters>();

	ImageIcon charactersImage;

	//MainPanel 이미지----------------------------------------------------------------------------
	JButton throwButton=new JButton(new ImageIcon("src/Image/ThrowButton.png"));
	JButton  chatingButton=new JButton(new ImageIcon("src/Image/chatingButton.PNG"));
	JButton plusButton=new JButton(new ImageIcon("src/Image/plusButton.png"));
	JButton logoutButton=new JButton(new ImageIcon("src/Image/logoutButton.png"));
	JButton sendButton=new JButton(new ImageIcon("src/Image/sendButton.png"));

	ImageIcon ThrowButton=new ImageIcon("src/Image/ThrowButton.png");
	ImageIcon EnteredThrowButton=new ImageIcon("src/Image/EnteredThrowButton.png");
	//red,blue건물 이미지
	ImageIcon redbuild1= new ImageIcon("src/Image/redBuilding2.PNG");
	ImageIcon redbuild2= new ImageIcon("src/Image/redBuilding1.PNG");
	ImageIcon bluebuild1=new ImageIcon("src/Image/buleBuilding2.PNG");
	ImageIcon bluebuild2=new ImageIcon("src/Image/buleBuilding1.PNG");
	//주사위 이미지
	ImageIcon one=new ImageIcon("src/Image/one.png");
	ImageIcon two=new ImageIcon("src/Image/two.png");
	ImageIcon three=new ImageIcon("src/Image/three.png");
	ImageIcon four=new ImageIcon("src/Image/four.png");
	ImageIcon five=new ImageIcon("src/Image/five.png");
	ImageIcon six=new ImageIcon("src/Image/six.png");
	//카운트 다운 이미지
	ImageIcon countone=new ImageIcon("src/Image/1.png");
	ImageIcon counttwo=new ImageIcon("src/Image/2.png");	
	ImageIcon countthree=new ImageIcon("src/Image/3.png");
	ImageIcon countfour=new ImageIcon("src/Image/4.png");
	ImageIcon countfive=new ImageIcon("src/Image/5.png");
	ImageIcon countsix=new ImageIcon("src/Image/6.png");
	ImageIcon countseven=new ImageIcon("src/Image/7.png");
	ImageIcon counteight=new ImageIcon("src/Image/8.png");
	ImageIcon countnine=new ImageIcon("src/Image/9.png");
	ImageIcon countten=new ImageIcon("src/Image/10.png");
	ImageIcon countten1=new ImageIcon("src/Image/11.png");
	ImageIcon countten2=new ImageIcon("src/Image/12.png");
	ImageIcon countten3=new ImageIcon("src/Image/13.png");
	ImageIcon countten4=new ImageIcon("src/Image/14.png");
	ImageIcon end=new ImageIcon("src/Image/end.png");
	ImageIcon finished=new ImageIcon("src/Image/finished.png");
	//프로필 캐릭터사진 & 프로필창 이미지 
	ImageIcon proLion=new ImageIcon("src/Image/proLion.png");
	ImageIcon proTube=new ImageIcon("src/Image/proTube.png");
	ImageIcon proNeo=new ImageIcon("src/Image/proNeo.png");
	Image proRed=new ImageIcon("src/Image/proRed.png").getImage();
	Image proBule=new ImageIcon("src/Image/proBlue.png").getImage();

	JLabel finishedLabel =new JLabel(finished);
	JLabel doubleDice=new JLabel(new ImageIcon("src/Image/double.PNG"));
	JLabel countdown=new JLabel(new ImageIcon("src/Image/15.png"));
	JLabel DiceMotion=new JLabel(new ImageIcon("src/Image/motion (1).PNG"));
	JLabel Dice1=new JLabel();
	JLabel Dice2=new JLabel();
	ArrayList<Map>build=new ArrayList<Map>();
	ArrayList<Map>cpbuild=new ArrayList<Map>();
	//대화창
	static String user;
	static ObjectOutputStream writer;
	static ObjectInputStream reader;
	static Socket sock;
	String[] list= {ChatMessage.ALL};
	JTextArea incoming=new JTextArea(15,20);
	JTextArea outgoing=new JTextArea(5,20);
	JList counterParts= new JList(list);
	JScrollPane qScroller=new JScrollPane(incoming);
	JScrollPane oScroller= new JScrollPane(outgoing);
	JScrollPane cScroller = new JScrollPane(counterParts);

	//캐릭터정보
	PosImageIcon topprofile=new PosImageIcon("src/Image/proBlue.png",0,0,250,150);
	PosImageIcon bottomprofile=new PosImageIcon("src/Image/proRed.png",645,455,250,150);
	PosImageIcon userch;
	PosImageIcon userpro;
	PosImageIcon counterPartch;
	PosImageIcon counterPartpro;
	int x,y;
	int nowSelected=0;	
	Map sendMap;
	Characters ch;
	Characters cp;
	//승리자
	String winnerName;
	int winnerGain;
	int winnerMoney;
	PosImageIcon winnerPro ;
	PosImageIcon winnerPanel=new PosImageIcon("src/Image/finished.png",300,200,300,233);
	//주사위모션
	ImageIcon motion[]= {
			new ImageIcon("src/Image/motion (1).PNG"),
			new ImageIcon("src/Image/motion (2).PNG"),
			new ImageIcon("src/Image/motion (3).PNG"),
			new ImageIcon("src/Image/motion (4).PNG"),
			new ImageIcon("src/Image/motion (5).PNG"),
			new ImageIcon("src/Image/motion (6).PNG")
	};

	public FriendsMable(ObjectOutputStream writer,ObjectInputStream reader) {
		this.writer = writer;
		this.reader = reader;
		Thread readerThread = new Thread(new IncomingReader());
		readerThread.start();
		setupGUI();
		diceMotion();
	}
	public void setupGUI() {

		x=380;
		y=480;
		setBounds(0, 0, 900, 636);
		setLayout(null);
		mapInit();
		charactersList.add(new Characters("lion.PNG","selectedLion.PNG"));
		charactersList.add(new Characters("tube.PNG","selectedTube.png"));
		charactersList.add(new Characters("neo.PNG","selectedNeo.png"));

		add(doubleDice);
		doubleDice.setVisible(false);
		doubleDice.setBounds(335,285, 220,70);
		//시끄러..	MABLE_BGM.start();

		startButton.setBounds(638,156,200,58);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {
				//버튼딸깍소리 제거
				BGM mouseEnteredsound=new BGM("mouseEnteredsound.mp3",false);

				try {
					Thread.sleep(100);
				} catch (Exception ex) {}
				mouseEnteredsound.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(StartButton);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(EnteredStartButton);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				//딸깍소리나게 소리설정
				BGM mouseEnteredsound=new BGM("mouseEnteredsound.mp3",false);
				mouseEnteredsound.start();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				//캐릭터 선택화면으로 화면전환 
				//음악꺼주기
				//화면바꿀때 노래도변경 !
				startButton.setVisible(false);
				quitButton.setVisible(false);
				loginButton.setVisible(false);
				characterButton.setVisible(true);
				leftButton.setVisible(true);
				rightButton.setVisible(true);
				nextButton.setVisible(true);
				MABLE_BGM.close();
				SELECTED_BGM=new BGM("SELECTED_BGM.mp3",true);
				SELECTED_BGM.start(); 
				whatPanel=1;	

				try {
					System.out.println("START메세지 타입보내기");
					writer.writeObject(new ChatMessage(ChatMessage.MsgType.START,user,"",""));
					writer.flush();

				}catch(IOException ioe){
					JOptionPane.showMessageDialog(null, "메시지 전송중 문제가 발생하였습니다.");
					ioe.printStackTrace();
				}
			}
		});

		add(startButton);

		quitButton.setBounds(638,356,200,58);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {
				//버튼딸깍소리 제거
				BGM mouseEnteredsound=new BGM("mouseEnteredsound.mp3",false);
				try {
					Thread.sleep(100);
				} catch (Exception ex) {}
				mouseEnteredsound.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(QuitButton);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setIcon(EnteredQuitButton);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				//딸깍소리나게 소리설정
				BGM mouseEnteredsound=new BGM("mouseEnteredsound.mp3",false);
				mouseEnteredsound.start();
			}

			@Override
			public void mouseClicked(MouseEvent e) {

				try {
					Thread.sleep(100);

				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				}
				System.exit(0);
			}
		});
		add(quitButton);

		loginButton.setBounds(638,256,200,58);
		loginButton.setBorderPainted(false);
		loginButton.setContentAreaFilled(false);
		loginButton.setFocusPainted(false);
		loginButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {
				//버튼딸깍소리 제거
				BGM mouseEnteredsound=new BGM("mouseEnteredsound.mp3",false);

				try {
					Thread.sleep(100);
				} catch (Exception ex) {}
				mouseEnteredsound.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				loginButton.setIcon(LoginButton);
				loginButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {

				loginButton.setIcon(EnteredLoginButton);
				loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				//딸깍소리나게 소리설정
				BGM mouseEnteredsound=new BGM("mouseEnteredsound.mp3",false);
				mouseEnteredsound.start();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				processLogin();
			}
		});
		add(loginButton);
		//Select
		nextButton.setVisible(false);
		nextButton.setBounds(640,480,200,58);
		nextButton.setBorderPainted(false);
		nextButton.setContentAreaFilled(false);
		nextButton.setFocusPainted(false);
		nextButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {
				//버튼딸깍소리 제거
				BGM mouseEnteredsound=new BGM("mouseEnteredsound.mp3",false);
				try {
					Thread.sleep(100);
				} catch (Exception ex) {}
				mouseEnteredsound.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				nextButton.setIcon(NextButton);
				nextButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {

				nextButton.setIcon(EnteredNextButton);
				nextButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				//딸깍소리나게 소리설정
				BGM mouseEnteredsound=new BGM("mouseEnteredsound.mp3",false);
				mouseEnteredsound.start();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				SELECTED_BGM.close(); //배경음끄기
				GAME_BGM=new BGM("GAME_BGM.mp3",true);
				GAME_BGM.start(); 
				nextButton.setVisible(false);
				leftButton.setVisible(false);
				rightButton.setVisible(false);
				characterButton.setVisible(false);	
				plusButton.setVisible(true);
				chatingButton.setVisible(true);
				logoutButton.setVisible(true);
				countdown.setVisible(true);

				throwButton.setVisible(true);
				ch.gameName.setBounds(835,560,60,20);
				ch.gameGain.setBounds(690,555,100,20);
				ch.gameMoney.setBounds(700,500,100,20);
				whatPanel=2;
				Dice1.setVisible(true);
				Dice2.setVisible(true);
				if(startcheck)
				{
					colorblue=true;
					topprofile.setImage(proRed);
					bottomprofile.setImage(proBule);
				}

			}
		});
		add(nextButton);

		//로그인버튼이미지준비 -주석처리 처리하기
		leftButton.setBounds(20,300,100,100);
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.setVisible(false);
		leftButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {
				//버튼딸깍소리 제거
				BGM mouseEnteredsound=new BGM("mouseEnteredsound.mp3",false);

				try {
					Thread.sleep(100);
				} catch (Exception ex) {}
				mouseEnteredsound.start();
				selectLeft();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(LeftButton);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {

				leftButton.setIcon(EnteredLeftButton);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				//딸깍소리나게 소리설정
				BGM mouseEnteredsound=new BGM("mouseEnteredsound.mp3",false);
				mouseEnteredsound.start();
			}

			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		add(leftButton);
		//로그인버튼이미지준비 -주석처리 처리하기
		rightButton.setBounds(315,300,100,100);
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.setVisible(false);
		rightButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {
				//버튼딸깍소리 제거
				BGM mouseEnteredsound=new BGM("mouseEnteredsound.mp3",false);

				try {
					Thread.sleep(100);
				} catch (Exception ex) {}
				mouseEnteredsound.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(RightButton);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {

				rightButton.setIcon(EnteredRightButton);
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				//딸깍소리나게 소리설정
				BGM mouseEnteredsound=new BGM("mouseEnteredsound.mp3",false);
				mouseEnteredsound.start();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				selectRight();
			}
		});
		add(rightButton);

		characterButton.setVisible(false);
		characterButton.setBounds(115,180,200,320);
		characterButton.setBorderPainted(false);
		characterButton.setContentAreaFilled(false);
		characterButton.setFocusPainted(false);
		characterButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {


			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(nowSelected==0) {		
					characterButton.setIcon(new ImageIcon("src/Image/selectedLion.PNG"));
					ch=new Characters(user,"src/Image/lion_game.png","src/Image/proLion.png");


					try {
						System.out.println("COUNTER_PART메세지 타입보내기: ");
						writer.writeObject(new ChatMessage(ChatMessage.MsgType.COUNTER_PART,user,ch));
						writer.flush();
					}catch(IOException ioe){
						JOptionPane.showMessageDialog(null, "메시지 전송중 문제가 발생하였습니다.");
						ioe.printStackTrace();
					}

				}else if(nowSelected==1){	
					characterButton.setIcon(new ImageIcon("src/Image/selectedTube.png"));
					ch=new Characters(user,"src/Image/tube_game.png","src/Image/proTube.png");

					try {
						System.out.println("COUNTER_PART메세지 타입보내기: ");
						writer.writeObject(new ChatMessage(ChatMessage.MsgType.COUNTER_PART,user,ch));
						writer.flush();
					}catch(IOException ioe){
						JOptionPane.showMessageDialog(null, "메시지 전송중 문제가 발생하였습니다.");
						ioe.printStackTrace();
					}


				}	else	{

					characterButton.setIcon(new ImageIcon("src/Image/selectedNeo.png"));	
					ch=new Characters(user,"src/Image/neo_game.png","src/Image/proNeo.png");
					try {
						System.out.println("COUNTER_PART메세지 타입보내기: ");
						writer.writeObject(new ChatMessage(ChatMessage.MsgType.COUNTER_PART,user,ch));
						writer.flush();
					}catch(IOException ioe){
						JOptionPane.showMessageDialog(null, "메시지 전송중 문제가 발생하였습니다.");
						ioe.printStackTrace();
					}
				}
				add(ch.gameName);
				add(ch.gameGain);
				add(ch.gameMoney);
				userch=new PosImageIcon(ch.getGameImage(),x,y,50,80);
				userpro=new PosImageIcon(ch.getProImage(),814,470,70,80);
				System.out.println(""+ch.getGameImage());
				System.out.println("nowSelected:"+nowSelected);

			}

		});
		add(characterButton);
		//Main
		chatingButton.setVisible(false);
		chatingButton.setBounds(720, 455, 30,34);
		chatingButton.setBorderPainted(false);
		chatingButton.setContentAreaFilled(false);
		chatingButton.setFocusPainted(false);
		chatingButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if(touch%2==0) {
					qScroller.setVisible(true);
					oScroller.setVisible(true);
					sendButton.setVisible(true);
					touch++;
				}
				else {
					qScroller.setVisible(false);
					oScroller.setVisible(false);
					sendButton.setVisible(false);
					touch++;
				}
			}
		});
		add(chatingButton);
		plusButton.setVisible(false);
		plusButton.setBounds(700, 460, 20, 24);
		plusButton.setBorderPainted(false);
		plusButton.setContentAreaFilled(false);
		plusButton.setFocusPainted(false);
		plusButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if(touch%2==0) {
					cScroller.setVisible(true);
					touch++;
				}else {
					cScroller.setVisible(false);
					touch++;
				}
			}
		});
		add(plusButton);
		logoutButton.setVisible(false);
		logoutButton.setBounds(750, 450, 40, 40);
		logoutButton.setBorderPainted(false);
		logoutButton.setContentAreaFilled(false);
		logoutButton.setFocusPainted(false);
		logoutButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				processLogout();
			}
		});
		add(logoutButton);
		sendButton.setVisible(false);
		sendButton.setBounds(810,290,111,111);
		sendButton.setVisible(false);
		sendButton.setBorderPainted(false);
		sendButton.setContentAreaFilled(false);
		sendButton.setFocusPainted(false);
		sendButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				String to =(String) counterParts.getSelectedValue();
				if(to==null) {
					JOptionPane.showMessageDialog(null,"송신할 대상을 선택한 후 메세지를 보내세요");
					return;
				}try {

					System.out.println("메세지보내기완료!");
					incoming.append(user+":"+outgoing.getText()+"\n");//나의 메세지 창에보이기
					writer.writeObject(new ChatMessage(ChatMessage.MsgType.CLIENT_MSG,user,to,outgoing.getText()));
					writer.flush();
					outgoing.setText("");
					outgoing.requestFocus();
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "메시지 전송중 문제가 발생하였습니다.");
					ex.printStackTrace();
				}
			}
		});
		add(sendButton);
		throwButton.setVisible(false);
		throwButton.setBounds(790,250,111,111);
		throwButton.setBorderPainted(false);
		throwButton.setContentAreaFilled(false);
		throwButton.setFocusPainted(false);
		throwButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) { }

			@Override
			public void mousePressed(MouseEvent e) {
				//버튼딸깍소리 제거
				BGM mouseEnteredsound=new BGM("mouseEnteredsound.mp3",false);
				try {
					Thread.sleep(100);
				} catch (Exception ex) {}
				mouseEnteredsound.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				throwButton.setIcon(ThrowButton);
				throwButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				throwButton.setIcon(EnteredThrowButton);
				throwButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				//딸깍소리나게 소리설정
				BGM mouseEnteredsound=new BGM("mouseEnteredsound.mp3",false);
				mouseEnteredsound.start();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				doubleDice.setVisible(false);
				if(startcheck) {
					if(myturn) {
						if(dice1!=dice2)
							turn--;
						countMotion();
						BGM diceroll=new BGM("diceroll.mp3",false);
						diceroll.start();
						roll();
						DiceMotion.setVisible(true);
						diceMotion.start();
						if(dice1==dice2) {
							myturn=true;
							BGM doubleSound=new BGM("doubleSound.mp3",false);
							doubleSound.start();
							doubleDice.setVisible(true);
						}
						else myturn=false;


						try {
							Thread.sleep(300);


						} catch (InterruptedException e1){e1.printStackTrace();}
						try {

							writer.writeObject(new ChatMessage(ChatMessage.MsgType.DICE_NUM,user,"",dice1+","+dice2+"/"+ch.getGain()));
							writer.flush();

						}catch(IOException ioe){
							JOptionPane.showMessageDialog(null, "메시지 전송중 문제가 발생하였습니다.");
							ioe.printStackTrace();
						}
						//주사위 숫자만큼 캐릭터움직이기 
						for(int i=0; i<dice; i++) {

							if(ch.getlocation()>=0&&ch.getlocation()<=7){
								x-=35;
								y-=22;
								ch.setlocation(ch.getlocation()+1);
							}else if(ch.getlocation()>7&&ch.getlocation()<=15){
								x+=35;
								y-=22;
								ch.setlocation(ch.getlocation()+1);
							}
							else if(ch.getlocation()>15&&ch.getlocation()<=23){
								x+=35;
								y+=22;	
								ch.setlocation(ch.getlocation()+1);
							}
							else if(ch.getlocation()>23&&ch.getlocation()<=31)	{			
								x-=35;
								y+=22;
								ch.setlocation(ch.getlocation()+1);
							}
							if(ch.getlocation()==8) {
								x=120;
								y=260;
							}
							if(ch.getlocation()==16) {
								x=390;
								y=90;
							}
							if(ch.getlocation()==24) {
								x=688;
								y=310;	
							}

							if(ch.getlocation()==32) {
								x=380;
								y=480;
								ch.setlocation(ch.getlocation()-32);			
							}
							userch.moveTo(x,y);
							if(ch.getlocation()==4) {
								ch.setGain(-10000);

								ch.gameGain.setText(""+ch.getGain());
							}
							if(ch.getlocation()/30==0) {
								ch.setGain(ch.getGain()+300000);

							}

						}
						try {

							writer.writeObject(new ChatMessage(ChatMessage.MsgType.MOVING,user,"",(x+","+y)));
							writer.flush();
						}catch(IOException ioe){
							JOptionPane.showMessageDialog(null, "메시지 전송중 문제가 발생하였습니다.");
							ioe.printStackTrace();
						}
						buildup();	

					}else JOptionPane.showMessageDialog(null, "상대방차례입니다....");
				}
				else JOptionPane.showMessageDialog(null, "게임준비중....");
			}

		});

		add(cScroller);
		add(oScroller);
		add(qScroller);
		add(throwButton);


		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		qScroller.setVisible(false);
		qScroller.setBackground(new Color(255,255,255,200));
		qScroller.setOpaque(false);
		incoming.setBackground(new Color(255,255,255,122));
		outgoing.setBackground(new Color(255,255,255,122));
		counterParts.setBackground(new Color(255,255,255,122));

		cScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		cScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		counterParts.setVisibleRowCount(5);
		counterParts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		counterParts.setFixedCellWidth(100);
		cScroller.setVisible(false);
		cScroller.setBackground(new Color(255,255,255,200));
		cScroller.setOpaque(false);
		//메세지 디스플레이창 

		outgoing.setLineWrap(true);
		outgoing.setWrapStyleWord(true);
		outgoing.setEditable(true);
		oScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		oScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		oScroller.setVisible(false);
		oScroller.setOpaque(false);

		qScroller.setBounds(600, 0, 316, 300);	
		oScroller.setBounds(600, 300, 310, 100);
		cScroller.setBounds(0, 0, 100, 130);

		add(Dice1);
		add(Dice2);
		add(DiceMotion);
		add(countdown);

		DiceMotion.setVisible(false);
		Dice1.setVisible(false);
		Dice2.setVisible(false);
		countdown.setVisible(false);

		Dice1.setBounds(380,350,70,70);
		Dice2.setBounds(430,350,70,70);	
		countdown.setBounds(415,252,35,35);	

	}
	
	public void finishedsetup() {
		finish=true;
		countdown.setVisible(false);
		Dice1.setVisible(false);
		Dice2.setVisible(false);
	}
	//setupGUI끝
	public void paintComponent(Graphics g) {
		super.paintComponents(g);	
		if(whatPanel==0)
			g.drawImage(StartBackground,0,0,null);
		else if(whatPanel==1) g.drawImage(SelectBackground,0,0,null);
		else {	
			g.drawImage(MainBackground,0,0,null);
			topprofile.draw(g);
			bottomprofile.draw(g);
			userch.draw(g);
			userpro.draw(g);

			if(counterPartch!=null)counterPartch.draw(g);
			if(counterPartpro!=null)counterPartpro.draw(g);	
			if(finish) {
				winnerPanel.draw(g);
				winnerPro.draw(g);
				g.setColor(Color.BLACK);
				g.setFont(new Font("Serif",Font.BOLD,25));
				g.drawString(""+winnerName,330,375);
				g.drawString(""+winnerGain,510,305);
				g.drawString(""+winnerMoney,510,260);
			}
		}

		this.repaint();
	}

	public void selectLeft() {
		if(nowSelected==0)
			nowSelected=charactersList.size()-1;
		else nowSelected--;
		ViewCharacters(nowSelected);
	}

	public void selectRight() {
		if(nowSelected==charactersList.size()-1)
			nowSelected=0;
		else nowSelected++;
		ViewCharacters(nowSelected);
	}	

	public void ViewCharacters(int nowSelected) {
		if(nowSelected==0) 
			characterButton.setIcon(new ImageIcon("src/Image/lion.PNG"));
		else if(nowSelected==1)
			characterButton.setIcon(new ImageIcon("src/Image/tube.png"));
		else
			characterButton.setIcon(new ImageIcon("src/Image/neo.png"));	
	}

	public int dice() {

		dice1=(int)(Math.random()*6)+1;
		dice2=(int)(Math.random()*6)+1;

		return dice1+dice2;
	}

	public void roll() {

		dice=dice();
		if(dice1==1)Dice1.setIcon(one);
		if(dice2==1)Dice2.setIcon(one);
		if(dice1==2)Dice1.setIcon(two);
		if(dice2==2)Dice2.setIcon(two);
		if(dice1==3)Dice1.setIcon(three);
		if(dice2==3)Dice2.setIcon(three);
		if(dice1==4)Dice1.setIcon(four);
		if(dice2==4)Dice2.setIcon(four);
		if(dice1==5)Dice1.setIcon(five);
		if(dice2==5)Dice2.setIcon(five);
		if(dice1==6)Dice1.setIcon(six);
		if(dice2==6)Dice2.setIcon(six);
		Dice1.repaint();
		Dice2.repaint();

	}
	public void roll(int d1,int d2) {
		if(d1==1)Dice1.setIcon(one);
		if(d2==1)Dice2.setIcon(one);
		if(d1==2)Dice1.setIcon(two);
		if(d2==2)Dice2.setIcon(two);
		if(d1==3)Dice1.setIcon(three);
		if(d2==3)Dice2.setIcon(three);
		if(d1==4)Dice1.setIcon(four);
		if(d2==4)Dice2.setIcon(four);
		if(d1==5)Dice1.setIcon(five);
		if(d2==5)Dice2.setIcon(five);
		if(d1==6)Dice1.setIcon(six);
		if(d2==6)Dice2.setIcon(six);
		Dice1.repaint();
		Dice2.repaint();

	}
	public void countMotion() {
		 if(turn==14) countdown.setIcon(countten4);
		else if(turn==13) countdown.setIcon(countten3);
		else if(turn==12) countdown.setIcon(countten2);
		else if(turn==11) countdown.setIcon(countten1);
		else if(turn==10) countdown.setIcon(countten);
		else if(turn==9) countdown.setIcon(countnine);
		else if(turn==8) countdown.setIcon(counteight);
		else if(turn==7) countdown.setIcon(countseven);
		else if(turn==6) countdown.setIcon(countsix);
		else if(turn==5) countdown.setIcon(countfive);
		else if(turn==4) countdown.setIcon(countfour);
		else if(turn==3) countdown.setIcon(countthree);
		else if(turn==2) countdown.setIcon(counttwo);
		else if(turn==1) countdown.setIcon(countone);
		else if(turn<=0) { 
			countdown.setIcon(end);
			finishedsetup();
			try {
				if(ch.getMoney()<cp.getMoney())
					writer.writeObject(new ChatMessage(ChatMessage.MsgType.FINISHED,user,cp));
				else 
					writer.writeObject(new ChatMessage(ChatMessage.MsgType.FINISHED,user,ch));
				writer.flush();
			}catch(IOException ioe){
				JOptionPane.showMessageDialog(null, "메시지 전송중 문제가 발생하였습니다.");
				ioe.printStackTrace();
			}
		}
	}

	public void mapInit() {

		build.add(new Map(355,450,1,false)); //베트남
		build.add(new Map(330,430,2,false)); //인도
		build.add(new Map(305,410,3,false)); //중국
		build.add(new Map(235,370,5,false)); //UAE
		build.add(new Map(210,350,6,false)); //이집트
		build.add(new Map(175,330,7,false)); //터키

		build.add(new Map(155,228,9,false)); //일본
		build.add(new Map(190,200,10,false)); //멕시코
		build.add(new Map(220,180,11,false)); //브라질
		build.add(new Map(270,135,13,false)); //호주
		build.add(new Map(300,115,14,false)); //덴마크
		build.add(new Map(340,90,15,false)); //스페인

		build.add(new Map(490,95,17,false)); //스웨덴
		build.add(new Map(520,114,18,false)); //노르웨이
		build.add(new Map(550,140,19,false)); //독일
		build.add(new Map(610,190,21,false)); //러시아
		build.add(new Map(640,210,22,false)); //이탈리아
		build.add(new Map(670,230,23,false)); //네덜란드

		build.add(new Map(650,320,25,false)); //캐나다
		build.add(new Map(615,334,26,false)); //영국
		build.add(new Map(580,366,27,false));//프랑스
		build.add(new Map(530,410,29,false));//미국
		build.add(new Map(490,435,30,false)); //한국
	}
	public void buildup() {

		for(Map m:build) {

			if(m.getlocation()==ch.getlocation()) {
				if(!m.own) {
					if(m.getlocation()>0&&m.getlocation()<=7|| m.getlocation()>15&&m.getlocation()<24) 
					{
						if(colorblue)
							m.setIcon(bluebuild2);
						else m.setIcon(redbuild1);	
					}
					else {
						if(colorblue)
							m.setIcon(bluebuild1);
						else m.setIcon(redbuild2);

					}
					
					bx=m.getX();
					by=m.getY();
					add(m);
					m.own=true;
					m.setBounds(bx,by,50,90);
					try {
						
						writer.writeObject(new ChatMessage(ChatMessage.MsgType.BUILD_UP,user,user,m));
						writer.flush();
					}catch(IOException ioe){
						JOptionPane.showMessageDialog(null, "메시지 전송중 문제가 발생하였습니다.");
						ioe.printStackTrace();
					}
				}else {
					ch.setGain(-10000);
					ch.gameGain.setText(""+ch.getGain());
				}

			}

		}

	
	}

	public void diceMotion() {
		tmp_x =0;
		tmp_y =0;//타이머 x,y좌표
		
		//주사위 모션
		diceMotion=new Timer(50,new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				DiceMotion.setIcon(motion[motion_tmp]);
				DiceMotion.setBounds(tmp_x,tmp_y,256,176);
				tmp_x += 30;
				tmp_y += 30;
				motion_tmp++;	
				if(motion_tmp==6)motion_tmp=0;
				if(tmp_y>300||tmp_x>400) {
					tmp_x=0;tmp_y=0;
					try {
						Thread.sleep(100);
						DiceMotion.setVisible(false);
					} catch (InterruptedException e1) {
						
						e1.printStackTrace();
					}

				}	
			}
		});
	}
	public class IncomingReader implements Runnable {
		public void run() {
			ChatMessage message;             
			ChatMessage.MsgType type;
			try {
				while (true) {
					message = (ChatMessage) reader.readObject();     	 // 서버로기 부터의 메시지 대기                   
					type = message.getType();
					if (type == ChatMessage.MsgType.LOGIN_FAILURE) {	 // 로그인이 실패한 경우라면
						JOptionPane.showMessageDialog(null, "Login이 실패하였습니다. 다시 로그인하세요");

					} else if (type == ChatMessage.MsgType.SERVER_MSG) { // 메시지를 받았다면 보여줌
						if (message.getSender().equals(user)) continue;  // 내가 보낸 편지면 보일 필요 없음
						incoming.append(message.getSender() + " : " + message.getContents() + "\n");
					} else if (type == ChatMessage.MsgType.LOGIN_LIST) {
						// 유저 리스트를 추출 해서 counterParts 리스트에 넣어 줌.
						// 나는  빼고 (""로 만들어 정렬 후 리스트 맨 앞에 오게 함)
						String[] users = message.getContents().split("/");
						for (int i=0; i<users.length; i++) {
							if (user.equals(users[i])) users[i] = "";
						}
						users = sortUsers(users);		// 유저 목록을 쉽게 볼 수 있도록 정렬해서 제공
						users[0] =  ChatMessage.ALL;	// 리스트 맨 앞에 "전체"가 들어가도록 함
						counterParts.setListData(users);
						repaint();
					} else if (type == ChatMessage.MsgType.NO_ACT){
						// 아무 액션이 필요없는 메시지. 그냥 스킵
					}else if(type == ChatMessage.MsgType.COUNTER_PART) {
						cp=message.getCh();

						if(!user.equals(message.getSender())) {
							counterPartch=new PosImageIcon(cp.getGameImage(),x,y,50,80);
							counterPartpro=new PosImageIcon(cp.getProImage(),175,25,70,80);
							cp.gameName.setBounds(190,110,60,20);
							cp.gameGain.setBounds(70,110,100,20);
							cp.gameMoney.setBounds(50,50,100,20);
							add(cp.gameName);
							add(cp.gameMoney);
							add(cp.gameGain);
						}


					}else if(type == ChatMessage.MsgType.MOVING) {
						String tmpCont = message.getContents();
						int tmpX = Integer.parseInt(tmpCont.substring(0,tmpCont.indexOf(",")));
						int tmpY = Integer.parseInt(tmpCont.substring(tmpCont.indexOf(",")+1));

						if(!user.equals(message.getSender())) {							
							counterPartch.moveTo(tmpX, tmpY);
						}

					}
					else if(type == ChatMessage.MsgType.START) {

						if(cp!=null) {
							System.out.println("메세지타입START"+cp);
							startcheck=true;
							myturn=true;
						}
						else {
							startcheck=false;
							myturn=false;
						}
					}else if(type == ChatMessage.MsgType.FINISHED) {
						Characters win = message.getCh();
						winnerPro =new PosImageIcon(win.getProImage(),340,250,70,80);
						winnerName = win.getName();
						winnerMoney = win.getMoney();
						winnerGain = win.getGain();
						finish=true;
						System.out.println("메세지타입FINISHED -- KAKAO");
					}
					else if(type == ChatMessage.MsgType.DICE_NUM) {
						String tmpCont=message.getContents();
						int tmpd1=Integer.parseInt(tmpCont.substring(0,tmpCont.indexOf(",")));
						int tmpd2=Integer.parseInt(tmpCont.substring(tmpCont.indexOf(",")+1,tmpCont.indexOf("/")));
						int tmpgain=Integer.parseInt(tmpCont.substring(tmpCont.indexOf("/")+1));
						roll(tmpd1,tmpd2);
						System.out.println("tmpGain="+tmpgain);
						cp.gameGain.setText(""+tmpgain);
						if(!user.equals(message.getSender())) {
							if(tmpd1==tmpd2) {
								myturn=false;
								BGM doubleSound=new BGM("doubleSound.mp3",false);
								doubleSound.start();
								doubleDice.setVisible(true);
							}
							else {
								myturn=true;
							}
						}
					}
					else if(type==ChatMessage.MsgType.BUILD_UP) {
						String own=message.getSender();
						Map tmpMap=message.getMap();

						if(tmpMap!=null) {
							int tmpX=tmpMap.getX();
							int tmpY=tmpMap.getY();
							//int bindex=build.indexOf(tmpMap);
							add(tmpMap);
							//cpbuild.add(tmpMap);
							tmpMap.setBounds(tmpX,tmpY,50,90);	
						}
						if(!own.equals(user))
						{
							ch.setGain(-100);
							ch.gameGain.setText(ch.getGain()+"");
						}
					}
					else {
						// 정체가 확인되지 않는 이상한 메시지
						throw new Exception("서버에서 알 수 없는 메시지 도착했음");
					}
				} // close while
			} catch(Exception ex) {
				ex.printStackTrace();
				System.out.println("클라이언트 스레드 종료");		// 프레임이 종료될 경우 이를 통해 스레드 종료
			}
		} 
		// close run

		// 주어진 String 배열을 정렬한 새로운 배열 리턴
		private String [] sortUsers(String [] users) {
			String [] outList = new String[users.length];
			ArrayList<String> list = new ArrayList<String>();
			for (String s : users) {
				list.add(s);
			}
			Collections.sort(list);				// Collections.sort를 사용해 한방에 정렬
			for (int i=0; i<users.length; i++) {
				outList[i] = list.get(i);
			}
			return outList;
		}
	} // close inner class     

	private void processLogin() {

		user=JOptionPane.showInputDialog("사용자 이름을 입력하세요");

		try {
			System.out.println("login 메세지 전송중...");
			writer.writeObject(new ChatMessage(ChatMessage.MsgType.LOGIN,user,"",""));
			writer.flush();

		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null,"로그인 중 서버접속에 문제가 발생하였습니다.");
			ex.printStackTrace();
		}
	}
	//ok
	private void processLogout() {
		int choice=JOptionPane.showConfirmDialog(null,"로그아웃합니다.");
		if(choice==JOptionPane.YES_OPTION) {
			try {
				writer.writeObject(new ChatMessage(ChatMessage.MsgType.LOGOUT,user,"",""));

				writer.flush();
				writer.close();
				reader.close();
				sock.close();

			}catch(Exception ex) {
				JOptionPane.showMessageDialog(null,"로그아웃 중 서버접속에 문제가 발생하였습니다. 강제종료!");
				ex.printStackTrace();

			}finally {
				System.exit(100); //클라이언트 완전종료!
			}
		}
	}


}
