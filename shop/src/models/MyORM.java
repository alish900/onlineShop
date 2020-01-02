package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TimeZone;

public class MyORM {
	private static MyORM object;
	private Connection con;
	private ResultSet rs;
	private Statement stmt;
	private static String dbtype = "mysql"; // параметры по умолчанию
	private static String server = "localhost";
	private static String database = "shop";
	private static String login = "root";
	private static String password = "";

	public MyORM (String dbtype, String server, String database, String login, String password) {
		try {
				switch (dbtype) {
				case "mysql":
					Class.forName("com.mysql.cj.jdbc.Driver");
					break;
				case "microsoft:sqlserver":
					Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
				}
			}
		catch (ClassNotFoundException e) {
			getExceptionMessage(e);
		}
		String url = "jdbc:" + dbtype + "://" + server + "/" + database + "?serverTimezone="+TimeZone.getDefault().getID();
		try {
			con = DriverManager.getConnection(url, login, password);
			stmt = con.createStatement();
		} 
		catch (SQLException e) {
			getExceptionMessage(e);
		}
	}
	
	public static MyORM getObject() { // метод вызова c параметрами по умолчанию
		if(object==null) {
			object = new MyORM (dbtype, server, database, login, password);
		}
		return object;
	}
	
	public static MyORM getObject(String dbtype, String server, String database, String login, String password) {
		if(object==null) { // параметризованный метод вызова
			object = new MyORM (dbtype, server, database, login, password);
		}
		return object;
	}
	
	public Connection getConnection() {
		if(con==null) { 
			con = MyORM.getObject().con;
		}
		return con;
	}
	
	public static void getExceptionMessage (Exception e) {
		System.out.println(e.getMessage());
	}
	
	// подсчет записей
	public int countStr(String sql) {
		int count = 0;
		try {
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				count++;
			}
		} 
		catch (SQLException e) {
			getExceptionMessage(e);
		}
		return count;
	}
	
	// подсчет полей
	public int countFields(String table) {
		int result=0;
		String sql = "select * from "+table+" LIMIT 1";
		try {
			rs = stmt.executeQuery(sql);
			result = rs.getMetaData().getColumnCount();
		} 
		catch (SQLException e) {
			getExceptionMessage(e);
		}
		return result;
	}
	
	// универсальный селект
	public ResultSet select (String sql) {
		try {
			rs = stmt.executeQuery(sql);
		} 
		catch (SQLException e) {
			getExceptionMessage(e);
		}
		return rs;
	}
	
	// универсальный апдейт (работает также с INSERT INTO, DELETE etc), потом может быть напишу отдельные методы
	public void update (String sql) {
		try {
			stmt.executeUpdate(sql);
		} 
		catch (SQLException e) {
			getExceptionMessage(e);
		}
	}
	
	//запрос UPDATE с HashMap "update table set key=value,key2=value2 where ..."
	public void update (String table, HashMap<String, Object> data, String where) {
		String sql = "update " + table + " set ";
		for(Entry<String, Object> element : data.entrySet()) {  // обходим хэшмэп
			sql += element.getKey() + " = " + "'" + element.getValue() + "', ";
		}
		sql = sql.substring(0, sql.length()-2);  // удаляем запятую в конце строки
		sql += " where " + where + " \n";
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			getExceptionMessage(e);
		}
		//System.out.println(sql);
	}

}
