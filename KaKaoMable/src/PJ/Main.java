package PJ;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import PJ.KaKaOMable;

public class Main extends JFrame {
	public static final int SCREEN_WIDTH=900;
	public static final int SCREEN_HEIGHT=636;
	KaKaOMable km;
	
	private ObjectOutputStream writer;
	private ObjectInputStream reader;
	private Socket sock;
	
	public static void main(String []args) {
		new Main();
	}
	
	public Main() {
		setupNetworking();
		km=new KaKaOMable(this.writer,this.reader);
		setTitle("FriendsMable");
		setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
		setResizable(false);//âũ�� �� �ٲٰ�
		setLocationRelativeTo(null);//â �����찡���!
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		add(km);
	}
	
	public void setupNetworking() {
	
		try {
			
			sock=new Socket("114.71.208.11",8810);//IP��ȣ�� ��Ʈ��ȣ
			reader=new ObjectInputStream(sock.getInputStream());
			writer=new ObjectOutputStream(sock.getOutputStream());
			System.out.println("SetupNetWorking�Ϸ�!");
		
			
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null,"���� ���ӿ� �����Ͽ����ϴ�.");
			ex.printStackTrace();
			dispose(); //Ŭ���̾�Ʈ ��������
		}
	}
}
