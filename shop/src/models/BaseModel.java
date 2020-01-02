package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BaseModel {
	private static MyORM orm = MyORM.getObject("mysql", "localhost", "shop", "root", "");
	private static Connection con;
	private static PreparedStatement ps;
	private static ResultSet rs;
	private static ArrayList<Good> goods = new ArrayList<Good>();
	private static ArrayList<Basket> basket = new ArrayList<Basket>();

	public static ArrayList<Good> getGoods() throws SQLException {
		con = orm.getConnection();
		ps = con.prepareStatement("select * from goods");
		rs = ps.executeQuery();

		goods.clear();
		
			while(rs.next()) {
				int id = rs.getInt("id");
				int price = rs.getInt("price");
				int weight = rs.getInt("weight");
				String sku = rs.getString("sku");
				String name = rs.getString("name");
				String image = rs.getString("image");
				String description = rs.getString("description");
				goods.add(new Good(id, sku, name, weight, price, image, description));
			}
		return goods;
	}
	
	public static void add2Basket (int idGood, int quantity) {
		if (!BaseModel.isInBasket(idGood)) {
			orm.update("insert into `basket` (`user`, `quantity`, `good`) values ('500', '"+ quantity+"', '"+idGood+"')");
		}
		else {
			orm.update("update basket set quantity="+ quantity+" where good="+idGood);
		}
	}
	
	public static boolean isInBasket (int idGood) {
		int count = orm.countStr("select id from basket where good="+idGood);
		if (count>0) {
			return true;
		}
		return false;
	}

	public static ArrayList<Basket> getBasket() throws SQLException {
		con = orm.getConnection();
		ps = con.prepareStatement("select basket.id, good, quantity, sku, name, price from basket join goods on basket.good=goods.id where user = '500'");
		rs = ps.executeQuery();
		
		basket.clear();
		
			while(rs.next()) {
				int id = rs.getInt("id");
				int good = rs.getInt("good");
				int price = rs.getInt("price");
				int quantity = rs.getInt("quantity");
				String sku = rs.getString("sku");
				String name = rs.getString("name");
				basket.add(new Basket(id, good, sku, name, quantity, price));
			}
		return basket;
	}
}
