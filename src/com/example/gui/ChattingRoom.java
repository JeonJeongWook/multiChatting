package com.example.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChattingRoom extends JFrame{
	public JPanel p_chatting = new JPanel();
	public JPanel p_userList = new JPanel();
	public JLabel lb_users[] = new JLabel[10];	//유저 10명
	public JTextArea ta_chatlog = new JTextArea();
	public JTextField tf_chatting = new JTextField(20);
	public JButton btn_send = new JButton("보내기");
	
	public ChattingRoom() {
		p_chatting.setBackground(Color.blue);
		p_userList.setBackground(Color.white);
		
		p_userList.setLayout(new GridLayout(10,1));
		
		add(p_userList,BorderLayout.EAST);
		add(p_chatting,BorderLayout.CENTER);
		
	}
	
	public void display() {
		setTitle("회원가입");
		setBounds(100, 100, 1000, 700);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
