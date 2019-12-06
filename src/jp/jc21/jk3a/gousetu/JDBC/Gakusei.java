package jp.jc21.jk3a.gousetu.JDBC;

import jp.jc21.jk3a.gousetu.gousetuDbInterface;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Gakusei implements gousetuDbInterface {

	String gakuseiId;
	String gakuseiName;
	String gakkaId;
	
	public String getGakuseiId() {
		return gakuseiId;
	}

	public void setGakuseiId(String gakuseiId) {
		this.gakuseiId = gakuseiId;
	}

	public String getGakuseiName() {
		return gakuseiName;
	}

	public void setGakuseiName(String gakuseiName) {
		this.gakuseiName = gakuseiName;
	}

	public String getGakkaId() {
		return gakkaId;
	}

	public void setGakkaId(String gakkaId) {
		this.gakkaId = gakkaId;
	}

	public Gakusei(String gakuseiId,String gakuseiName,String gakkaId) {
		setGakuseiId(gakuseiId);
		setGakuseiName(gakuseiName);
		setGakkaId(gakkaId);
	}

	/////////////////////////////////////////////////////////////////////////

	public static boolean isCreated() {
		try {
			Connection connection = gousetuDbInterface.getStatement();

			PreparedStatement ps = connection
					.prepareStatement("select count(*) as COUNTER from user_tables where TABLE_NAME=?");
			ps.setString(1, "GAKUSEI");
			ResultSet rs = ps.executeQuery();
			rs.next();
			int counter = rs.getInt("counter");
			if (counter > 0) {
				return true;
			} else {
				return false;
			}

		} catch ( SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean isStored() {
		try {
			Connection connection = gousetuDbInterface.getStatement();

			PreparedStatement ps = connection.prepareStatement("select count(*) as counter from GAKUSEI");
			ResultSet rs = ps.executeQuery();
			rs.next();
			int counter = rs.getInt("counter");
			if (counter > 0) {
				return true;
			} else {
				return false;
			}

		} catch ( SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean create() {
		try {
			Connection connection = gousetuDbInterface.getStatement();
			PreparedStatement ps;

			if (isCreated() == true) {
				drop();
			}
			ps = connection.prepareStatement(
					"create table GAKUSEI (" + "GAKUSEKI_ID char(6) primary key , " + "GAKUSEI_NAME varchar(100) ,"+
			"GAKKA_ID char(2) references GAKKA(GAKKA_ID) "+
			")");
			ps.executeUpdate();
			ps = connection.prepareStatement("insert into GAKUSEI(GAKUSEKI_ID,GAKUSEI_NAME,GAKKA_ID) "+
					"values('140100','èHéRÅ@Ç†Ç©ÇË','JK')");
			ps.executeUpdate();
			ps = connection.prepareStatement("insert into GAKUSEI(GAKUSEKI_ID,GAKUSEI_NAME,GAKKA_ID) "+
					"values('140583','à…ì°Å@ä≤','JK')");
			ps.executeUpdate();
			ps = connection.prepareStatement("insert into GAKUSEI(GAKUSEKI_ID,GAKUSEI_NAME,GAKKA_ID) "+
					"values('140586','è„ñÏÅ@äCîg','JK')");
			ps.executeUpdate();
			ps = connection.prepareStatement("commit");
			ps.executeUpdate();
			return true;
		} catch ( SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean drop()  {
		if(isCreated() == false)return true;
		Connection connection = gousetuDbInterface.getStatement();
		try {

		PreparedStatement ps;
		ps = connection.prepareStatement("drop table GAKUSEI ");
		ps.executeUpdate();
		return true;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	


	static  List<Gakusei> get() {
		Connection connection = gousetuDbInterface.getStatement();
		List<Gakusei> list = new ArrayList<Gakusei>();

		try {

			PreparedStatement ps = connection.prepareStatement("select GAKUSEKI_ID,GAKUSEI_NAME,GAKKA_ID from GAKUSEI");
			ResultSet rs = ps.executeQuery();
			while (rs.next() == true) {
				Gakusei g = new Gakusei(
						rs.getString("GAKUSEKI_ID"), 
						rs.getString("GAKUSEI_NAME"), 
						rs.getString("GAKKA_ID")
						);
				list.add(g);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
