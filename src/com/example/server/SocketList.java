package com.example.server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class SocketList {
	ArrayList<Socket> map;
	
	int num = 0;
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
