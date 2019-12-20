package book;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

import book.entity.Book;

public class BookSearch extends JFrame {
	protected static final Object Boolean = null;
	private JPanel contentPane;
	private JButton btnNewButton;
	private JTextField authorField;
	private JTextField titleField;
	private JComboBox genreBox;
	private JButton btnNewButton_1;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_5;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JScrollPane scrollPane = null;
	private JButton btnNewButton_5;
	private JComboBox priceBox;
	private JScrollPane scrollPane_1;
	private JTextField publisherField;
	private JLabel label;
	private String[] columnNames = { "ID", "タイトル", "著者名", "出版社", "ジャンル", "金額(円)" };
	private DefaultTableModel tableModel;
	private JTable table;

	// ユーザID
	private static String userId;
	// 取得したBookテーブルのエンティティリスト
	private List<Book> bookEntityList;

	private JButton btnNewButton_4;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					userId = args[0];
					BookSearch frame = new BookSearch();
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
	public BookSearch() {

		setTitle("ユーザー検索");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 896, 378);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		btnNewButton = new JButton("Topに戻る");
		btnNewButton.setBounds(12, 308, 91, 21);
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Userlogin shift = new Userlogin();
				Userlogin.main(null);
				if (e.getSource() == btnNewButton) {
					hide();
				}
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);

		authorField = new JTextField();
		authorField.setBounds(664, 30, 96, 19);
		contentPane.add(authorField);
		authorField.setColumns(10);

		titleField = new JTextField();
		titleField.setBounds(556, 30, 96, 19);
		contentPane.add(titleField);
		titleField.setColumns(10);

		genreBox = new JComboBox();
		genreBox.setBounds(610, 81, 96, 21);
		genreBox.addItem(null);
		genreBox.addItem("アニメ");
		genreBox.addItem("言語");
		genreBox.addItem("スポーツ");
		genreBox.addItem("音楽");
		genreBox.addItem("芸能");
		genreBox.addItem("映画");
		contentPane.add(genreBox);

		btnNewButton_1 = new JButton("検索\r\n");
		btnNewButton_1.setBounds(736, 132, 132, 52);

		//		検索ボタンアクション
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				try {
					// データベースに接続
					con = DBconnect.getConnection();

					// SQL文作成
					ArrayList<String> conditions = new ArrayList<String>();

					String str = titleField.getText();

					if (str.length() > 0) {
						conditions.add("title like '%" + str + "%'");
					}

					str = authorField.getText();

					if (str.length() > 0) {
						conditions.add("author like '%" + str + "%'");
					}

					str = publisherField.getText();

					if (str.length() > 0) {
						conditions.add("publisher like '%" + str + "%'");
					}

					Object obj = genreBox.getSelectedItem();

					if (obj != null) {
						conditions.add("genre like '%" + obj.toString() + "%'");
					}

					obj = priceBox.getSelectedItem();

					if (obj != null) {
						conditions.add("price like '%" + obj.toString() + "%'");
					}
					if (conditions.size() == 0) {
						JOptionPane.showMessageDialog(null, "検索ワードを入力して下さい。");
						return;
					}

					Iterator<String> itr = conditions.iterator();
					String mySql = "select * from book where " + itr.next();

					while (itr.hasNext()) {
						mySql += " and " + itr.next();
					}

					// ステートメントオブジェクトを作成
					pstmt = con.prepareStatement(mySql);

					// 検索するSQL実行
					rs = pstmt.executeQuery();

					// DB取得結果をエンティティリストに格納
					List<Book> resultList = new ArrayList<Book>();
					while (rs.next()) {
						Book book = new Book();

						book.setId(rs.getInt("id"));
						book.setTitle(rs.getString("title"));
						book.setAuthor(rs.getString("author"));
						book.setPublisher(rs.getString("publisher"));
						book.setGenre(rs.getString("genre"));
						book.setPrice(rs.getInt("price"));
						resultList.add(book);
					}

					// クラス変数に格納
					bookEntityList = resultList;

					// 表のヘッダー部作成
					tableModel = new DefaultTableModel(columnNames, 0);
					// java.sql.ResultSet の行数を取得するためカーソルを最終行に移動
					rs.last();
					// 結果の行数をセット
					tableModel.setRowCount(rs.getRow());
					// カーソルを先頭行に移動
					rs.beforeFirst();
					System.out.println("表件数＝" + tableModel.getRowCount() + "¥t");

					// 行のカウントアップ変数
					int rowIndex = 0;
					// 列のカウントアップ変数
					int columnIndex = 0;

					// 結果セットからデータを取り出す next()で次の行に移動

					for (Book rowData : bookEntityList) {
						columnIndex = 0;

						// 検索結果から表に書き込み
						tableModel.setValueAt(rowData.getId(), rowIndex, columnIndex++);
						tableModel.setValueAt(rowData.getTitle(), rowIndex, columnIndex++);
						tableModel.setValueAt(rowData.getAuthor(), rowIndex, columnIndex++);
						tableModel.setValueAt(rowData.getPublisher(), rowIndex, columnIndex++);
						tableModel.setValueAt(rowData.getGenre(), rowIndex, columnIndex++);
						tableModel.setValueAt(rowData.getPrice(), rowIndex, columnIndex++);
						System.out.println("行数＝" + rowIndex + "¥t");
						rowIndex++;
					}

					if (rowIndex == 0) {
						JOptionPane.showMessageDialog(null, "該当する書籍が見つかりませんでした。");
						tableModel.setRowCount(0);
					}

					// 表を描画
					getContentPane().add(scrollPane_1);
					table.setModel(tableModel);
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					table.setColumnSelectionAllowed(true);
					table.setRowSelectionAllowed(true);
					table.getColumnModel().getColumn(0).setMinWidth(2);
					table.getColumnModel().getColumn(1).setPreferredWidth(75);
					table.getColumnModel().getColumn(1).setMinWidth(1);
					scrollPane_1.setViewportView(table);
					scrollPane_1.repaint();

				} catch (Exception ex) {
					System.out.println("例外発生：" + ex.toString());
					JOptionPane.showMessageDialog(contentPane, "例外発生：" + ex.toString());
				} finally {
					try {
						// 実行結果をクローズ
						if (rs != null) {
							rs.close();
						}
						// ステートメントをクローズ
						if (pstmt != null) {
							pstmt.close();
						}
						// 接続をクローズ
						if (con != null) {
							con.close();
						}
					} catch (SQLException se) {
						// 何もしない
					}
				}
			}
		});
		contentPane.add(btnNewButton_1);

		lblNewLabel_1 = new JLabel("タイトル");
		lblNewLabel_1.setBounds(556, 7, 50, 13);
		contentPane.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("著者名");
		lblNewLabel_2.setBounds(664, 7, 50, 13);
		contentPane.add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("ジャンル");
		lblNewLabel_3.setBounds(610, 59, 50, 13);
		contentPane.add(lblNewLabel_3);

		lblNewLabel_5 = new JLabel("金額\r\n");
		lblNewLabel_5.setBounds(736, 59, 50, 13);
		contentPane.add(lblNewLabel_5);

		btnNewButton_2 = new JButton("①貸出");
		btnNewButton_2.setBounds(598, 236, 108, 42);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row == -1) {
					Validate.noSelect();
				} else {

					// 貸出クラスに選択した書籍の情報をセット
					Lending.setSelectedBook(bookEntityList.get(table.getSelectedRow()));
					Validate.setSelectedBook(bookEntityList.get(table.getSelectedRow()));

					String[] param = { userId };
					Lending shift = new Lending();
					Lending.main(param);
					if (e.getSource() == btnNewButton_5)
						;
					hide();
				}
			}
		});
		contentPane.add(btnNewButton_2);

		btnNewButton_3 = new JButton("②返却");
		btnNewButton_3.setBounds(736, 236, 108, 42);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row == -1) {
					Validate.noSelect();
				} else {

					// 返却クラスに選択した書籍の情報をセット
					Return.setSelectedBook(bookEntityList.get(table.getSelectedRow()));
					Validate.setSelectedBook(bookEntityList.get(table.getSelectedRow()));

					String[] param = { userId };
					Return shift = new Return();
					Return.main(param);
					if (e.getSource() == btnNewButton_5)
						;
					hide();
				}
			}
		});
		contentPane.add(btnNewButton_3);

		btnNewButton_5 = new JButton("③購入");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row == -1) {
					Validate.noSelect();
				} else {

					// 購入クラスに選択した書籍の情報をセット
					Purchase.setSelectedBook(bookEntityList.get(table.getSelectedRow()));
					Validate.setSelectedBook(bookEntityList.get(table.getSelectedRow()));

					String[] param = { userId };
					Purchase shift = new Purchase();
					Purchase.main(param);
					if (e.getSource() == btnNewButton_5)
						;
					hide();
				}
			}
		});
		btnNewButton_5.setBounds(598, 289, 108, 40);

		contentPane.add(btnNewButton_5);

		priceBox = new JComboBox();
		priceBox.setBounds(736, 81, 96, 21);
		priceBox.addItem(null);
		priceBox.addItem("100");
		priceBox.addItem("200");
		priceBox.addItem("300");
		priceBox.addItem("400");
		priceBox.addItem("500");
		contentPane.add(priceBox);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 6, 532, 292);
		contentPane.add(scrollPane_1);

		table = new JTable();

		scrollPane_1.setColumnHeaderView(table);

		new JTable();

		publisherField = new JTextField();
		publisherField.setBounds(772, 30, 96, 19);
		contentPane.add(publisherField);
		publisherField.setColumns(10);

		label = new JLabel("出版社");
		label.setBounds(772, 7, 50, 13);
		contentPane.add(label);

		JButton button = new JButton("★ランキング");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ranking shift = new Ranking();
				Ranking.main(null);
				if (e.getSource() == btnNewButton) {
					hide();

				}
			}
		});
		button.setBounds(736, 288, 108, 42);
		contentPane.add(button);

		btnNewButton_4 = new JButton("一括表示");
		btnNewButton_4.addActionListener(new ActionListener() {
			/**
			 *
			 */
			public void actionPerformed(ActionEvent e) {
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				try {
					//データベースに接続
					con = DBconnect.getConnection();

					// データ存在チェック
					int errorCode = Validate.chkExistsData(con);
					if (errorCode != Validate.getErrCode0()) {
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

					// DB取得結果をエンティティリストに格納
					List<Book> resultList = new ArrayList<Book>();
					while (rs.next()) {
						Book book = new Book();

						book.setId(rs.getInt("id"));
						book.setTitle(rs.getString("title"));
						book.setAuthor(rs.getString("author"));
						book.setPublisher(rs.getString("publisher"));
						book.setGenre(rs.getString("genre"));
						book.setPrice(rs.getInt("price"));
						resultList.add(book);
					}

					// クラス変数に格納
					bookEntityList = resultList;

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
					while (rs.next()) {
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
					getContentPane().add(scrollPane_1);
					table.setModel(tableModel);
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					table.setColumnSelectionAllowed(false);
					table.setRowSelectionAllowed(true);
					table.getColumnModel().getColumn(0).setMinWidth(2);
					table.getColumnModel().getColumn(1).setPreferredWidth(75);
					table.getColumnModel().getColumn(1).setMinWidth(1);
					scrollPane_1.setViewportView(table);
					scrollPane_1.repaint();

				} catch (Exception ex) {
					ex.printStackTrace();
					System.out.println("例外発生：" + ex.toString());
					JOptionPane.showMessageDialog(contentPane, "例外発生：" + ex.toString());
				} finally {
					try {
						// 実行結果をクローズ
						if (rs != null) {
							rs.close();
						}
						// ステートメントをクローズ
						if (pstmt != null) {
							pstmt.close();
						}
						// 接続をクローズ
						if (con != null) {
							con.close();
						}
					} catch (SQLException se) {
						se.printStackTrace();
						// 何もしない
					}
				}

			}
		});
		btnNewButton_4.setBounds(572, 132, 134, 52);
		contentPane.add(btnNewButton_4);

		lblNewLabel = new JLabel("左リストより書籍を選択後、①～③をお選び下さい");
		lblNewLabel.setBounds(556, 202, 312, 13);
		contentPane.add(lblNewLabel);
	}
}
