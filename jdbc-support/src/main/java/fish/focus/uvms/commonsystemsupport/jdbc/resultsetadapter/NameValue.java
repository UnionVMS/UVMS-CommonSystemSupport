package fish.focus.uvms.commonsystemsupport.jdbc.resultsetadapter;

import javax.management.InvalidAttributeValueException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "NameValue",namespace = "http://www.havochvatten.se")
public class NameValue {

	String name;
	String value;

	public NameValue() {
		// required by JSON
	}

	public NameValue(String name, String value) throws InvalidAttributeValueException {
		if (name == null) {
			throw new InvalidAttributeValueException("Name cannot have a value of null");
		}
		this.name = name;
		this.value = value;
	}

	@XmlElement(name = "Name")
	public String getName() {
		return name;
	}

	public void setName(String name) throws InvalidAttributeValueException {
		if (name == null) {
			throw new InvalidAttributeValueException("Name cannot have a value of null");
		}
		this.name = name;
	}

	@XmlElement(name = "Value")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return name + "=" + value;
	}

	@Override
	public NameValue clone() {
		try {
			return new NameValue(name, value);
		} catch (InvalidAttributeValueException e) {
			return null;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NameValue other = (NameValue) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
}
