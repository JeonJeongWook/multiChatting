package com.example.server;

import java.net.Socket;
import java.util.ArrayList;

public class SocketList {
	ArrayList<Socket> map;
		
	public SocketList() {
		map = new ArrayList<Socket>();
	}
	
	public void addUser(Socket pw) {
		map.add(pw);
	}
	
	public int userCount() {
		return map.size();
	}
	
	
	public ArrayList<Socket> getAll() {
		return map;
	}
}
