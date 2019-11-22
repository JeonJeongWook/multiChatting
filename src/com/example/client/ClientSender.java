package com.example.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSender {
	Socket socket;
	PrintWriter pw;
	
	public ClientSender() {}
	
	public ClientSender(Socket socket) throws IOException{
		System.out.println("ready for sending");
		this.socket = socket;
		pw = new PrintWriter(socket.getOutputStream());
	}
	
	public void sendMsg(String msg) {
		pw.println(msg);
		pw.flush();
	}
}
