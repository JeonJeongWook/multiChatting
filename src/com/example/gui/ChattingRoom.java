package com.example.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.example.client.ClientSender;

public class ChattingRoom extends JFrame implements KeyListener {
	ClientSender cs;
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
			lb_users[i] = new JLabel("");
			lb_users[i].setPreferredSize(new Dimension(100, 100));
			lb_users[i].setBorder(BorderFactory.createLineBorder(Color.yellow,2));
			p_userList.add(lb_users[i]);
		}
		
		ta_chatlog.setEditable(false);	//textarea 수정 막음
		tf_chatting.addKeyListener(this);	//채팅창에서 엔터 클릭 시 기능 
		
		chatBar.add(tf_chatting);
		chatBar.add(btn_send);
		
		p_chatting.setLayout(new BorderLayout());
		p_chatting.add(ta_chatlog, BorderLayout.CENTER);
		p_chatting.add(chatBar, BorderLayout.SOUTH);
		
		add(p_userList,BorderLayout.EAST);
		add(p_chatting,BorderLayout.CENTER);
		
		//[보내기]버튼 클릭시 작동
		btn_send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("보내기를 눌렀습니다");
				String msg = tf_chatting.getText();
//				ta_chatlog.append(tf_chatting.getText() + "\n");
				cs.sendMsg("200#"+msg);
				tf_chatting.setText("");
			}
		});
		
		//창이 열리면 자동으로 텍스트 필드에 포커스를 줌
		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				tf_chatting.requestFocus();
			}
		});
	}
	
	public void display() {
		setTitle("회원가입");
		setBounds(100, 100, 1000, 700);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			System.out.println("엔터키를 눌렀습니다");
			String msg = tf_chatting.getText();
//			ta_chatlog.append(tf_chatting.getText() + "\n");
			cs.sendMsg("210#"+msg);
			tf_chatting.setText("");
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	
	public void setSender(ClientSender cs) {
		this.cs = cs;
	}
	
	public void receiveChat(String content) {
		ta_chatlog.append(content+"\n");
	}
	
	public void joinedUsers(List<String> list) {
		for(int i=0; i<list.size(); i++) {
			lb_users[i].setText(list.get(i));
		}
	}
	
	public void joinUser(int seat, String id) {
		lb_users[seat].setText(id);
	}
}
