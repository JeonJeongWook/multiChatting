package com.example.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import java.util.StringTokenizer;

import com.example.db.Connection;

public class ServerReader implements Runnable{
	//class
	ServerSender ss;
	Connection db;
	//
	Socket socket;
	BufferedReader br;
	String nickname = "";
	public ServerReader() {
	}
	public ServerReader(Socket socket) {
		try {
			this.socket = socket;
			
			///
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			// Reader 만들고 Thread 생성
			Thread t = new Thread(this);
			t.start();
			System.out.println("Create Receive");
			
		} catch(SocketException se) {
			System.out.println("disconnected");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		StringTokenizer st;
		int tag = 0;
		String msg = "";
		String content = "";
		while(true) {
			try {
				if((msg = br.readLine())!= null) {
					System.out.println("클라이언트가 보낸 메시지 : "+ msg);
					st = new StringTokenizer(msg, "#");
					tag = Integer.parseInt(st.nextToken());
					content = st.nextToken();
					System.out.println("content = "+content +"/tag = "+tag);
					
					switch(tag) {
						case 100 : 
							doLogin(content);
							break;
						case 110 :
							doRegister(content);
							break;
						default :
							System.out.println("server Reader default진입");
							break;	
					}
					//ss.sendAll(msg);
					System.out.println("==================");
				}
			}catch(Exception e) {
				e.printStackTrace();
				break;
			}
		}
	}
	private void doLogin(String content) {
		System.out.println("login실행");
		StringTokenizer st = new StringTokenizer(content, "/");
		String id = st.nextToken();
		String pw = st.nextToken();
		String result = db.doLogin(id, pw);
		String tag = "";
		if(result.equals("")) {
			tag = "109#";
		}else {
			tag = "101#";
		}
		ss.sendMsg(tag + result);
	}
	
	private void doRegister(String content) {
		System.out.println("doRegister 실행");
		StringTokenizer st = new StringTokenizer(content, "/");
		String id = st.nextToken();
		String pw = st.nextToken();
		String tag = "";
		String msg = "";
		System.out.println(id + "/" + pw);
		int check = db.doRegister(id, pw);
		if(check == 1) {
			System.out.println("회원가입 완료");
			tag = "111#";
			msg = "success";
		}else {
			System.out.println("가입 실패");
			tag = "119#";
			msg = "가입 실패";
		}
		
		ss.sendMsg(tag+msg);
	}
	
	
	//setter
	public void setConnection(Connection db) {
		this.db = db;
	}
	public void setSender(ServerSender ss) {
		this.ss = ss;
	}
}
