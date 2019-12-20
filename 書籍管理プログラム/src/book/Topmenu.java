package book;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Topmenu extends JFrame {

	private JPanel contentPane;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Topmenu frame = new Topmenu();
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
	public Topmenu() {
		setTitle("書籍管理システム");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnNewButton = new JButton("一般ユーザ");
		btnNewButton.setFont(new Font("HG丸ｺﾞｼｯｸM-PRO", Font.BOLD, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Userlogin shift=new Userlogin();
				Userlogin.main(null);
				if(e.getSource()==btnNewButton) {
					hide();
			}
			}
		});
		btnNewButton.setBounds(75, 85, 100, 68);
		contentPane.add(btnNewButton);

		btnNewButton_1 = new JButton("管理者");
		btnNewButton_1.setFont(new Font("HG丸ｺﾞｼｯｸM-PRO", Font.BOLD, 12));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Managerlogin shift=new Managerlogin();
				Managerlogin.main(null);
				if(e.getSource()==btnNewButton_1) {
					hide();

			}
			}
		});
		btnNewButton_1.setBounds(267, 85, 100, 68);
		contentPane.add(btnNewButton_1);

		lblNewLabel = new JLabel("どちらかを選択してください");
		lblNewLabel.setFont(new Font("HG丸ｺﾞｼｯｸM-PRO", Font.PLAIN, 18));
		lblNewLabel.setBounds(98, 10, 269, 65);

		contentPane.add(lblNewLabel);

	}
}
