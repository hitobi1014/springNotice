package kr.or.ddit.user.model;

public class ManagerVo {
	private String mng_id;
	private String mng_pass;
	
	public String getMng_id() {
		return mng_id;
	}
	public void setMng_id(String mng_id) {
		this.mng_id = mng_id;
	}
	public String getMng_pass() {
		return mng_pass;
	}
	public void setMng_pass(String mng_pass) {
		this.mng_pass = mng_pass;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mng_id == null) ? 0 : mng_id.hashCode());
		result = prime * result + ((mng_pass == null) ? 0 : mng_pass.hashCode());
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
		ManagerVo other = (ManagerVo) obj;
		if (mng_id == null) {
			if (other.mng_id != null)
				return false;
		} else if (!mng_id.equals(other.mng_id))
			return false;
		if (mng_pass == null) {
			if (other.mng_pass != null)
				return false;
		} else if (!mng_pass.equals(other.mng_pass))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "ManagerVo [mng_id=" + mng_id + ", mng_pass=" + mng_pass + "]";
	}
	
}
