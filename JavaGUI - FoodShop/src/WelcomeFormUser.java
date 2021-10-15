import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WelcomeFormUser extends JFrame implements ActionListener {
	
	JLabel lblWelcome;
	JButton btnBeliMakan, btnHistoryTransaksi, btnLogout;
	
	
	void InitialComponent () {
		//Initialiasi
		
		//JLabel
		lblWelcome = new JLabel("Selamat Datang");
				
		btnBeliMakan = new JButton("Beli Makan");
		btnHistoryTransaksi = new JButton("History Transaksi");
		btnLogout = new JButton("Logout");
	}
	
	void Center () {
		//JPanel untuk menampilkan button dan label
		JPanel panel = new JPanel(new GridLayout(4,1));
				
		JPanel panel2 = new JPanel(new GridLayout(4,3));
			
		panel2.add(lblWelcome);
		panel2.add(btnBeliMakan);
		panel2.add(btnHistoryTransaksi);
		panel2.add(btnLogout);
		panel.add(panel2);
				
				
		add(panel, BorderLayout.CENTER);
		
		//Action Listener untuk melakukan action pindah halaman
		btnBeliMakan.addActionListener(this);
		btnHistoryTransaksi.addActionListener(this);
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
	
	
	public WelcomeFormUser() {
		InitialComponent();
		InternalFrame();
		Center();
	}

	public static void main(String[] args) {
		new WelcomeFormUser();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//validasi untuk pindah halaman
		if (e.getSource() == btnBeliMakan) {
			this.dispose();
			new FormBeliMakan();
		}
		
		if (e.getSource() == btnHistoryTransaksi) {
			this.dispose();
			new HistoryTransaksi();
		}
		
		if (e.getSource() == btnLogout) {
			this.dispose();
			new Login();
		}
		
	}

}
