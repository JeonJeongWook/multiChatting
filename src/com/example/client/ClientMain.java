package com.example.client;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.example.gui.Login;

public class ClientMain {
	private static final int PORT = 13579;
	Socket socket;
	PrintWriter pw;
	BufferedReader br;
	
	Login login;
	public ClientMain() {
		
	}
	
	public ClientMain(Login login) {
		this.login = login;
	}
	public void connect() {
		System.out.println("ClientMain");
		try {
			socket = new Socket("localhost", PORT);
			ClientReader cr = new ClientReader(socket);
			ClientSender cs = new ClientSender(socket);
			login.setSender(cs);
			cr.setLogin(login);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
