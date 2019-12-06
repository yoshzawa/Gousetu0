package jp.jc21.jk3a.gousetu.JDBC;

import jp.jc21.jk3a.gousetu.gousetuDbInterface;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntryCompany implements gousetuDbInterface {

	String entryId;
	int period;
	String companyId;
	Date entryDate;

	public EntryCompany(String entryId, int period, String companyId, Date entryDate) {
		setEntryId(entryId);
		setPeriod(period);
		setCompanyId(companyId);
		setEntryDate(entryDate);
	}

	public String getEntryId() {
		return entryId;
	}

	public void setEntryId(String entryId) {
		this.entryId = entryId;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	/////////////////////////////////////////////////////////////////////////

	public static boolean isCreated() {
		try {
			Connection connection = gousetuDbInterface.getStatement();

			PreparedStatement ps = connection
					.prepareStatement("select count(*) as COUNTER from user_tables where TABLE_NAME=?");
			ps.setString(1, "ENTRY_COMPANY");
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

			PreparedStatement ps = connection.prepareStatement("select count(*) as counter from ENTRY_COMPANY");
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
			ps = connection.prepareStatement("create table ENTRY_COMPANY (" + 
					"ENTRY_ID number(8) references ENTRY_ID(ENTRY_ID) , "+
					"PERIOD number(1) ,"+
					 " COMPANY_ID char(8) references COMPANY(COMPANY_ID), "+
					 " ENTRY_DATE date default sysdate )");
			ps.executeUpdate();
			ps = connection.prepareStatement(
					"insert into ENTRY_COMPANY(  ENTRY_ID,  PERIOD,  COMPANY_ID) " + "values(1,1,'10-1129')");
			ps.executeUpdate();
			ps = connection.prepareStatement(
					"insert into ENTRY_COMPANY(  ENTRY_ID,  PERIOD,  COMPANY_ID) " + "values(2,1,'10-1001')");
			ps.executeUpdate();
			ps = connection.prepareStatement(
					"insert into ENTRY_COMPANY(  ENTRY_ID,  PERIOD,  COMPANY_ID) " + "values(3,1,'10-1129')");
			ps.executeUpdate();
			ps = connection.prepareStatement(
					"insert into ENTRY_COMPANY(  ENTRY_ID,  PERIOD,  COMPANY_ID) " + "values(3,2,'10-1002')");
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
			ps = connection.prepareStatement("drop table ENTRY_COMPANY ");
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	static List<EntryCompany> get() {
		Connection connection = gousetuDbInterface.getStatement();
		List<EntryCompany> list = new ArrayList<EntryCompany>();

		try {

			PreparedStatement ps = connection.prepareStatement("select ENTRY_ID,  PERIOD,  COMPANY_ID , ENTRY_DATE from ENTRY_COMPANY");
			ResultSet rs = ps.executeQuery();
			while (rs.next() == true) {
				EntryCompany g = new EntryCompany(rs.getString("ENTRY_ID"), rs.getInt("PERIOD"),
						rs.getString("COMPANY_ID"),rs.getDate("ENTRY_DATE"));
				list.add(g);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

}
