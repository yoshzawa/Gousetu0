package jp.jc21.jk3a.gousetu.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.jc21.jk3a.gousetu.gousetuDbInterface;

public class Company implements gousetuDbInterface {

	String companyId;
	String companyName;
	String eventId;
	int capacity;

	public Company(String companyId, String companyName,String eventId,int capacity) {
		setCompanyId(companyId);
		setCompanyName(companyName);
		setEventId(eventId);
		setCapacity(capacity);
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/////////////////////////////////////////////////////////////////////////

	public static boolean isCreated() {
		try {
			Connection connection = gousetuDbInterface.getStatement();

			PreparedStatement ps = connection
					.prepareStatement("select count(*) as COUNTER from user_tables where TABLE_NAME=?");
			ps.setString(1, "COMPANY");
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

			PreparedStatement ps = connection.prepareStatement("select count(*) as counter from COMPANY");
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
				drop();
			}
			ps = connection.prepareStatement(
					"create table COMPANY (" + "COMPANY_ID char(8) primary key , " + "COMPANY_NAME varchar(100), "+
						" EVENT_ID char(6) references EVENT(EVENT_ID) ,CAPACITY NUMBER(3))");
			ps.executeUpdate();
			ps = connection.prepareStatement("insert into COMPANY(COMPANY_ID, COMPANY_NAME,EVENT_ID,CAPACITY) "+
																					"values('10-1129','焼肉居酒屋　和牛','R01_01',30)");
			ps.executeUpdate();
			ps = connection.prepareStatement("insert into COMPANY(COMPANY_ID, COMPANY_NAME,EVENT_ID,CAPACITY) "+
					"values('10-1001','株式会社ボーダーラインホールディングス','R01_01',30)");
			ps.executeUpdate();
			ps = connection.prepareStatement("insert into COMPANY(COMPANY_ID, COMPANY_NAME,EVENT_ID,CAPACITY) "+
					"values('10-1002','株式会社トペコンヒーロ','R01_01',65)");
			ps.executeUpdate();
			ps = connection.prepareStatement("commit");
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean drop() {
		if (isCreated() == false)
			return true;

		Connection connection = gousetuDbInterface.getStatement();
		try {

			PreparedStatement ps;
			ps = connection.prepareStatement("drop table COMPANY ");
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	static List<Company> get() {
		Connection connection = gousetuDbInterface.getStatement();
		List<Company> list = new ArrayList<Company>();

		try {

			PreparedStatement ps = connection.prepareStatement("select COMPANY_ID, COMPANY_NAME,EVENT_ID,CAPACITY from COMPANY");
			ResultSet rs = ps.executeQuery();
			while (rs.next() == true) {
				Company g = new Company(
						rs.getString("COMPANY_ID"), 
						rs.getString("COMPANY_NAME"), 
						rs.getString("EVENT_ID"), 
						rs.getInt("CAPACITY")
						);
				list.add(g);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

}
