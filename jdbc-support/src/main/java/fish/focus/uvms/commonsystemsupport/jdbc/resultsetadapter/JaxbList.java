package fish.focus.uvms.commonsystemsupport.jdbc.resultsetadapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "JaxbList",namespace = "http://www.havochvatten.se")
public class JaxbList<T> {

	List<T> list;

	public JaxbList() {
		// required by JSON
	}

	public JaxbList(List<T> dta) {
		list = new ArrayList<T>();
		list.addAll(dta);
	}

	@XmlElement(name = "List")
	public List<T> getList() {
		if (list == null) {
			list = new ArrayList<T>();
		}
		return list;
	}

	public void setList(List<T> dta) {
		list = new ArrayList<T>();
		list.addAll(dta);
	}

	public void addElement(T dta) {
		if (list == null) {
			list = new ArrayList<T>();
		}
		list.add(dta);
	}

	@Override
	public boolean equals(Object aThat) {
		if (this == aThat) {
			return true;
		}
		if (!(aThat instanceof JaxbList<?>)) {
			return false;
		}
		@SuppressWarnings("unchecked")
		JaxbList<T> that = (JaxbList<T>) aThat;

	
		if (this.getList().size() != that.getList().size()) {
			return false;
		}

		Iterator<T> thisIter = this.getList().iterator();
		Iterator<T> thatIter = that.getList().iterator();
		while (thisIter.hasNext()) {
			T thisT = thisIter.next();
			T thatT = thatIter.next();
			if (!thisT.equals(thatT)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.getList() == null) ? 0 : this.getList().hashCode());
		return result;
	}

	@Override
	public String toString() {
		return this.getList().toString();
	}

	@Override
	public JaxbList<T> clone() {
		return new JaxbList<T>(this.getList());
	
	}

}
