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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class HistoryTransaksi extends JFrame implements ActionListener{
	
	JButton btnKembali;
	
	Vector<String> header;
	Vector<Vector> values;
	
	DefaultTableModel dtm = new DefaultTableModel();
	JTable table = new JTable();
	JPanel panelTabel;
	
	Connection connect;
	Statement statement;
	ResultSet rsSet;
	
	void InitialComponent() {
		//Inisialisasi
		//JButton
		btnKembali = new JButton("Kembali");
				
		//JTable pembuatan table
		DefaultTableModel dtm = new DefaultTableModel();
		JTable table = new JTable();
		JPanel panelTabel;
							
		panelTabel = new JPanel();
								
		header = new Vector<String>();
		values = new Vector<Vector>();
							
		header.add("ID Transaksi");
		header.add("Total Harga");
		header.add("Date");
							
		dtm.setDataVector(values, header);
		table.setModel(dtm);
								
		//JScrollPane
		panelTabel.add(new JScrollPane(table));
		add(panelTabel);
	}
	
	void Footer() {
		//JPanel untuk menampilkan button
		JPanel panel = new JPanel(new GridLayout(1,1));
		
		JPanel panel2 = new JPanel(new GridLayout(1,1));
		panel2.add(btnKembali);
		panel.add(panel2);	
		
		add(panel, BorderLayout.SOUTH);
		
		btnKembali.addActionListener(this);
		
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
				rsSet = statement.executeQuery("SELECT * FROM headertransaction");
				while(rsSet.next()) {
					Vector<String> row = new Vector<String>();
					row.add(rsSet.getString(1));
					row.add(rsSet.getString(2));
					row.add(rsSet.getString(3));
					row.add(rsSet.getString(4));
					row.add(rsSet.getString(5));
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
		setTitle("History Transaksi");
		setSize(600, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public HistoryTransaksi() {
		InitialComponent();
		InternalFrame();
		Footer();
		Connection();
		LoadData();
	}

	public static void main(String[] args) {
		new HistoryTransaksi();

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
