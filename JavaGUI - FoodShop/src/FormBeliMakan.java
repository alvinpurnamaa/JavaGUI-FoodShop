import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FormBeliMakan extends JFrame implements ActionListener {

	JFrame frame;
	JLabel lblPilih, lblTotal;
	JTable tabel;
	JButton btnKembali, btnCheckout;

	Vector<String> header;
	Vector<Vector> values;
	
	DefaultTableModel dtm = new DefaultTableModel();
	JTable table = new JTable();
	JPanel panelTabel;
	
	Connection connect;
	Statement statement;
	ResultSet rsSet;
	
	void InitialComponent () {
		//Initialisasi
		
		//JLabel
		lblPilih = new JLabel("Pilih Makanan Anda");
		lblTotal = new JLabel("Total");
		
		//JButton
		btnKembali = new JButton("Kembali");
		btnCheckout = new JButton("Checkout");
		
		//Untuk Pembuatan Table
		header = new Vector<String>();	
		header.add("ID");
		header.add("Jenis Makanan");
		header.add("Nama Makanan");
		header.add("Harga");
		
		header.add("Jenis Makanan");
		header.add("Nama Makanan");
		header.add("Jumlah");
		
		values = new Vector<Vector>();
		
		dtm = new DefaultTableModel();
		dtm.setDataVector(values, header);
		table.setModel(dtm);
				
		panelTabel = new JPanel();
				
		//JScrollPane
		panelTabel.add(new JScrollPane(table));
		add(panelTabel);
	}
	
	void Footer () {
		//Untuk Menampilkan Button
		
		JPanel panel = new JPanel(new GridLayout(1,3));
		
		JPanel panel2 = new JPanel(new GridLayout(1,3));
		panel2.add(lblTotal);
		panel2.add(btnKembali);
		panel2.add(btnCheckout);
		panel.add(panel2);
		
		add(panel, BorderLayout.SOUTH);
		
		//Action Listener untuk melakukan action pindah halaman
		btnKembali.addActionListener(this);
		btnCheckout.addActionListener(this);
		
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
	
	//Untuk menampilkan data ke dalam GUI
	void LoadData () {
		try {
			rsSet = statement.executeQuery("SELECT * FROM food");
			while(rsSet.next()) {
				Vector<String> row = new Vector<String>();
				row.add(rsSet.getString(1));
				row.add(rsSet.getString(2));
				row.add(rsSet.getString(3));
				row.add(rsSet.getString(4));
				
				values.add(row);
				
			}
			
			dtm.setDataVector(values, header);
			table.setModel(dtm);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	void InternalFrame () {
		//Set Frame
		setTitle("Beli Makan");
		setSize(1500, 1000);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public FormBeliMakan() {
		InitialComponent();
		InternalFrame();
		Footer();
		Connection();
		LoadData();
	}

	public static void main(String[] args) {
		new FormBeliMakan();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Validasi untuk pindah halaman
		
		if (e.getSource() == btnKembali) {
			this.dispose();
			new WelcomeFormUser();
		}
	}

}
