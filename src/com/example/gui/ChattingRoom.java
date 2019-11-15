package com.example.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChattingRoom extends JFrame{
	public JPanel p_chatting = new JPanel();
	public JPanel p_userList = new JPanel();
	public JPanel chatBar = new JPanel();
	public JLabel[] lb_users = new JLabel[10];
	public JTextArea ta_chatlog = new JTextArea();
	public JTextField tf_chatting = new JTextField(20);
	public JButton btn_send = new JButton("보내기");
	
	public ChattingRoom() {
		p_chatting.setBackground(Color.blue);
		p_userList.setBackground(Color.white);
		
		p_userList.setLayout(new GridLayout(10,1));
		for(int i=0; i<lb_users.length; i++) {
			lb_users[i] = new JLabel(""+i);
			lb_users[i].setPreferredSize(new Dimension(100, 100));
			lb_users[i].setBorder(BorderFactory.createLineBorder(Color.yellow,2));
			p_userList.add(lb_users[i]);
		}
		chatBar.add(tf_chatting);
		chatBar.add(btn_send);
		
		p_chatting.setLayout(new BorderLayout());
		p_chatting.add(ta_chatlog, BorderLayout.CENTER);
		p_chatting.add(chatBar, BorderLayout.SOUTH);
		
		add(p_userList,BorderLayout.EAST);
		add(p_chatting,BorderLayout.CENTER);
		
		btn_send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("보내기를 눌렀습니다");
			}
		});
	}
	
	public void display() {
		setTitle("회원가입");
		setBounds(100, 100, 1000, 700);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
