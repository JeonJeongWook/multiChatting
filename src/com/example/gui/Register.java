package com.example.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.example.client.ClientSender;

public class Register extends JFrame{
	ClientSender cs;
	public JPanel panel = new JPanel();
	public JLabel lb_id = new JLabel("아이디");
	public JLabel lb_pw = new JLabel("패스워드");
	public JLabel lb_pwCheck = new JLabel("패스워드 체크");
	public JTextField tf_id = new JTextField(10);
	public JTextField tf_pw = new JTextField(10);
	public JTextField tf_pwCheck = new JTextField(10);
	public JButton btn_register = new JButton("회원가입");
	public JButton btn_back = new JButton("돌아가기");
	public Register(){
		panel.setLayout(new FlowLayout());
		panel.add(lb_id);
		panel.add(tf_id);
		
		panel.add(lb_pw);
		panel.add(tf_pw);
		
		panel.add(lb_pwCheck);
		panel.add(tf_pwCheck);
		
		panel.add(btn_register);
		panel.add(btn_back);
		add(panel);
		
		//회원가입 버튼
		btn_register.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("회원가입 클릭");
				String id = tf_id.getText();
				String pw = tf_pw.getText();
				cs.sendMsg("110#"+id+"/"+pw);
			}
		});
		
		btn_back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("돌아가기 버튼");
				dispose();
			}
		});
	}
	
	public void display() {
		setTitle("회원가입");
		setBounds(100, 100, 400, 400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void registerResult(String content) {
		JOptionPane.showMessageDialog(null, content);
	}
	
	public void setSender(ClientSender cs) {
		this.cs = cs;
	}
}
