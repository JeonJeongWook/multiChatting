package com.example.server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class SocketList {
	ArrayList<Socket> map;
	ArrayList<String> user;
	public SocketList() {
		map = new ArrayList<Socket>();
		user = new ArrayList<String>();
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
