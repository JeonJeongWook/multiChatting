package com.example.gui;


import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.example.client.ClientMain;
import com.example.client.ClientSender;

public class Login extends JFrame{
	ClientSender cs;
	public JPanel panel = new JPanel();
	public JTextField tf_id = new JTextField(10); 
	public JTextField tf_pw = new JTextField(10);
	public JButton btn_login = new JButton("로그인");
	public JButton btn_register = new JButton("회원가입");
	public JLabel lb_id = new JLabel("아이디");
	public JLabel lb_pw = new JLabel("패스워드");
	
	public Login(){
		panel.setLayout(new FlowLayout());
		panel.add(lb_id);
		panel.add(tf_id);
		
		panel.add(lb_pw);
		panel.add(tf_pw);
		
		panel.add(btn_login);
		panel.add(btn_register);
		
		add(panel);
		btn_login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("로그인 버튼 눌렸습니다");
				String id = tf_id.getText();
				String pw = tf_pw.getText();
				System.out.println(id + "/" + pw);
				cs.sendMsg("100#" + id + "/" + pw);
				setVisible(false);
			}
		});
		btn_register.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("회원가입 버튼 눌림");
				Register register = new Register();
				register.display();
			}
		});
	}
	
	public void display() {
		setTitle("로그인");
		setBounds(100, 100, 400, 400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setSender(ClientSender cs) {
		this.cs = cs;
	}
	
	public static void main(String[] args) {
		Login login = new Login();
		login.display();
		ClientMain cm = new ClientMain(login);
		cm.connect();
	}
}
