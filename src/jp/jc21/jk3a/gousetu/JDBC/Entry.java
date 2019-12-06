package jp.jc21.jk3a.gousetu.JDBC;

import jp.jc21.jk3a.gousetu.gousetuDbInterface;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Entry implements gousetuDbInterface {

	String entryId;
	String eventId;
	String gakusekiId;

	public Entry(String entryId, String eventId, String gakuseiId) {
	}

	public String getEntryId() {
		return entryId;
	}

	public void setEntryId(String entryId) {
		this.entryId = entryId;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getGakusekiId() {
		return gakusekiId;
	}

	public void setGakuseiId(String gakusekiId) {
		this.gakusekiId = gakusekiId;
	}

	/////////////////////////////////////////////////////////////////////////

	public static boolean isCreated() {
		try {
			Connection connection = gousetuDbInterface.getStatement();

			PreparedStatement ps = connection
					.prepareStatement("select count(*) as COUNTER from user_tables where TABLE_NAME=?");
			ps.setString(1, "ENTRY");
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

			PreparedStatement ps = connection.prepareStatement("select count(*) as counter from ENTRY");
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
					"create table ENTRY (" + "ENTRY_ID number(8) primary key , " + 
							" EVENT_ID char(6) references Event(EVENT_ID), "+
							" GAKUSEKI_ID char(6) references GAKUSEI(GAKUSEKI_ID) )");
			ps.executeUpdate();
			ps = connection.prepareStatement("insert into ENTRY( ENTRY_ID,  EVENT_ID,  GAKUSEKI_ID) "
					+ "values(1,'R01_01','140100')");
			ps = connection.prepareStatement("insert into ENTRY( ENTRY_ID,  EVENT_ID,  GAKUSEKI_ID) "
					+ "values(2,'R01_01','140583')");
			ps = connection.prepareStatement("insert into ENTRY( ENTRY_ID,  EVENT_ID,  GAKUSEKI_ID) "
					+ "values(3,'R01_01','140586')");
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
			ps = connection.prepareStatement("drop table ENTRY ");
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	static List<Entry> get() {
		Connection connection = gousetuDbInterface.getStatement();
		List<Entry> list = new ArrayList<Entry>();

		try {

			PreparedStatement ps = connection
					.prepareStatement("select ENTRY_ID,  EVENT_ID,  GAKUSEKI_ID from ENTRY");
			ResultSet rs = ps.executeQuery();
			while (rs.next() == true) {
				Entry g = new Entry(rs.getString("ENTRY_ID"), rs.getString("EVENT_ID"), rs.getString("GAKUSEKI_ID"));
				list.add(g);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

}
