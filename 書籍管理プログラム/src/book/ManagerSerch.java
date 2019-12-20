package book;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ManagerSerch extends JFrame {


	private JPanel contentPane;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JTextField titleField;
	private JButton btnNewButton_4;
	private JTextField authorField;
	private JLabel label;
	private JLabel label_1;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_3;
	private JScrollPane scrollPane = null;

	private JComboBox<String> genreBox;
	private JTable table;
    private
    String[] columnNames = { "ID", "タイトル", "著者名", "出版社", "ジャンル", "金額(円)"};

	static DefaultTableModel tableModel;
	private JComboBox<String> priceBox;
	private JTextField publisherField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerSerch frame = new ManagerSerch();
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
	public ManagerSerch() {
		setTitle("管理者検索");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 771, 331);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);



		genreBox = new JComboBox<String>();
		genreBox.setBounds(647, 25, 96, 21);
		genreBox.addItem("");
		genreBox.addItem("アニメ");
		genreBox.addItem("言語");
		genreBox.addItem("スポーツ");
		genreBox.addItem("音楽");
		genreBox.addItem("芸能");
		genreBox.addItem("映画");
		contentPane.add(genreBox);

		priceBox = new JComboBox<String>();
		priceBox.setBounds(647, 69, 96, 21);
		priceBox.addItem("");
		priceBox.addItem("100");
		priceBox.addItem("200");
		priceBox.addItem("300");
		priceBox.addItem("400");
		priceBox.addItem("500");
		contentPane.add(priceBox);

		btnNewButton = new JButton("登録");
		btnNewButton.setBounds(522, 164, 91, 44);
		btnNewButton.addActionListener(new ActionListener() {

//登録ボタンアクション
			@Override
			public void actionPerformed(ActionEvent e) {
				Connection con = null;
				PreparedStatement s = null;


				 Object obj = genreBox.getSelectedItem();
				 Object obj2 = priceBox.getSelectedItem();

				try {
					// 空白チェック
					int errorCode = Validate.chkNull2(titleField.getText(), authorField.getText(), publisherField.getText(),obj.toString(),obj2.toString());
					if(errorCode != Validate.getErrCode0()) {
						//エラーダイアログ表示　（文字列が入力されていません）
						JOptionPane.showMessageDialog(contentPane, Validate.getErrMsg(errorCode));
						return;
					}

//					if(obj != null|| obj2 !=null ) {
//						JOptionPane.showMessageDialog(null, "文字列を入力して下さい。");
//						return;
//					}

					//入力文字内容チェック
					 errorCode = Validate.chkContents1(obj2.toString());
					if(errorCode != Validate.getErrCode0()) {
						//エラーダイアログ表示
						JOptionPane.showMessageDialog(contentPane, Validate.getErrMsg(errorCode));
                        return;
					}

					// データベースに接続
					con = DBconnect.getConnection();
					// オートコミット解除
					con.setAutoCommit(false);
					// SQL文作成
					String mySql = "insert into library.book (id, title, author, publisher, genre,price) select COALESCE(max(id), 0) + 1, ?, ?, ?, ?, ? from library.book";
					// ステートメントオブジェクトを作成
					s = con.prepareStatement(mySql);
					s.setString(1, titleField.getText());
					s.setString(2, authorField.getText());
					s.setString(3, publisherField.getText());
					s.setString(4, obj.toString());
					s.setString(5, obj2.toString());

					int num = s.executeUpdate();
					System.out.println("結果：" + num + "\t");

					// コミット
					con.commit();

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
				// 表示ボタン押下
				btnNewButton_2.doClick();

			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);

		btnNewButton_1 = new JButton("更新");
		btnNewButton_1.setBounds(522, 232, 91, 50);

//	更新ボタンアクション処理
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row == -1) {
					Validate.noSelect();
				} else {


				Connection con = null;
				PreparedStatement pstmt = null;
				try {

					// データベースに接続
					con = DBconnect.getConnection();
					// オートコミット解除
					con.setAutoCommit(false);
					// SQL文作成
					String mySql = "update library.book SET title = ?, author = ?, publisher = ?, genre = ?, price = ? where id = ?";

					for(int i = 0; i < table.getRowCount(); i++) {
						// IDをstring型で返す
						String id = String.valueOf(table.getValueAt(i, 0));
						// titleをstring型で返す
						String title = String.valueOf(table.getValueAt(i, 1));
						String author = String.valueOf(table.getValueAt(i, 2));
						String publisher = String.valueOf(table.getValueAt(i, 3));
						String genre = String.valueOf(table.getValueAt(i, 4));
						String price = String.valueOf(table.getValueAt(i, 5));

						System.out.println("id：" + id + "\t");
						System.out.println("title：" + title + "\t");
						System.out.println("author：" + author + "\t");
						System.out.println("publisher：" + publisher + "\t");
						System.out.println("genre：" + genre + "\t");
						System.out.println("price：" + price + "\t");


						// 更新データ存在チェック
						int errorCode = Validate.chkExistsUpdData(con, id);
						if(errorCode != Validate.getErrCode0()) {
							//エラーダイアログ表示（更新対象のデータが存在しません）
							JOptionPane.showMessageDialog(contentPane, Validate.getErrMsg(errorCode));
							return;
						}

						// 空白チェック
						errorCode = Validate.chkNull2(title, author, publisher, genre, price);
						if(errorCode != Validate.getErrCode0()) {
							//エラーダイアログ表示　（文字列が入力されていません）
							JOptionPane.showMessageDialog(contentPane, Validate.getErrMsg(errorCode));
							return;
						}

//						if(obj != null|| obj2 !=null ) {
//							JOptionPane.showMessageDialog(null, "文字列を入力して下さい。");
//							return;
//						}

						//入力文字内容チェック
						int chkErrorCode = Validate.chkContents1(price);
						if(chkErrorCode != Validate.getErrCode0()) {
							//エラーダイアログ表示
							JOptionPane.showMessageDialog(contentPane, Validate.getErrMsg(chkErrorCode));

						}
						//ステートメントオブジェクトを作成
						pstmt = con.prepareStatement(mySql);
						// 条件値をセット
						pstmt.setString(1, title);
						pstmt.setString(2, author);
						pstmt.setString(3, publisher);
						pstmt.setString(4, genre);
						pstmt.setString(5, price);

						//String を int に変換
						pstmt.setInt(6, Integer.parseInt(id));
						// SQL実行

						int num = pstmt.executeUpdate();
						System.out.println("結果：" + num + "\t");
					}
					// コミット
					con.commit();

				} catch (Exception ex) {
					try {
						// ロールバック
						if(con != null) {
							con.rollback();
						}
					} catch (SQLException e1) {

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
						// 何もしない
					}
				}
				// 表示ボタン押下
				btnNewButton_2.doClick();
				}
			}
		});
		contentPane.add(btnNewButton_1);

		btnNewButton_2 = new JButton("表示");
		btnNewButton_2.setBounds(652, 164, 91, 44);

