package com.example.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

import com.example.gui.Login;

public class ClientReader implements Runnable {
	Socket socket;
	BufferedReader br;
	PrintWriter pw;
	public Login login;
	
	public ClientReader() {}
	public ClientReader(Socket socket) {
		this.socket = socket;
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Thread thread = new Thread(this);
			thread.start();
			System.out.println("쓰레드 실행");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setLogin(Login login) {
		this.login = login;
	}
	
	@Override
	public void run() {
		StringTokenizer st;
		String msg = "";
		int tag = 0;
		String content;
		while(true) {
			try {
				if((msg = br.readLine()) != null) {
					st = new StringTokenizer(msg, "#");
					tag = Integer.parseInt(st.nextToken());
					content = st.nextToken();
					
					switch (tag) {
						case 101:
							loginAuth();
							break;
						case 109:
							loginFail();
							break;
							
						case 111:	//회원가입 성공
							registerResult(content);
							break;
						case 119:	//회원가입 실패
							registerResult(content);
							break;
							
						case 201:
							receiveChat(content);
							break;
							
							
						default:
							System.out.println("default 진입");
							break;
					}
					System.out.println("content >> " + content);
				}
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
	}
	
	
	public void loginAuth() {
		login.setVisible(false);
		login.enterRoom();
	}
	
	public void loginFail() {
		login.loginFail();
	}
	
	public void registerResult(String content) {
		login.register.registerResult(content);
	}
	
	private void receiveChat(String content) {
		login.chat.receiveChat(content);
	}
}
