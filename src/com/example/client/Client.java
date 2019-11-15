package com.example.client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
	private static final int PORT = 13579;
	public static void main(String[] args) {
		try {
			//소켓 생성
			Socket socket = new Socket("localhost", PORT);
			
			//스트림 얻어옴
			InputStream stream = socket.getInputStream();
			
			//스트림 래핑
			BufferedReader br = new BufferedReader(new InputStreamReader(stream));
			
			//결과 일긱
			String response = br.readLine();
			System.out.println(response);
			
			socket.close();
			System.exit(0);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
