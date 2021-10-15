import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.mysql.jdbc.ConnectionImpl;

public class Login extends JFrame implements ActionListener {
	
	JLabel lblNomorTlp, lblPassword;
	JTextField txtNomorTlp;
	JPasswordField pfPassword;
	JButton btnLogin, btnReset;
	
	Connection connect;
	Statement statement;
	ResultSet rsSet;
	
	void InitialComponent() {
		//Initialisasi
		
		//JLabel
		lblNomorTlp = new JLabel("Nomor Tlp");
		lblPassword = new JLabel("Password");
		
		//JTextField
		txtNomorTlp = new JTextField();
		
		//JPasswordField
		pfPassword = new JPasswordField();
		
		//JButton
		btnLogin = new JButton("Login");
		btnReset = new JButton("Reset");
		
	}
	
	void Header () {
		//JPanel untuk menampilkan label,textfield, dan password
		JPanel panel = new JPanel(new GridLayout(2,1));
			
		JPanel panel2 = new JPanel(new GridLayout(1,2));
		panel2.add(lblNomorTlp);
		panel2.add(txtNomorTlp);
		panel.add(panel2);
				
		panel2 = new JPanel(new GridLayout(1,2));
		panel2.add(lblPassword);
		panel2.add(pfPassword);
		panel.add(panel2);
				
		add(panel, BorderLayout.CENTER);
	}
	
	void Footer () {
		//JPanel menampilkan untuk button
		JPanel panel = new JPanel(new FlowLayout());
		panel.add(btnLogin);
		panel.add(btnReset);
		
		add(panel, BorderLayout.SOUTH);
		
		//Action Listener untuk melakukan action pindah halaman
		btnLogin.addActionListener(this);
		btnReset.addActionListener(this);
	}
	
	//Untuk menghubungkan dengan database
		void Connection () {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				
				try {
					connect = DriverManager.getConnection("jdbc:mysql://localhost:8080/db uas","root","");
					statement = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		void loginDatabase () {
			 connect = (java.sql.Connection) new Connect ();
			 String role,query;
			 
			 query = "SELECT * FROM user WHERE NoTlp = ' " + txtNomorTlp.getText() + "' AND Password = ' " pfPassword.getText() + "'";
			 
			 //Untuk pembagian role
			 Object con;
			con.rsSet = con.executeQuery(query);
			 try {
				 if(connect.rsSet.next()== true) 
				 {
					 System.out.println("Login Success");
					 
					 if(connect.rsSet.getString("role").equals("User"))
					 {
						role = "Admin";
						JOptionPane.showMessageDialog(this, "Selamat Datang "+Nama.getText());
						setVisible(false);
						new WelcomeFormUser();
					 }
					 else if (connect.rsSet.getString("role").equals("Admin")) {
						 role = "Accountant";
						 JOptionPane.showMessageDialog(this,"Selamat Datang "+Nama.getText());
						 setVisible(false);
						 new WelcomeFormAdmin();
					 }
					
				 }
			 
		}
	
	void InternalFrame() {
		//Set Frame
		setTitle("Login Form");
		setSize(600, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public Login() {
		InitialComponent();
		InternalFrame();
		Header();
		Footer();
		Connection();
	}

	public static void main(String[] args) {
		new Login();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLogin) {
			try {
				if (txtNomorTlp.equals("") || pfPassword.equals("") ){
					//JOption Pane untuk show message dialog
					JOptionPane.showMessageDialog(this, "Nomor Telephone dan Password Harus diisi");
					this.dispose();
				}else {
					JOptionPane.showMessageDialog(this, "User Tidak ditemukan");
					this.dispose();
				}
		}catch (Exception e1) {
			if(e.getSource() == btnLogin) {
				//JOptionPane untuk show message dialog
				JOptionPane.showMessageDialog(this, "Selamat Datang");
				this.dispose();
				new WelcomeFormAdmin();
				new WelcomeFormUser();
		
	}

			}
		}
	}
}

