package com.example.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ServerSender {
	SocketList sl;
	Socket socket;
	PrintWriter pw ;
	ArrayList<Socket> map;
	
	public ServerSender() {}
	public ServerSender(Socket socket) {
		this.socket = socket;
		
		try {
			pw = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void sendMsg(String msg) {
		pw.println(msg);
		pw.flush();
	}
	
	public void sendAll(String msg) {
		map = sl.getAll();
		for(int i = 0; i<map.size();i++) {
			try {
				pw = new PrintWriter(map.get(i).getOutputStream());
				pw.println(msg);
				pw.flush();
				
			} catch (IOException e) {
				// TODO Auto-generated c atch block
				e.printStackTrace();
			}
		}
	}
	
	public void setList(SocketList sl) {
		this.sl = sl;
	}
}
