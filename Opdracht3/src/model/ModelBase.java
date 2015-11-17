package model;

import java.io.Serializable;

public class ModelBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3240656120744443938L;
	
	private int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		ModelBase other = (ModelBase) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ModelBase [id=" + id + "]";
	}
	
}
