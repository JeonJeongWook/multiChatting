package com.example.server;

import java.util.ArrayList;
import java.util.List;

public class UserList {
	ArrayList<String> user; 
	
	public UserList(){ 
		user = new ArrayList<String>();
	}
	
	public void addUser(String name) {
		user.add(name);
	}
	
	public int countUser() {
		return user.size();
	}
	
	public List<String> getUserList(){
		List<String> list = new ArrayList<String>();
		for(int i=0; i<user.size(); i++) {
			list.add(user.get(i));
		}
		return list;
	}
}
