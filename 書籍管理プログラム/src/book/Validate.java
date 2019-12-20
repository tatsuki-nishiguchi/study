package book;

import java.awt.Component;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import book.entity.Book;

public class Validate {

	// エラーコード数字を代入
	private static final int IS_SUCCESS_CODE = 0;
	private static final int ERROR_CODE_1000 = 1000;
	private static final int ERROR_CODE_1010 = 1010;
	private static final int ERROR_CODE_1020 = 1020;
	private static final int ERROR_CODE_1030 = 1030;
	private static final int ERROR_CODE_1040 = 1040;
	private static final int ERROR_CODE_1050 = 1050;
	private static final int ERROR_CODE_1060 = 1060;
	private static final int ERROR_CODE_1070 = 1070;
	private static final int ERROR_CODE_1080 = 1080;
	private static final int ERROR_CODE_1090 = 1090;
	private static final int ERROR_CODE_1100 = 1100;
	private static final int ERROR_CODE_1110 = 1110;
	private static final int ERROR_CODE_1120 = 1120;
	private static final int ERROR_CODE_1130 = 1130;

	private static final String IS_SUCCESS_CODE_MESSAGE = null;
	public static final String ERROR_MESSAGE_1000 = "書籍が選択されていません。";
	private static final String ERROR_MESSAGE_1010 = "文字列が入力されていません。";
	private static final String ERROR_MESSAGE_1020 = "入力フィールドに半角文字が含まれています。";
	private static final String ERROR_MESSAGE_1030 = "対象データが存在しません。";
	private static final String ERROR_MESSAGE_1040 = "入力フィールドに全角、半角文字列またはハイフン以外の記号が含まれています。";
	private static final String ERROR_MESSAGE_1050 = "郵便番号、電話番号、カード番号、セキュリティコードのいずれかに全角または、半角文字列が含まれています。";
	private static final String ERROR_MESSAGE_1060 = "選択された書籍は購入されています。";
	private static final String ERROR_MESSAGE_1070 = "選択された書籍はすでに	購入されています。";
	private static final String ERROR_MESSAGE_1080 = "選択された書籍はすでに貸出されています。";
	private static final String ERROR_MESSAGE_1090 = "選択された書籍はすでに返却されています。";
	private static final String ERROR_MESSAGE_1100 = "選択された書籍は貸出中です。";
	private static final String ERROR_MESSAGE_1110 = "対象データが存在しません。";
	private static final String ERROR_MESSAGE_1120 = "表示対象データが存在しません。";
	private static final String ERROR_MESSAGE_1130 = "返却対象データが存在しません。";
	private static Book selectedBook;

	// ％ｓは戻り値をstring formatで置換
	/**
	 * @return errCode0
	 */
	public static int getErrCode0() {
		return IS_SUCCESS_CODE;
	}

	/**
	 * @return errCode1000
	 */
	public static int getErrCode1000() {
		return ERROR_CODE_1000;
	}

	/**
	 * @return errCode1010
	 */
	public static int getErrCode1010() {
		return ERROR_CODE_1010;
	}

	/**
	 * @return errCode1020
	 */
	public static int getErrCode1020() {
		return ERROR_CODE_1020;
	}

	/**
	 * @return errCode1030
	 */
	public static int getErrCode1030() {
		return ERROR_CODE_1030;
	}

	/**
	 * @return errCode1040
	 */
	public static int getErrCode1040() {
		return ERROR_CODE_1040;
	}

	public static int getErrCode1050() {
		return ERROR_CODE_1050;
	}

	public static int getErrCode1060() {
		return ERROR_CODE_1060;
	}

	public static int getErrCode1070() {
		return ERROR_CODE_1070;
	}

	public static int getErrCode1080() {
		return ERROR_CODE_1080;
	}

	public static int getErrCode1090() {
		return ERROR_CODE_1090;
	}

	public static int getErrCode1100() {
		return ERROR_CODE_1100;
	}

	public static int getErrCode1110() {
		return ERROR_CODE_1110;
	}

	public static int getErrCode1120() {
		return ERROR_CODE_1120;
	}

