package PJ;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;



public class Main extends JFrame {
	public static final int SCREEN_WIDTH=900;
	public static final int SCREEN_HEIGHT=636;
	FriendsMable fm;
	
	private ObjectOutputStream writer;
	private ObjectInputStream reader;
	private Socket sock;
	
	public static void main(String []args) {
		new Main();
	}
	
	public Main() {
		setupNetworking();
		fm=new FriendsMable(this.writer,this.reader);
		setTitle("KaKaoMable");
		setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
		setResizable(false);//âũ�� �� �ٲٰ�
		setLocationRelativeTo(null);//â �����찡���!
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		add(fm);
	}
	
	public void setupNetworking() {
	
		try {
			sock=new Socket("127.0.0.1",8810);//IP��ȣ�� ��Ʈ��ȣ
			reader=new ObjectInputStream(sock.getInputStream());
			writer=new ObjectOutputStream(sock.getOutputStream());
	
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null,"���� ���ӿ� �����Ͽ����ϴ�.");
			ex.printStackTrace();
			dispose(); //Ŭ���̾�Ʈ ��������
		}
	}
}
