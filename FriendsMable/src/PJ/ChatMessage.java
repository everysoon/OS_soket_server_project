package PJ;
import java.io.Serializable;

public class ChatMessage implements Serializable {
	// �޽��� Ÿ�� ����
	// 1���� �޽��� ���� �ʵ�� 3���� String�� �ʵ�.
	// NO_ACT�� ������ �� �ִ� Dummy �޽���. ������ ������ ����ϱ� ���� ����� ����
	// (1) Ŭ���̾�Ʈ�� ������ �޽��� ����
	//	- LOGIN  : CLIENT �α���.
	//		�޽��� ���� : LOGIN, "�۽���", "", ""
	//	- LOGOUT : CLIENT �α׾ƿ�.
	//		�޽��� ���� : LOGOUT, "�۽���", "", ""
	// 	- CLIENT_MSG : �������� ������  ��ȭ .
	// 		�޽�������  : CLIENT_MSG, "�۽���", "������", "����"
	// (2) ������ ������ �޽��� ����
	// 	- LOGIN_FAILURE  : �α��� ����
	//		�޽��� ���� : LOGIN_FAILURE, "", "", "�α��� ���� ����"
	// 	- SERVER_MSG : Ŭ���̾�Ʈ���� �������� ������ ��ȭ 
	//		�޽�������  : SERVER_MSG, "�۽���", "", "����" 
	// 	- LOGIN_LIST : ���� �α����� ����� ����Ʈ.
	//		�޽��� ���� : LOGIN_LIST, "", "", "/�� ���е� ����� ����Ʈ"
	public enum MsgType {NO_ACT, LOGIN, LOGOUT, CLIENT_MSG, LOGIN_FAILURE, SERVER_MSG, LOGIN_LIST
							,MOVING,DICE_NUM,BUILD_UP,COUNTER_PART,START,FINISHED};
							//DICE_NUM=turn����
							//MOVING=����ɸ��ͱ׸���
							//BUILD_UP=�ǹ��׸���/�ǹ�����������/���ǰǹ��̸� ���P���
	public static final String ALL = "��ü";	 // ����� �� �� �ڽ��� ������ ��� �α��εǾ� �ִ�
											 // ����ڸ� ��Ÿ���� �ĺ���
	private MsgType type;
	private String sender;
	private String receiver;
	private String contents;
	private Characters ch;
	private Map m;

	public ChatMessage() {
		this(MsgType.NO_ACT, "", "", "");
	}
	public ChatMessage(MsgType t, String sID, String rID, String mesg) {
		type = t;
		sender = sID;
		receiver = rID;
		contents = mesg;
	}
	public ChatMessage(MsgType t, String sID,Characters ch) {
		type = t;
		sender = sID;
		this.ch=ch;
	}
	public ChatMessage(MsgType t, String sID,String mesg,Map map) {
		type = t;
		sender = sID;
		contents=mesg;
		m=map;
	
	}
	
	public Map getMap() {
		return m;
	}
	public Characters getCh() {
		return ch;
	}
	public void setType (MsgType t) {
		type = t;
	}
	public MsgType getType() {
		return type;
	}

	public void setSender (String id) {
		sender = id;
	}
	public String getSender() {
		return sender;
	}
	
	public void setReceiver (String id) {
		receiver = id;
	}
	public String getReceiver() {
		return receiver;
	}
	
	public void setContents (String mesg) {
		contents = mesg;
	}
	public String getContents() {
		return contents;
	}
	
	public String toString() {
		return ("�޽��� ���� : " + type + "\n" +
				"�۽���         : " + sender + "\n" +
				"������         : " + receiver + "\n" +
				"�޽��� ���� : " + contents + "\n");
	}
}