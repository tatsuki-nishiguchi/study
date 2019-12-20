package book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Batch {

public static void main(String args[]) throws ClassNotFoundException,SQLException{
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library?characterEncording=UTF-8&serverTimezone=JST","root","nishiguchi99");


String sql ="insert into book values(?,?,?,?,?,?);";
PreparedStatement statement =con.prepareStatement(sql);


statement.setInt(1, 1);
statement.setString(2, "ハイキュー");
statement.setString(3, "ハイキュー");
statement.setString(4, "ハイキュー");
statement.setString(5, "アニメ");
statement.setInt(6, 300);
statement.addBatch();

sql="delete product where price>=400";
statement.addBatch(sql);
int result[]=statement.executeBatch();

for(int i=0;i<result.length;i++){
  System.out.println("result"+i+": "+result[i]);
}
}}


