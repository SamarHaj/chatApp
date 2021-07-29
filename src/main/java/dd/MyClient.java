package dd;

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
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JScrollBar;

public class MyClient extends JFrame {

	private JPanel contentPane;
	private JTextField msgText;
	static Socket s ; 
	static DataInputStream scanner; 
	static DataOutputStream printWriter;
	static JTextArea msgArea ; 
	private static JLabel lblNewLabel;

	/**
	 * Launch the application.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws IOException  {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyClient frame = new MyClient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		String msg = ""; 
		boolean key = true ; 
		try {
			s = new Socket("127.0.0.1" , 2000);

			scanner = new DataInputStream(s.getInputStream()); 
		printWriter = new DataOutputStream(s.getOutputStream()); 

		while(key) {
			msg = scanner.readUTF();
			msgArea.setText(msgArea.getText().trim() + "\n" + msg);

			
		}
		} catch (UnknownHostException e) {
			lblNewLabel.setText("No connection");

		} catch (IOException e) {
			key = false ; 
			lblNewLabel.setText("No connection");

		} 
	
		
	}

	/**
	 * Create the frame.
	 */
	public MyClient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("MyClient");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 msgArea = new JTextArea();
		msgArea.setBounds(24, 22, 376, 139);
		contentPane.add(msgArea);
		
		msgText = new JTextField();
		
		msgText.setBounds(10, 197, 321, 43);
		contentPane.add(msgText);
		msgText.setColumns(10);
		
		JButton send = new JButton("send");
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg = ""; 
				msg = msgText.getText().trim(); 
				msgText.setText("");
				try {
					printWriter.writeUTF("Client : " + msg);
					
					msgArea.setText(msgArea.getText().trim() + "\n" + "Client : " + msg);

				} catch (IOException e1) {
					lblNewLabel.setText("sss");
				}
			}
		});
		send.setBounds(341, 207, 89, 23);
		contentPane.add(send);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(20, 172, 106, 23);
		contentPane.add(lblNewLabel);
	}
}