	/**
	 * エラーメッセージ取得
	 *
	 * @param errorCode
	 *            エラーコード
	 * @return エラーメッセージ
	 */
	public static String getErrMsg(int errorCode) {
		switch (errorCode) {
		case IS_SUCCESS_CODE:
			return IS_SUCCESS_CODE_MESSAGE;
		case ERROR_CODE_1000:
			return ERROR_MESSAGE_1000;
		case ERROR_CODE_1010:
			return ERROR_MESSAGE_1010;
		case ERROR_CODE_1020:
			return ERROR_MESSAGE_1020;
		case ERROR_CODE_1030:
			return ERROR_MESSAGE_1030;
		case ERROR_CODE_1040:
			return ERROR_MESSAGE_1040;
		case ERROR_CODE_1050:
			return ERROR_MESSAGE_1050;
		case ERROR_CODE_1060:
			return ERROR_MESSAGE_1060;
		case ERROR_CODE_1070:
			return ERROR_MESSAGE_1070;
		case ERROR_CODE_1080:
			return ERROR_MESSAGE_1080;
		case ERROR_CODE_1090:
			return ERROR_MESSAGE_1090;
		case ERROR_CODE_1100:
			return ERROR_MESSAGE_1100;
		case ERROR_CODE_1110:
			return ERROR_MESSAGE_1110;
		case ERROR_CODE_1120:
			return ERROR_MESSAGE_1120;

		default:
			return "";
		}
	}

	/**
	 * エラーメッセージ取得（1010）
	 *
	 * @param errorCode
	 *            エラーコード
	 * @param MaxLength
	 *            桁数
	 * @return エラーメッセージ
	 */
	public static String replaceErrMsg1010(int errorCode, int MaxLength) {
		return String.format(getErrMsg(errorCode), String.valueOf(MaxLength));
	}

	public static String replaceErrMsg1020(int errorCode, int MaxLength, int MaxLength1) {
		return String.format(getErrMsg(errorCode), String.valueOf(MaxLength), String.valueOf(MaxLength1));
	}

//	選択チェック
public static void noSelect() {
	JOptionPane optionPane = new JOptionPane("書籍が選択されていません。", JOptionPane.ERROR_MESSAGE);
	JDialog dialog = optionPane.createDialog(null, "ERROR");
	setFont(dialog.getContentPane().getComponents(), new Font("Meiryo UI", Font.PLAIN, 18));
	dialog.setBounds(800, 400, 400, 150);
	dialog.setVisible(true);
}


	private static void setFont(Component[] components, Font font) {
	// TODO 自動生成されたメソッド・スタブ

}

	// 空白チェック(会員登録)
	public static int chkNull1(String str, String str1, String str2, String str3, String str4, String str5, String str6,
			String str7, String str8, String str9, String str10, String str11, String str12) {
		if(str == null || str.length() == 0 && str1 == null || str1.length() == 0 && str2 == null
				|| str2.length() == 0 && str3 == null || str3.length() == 0 && str4 == null
				|| str4.length() == 0 && str5 == null || str5.length() == 0 && str6 == null
				|| str6.length() == 0 && str7 == null || str7.length() == 0 && str8 == null
				|| str8.length() == 0 && str9 == null || str9.length() == 0 && str10 == null
				|| str10.length() == 0 && str11 == null || str11.length() == 0 && str12 == null
				|| str12.length() == 0) {
			return ERROR_CODE_1000;
		}
		return IS_SUCCESS_CODE;
	}

	//空白チェック(書籍管理)
	public static int chkNull2(String str1, String str2, String str3,String str4, String str5) {
		if(str1 == null || str1.length() == 0 && str2 == null
				|| str2.length() == 0 && str3 == null || str3.length() == 0 && str4 == null || str4.length() == 0 && str5 == null || str5.length() == 0 ) {
			return ERROR_CODE_1010;
		}
		return IS_SUCCESS_CODE;
	}

	/**
	 * 入力文字内容チェック
	 *
	 * @param str
	 *            チェック文字列
	 * @return 判定結果
	 */
	public static int chkContents(String str) {
		char[] chars = str.toCharArray();
		for(int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if(String.valueOf(chars[i]).getBytes().length < 2) {
				return ERROR_CODE_1020;
			}
		}
		return IS_SUCCESS_CODE;
	}

	// 入力文字内容チェック(半角(書籍管理)
	public static int chkContents1(String str) {
		char[] chars = str.toCharArray();
//		char[] chars1 = str1.toCharArray();
		for(int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if((c =='\u00a5') ||
					(c == '\u203e') ||
					(c >= '\uff61' && c <= '\uff9f')
					) {
				return ERROR_CODE_1030;
			}
		}
////		for(int i = 0; i < chars1.length; i++) {
////			char c = chars1[i];
//			if((c =='\u00a5') ||
//					(c == '\u203e') ||
//					(c >= '\uff61' && c <= '\uff9f')
//					) {
//				return ERROR_CODE_1030;
		return IS_SUCCESS_CODE;
	}

