package book;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;

import book.entity.Book;

public class Return extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton btnNewButton;
	private JLabel title;
	private JSpinner spinner;
	private JLabel label_1;
	private JComboBox comboBox;
	private JButton btnNewButton_1;
	private static String userId;
	private static Book selectedBook;
	protected static Object obj;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		userId = args[0];
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Return frame = new Return();
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
	public Return() {
		setTitle("返却登録");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNewLabel = new JLabel("タイトル：");
		lblNewLabel.setFont(new Font("MS UI Gothic", Font.PLAIN, 23));
		lblNewLabel.setBounds(76, 61, 97, 29);
		contentPane.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("返却日：");
		lblNewLabel_1.setFont(new Font("MS UI Gothic", Font.PLAIN, 23));
		lblNewLabel_1.setBounds(76, 20, 91, 31);
		contentPane.add(lblNewLabel_1);

		SpinnerDateModel model=new SpinnerDateModel();
		spinner = new JSpinner(model);
		spinner.setFont(new Font("MS UI Gothic", Font.PLAIN, 23));
		JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "yyyy-MM-dd");
		spinner.setEditor(editor);
		spinner.setBounds(179, 20, 151, 29);
		contentPane.add(spinner);




		btnNewButton = new JButton("返却登録");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {


				Connection con = null;
				PreparedStatement pstmt = null;
				Object obj = comboBox.getSelectedItem();
				// SQL文作成
				String mySql = "update library.lendinghistory SET status = 1, valuate = ? where book_id = ?";

				try {

//					更新データ存在チェック
					int errorCode = Validate.chkExistsUpdData1(selectedBook.getId());
			System.out.println(errorCode);
					if(errorCode != Validate.getErrCode0()) {
						//エラーダイアログ表示（更新対象のデータが存在しません)
						JOptionPane.showMessageDialog(contentPane, Validate.getErrMsg(errorCode));
                 return;

					}
//					else {
//						JOptionPane.showMessageDialog(null, "返却対象の書籍が存在しません。");
//						String[] param = {userId};
//						BookSearch shift = new BookSearch();
//						BookSearch.main(param);
//						hide();


					// データベースに接続
					con = DBconnect.getConnection();
					// オートコミット解除
					con.setAutoCommit(false);

						//ステートメントオブジェクトを作成
						pstmt = con.prepareStatement(mySql);
						// 条件値をセット
						pstmt.setString(1,obj.toString());
						pstmt.setInt(2,selectedBook.getId());


						// SQL実行
						int num = pstmt.executeUpdate();
						System.out.println("結果：" + num + "\t");

					// コミット
					con.commit();
					JOptionPane.showMessageDialog(null, "返却完了しました！");

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
						if(pstmt != null) {
							pstmt.close();
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
		btnNewButton.setBounds(185, 191, 91, 21);
		contentPane.add(btnNewButton);

		title = new JLabel(selectedBook.getTitle());
		title.setFont(new Font("MS UI Gothic", Font.PLAIN, 23));
		title.setBounds(189, 61, 165, 29);
		contentPane.add(title);

		label_1 = new JLabel("評価：");
		label_1.setFont(new Font("MS UI Gothic", Font.PLAIN, 23));
		label_1.setBounds(99, 101, 91, 29);
		contentPane.add(label_1);

		comboBox = new JComboBox();
		comboBox.setFont(new Font("MS UI Gothic", Font.PLAIN, 21));
		comboBox.setBounds(185, 108, 145, 21);
		comboBox.addItem("");
		comboBox.addItem("★");
		comboBox.addItem("★★");
		comboBox.addItem("★★★");
		comboBox.addItem("★★★★");
		comboBox.addItem("★★★★★");
		contentPane.add(comboBox);

		btnNewButton_1 = new JButton("検索画面に戻る");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] param = {userId};
				BookSearch shift = new BookSearch();
				BookSearch.main(param);
				if(e.getSource() == btnNewButton) {
					hide();
				}
			}
		});
		btnNewButton_1.setBounds(12, 230, 138, 21);
		contentPane.add(btnNewButton_1);
	}
	/**
	 * @param selectedBook セットする selectedBook
	 */
	public static void setSelectedBook(Book book) {
		selectedBook = book;
}
}
