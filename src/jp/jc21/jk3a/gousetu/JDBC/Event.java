package jp.jc21.jk3a.gousetu.JDBC;

import jp.jc21.jk3a.gousetu.gousetuDbInterface;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Event implements gousetuDbInterface {

	String eventId;
	String eventName;

	public Event(String eventId, String eventName) {
		setEventId(eventId);
		setEventName(eventName);
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	/////////////////////////////////////////////////////////////////////////

	public static boolean isCreated() {
		try {
			Connection connection = gousetuDbInterface.getStatement();

			PreparedStatement ps = connection
					.prepareStatement("select count(*) as COUNTER from user_tables where TABLE_NAME=?");
			ps.setString(1, "EVENT");
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

			PreparedStatement ps = connection.prepareStatement("select count(*) as counter from EVENT");
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
				ps = connection.prepareStatement("drop table EVENT ");
				ps.executeUpdate();
			}
			ps = connection.prepareStatement(
					"create table EVENT (" + "EVENT_ID char(6) primary key , " + "EVENT_NAME varchar(40) )");
			ps.executeUpdate();
			ps = connection.prepareStatement("insert into EVENT(EVENT_ID,EVENT_NAME) values('R01_01','3月5日午前学内合説')");
			ps.executeUpdate();
			ps = connection.prepareStatement("insert into EVENT(EVENT_ID,EVENT_NAME) values('R01_03','3月6日午前学内合説')");
			ps.executeUpdate();
			ps = connection.prepareStatement("insert into EVENT(EVENT_ID,EVENT_NAME) values('R01_10','4月21日MISA合説')");
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
			ps = connection.prepareStatement("drop table EVENT ");
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	static List<Event> get() {
		Connection connection = gousetuDbInterface.getStatement();
		List<Event> list = new ArrayList<Event>();

		try {

			PreparedStatement ps = connection.prepareStatement("select EVENT_ID,EVENT_NAME from EVENT");
			ResultSet rs = ps.executeQuery();
			while (rs.next() == true) {
				Event g = new Event(rs.getString("EVENT_ID"), rs.getString("EVENT_NAME"));
				list.add(g);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

}
