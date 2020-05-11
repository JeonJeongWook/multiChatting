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
				Socket socket = server.accept();
				System.out.println("연결됨");
				
				//서버에서 클라리언트로 보내는 클래스 생성
				ServerSender ss = new ServerSender(socket);
				
				//클라이언트에서 서버로 받는 클래스 생성
				ServerReader sr = new ServerReader(socket);
				
				//새로운 클라이언트가 접속 시 유저 리스트에 추가
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