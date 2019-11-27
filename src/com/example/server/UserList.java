package com.example.server;

public class UserList {
	public String userList[] = new String[10];

	public UserList(){ 
		
	}
	
	public void addUser(String id) {
		for(int i=0; i<userList.length; i++) {
			System.out.println(i);
		}
	}
}
