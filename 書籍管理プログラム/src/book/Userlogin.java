package book;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.EventObject;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Userlogin extends JFrame {

	private JPanel contentPane;
	private JTextField user;
	private JButton LoginButton;
	private JLabel lblId;
	private JLabel lblPass;
	private JButton backButton;
	private JPasswordField pass;
	protected EventObject arg0;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Userlogin frame = new Userlogin();
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
	public Userlogin() {
		setTitle("ユーザーログイン");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		user = new JTextField();
		user.setBounds(171, 59, 96, 19);
		contentPane.add(user);
		user.setColumns(10);

		LoginButton = new JButton("ログイン");
		LoginButton.setFont(new Font("HG丸ｺﾞｼｯｸM-PRO", Font.PLAIN, 12));
		LoginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
            try {
            	Class.forName("com.mysql.cj.jdbc.Driver");
            	Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/library?characterEncording=UTF-8&serverTimezone=JST","root","nishiguchi99");
          	java.sql.Statement stmt=con.createStatement();
          	String sql="Select*from user where id='"+ user.getText()+"'and pass='"+pass.getText().toString() +"'";
          			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				// ユーザIDを起動パラメータに設定
				String[] param = {user.getText()};
				JOptionPane.showMessageDialog(null, "ログインしました。");
				BookSearch shift = new BookSearch();
				BookSearch.main(param);
				if(arg0.getSource() == LoginButton) {
					hide();
				}
			} else
				JOptionPane.showMessageDialog(null, "ユーザIDまたはパスワードに誤りがあります。");
			con.close();

		} catch (Exception e1) {
			System.out.print(e1);
            }

			};
		});
		LoginButton.setBounds(176, 153, 91, 21);
		contentPane.add(LoginButton);

		lblId = new JLabel("ユーザID");
		lblId.setFont(new Font("HG丸ｺﾞｼｯｸM-PRO", Font.PLAIN, 12));
		lblId.setBounds(109, 62, 50, 13);
		contentPane.add(lblId);

		lblPass = new JLabel("PASS");
		lblPass.setFont(new Font("HG丸ｺﾞｼｯｸM-PRO", Font.PLAIN, 12));
		lblPass.setBounds(109, 106, 50, 13);
		contentPane.add(lblPass);

		backButton = new JButton("戻る");
		backButton.setFont(new Font("HG丸ｺﾞｼｯｸM-PRO", Font.PLAIN, 12));
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Topmenu topmenu=new Topmenu();
				topmenu.show();
				hide();
			}
		});
		backButton.setBounds(12, 230, 70, 21);
		contentPane.add(backButton);

		pass = new JPasswordField();
		pass.setBounds(171, 103, 96, 19);
		contentPane.add(pass);

		lblNewLabel = new JLabel("※半角英");
		lblNewLabel.setBounds(279, 62, 70, 13);
		contentPane.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("※半角英数字");
		lblNewLabel_1.setBounds(279, 106, 96, 13);
		contentPane.add(lblNewLabel_1);
	}
}
