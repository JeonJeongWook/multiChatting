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
	
	public void addUser(String id) {
		user.add(id);
	}
	
	public int userCount() {
		return map.size();
	}
	
	public ArrayList<Socket> getAll() {
		return map;
	}
	
	public String userList() {
		String userList = "";
		for(int i=0; i<user.size(); i++) {
			userList += "/" + user.get(i);
		}
		System.out.println("userList :: " + userList);
		return userList;
	}
}
