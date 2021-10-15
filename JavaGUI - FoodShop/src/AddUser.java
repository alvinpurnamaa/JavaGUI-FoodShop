import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AddUser extends JFrame implements ActionListener{

	JLabel lblIDUser, lblNoTlp, lblPassword;
	JTextField txtNoTlp;
	JPasswordField pfPassword;
	JButton btnAddUser, btnKembali;
	
	void InititalComponent() {
		//Initialisasi
		
		//JLabel
		lblIDUser = new JLabel();
		
		//JTextField
		txtNoTlp = new JTextField();
		
		//JPasswordField
		pfPassword = new JPasswordField();
		
		//JButton
		btnAddUser = new JButton("Add User");
		btnKembali = new JButton("Kembali");
		
	}
	
	void Center () {
		//JPanel untuk menampilkan label,textfield, dan button
		
		JPanel panel = new JPanel(new GridLayout(4,1));
		
		JPanel panel2 = new JPanel(new GridLayout(1,2));
		panel2.add(lblIDUser);
		panel.add(panel2);
				
		panel2 = new JPanel(new GridLayout(1,2));
		panel2.add(lblNoTlp);
		panel2.add(txtNoTlp);
		panel.add(panel2);
		
		panel2 = new JPanel(new GridLayout(1,2));
		panel2.add(lblPassword);
		panel2.add(pfPassword);
		panel.add(panel2);
		
		panel2 = new JPanel(new GridLayout(1,2));
		panel2.add(btnAddUser);
		panel2.add(btnKembali);
		panel.add(panel2);
		
		add(panel, BorderLayout.CENTER);
		
		//Action Listener untuk melakukan action pindah halaman
		btnAddUser.addActionListener(this);
		btnKembali.addActionListener(this);
						
		
	}
	void InternalFrame () {
		//Set Frame
		setTitle("Add User");
		setSize(600, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
		
	public AddUser() {
		InititalComponent();
		InternalFrame();
		Center();
		
	}

	public static void main(String[] args) {
		new AddUser();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//validasi untuk pindah halaman
		
		if (e.getSource() == btnKembali) {
			this.dispose();
			new WelcomeFormAdmin();
		}
		
		if (e.getSource() == btnAddUser) {
				try {
					if (txtNoTlp.equals("") || pfPassword.equals("") ){
						//JOption Pane untuk show message dialog
						JOptionPane.showMessageDialog(this, "Nomor Telephone dan Password Harus diisi");
						this.dispose();
					}if (txtNoTlp.equals("12 Number"))  {
						JOptionPane.showMessageDialog(this, "No Telepon harus terdiri dari 12 angka");
						this.dispose();
					}
					
				}
		
		}
	
		
	


