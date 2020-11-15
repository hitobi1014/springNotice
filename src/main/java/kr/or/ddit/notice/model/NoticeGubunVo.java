package kr.or.ddit.notice.model;

public class NoticeGubunVo {
	private String ntgu_code;
	private int ntgu_num;
	private String ntgu_name;
	private int ntgu_stat;
	
	public int getNtgu_stat() {
		return ntgu_stat;
	}
	public void setNtgu_stat(int ntgu_stat) {
		this.ntgu_stat = ntgu_stat;
	}
	public String getNtgu_code() {
		return ntgu_code;
	}
	public void setNtgu_code(String ntgu_code) {
		this.ntgu_code = ntgu_code;
	}
	public int getNtgu_num() {
		return ntgu_num;
	}
	public void setNtgu_num(int ntgu_num) {
		this.ntgu_num = ntgu_num;
	}
	public String getNtgu_name() {
		return ntgu_name;
	}
	public void setNtgu_name(String ntgu_name) {
		this.ntgu_name = ntgu_name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ntgu_code == null) ? 0 : ntgu_code.hashCode());
		result = prime * result + ((ntgu_name == null) ? 0 : ntgu_name.hashCode());
		result = prime * result + ntgu_num;
		result = prime * result + ntgu_stat;
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
		NoticeGubunVo other = (NoticeGubunVo) obj;
		if (ntgu_code == null) {
			if (other.ntgu_code != null)
				return false;
		} else if (!ntgu_code.equals(other.ntgu_code))
			return false;
		if (ntgu_name == null) {
			if (other.ntgu_name != null)
				return false;
		} else if (!ntgu_name.equals(other.ntgu_name))
			return false;
		if (ntgu_num != other.ntgu_num)
			return false;
		if (ntgu_stat != other.ntgu_stat)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "NoticeGubunVo [ntgu_code=" + ntgu_code + ", ntgu_num=" + ntgu_num + ", ntgu_name=" + ntgu_name
				+ ", ntgu_stat=" + ntgu_stat + "]";
	}
	
}
