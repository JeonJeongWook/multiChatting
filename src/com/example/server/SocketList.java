package com.example.server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class SocketList {
	int findKey = 0;
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
	
	public void deleteUser(String id) {
		for(int i=0; i<user.size();i++) {
			if(user.get(i).equals(id)) {
				System.out.println(i+"번째 값이에요!");
				user.remove(i);
				map.remove(i);
			}
		}
		Collections.sort(user);
		System.out.println("user >> " + user.toString());
		System.out.println("map >> " + map.toString());
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
		return userList;
	}
}
