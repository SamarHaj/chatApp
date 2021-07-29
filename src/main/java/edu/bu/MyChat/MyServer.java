package edu.bu.MyChat;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Label;

public class MyServer extends JFrame {
	static Label label; 
     static JTextArea msgArea; 
	private JPanel contentPane;

	private JTextField msgText;
	static ServerSocket ss ; 
	static Socket s ; 
	static DataInputStream scanner; 
	static DataOutputStream printWriter;

	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	/**
	 * Create the frame.
	 */
	public MyServer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("MyServer"); 
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 msgArea = new JTextArea();
		msgArea.setBounds(10, 11, 414, 160);
		contentPane.add(msgArea);
		
		msgText = new JTextField();
		msgText.setBounds(10, 195, 303, 41);
		contentPane.add(msgText);
		msgText.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String msg = ""; 
				msg = msgText.getText().trim(); 
				msgText.setText("");


				try {
					printWriter.writeUTF("Server : " + msg);
					msgArea.setText(msgArea.getText().trim() + "\n" + "Server : " + msg);

				} catch (IOException e) {
					label.setText("sss");
				}
				
				
				
			}
		});
		btnSend.setBounds(324, 204, 89, 23);
		contentPane.add(btnSend);
		
		 label = new Label("");
		label.setBounds(24, 173, 119, 22);
		contentPane.add(label);
	}
	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyServer frame = new MyServer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		String msg = ""; 
		
		ss = new ServerSocket(2000); 
		
	s =	ss.accept(); 

		scanner = new DataInputStream(s.getInputStream()); 
		printWriter = new DataOutputStream(s.getOutputStream()); 
		boolean key = true ; 
		while(key) {
			try {
				msg = scanner.readUTF();
				msgArea.setText(msgArea.getText().trim() + "\n" + msg);

			} catch (IOException e) {
				key = false ;

				label.setText("Client is not in connection");

			}
			System.out.println("you");
		}
	}

	
}