//		表示ボタンアクション
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				try {
					//データベースに接続
					con = DBconnect.getConnection();

					// データ存在チェック
					int errorCode = Validate.chkExistsData(con);
					if(errorCode != Validate.getErrCode0()) {
						//エラーダイアログ表示（更新対象のデータが存在しません)
						JOptionPane.showMessageDialog(contentPane, Validate.getErrMsg(errorCode));
						return;
					}

					//SQL文作成
					String mySql = "select id, title, author, publisher, genre, price from library.book order by id";
					//ステートメントオブジェクトを作成
					pstmt = con.prepareStatement(mySql);
					//検索するSQL実行
					rs = pstmt.executeQuery();

					// 表のヘッダー部作成
					DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
					// java.sql.ResultSet の行数を取得するためカーソルを最終行に移動
					rs.last();
					// 結果の行数をセット
					tableModel.setRowCount(rs.getRow());
					// カーソルを先頭行に移動
					rs.beforeFirst();
					System.out.println("表件数＝" + tableModel.getRowCount() + "\t");

					int i = 0; // カウントアップ変数
					//結果セットからデータを取り出す next()で次の行に移動
					while(rs.next()) {
						// 検索結果から表に書き込み
						tableModel.setValueAt(java.lang.String.format("%03d", rs.getInt("id")), i, 0);
						tableModel.setValueAt(rs.getString("title"), i, 1);
						tableModel.setValueAt(rs.getString("author"), i, 2);
						tableModel.setValueAt(rs.getString("publisher"), i, 3);
						tableModel.setValueAt(rs.getString("genre"), i, 4);
						tableModel.setValueAt(rs.getString("price"), i, 5);

						System.out.println("行数＝" + i + "\t");
						i++;
					}
					// 表を描画
					getContentPane().add(scrollPane);
					table.setModel(tableModel);
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					table.setColumnSelectionAllowed(false);
					table.setRowSelectionAllowed(true);
					table.getColumnModel().getColumn(0).setMinWidth(2);
					table.getColumnModel().getColumn(1).setPreferredWidth(75);
					table.getColumnModel().getColumn(1).setMinWidth(1);
					scrollPane.setViewportView(table);
					scrollPane.repaint();

				} catch (Exception ex) {
					System.out.println("例外発生：" + ex.toString());
					JOptionPane.showMessageDialog(contentPane, "例外発生：" + ex.toString());
				} finally {
					try {
						// 実行結果をクローズ
						if(rs != null) {
							rs.close();
						}
						// ステートメントをクローズ
						if(pstmt != null) {
							pstmt.close();
						}
						// 接続をクローズ
						if(con != null) {
							con.close();
						}
					} catch (SQLException se) {
						// 何もしない
					}
				}

			}
		});
		contentPane.add(btnNewButton_2);

		btnNewButton_3 = new JButton("削除");
		btnNewButton_3.setBounds(652, 232, 91, 50);