	// 入力文字内容チェック(半角(会員登録))

	public static int chkContents2(String str1, String str2, String str3, String str4) {
		char[] chars1 = str1.toCharArray();
		char[] chars2 = str2.toCharArray();
		char[] chars3 = str3.toCharArray();
		char[] chars4 = str4.toCharArray();
		for(int i = 0; i < chars1.length; i++) {
			char c = chars1[i];
			if(!(c >= '0' && c <= '9')) {
				return ERROR_CODE_1050;
			}
		}
		for(int i = 0; i < chars2.length; i++) {
			char c = chars2[i];
			if(!(c >= '0' && c <= '9')) {
				return ERROR_CODE_1050;
			}
		}
		for(int i = 0; i < chars3.length; i++) {
			char c = chars3[i];
			if(!(c >= '0' && c <= '9')) {
				return ERROR_CODE_1050;
			}
		}
		for(int i = 0; i < chars4.length; i++) {
			char c = chars4[i];
			if(!(c >= '0' && c <= '9')) {
				return ERROR_CODE_1050;
			}
		}
		return IS_SUCCESS_CODE;
	}

	// 入力文字内容チェック(半角、ハイフン)
	public static int chkContents3(String str) {
		char[] chars = str.toCharArray();
		for(int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if(!(c >= '0' && c <= '9') && c != '-') {
				return ERROR_CODE_1040;
			}
		}
		return IS_SUCCESS_CODE;
	}

	// 入力文字数チェック(ユーザ名、パスワード)
	public static int chkLength1(String str, String str1, int max) {
		if(str.length() > max || str1.length() > max) {
			return ERROR_CODE_1010;
		}
		return IS_SUCCESS_CODE;
	}

	// 入力文字数チェック(郵便番号)
	public static int chkLength2(String str, int postLen) {
		if(str.length() != postLen) {
			return ERROR_CODE_1060;
		}
		return IS_SUCCESS_CODE;
	}

	// 入力文字数チェック(電話番号(10桁))
	// public static int chkLength3(String str, int phoneLen, int phoneLen1) {
	// if(str.length() != phoneLen || str.length() != phoneLen1) {
	// return ERROR_CODE_1080;
	// }
	// return IS_SUCCESS_CODE;
	// }

	// 入力文字数チェック(携帯番号)
	public static int chkLength31(String str, int phoneLen1) {
		if(str.length() != phoneLen1) {
			return ERROR_CODE_1080;
		}
		return IS_SUCCESS_CODE;
	}

	// 入力文字数チェック(カード番号)
	public static int chkLength4(String str, int CardLen) {
		if(str.length() != CardLen) {
			return ERROR_CODE_1090;
		}
		return IS_SUCCESS_CODE;
	}

	// 入力文字数チェック(セキュリティコード)
	public static int chkLength5(String str, int SCLen) {
		if(str.length() != SCLen) {
			return ERROR_CODE_1100;
		}
		return IS_SUCCESS_CODE;
	}

	/**
	 * 更新データ存在チェック
	 *
	 * @param con
	 *            コネクション
	 * @param str
	 *            チェック文字列
	 * @return 判定結果
	 * @throws Exception
	 */
	public static int chkExistsUpdData(Connection con, String str) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// SQL文作成
			String mySql = "select count(*) as cnt from library.book where id = ?;";
			// ステートメントオブジェクトを作成
			pstmt = con.prepareStatement(mySql);
			// 検索条件
			pstmt.setInt(1, Integer.parseInt(str));
			// 検索するSQL実行
			rs = pstmt.executeQuery();

			int count = 0; // 検索結果（件数）
			// 結果セットからデータを取り出す next()で次の行に移動
			while(rs.next()) {
				count = rs.getInt("cnt");
			}

