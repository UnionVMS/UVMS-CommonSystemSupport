package fish.focus.uvms.commonsystemsupport.jdbc.resultsetadapter;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RecordSet",namespace = "http://www.havochvatten.se")
public class RecordSet {

	List<NameValue> columns;

	public RecordSet() {
		// required by JSON
	}

	public RecordSet(List<NameValue> columns) {
		this.columns = new ArrayList<NameValue>();
		this.columns.addAll(columns);
	}

	@XmlElement(name = "Columns")
	public List<NameValue> getColumns() {
		if (this.columns == null) {
			this.columns = new ArrayList<NameValue>();
		}
		return this.columns;
	}

	public void setColumns(List<NameValue> columns) {
		this.columns = new ArrayList<NameValue>();
		this.columns.addAll(columns);
	}

	public void addColumn(NameValue nameValue) {
		if (this.columns == null) {
			this.columns = new ArrayList<NameValue>();
		}
		this.columns.add(nameValue);
	}

	@Override
	public boolean equals(Object aThat) {
		if (this == aThat) {
			return true;
		}
		if (!(aThat instanceof RecordSet)) {
			return false;
		}
		RecordSet that = (RecordSet) aThat;

		if (this.getColumns().size() != that.getColumns().size()) {
			return false;
		}
		List<NameValue> wrkThisNameValue = this.getColumns();
		List<NameValue> wrkThatNameValue = that.getColumns();
		for (NameValue NVP : wrkThisNameValue) {
			if (!wrkThatNameValue.contains(NVP)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return "items#" + getColumns().size() + "    " + getColumns().toString();
	}

	@Override
	public RecordSet clone() {
		return new RecordSet(this.getColumns());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((columns == null) ? 0 : columns.hashCode());
		return result;
	}

}