//削除ボタンアクション
		btnNewButton_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row == -1) {
					Validate.noSelect();
				} else {


//				int row = table.getSelectedRow();
				System.out.println("getSelectedRow＝" + row + "\t");
				Connection con = null;
				PreparedStatement pstmt = null;
				try {
					// データベースに接続
					con = DBconnect.getConnection();
					// 更新データ存在チェック
					int errorCode = Validate.chkExistsUpdData(con, String.valueOf(table.getValueAt(row, 0)));
					if(errorCode != Validate.getErrCode0()) {
						//エラーダイアログ表示（更新対象のデータが存在しません）
						JOptionPane.showMessageDialog(contentPane, Validate.getErrMsg(errorCode));
						return;
					}
					// オートコミット解除（トランザクションをコミット後にデータ反映）
					con.setAutoCommit(false);
					// SQL文作成
					String mySql = "DELETE FROM book where id = ?";
					//ステートメントオブジェクトを作成
					pstmt = con.prepareStatement(mySql);
					// 条件値をセット
					int val = Integer.parseInt(String.valueOf(table.getValueAt(row, 0)));
					System.out.println("Cell＝" + val + "\t");
					pstmt.setInt(1, val);
					// SQL実行
					int updateCount = pstmt.executeUpdate();
					System.out.println("Delete: " + updateCount);

					// コミット
					con.commit();

				} catch (Exception ex) {

					try {
						// ロールバック
						if(con != null) {
							con.rollback();
						}
					} catch (SQLException e1) {
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
						// 何もしない
					}
				}
				// 表示ボタン押下
				btnNewButton_2.doClick();

				}
			}
		});
		contentPane.add(btnNewButton_3);

		titleField = new JTextField();
		titleField.setBounds(522, 26, 96, 19);
		contentPane.add(titleField);
		titleField.setColumns(10);

		btnNewButton_4 = new JButton("Topに戻る");
		btnNewButton_4.setBounds(12, 261, 91, 21);
		btnNewButton_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Topmenu shift=new Topmenu();
				shift.show();
				hide();
			}
		});
		contentPane.add(btnNewButton_4);

		authorField = new JTextField();
		authorField.setBounds(522, 70, 96, 19);
		contentPane.add(authorField);
		authorField.setColumns(10);

		label = new JLabel("タイトル");
		label.setBounds(525, 10, 50, 13);
		contentPane.add(label);

		label_1 = new JLabel("著者名");
		label_1.setBounds(522, 55, 50, 13);
		contentPane.add(label_1);

		lblNewLabel = new JLabel("出版社");
		lblNewLabel.setBounds(522, 99, 50, 13);
		contentPane.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("ジャンル");
		lblNewLabel_1.setBounds(652, 10, 50, 13);
		contentPane.add(lblNewLabel_1);




		lblNewLabel_3 = new JLabel("金額");
		lblNewLabel_3.setBounds(647, 56, 50, 13);
		contentPane.add(lblNewLabel_3);
        //表を配置
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 498, 241);
		contentPane.add(scrollPane);
		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"ID", "\u30BF\u30A4\u30C8\u30EB", "\u8457\u8005\u540D", "\u51FA\u7248\u793E", "\u30B8\u30E3\u30F3\u30EB", "\u91D1\u984D\u0028\u5186\u0029"
				}));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setColumnSelectionAllowed(true);
		table.setRowSelectionAllowed(true);
		scrollPane.setColumnHeaderView(table);

		// 表を描画
		getContentPane().add(scrollPane);
		table.setModel(tableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(true);
		table.getColumnModel().getColumn(0).setMinWidth(2);
		table.getColumnModel().getColumn(1).setPreferredWidth(75);
		table.getColumnModel().getColumn(1).setMinWidth(1);
		scrollPane.setViewportView(table);


		publisherField = new JTextField();
		publisherField.setBounds(522, 122, 96, 19);
		contentPane.add(publisherField);
		publisherField.setColumns(10);
		scrollPane.repaint();
	}
}