			// 検索結果件数チェック
			if(count == 0) {
				return ERROR_CODE_1110;
			}

		} catch (Exception ex) {
			System.out.println("例外発生：" + ex);
			ex.printStackTrace();
			throw ex;
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
			} catch (SQLException se) {
				// 何もしない
			}
		}
		return IS_SUCCESS_CODE;
	}

	//ユーザー重複チェック
	public static int chkExistsPass(String s, String t) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBconnect.getConnection();
			// SQL文作成
			String mySql = "select count(*) as cnt from library.user;";
			// ステートメントオブジェクトを作成
			pstmt = con.prepareStatement(mySql);
			// 検索するSQL実行
			rs = pstmt.executeQuery();

			int count = 0; // 検索結果（件数）
			// 結果セットからデータを取り出す next()で次の行に移動
			while(rs.next()) {
				count = rs.getInt("cnt");
			}

			// 検索結果件数チェック
			if(count != 0) {
				return ERROR_CODE_1070;
			}

		} catch (Exception ex) {
			System.out.println("例外発生：" + ex);
			ex.printStackTrace();
			throw ex;
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
			} catch (SQLException se) {
				// 何もしない
			}
		}
		return IS_SUCCESS_CODE;
	}
	//購入登録重複チェック
		public static int chkExistsDate(int s) throws Exception {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = DBconnect.getConnection();
				// SQL文作成
				String mySql = "select count(*) as cnt from library.purchasehistory where book_id = ?;";
				// ステートメントオブジェクトを作成
				pstmt = con.prepareStatement(mySql);
//				検索条件
				pstmt.setInt(1, selectedBook.getId());
				// 検索するSQL実行
				rs = pstmt.executeQuery();

				int count = 0; // 検索結果（件数）
				// 結果セットからデータを取り出す next()で次の行に移動
				while(rs.next()) {
					count = rs.getInt("cnt");
				}

				// 検索結果件数チェック
				if(count != 0) {
					return ERROR_CODE_1070;
				}

			} catch (Exception ex) {
				System.out.println("例外発生：" + ex);
				ex.printStackTrace();
				throw ex;
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
				} catch (SQLException se) {
					// 何もしない
				}
			}
			return IS_SUCCESS_CODE;
		}

		//貸出登録重複チェック
		public static int chkExistsDate1(int s) throws Exception {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = DBconnect.getConnection();
				// SQL文作成
				String mySql = "select count(*) as cnt from library.lendinghistory where book_id = ?;";
				// ステートメントオブジェクトを作成
				pstmt = con.prepareStatement(mySql);
//				検索条件
				pstmt.setInt(1, selectedBook.getId());
				// 検索するSQL実行
				rs = pstmt.executeQuery();

				int count = 0; // 検索結果（件数）
				// 結果セットからデータを取り出す next()で次の行に移動
				while(rs.next()) {
					count = rs.getInt("cnt");
				}

				// 検索結果件数チェック
				if(count != 0) {
					return ERROR_CODE_1080;
				}

			} catch (Exception ex) {
				System.out.println("例外発生：" + ex);
				ex.printStackTrace();
				throw ex;
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
				} catch (SQLException se) {
					// 何もしない
				}
			}
			return IS_SUCCESS_CODE;
		}

		//購入登録時貸出中書籍と重複チェック
				public static int chkExistsDate3(int s) throws Exception {
					Connection con = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					try {
						con = DBconnect.getConnection();
						// SQL文作成
						String mySql = "select count(*) as cnt from library.lendinghistory where book_id = ?;";
						// ステートメントオブジェクトを作成
						pstmt = con.prepareStatement(mySql);
//						検索条件
						pstmt.setInt(1, selectedBook.getId());
						// 検索するSQL実行
						rs = pstmt.executeQuery();

						int count = 0; // 検索結果（件数）
						// 結果セットからデータを取り出す next()で次の行に移動
						while(rs.next()) {
							count = rs.getInt("cnt");
						}

						// 検索結果件数チェック
						if(count != 0) {
							return ERROR_CODE_1100;
						}

					} catch (Exception ex) {
						System.out.println("例外発生：" + ex);
						ex.printStackTrace();
						throw ex;
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
						} catch (SQLException se) {
							// 何もしない
						}
					}
					return IS_SUCCESS_CODE;
				}
				//貸出登録時に購入書籍と重複チェック
				public static int chkExistsDate4(int s) throws Exception {
					Connection con = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					try {
						con = DBconnect.getConnection();
						// SQL文作成
						String mySql = "select count(*) as cnt from library.purchasehistory where book_id = ?;";
						// ステートメントオブジェクトを作成
						pstmt = con.prepareStatement(mySql);
//						検索条件
						pstmt.setInt(1, selectedBook.getId());
						// 検索するSQL実行
						rs = pstmt.executeQuery();

						int count = 0; // 検索結果（件数）
						// 結果セットからデータを取り出す next()で次の行に移動
						while(rs.next()) {
							count = rs.getInt("cnt");
						}

						// 検索結果件数チェック
						if(count != 0) {
							return ERROR_CODE_1060;
						}

					} catch (Exception ex) {
						System.out.println("例外発生：" + ex);
						ex.printStackTrace();
						throw ex;
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
						} catch (SQLException se) {
							// 何もしない
						}
					}
					return IS_SUCCESS_CODE;
				}
