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
	Login login;
	
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
					case 100:
						
						break;
						
					default:
						System.out.println("default 진입");
						break;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
	}
	
	public void setLogin(Login login) {
		this.login = login;
	}
}
