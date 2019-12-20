package book;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class LendingHistory extends JFrame {


	private JPanel contentPane;
	private JButton btnNewButton_4;
	private JScrollPane scrollPane = null;
	private JTable table;
    private String[] columnNames = { "書籍No","購入日時","ステータス","評価","ID", "タイトル", "著者名", "出版社", "ジャンル", "金額(円)"};
	static DefaultTableModel tableModel;
	private JButton btnNewButton;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LendingHistory frame = new LendingHistory();
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
	public LendingHistory() {
		setTitle("貸出履歴");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 898, 356);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);


		btnNewButton_4 = new JButton("戻る");
		btnNewButton_4.setBounds(12, 261, 69, 21);
		btnNewButton_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Lending shift=new Lending();
				shift.show();
				hide();
			}
		});
		contentPane.add(btnNewButton_4);
        //表のヘッダー部分作成
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 858, 241);
		contentPane.add(scrollPane);
		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"\u66F8\u7C4D\u0049\u0044","\u8CFC\u5165\u65E5\u6642","\u30B9\u30C6\u30FC\u30BF\u30B9","\u8A55\u4FA1","ID", "\u30BF\u30A4\u30C8\u30EB", "\u8457\u8005\u540D", "\u51FA\u7248\u793E", "\u30B8\u30E3\u30F3\u30EB", "\u91D1\u984D\u0028\u5186\u0029"
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
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setMinWidth(1);
		scrollPane.setViewportView(table);

		btnNewButton = new JButton("表示");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				try {
					//データベースに接続
					con = DBconnect.getConnection();

					//SQL文作成
					String mySql ="select * from lendinghistory inner join book on lendinghistory.book_id = book.id order by purchase_date;";
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
						tableModel.setValueAt(rs.getInt("book_id"), i, 0);
						tableModel.setValueAt(rs.getTimestamp("purchase_date"), i, 1);
						tableModel.setValueAt(rs.getString("status"), i, 2);
						tableModel.setValueAt(rs.getString("valuate"), i, 3);
						tableModel.setValueAt(rs.getInt("id"), i, 4);
						tableModel.setValueAt(rs.getString("title"), i, 5);
						tableModel.setValueAt(rs.getString("author"), i, 6);
						tableModel.setValueAt(rs.getString("publisher"), i, 7);
						tableModel.setValueAt(rs.getString("genre"), i, 8);
						tableModel.setValueAt(rs.getInt("price"), i, 9);
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
					table.getColumnModel().getColumn(1).setPreferredWidth(150);
					table.getColumnModel().getColumn(1).setMinWidth(1);
					scrollPane.setViewportView(table);
					scrollPane.repaint();

				} catch (Exception ex) {
					ex.printStackTrace();
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
						se.printStackTrace();
						// 何もしない
					}
				}

			}
		});
		btnNewButton.setBounds(377, 261, 79, 21);
		contentPane.add(btnNewButton);

		lblNewLabel = new JLabel("ステータス　0：貸出中");
		lblNewLabel.setBounds(610, 261, 165, 13);
		contentPane.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("1：返却済み");
		lblNewLabel_1.setBounds(691, 282, 69, 13);
		contentPane.add(lblNewLabel_1);
		scrollPane.repaint();
	}
}
