package book;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Managerlogin extends JFrame {

	private JPanel contentPane;
	private JButton btnNewButton_2;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField manager;

	private JButton button;
	private JPasswordField manager_pass;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Managerlogin frame = new Managerlogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Managerlogin() {
		setTitle("管理者ログイン");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnNewButton_2 = new JButton("ログイン");
		btnNewButton_2.setFont(new Font("HG丸ｺﾞｼｯｸM-PRO", Font.PLAIN, 12));
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
	            	Class.forName("com.mysql.cj.jdbc.Driver");
	            	Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/library?characterEncording=UTF-8&serverTimezone=JST","root","nishiguchi99");
	          	java.sql.Statement stmt=con.createStatement();
	          	String sql="Select*from manager where manager_id='"+ manager.getText()+"'and manager_pass='"+manager_pass.getText().toString() +"'";
	          			ResultSet rs = stmt.executeQuery(sql);
				if(rs.next()) {
					String[] param = {manager.getText()};
					JOptionPane.showMessageDialog(null, "ログインしました。");
					ManagerSerch shift = new ManagerSerch();
					ManagerSerch.main(param);
					if(arg0.getSource() == btnNewButton_2) {
						hide();
					}
				} else
					JOptionPane.showMessageDialog(null, "ユーザIDまたはパスワードに誤りがあります。");
				con.close();

			} catch (Exception e1) {
				System.out.print(e1);
			}
			}
		});
		btnNewButton_2.setBounds(162, 192, 91, 21);
		contentPane.add(btnNewButton_2);

		lblNewLabel = new JLabel("管理者ID");
		lblNewLabel.setFont(new Font("HG丸ｺﾞｼｯｸM-PRO", Font.PLAIN, 12));
		lblNewLabel.setBounds(100, 67, 50, 13);
		contentPane.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("PASS");
		lblNewLabel_1.setFont(new Font("HG丸ｺﾞｼｯｸM-PRO", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(100, 129, 50, 13);
		contentPane.add(lblNewLabel_1);

		manager = new JTextField();
		manager.setBounds(157, 64, 96, 19);
		contentPane.add(manager);
		manager.setColumns(10);

		button = new JButton("戻る");
		button.setFont(new Font("HG丸ｺﾞｼｯｸM-PRO", Font.PLAIN, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Topmenu shift = new Topmenu();
				Topmenu.main(null);
				if(e.getSource() == button) {
					hide();
				}
			}
		});
		button.setBounds(12, 230, 67, 21);
		contentPane.add(button);

		manager_pass = new JPasswordField();
		manager_pass.setBounds(157, 126, 96, 19);
		contentPane.add(manager_pass);

		lblNewLabel_2 = new JLabel("※半角英");
		lblNewLabel_2.setBounds(265, 67, 76, 13);
		contentPane.add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("※半角英数字");
		lblNewLabel_3.setBounds(265, 129, 91, 13);
		contentPane.add(lblNewLabel_3);
	}
}
