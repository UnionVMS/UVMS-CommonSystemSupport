package fish.focus.uvms.commonsystemsupport.jdbc.resultsetadapter;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.management.InvalidAttributeValueException;

public class JDBCAdapter {

	public StringResultSet convert(java.sql.ResultSet rs) throws SQLException {

		StringResultSet ret = new StringResultSet();
		if (rs == null) {
			return ret;
		}
		if (rs.isClosed()) {
			return ret;
		}

		ResultSetMetaData md = rs.getMetaData();
		int cols = md.getColumnCount();
		String names[] = new String[cols];
		for (int i = 1; i <= cols; i++) {
			String name = md.getColumnName(i);
			names[i - 1] = name;
		}

		while (rs.next()) {
			RecordSet recSet = new RecordSet();
			for (int i = 1; i <= cols; i++) {
				String value = rs.getString(i);
				try {
					NameValue nvp = new NameValue(names[i - 1], value);
					recSet.addColumn(nvp);
				} catch (InvalidAttributeValueException e) {
					// EMPTY
				}
			}
			ret.addRow(recSet);
		}
		return ret;
	}

}
