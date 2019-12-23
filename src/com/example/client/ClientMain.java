package com.example.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import com.example.gui.Login;

public class ClientMain {
	private static final int PORT = 13579;
	private ClientMain cm;
	public Socket socket;
	PrintWriter pw;
	BufferedReader br;
	
	Login login;
	public ClientMain() {
		
	}
	
	public ClientMain(Login login) {
		this.login = login;
	}
	public void connect() {
		try {
			socket = new Socket("localhost", PORT);
			ClientReader cr = new ClientReader(socket);
			ClientSender cs = new ClientSender(socket);
			
			System.out.println(socket.getInetAddress());
			login.setSender(cs);
			cr.setLogin(login);
			cr.setSender(cs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setClient(ClientMain cm) {
		this.cm = cm;
	}
}
