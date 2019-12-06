package jp.jc21.jk3a.gousetu.JDBC;

import jp.jc21.jk3a.gousetu.gousetuDbInterface;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Gakka implements gousetuDbInterface {

	String GakkaID;
	String GakkaName;

	public Gakka(String gakkaId, String gakkaName) {
		setGakkaID(gakkaId);
		setGakkaName(gakkaName);

	}

	public String getGakkaID() {
		return GakkaID;
	}

	public void setGakkaID(String gakkaID) {
		GakkaID = gakkaID;
	}

	public String getGakkaName() {
		return GakkaName;
	}

	public void setGakkaName(String gakkaName) {
		GakkaName = gakkaName;
	}

	/////////////////////////////////////////////////////////////////////////

	public static boolean isCreated() {
		try {
			Connection connection = gousetuDbInterface.getStatement();

			PreparedStatement ps = connection
					.prepareStatement("select count(*) as COUNTER from user_tables where TABLE_NAME=?");
			ps.setString(1, "GAKKA");
			ResultSet rs = ps.executeQuery();
			rs.next();
			int counter = rs.getInt("counter");
			if (counter > 0) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean isStored() {
		try {
			Connection connection = gousetuDbInterface.getStatement();

			PreparedStatement ps = connection.prepareStatement("select count(*) as counter from GAKKA");
			ResultSet rs = ps.executeQuery();
			rs.next();
			int counter = rs.getInt("counter");
			if (counter > 0) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean create() {
		try {
			Connection connection = gousetuDbInterface.getStatement();
			PreparedStatement ps;

			if (isCreated() == true) {
				ps = connection.prepareStatement("drop table GAKKA ");
				ps.executeUpdate();
			}
			ps = connection.prepareStatement(
					"create table GAKKA (" + "GAKKA_ID char(2) primary key , " + "GAKKA_NAME varchar(40) )");
			ps.executeUpdate();
			ps = connection.prepareStatement("insert into GAKKA(GAKKA_ID,GAKKA_NAME) values('JK','システムエンジニア科')");
			ps.executeUpdate();
			ps = connection.prepareStatement("insert into GAKKA(GAKKA_ID,GAKKA_NAME) values('JS','情報システム科')");
			ps.executeUpdate();
			ps = connection.prepareStatement("commit");
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean drop()  {
		if(isCreated() == false)return true;

		Connection connection = gousetuDbInterface.getStatement();
		try {

		PreparedStatement ps;
		ps = connection.prepareStatement("drop table GAKKA ");
		ps.executeUpdate();
		return true;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	static List<Gakka> get() {
		Connection connection = gousetuDbInterface.getStatement();
		List<Gakka> list = new ArrayList<Gakka>();

		try {

			PreparedStatement ps = connection.prepareStatement("select GAKKA_ID,GAKKA_NAME from GAKKA");
			ResultSet rs = ps.executeQuery();
			while (rs.next() == true) {
				Gakka g = new Gakka(rs.getString("GAKKA_ID"), rs.getString("GAKKA_NAME"));
				list.add(g);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

}
