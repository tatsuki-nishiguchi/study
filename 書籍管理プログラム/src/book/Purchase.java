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
import javax.swing.border.EmptyBorder;

import book.entity.Book;

public class Purchase extends JFrame {

	private JPanel contentPane;
	public JLabel lblNewLabel;
	public JLabel lblNewLabel_1;
	private JButton btnNewButton;
	private JButton button;
	private JLabel a;
	private JLabel b;
	JSpinner spinner;
	private JButton btnNewButton_1;
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
					Purchase frame = new Purchase();
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
	public Purchase() {
		setTitle("購入登録");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNewLabel = new JLabel("タイトル：");
		lblNewLabel.setFont(new Font("MS UI Gothic", Font.PLAIN, 23));
		lblNewLabel.setBounds(97, 54, 91, 28);
		contentPane.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("金額(円)：");
		lblNewLabel_1.setFont(new Font("MS UI Gothic", Font.PLAIN, 23));
		lblNewLabel_1.setBounds(83, 105, 108, 28);
		contentPane.add(lblNewLabel_1);

		btnNewButton = new JButton("購入");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Connection con = null;

				PreparedStatement s = null;


				// SQL文作成
				String mySql = "insert into library.purchasehistory (id,purchase_date,book_id) values (?,now(),?)";
				try {

					int errorCode = Validate.chkExistsDate(selectedBook.getId());
					if(errorCode != Validate.getErrCode0()) {
						//エラーダイアログ表示（その書籍はすでに登録されています。）
						JOptionPane.showMessageDialog(contentPane, Validate.getErrMsg(errorCode));
						return;
					}
					int errorCode1 = Validate.chkExistsDate3(selectedBook.getId());
					if(errorCode1 != Validate.getErrCode0()) {
						//エラーダイアログ表示（選択された書籍は貸出中です。）
						JOptionPane.showMessageDialog(contentPane, Validate.getErrMsg(errorCode1));
						return;
					}

					// データベースに接続
					con = DBconnect.getConnection();
					// オートコミット解除
					con.setAutoCommit(false);
					 //実行するSQL文とパラメータを指定する
					s = con.prepareStatement(mySql);
                   s.setString(1,"root");
                   s.setInt(2,selectedBook.getId());

                  //INSERT文を実行する
                    int i = s.executeUpdate();

                    //処理件数を表示する
                    System.out.println("結果：" + i);
//				// コミット
				con.commit();



				JOptionPane.showMessageDialog(null, "購入完了しました！");

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
					if(s != null) {
						s.close();
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

		btnNewButton.setBounds(97, 165, 91, 21);
		contentPane.add(btnNewButton);

		button = new JButton("購入履歴");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				String[] param = {userId};
				PurchaseHistory shift = new PurchaseHistory();
				PurchaseHistory.main(param);
				if(e.getSource() == button);
				hide();

			}
		});
		button.setBounds(270, 165, 91, 21);
		contentPane.add(button);

		a = new JLabel(selectedBook.getTitle());
		a.setFont(new Font("MS UI Gothic", Font.PLAIN, 23));
		a.setBounds(190, 56, 135, 24);

		contentPane.add(a);

		b = new JLabel(String.valueOf(selectedBook.getPrice()));
		b.setFont(new Font("MS UI Gothic", Font.PLAIN, 23));
		b.setBounds(190, 105, 108, 24);
		contentPane.add(b);



		btnNewButton_1 = new JButton("検索画面に戻る");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] param = {userId};
				BookSearch shift = new BookSearch();
				BookSearch.main(param);
				if(e.getSource() == btnNewButton_1) {
					hide();
			}


			}
		});
		btnNewButton_1.setBounds(12, 230, 135, 21);
		contentPane.add(btnNewButton_1);

	}



	/**
	 * @param selectedBook セットする selectedBook
	 */
	public static void setSelectedBook(Book book) {
		selectedBook = book;
	}

}
