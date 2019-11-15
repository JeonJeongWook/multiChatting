package com.example.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private static final int PORT = 13579;
	public static void main(String[] args) {
		try {
			//서버생성
			ServerSocket serverSocket = new ServerSocket(PORT);
			
			//서버 종료까지 무한루프
			while(true) {
				//소켓 접속 요청 대기
				Socket socket = serverSocket.accept();
				try {
					OutputStream stream = socket.getOutputStream();
				}catch (Exception e) {
					e.printStackTrace();
				}finally {
					socket.close();
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
