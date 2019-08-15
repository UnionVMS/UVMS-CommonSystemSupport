package fish.focus.uvms.commonsystemsupport.jdbc.resultsetadapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "ResultSet",namespace = "http://www.havochvatten.se")
public class StringResultSet {

	List<RecordSet> rows = null;

	public StringResultSet() {
		// required by JSON
	}

	public StringResultSet(List<RecordSet> rows) {
		this.rows = new ArrayList<RecordSet>();
		this.rows.addAll(rows);
	}

	@XmlElement(name = "Rows")
	public List<RecordSet> getRows() {
		if (this.rows == null) {
			this.rows = new ArrayList<RecordSet>();
		}
		return this.rows;
	}

	public void setRows(List<RecordSet> rows) {
		this.rows = new ArrayList<RecordSet>();
		this.rows.addAll(rows);
	}

	public void addRow(RecordSet row) {
		if (this.rows == null) {
			this.rows = new ArrayList<RecordSet>();
		}
		this.rows.add(row);
	}

	@Override
	public boolean equals(Object aThat) {
		if (this == aThat) {
			return true;
		}
		if (!(aThat instanceof StringResultSet)) {
			return false;
		}
		StringResultSet that = (StringResultSet) aThat;

		if (this.getRows().size() != that.getRows().size()) {
			return false;
		}

		Iterator<RecordSet> thisRows = this.getRows().iterator();
		Iterator<RecordSet> thatRows = that.getRows().iterator();
		while (thisRows.hasNext()) {
			RecordSet thisRow = thisRows.next();
			RecordSet thatRow = thatRows.next();
			if (!thisRow.equals(thatRow)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return this.getRows().toString();
	}

	@Override
	public StringResultSet clone() {
		return new StringResultSet(this.getRows());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rows == null) ? 0 : rows.hashCode());
		return result;
	}

}
