package com.example.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

import com.example.gui.ChattingRoom;
import com.example.gui.Login;

public class ClientReader implements Runnable {
	Socket socket;
	BufferedReader br;
	PrintWriter pw;
	ClientSender cs;
	public Login login;
	public ChattingRoom chat;
	
	boolean connection = true;
	
	public ClientReader() {}
	public ClientReader(Socket socket) {
		this.socket = socket;
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Thread thread = new Thread(this);
			thread.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setLogin(Login login) {
		this.login = login;
	}
	public void setSender(ClientSender cs) {
		this.cs = cs;
	}
	
	@Override
	public void run() {
		StringTokenizer st;
		String msg = "";
		int tag = 0;
		String content = "";
		do {
			try {
				if((msg = br.readLine()) != null) {
					st = new StringTokenizer(msg, "#");
					tag = Integer.parseInt(st.nextToken());
					if(st.countTokens() != 0)
						content = st.nextToken();

					System.out.println("tag :: " + tag);
					System.out.println("content :: " + content);
					switch (tag) {
						case 111:
							loginAuth(content);
							break;
						case 119:
							loginFail();
							break;
							
						case 101:	//회원가입 성공
							registerResult(content);
							break;
						case 109:	//회원가입 실패
							registerResult(content);
							break;
							
						case 201:
							setUserList(content);
							break;
						case 211:
							receiveChat(content);
							break;
							
						case 300:
							welcomeUser(content);
							break;
						case 301:
							exitUser(content);
							
//							socket.close();
							break;
							
						default:
							System.out.println("default 진입");
							break;
					}
					System.out.println("==================");
				}
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}while(connection);
	}
	
	
	public void loginAuth(String content) {
		login.setVisible(false);
		chat = new ChattingRoom();
		chat.display();
		chat.setSender(cs);
		chat.setId(content);
		chat.setSocket(socket);
		cs.sendMsg("200#setUserList");
	}
	
	public void loginFail() {
		login.loginFail();
	}
	
	public void registerResult(String content) {
		login.register.registerResult(content);
	}
	
	private void setUserList(String content) {
		chat.setUserNull();
		StringTokenizer st = new StringTokenizer(content, "/");
		int seat = Integer.parseInt(st.nextToken());
		for(int i=0; i<seat; i++) {
			System.out.println("i : " + i + " / seat : " + seat);
			String id = st.nextToken();
			chat.lb_users[i].setText(id);
		}
	}
	
	//채팅 받기
	private void receiveChat(String content) {
		chat.receiveChat(content);
	}
	
	private void welcomeUser(String content) {
		chat.systemChat(content);
	}
	
	private void exitUser(String content) {
		chat.systemChat(content);
//		connection = false;
	}
}