//		 *更新データ存在チェック
		public static int chkExistsUpdData1( int i) throws Exception {
Connection con=null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = DBconnect.getConnection();
				// SQL文作成
				String mySql = "select count(*) as cnt from library.lendinghistory where book_id = ? and status =0;";
				// ステートメントオブジェクトを作成
				pstmt = con.prepareStatement(mySql);
				// 検索条件
			pstmt.setInt(1, selectedBook.getId());
				// 検索するSQL実行
				rs = pstmt.executeQuery();

				int count = 0; // 検索結果（件数）
				// 結果セットからデータを取り出す next()で次の行に移動
				while(rs.next()) {
					count = rs.getInt("cnt");
				}

				// 検索結果件数チェック

				if(count == 0) {
					return ERROR_CODE_1030;
				}

			} catch (Exception ex) {
				System.out.println("例外発生：" + ex);
				ex.printStackTrace();
				throw ex;
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
				} catch (SQLException se) {
					// 何もしない
				}
			}
			return IS_SUCCESS_CODE;
		}

		public static int chkExistsUpdData2(Connection con,int str) throws Exception {

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = DBconnect.getConnection();
				// SQL文作成
				String mySql = "select count(*) as cnt from library.lendinghistory where book_id = ? and status = 1 ;";
				// ステートメントオブジェクトを作成
				pstmt = con.prepareStatement(mySql);
				// 検索条件
				pstmt.setInt(1, selectedBook.getId());
				// 検索するSQL実行
				rs = pstmt.executeQuery();

				int count = 0; // 検索結果（件数）
				// 結果セットからデータを取り出す next()で次の行に移動
				while(rs.next()) {
					count = rs.getInt("cnt");
				}

				// 検索結果件数チェック
				if(count == 0) {
					return ERROR_CODE_1110;
				}

			} catch (Exception ex) {
				System.out.println("例外発生：" + ex);
				ex.printStackTrace();
				throw ex;
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
				} catch (SQLException se) {
					// 何もしない
				}
			}
			return IS_SUCCESS_CODE;
		}

	/**
	 * 表示データ存在チェック
	 *
	 * @param con
	 *            コネクション
	 * @return 判定結果
	 * @throws Exception
	 */
	public static int chkExistsData(Connection con) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// SQL文作成
			String mySql = "select count(*) as cnt from library.book;";
			// ステートメントオブジェクトを作成
			pstmt = con.prepareStatement(mySql);
			// 検索するSQL実行
			rs = pstmt.executeQuery();

			int count = 0; // 検索結果（件数）
			// 結果セットからデータを取り出す next()で次の行に移動
			while(rs.next()) {
				count = rs.getInt("cnt");
			}

			// 検索結果件数チェック
			if(count == 0) {
				return ERROR_CODE_1120;
			}

		} catch (Exception ex) {
			System.out.println("例外発生：" + ex);
			ex.printStackTrace();
			throw ex;
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
			} catch (SQLException se) {
				// 何もしない
			}
		}

		return IS_SUCCESS_CODE;
	}

	//重複チェック
		public static int chkExistsPass1(int  t) throws Exception {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = DBconnect.getConnection();
				// SQL文作成
				String mySql = "select count(*) as cnt from library.lendinghistory where book_id = ?  and status = 1;";
				// ステートメントオブジェクトを作成
				pstmt = con.prepareStatement(mySql);
				// 検索条件
				pstmt.setInt(1, selectedBook.getId());

				// 検索するSQL実行
				rs = pstmt.executeQuery();

				int count = 0; // 検索結果（件数）
				// 結果セットからデータを取り出す next()で次の行に移動
				while(rs.next()) {
					count = rs.getInt("cnt");
				}

				// 検索結果件数チェック
				if(count != 0) {
					return ERROR_CODE_1090;
				}

			} catch (Exception ex) {
				System.out.println("例外発生：" + ex);
				ex.printStackTrace();
				throw ex;
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
				} catch (SQLException se) {
					// 何もしない
				}
			}
			return IS_SUCCESS_CODE;
		}


	public static void setSelectedBook(Book book) {
		selectedBook = book;

	}


	}

