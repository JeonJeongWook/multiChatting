package com.example.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.JTextPane;
import javax.swing.SpringLayout.Constraints;
import javax.swing.text.AttributeSet;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyleConstants.FontConstants;

import com.example.client.ClientSender;

public class ChattingRoom extends JFrame implements KeyListener {
	ClientSender cs;
	public JPanel p_chatting = new JPanel();
	public JPanel p_userList = new JPanel();
	public JPanel chatBar = new JPanel();
	public JLabel[] lb_users = new JLabel[10];
	public JTextField tf_chatting = new JTextField(20);
	public JButton btn_send = new JButton("보내기");
	Font normal = new Font("굴림", Font.PLAIN, 20);
	private String nick = "";
	
	DefaultStyledDocument doc = new DefaultStyledDocument();
	JTextPane tp_chatlog = new JTextPane(doc);
	StyleContext context = new StyleContext();
	
	Style style = context.addStyle("system", null);
	
	
	
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
		
//		ta_chatlog.setEditable(false);	//textarea 수정 막음
//		ta_chatlog.setFont(normal);
		
		tf_chatting.addKeyListener(this);	//채팅창에서 엔터 클릭 시 기능 
		
		chatBar.add(tf_chatting);
		chatBar.add(btn_send);
		
		p_chatting.setLayout(new BorderLayout());
//		p_chatting.add(ta_chatlog, BorderLayout.CENTER);
		p_chatting.add(tp_chatlog, BorderLayout.CENTER);
		p_chatting.add(chatBar, BorderLayout.SOUTH);
		
		add(p_userList,BorderLayout.EAST);
		add(p_chatting,BorderLayout.CENTER);
		
		//[보내기]버튼 클릭시 작동
		btn_send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sendMsg();
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
		System.out.println("display 실행");
		setTitle("회원가입");
		setBounds(100, 100, 400, 400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			sendMsg();
		}
	}

	private void sendMsg() {
		String msg = tf_chatting.getText();
		cs.sendMsg("210#" + nick + "/" + msg);
		tf_chatting.setText("");
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
		appendToPane(tp_chatlog, content, Color.BLACK, false);
	}

	public void systemChat(String content) {
		appendToPane(tp_chatlog, content, Color.RED, true);
	}
	
	public void setId(String content) {
		this.nick = content;
	}
	
	private void appendToPane(JTextPane tp, String msg, Color c, boolean bold)
    {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "돋움");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_LEFT);
        aset = sc.addAttribute(aset, StyleConstants.Bold, bold);
        
        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        tp.replaceSelection(msg+"\n");
    }
}
