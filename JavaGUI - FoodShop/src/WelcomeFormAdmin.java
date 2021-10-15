import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WelcomeFormAdmin extends JFrame implements ActionListener {
	
	JLabel lblWelcome;
	JButton btnAddUser, btnLogout;
	
	void InitialComponent () {
		//Initialiasi
		
		//JLabel
		lblWelcome = new JLabel("Selamat Datang");
		
		//JButton
		btnAddUser = new JButton("Add User");
		btnLogout = new JButton("Logout");
		
	}
	

	void Center() {
		//JPanel untuk menampilkan button dan label
		JPanel panel = new JPanel(new GridLayout(3,1));
		
		JPanel panel2 = new JPanel(new GridLayout(3,2));
		
		panel2.add(lblWelcome);
		panel2.add(btnAddUser);
		panel2.add(btnLogout);
		panel.add(panel2);
		
		
		add(panel, BorderLayout.CENTER);
		
		//Action Listener untuk melakukan action pindah halaman
		btnAddUser.addActionListener(this);
		btnLogout.addActionListener(this);
	}
	
	void InternalFrame () {
		//Set Frame
		setTitle("Welcome Form");
		setSize(600, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public WelcomeFormAdmin() {
		InitialComponent();
		InternalFrame();
		Center();
		
	}
	
	

	public static void main(String[] args) {
		new WelcomeFormAdmin();

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		//validasi untuk pindah halaman
		if (e.getSource() == btnAddUser) {
			this.dispose();
			new AddUser();
		}
		
		if (e.getSource() == btnLogout) {
			this.dispose();
			new Login();
		}
	}

}
