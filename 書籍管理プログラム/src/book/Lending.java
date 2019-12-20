package book;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;

import book.entity.Book;

public class Lending extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton btnNewButton;
	private JLabel title;
	private JSpinner spinner;
	SpinnerDateModel model1;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private static String userId;
	private static Book selectedBook;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		userId = args[0];
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lending frame = new Lending();
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
	public Lending() {
		setTitle("貸出登録");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNewLabel = new JLabel("タイトル：");
		lblNewLabel.setFont(new Font("MS UI Gothic", Font.PLAIN, 23));
		lblNewLabel.setBounds(87, 79, 99, 56);
		contentPane.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("貸出日：");
		lblNewLabel_1.setFont(new Font("MS UI Gothic", Font.PLAIN, 23));
		lblNewLabel_1.setBounds(87, 37, 85, 32);
		contentPane.add(lblNewLabel_1);

		btnNewButton = new JButton("貸出登録");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = null;
				PreparedStatement t = null;

				// SQL文作成
				String mySql = "insert into lendinghistory (book_id,purchase_date,status,valuate) values (?,now(),0,'★')";

				try {

					int errorCode = Validate.chkExistsDate1(selectedBook.getId());
					if(errorCode != Validate.getErrCode0()) {
						//エラーダイアログ表示（入力された書籍はすでに貸出登録されています。）
						JOptionPane.showMessageDialog(contentPane, Validate.getErrMsg(errorCode));
						return;
					}
					int errorCode1 = Validate.chkExistsDate4(selectedBook.getId());
					if(errorCode1 != Validate.getErrCode0()) {
						//エラーダイアログ表示（入力された書籍はすでに貸出登録されています。）
						JOptionPane.showMessageDialog(contentPane, Validate.getErrMsg(errorCode1));
						return;
					}

					// データベースに接続
					con = DBconnect.getConnection();
					// オートコミット解除
					con.setAutoCommit(false);


					 //実行するSQL文とパラメータを指定する
					t = con.prepareStatement(mySql);
                   t.setInt(1,selectedBook.getId());

                  //INSERT文を実行する
                    int i = t.executeUpdate();

                    //処理件数を表示する
                    System.out.println("結果：" + i);
				// コミット
				con.commit();
				JOptionPane.showMessageDialog(null, "貸出完了しました！");

			} catch (Exception ex) {
				ex.printStackTrace();
				try {
					// ロールバック
					if(con != null) {
						con.rollback();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
					// 何もしない
				} finally {
					System.out.println("例外発生：" + ex.toString());
					JOptionPane.showMessageDialog(contentPane, "例外発生：" + ex.toString());
				}
			} finally {
				try {
					// ステートメントをクローズ
					if(t != null) {
						t.close();
					}
					// 接続をクローズ
					if(con != null) {
						con.close();
					}
				} catch (SQLException se) {
					se.printStackTrace();
					// 何もしない
				}
		    }
			}
		});
		btnNewButton.setBounds(110, 156, 91, 32);
		contentPane.add(btnNewButton);

		title = new JLabel(selectedBook.getTitle());
		title.setFont(new Font("MS UI Gothic", Font.PLAIN, 23));
		title.setBounds(184, 89, 149, 32);
		contentPane.add(title);

		SpinnerDateModel model=new SpinnerDateModel();
		spinner = new JSpinner(model);
		spinner.setFont(new Font("MS UI Gothic", Font.PLAIN, 23));
		JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "yyyy-MM-dd");
		spinner.setEditor(editor);
		spinner.setBounds(184, 34, 149, 35);
		contentPane.add(spinner);

		btnNewButton_1 = new JButton("貸出履歴");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String[] param = {userId};
				LendingHistory shift = new LendingHistory();
				LendingHistory.main(param);
				if(e.getSource() == btnNewButton_1);
				hide();

			}
		});
		btnNewButton_1.setBounds(242, 156, 91, 32);
		contentPane.add(btnNewButton_1);

		btnNewButton_2 = new JButton("検索画面に戻る");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] param = {userId};
				BookSearch shift = new BookSearch();
				BookSearch.main(param);
				if(e.getSource() == btnNewButton) {
					hide();
				}}
		});
		btnNewButton_2.setBounds(12, 230, 129, 21);
		contentPane.add(btnNewButton_2);;
	}
	/**
	 * @param selectedBook セットする selectedBook
	 */
	public static void setSelectedBook(Book book) {
		selectedBook = book;
}
}
