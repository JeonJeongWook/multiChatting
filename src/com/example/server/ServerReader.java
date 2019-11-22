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
	Connection conn;
	//
	Socket socket;
	BufferedReader br;
	String nickname = "";
	public ServerReader() {
	}
	public ServerReader(Socket socket) {
		System.out.println("serverReader");
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
					System.out.println("from client = "+ msg);
					st = new StringTokenizer(msg, "#");
					tag = Integer.parseInt(st.nextToken());
					content = st.nextToken();
					System.out.println("content = "+content +"/tag = "+tag);
					
					switch(tag) {
						case 100 : 
							break;
						default :
							System.out.println("server Reader default진입");
							break;	
					}
					//ss.sendAll(msg);
				}
			}catch(Exception e) {
				e.printStackTrace();
				break;
			}
		}
	}
	
	//setter
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	public void setSender(ServerSender ss) {
		this.ss = ss;
	}
}
