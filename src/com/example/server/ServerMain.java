package com.example.server;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import com.example.db.Connection;

public class ServerMain {
	private static final int PORT = 13579;
	
	ServerSocket server;
	PrintWriter pw;
	BufferedReader br;
	Connection conn;
	SocketList sl;
	
	public ServerMain() {
		System.out.println("ServerMain 서버시작");
		
	}
	
	public void connection() {
		try {
			conn = new Connection();
			server = new ServerSocket(PORT);
			sl = new SocketList();
			while(true) {
				System.out.println("대기중");
				System.out.println(server.isClosed());
				Socket socket = server.accept();
				System.out.println("연결됨");
				
				ServerSender ss = new ServerSender(socket);
				ServerReader sr = new ServerReader(socket);
				sl.addUser(socket);
				
				sr.setSender(ss);
				sr.setConnection(conn);
				ss.setList(sl);
				sr.setSocketList(sl);
			}
			
			
		} catch (SocketException se) {
			System.out.println("연결 해제");
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ServerMain sm = new ServerMain();
		sm.connection();
	}
}
