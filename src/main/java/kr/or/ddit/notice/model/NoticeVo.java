package kr.or.ddit.notice.model;

import java.util.Date;

public class NoticeVo {
	private int nt_num;
	private String ntgu_code;
	private String user_id;
	private int nt_stat;
	private int ntcont_stat;
	private Date nt_dt;
	private String nt_cont;
	private String nt_title;
	private int nt_panum;
	private int level;
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getNt_num() {
		return nt_num;
	}
	public void setNt_num(int nt_num) {
		this.nt_num = nt_num;
	}
	public String getNtgu_code() {
		return ntgu_code;
	}
	public void setNtgu_code(String ntgu_code) {
		this.ntgu_code = ntgu_code;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getNt_stat() {
		return nt_stat;
	}
	public void setNt_stat(int nt_stat) {
		this.nt_stat = nt_stat;
	}
	public int getNtcont_stat() {
		return ntcont_stat;
	}
	public void setNtcont_stat(int ntcont_stat) {
		this.ntcont_stat = ntcont_stat;
	}
	public Date getNt_dt() {
		return nt_dt;
	}
	public void setNt_dt(Date nt_dt) {
		this.nt_dt = nt_dt;
	}
	public String getNt_cont() {
		return nt_cont;
	}
	public void setNt_cont(String nt_cont) {
		this.nt_cont = nt_cont;
	}
	public String getNt_title() {
		return nt_title;
	}
	public void setNt_title(String nt_title) {
		this.nt_title = nt_title;
	}
	public int getNt_panum() {
		return nt_panum;
	}
	public void setNt_panum(int nt_panum) {
		this.nt_panum = nt_panum;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + level;
		result = prime * result + ((nt_cont == null) ? 0 : nt_cont.hashCode());
		result = prime * result + ((nt_dt == null) ? 0 : nt_dt.hashCode());
		result = prime * result + nt_num;
		result = prime * result + nt_panum;
		result = prime * result + nt_stat;
		result = prime * result + ((nt_title == null) ? 0 : nt_title.hashCode());
		result = prime * result + ntcont_stat;
		result = prime * result + ((ntgu_code == null) ? 0 : ntgu_code.hashCode());
		result = prime * result + ((user_id == null) ? 0 : user_id.hashCode());
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
		NoticeVo other = (NoticeVo) obj;
		if (level != other.level)
			return false;
		if (nt_cont == null) {
			if (other.nt_cont != null)
				return false;
		} else if (!nt_cont.equals(other.nt_cont))
			return false;
		if (nt_dt == null) {
			if (other.nt_dt != null)
				return false;
		} else if (!nt_dt.equals(other.nt_dt))
			return false;
		if (nt_num != other.nt_num)
			return false;
		if (nt_panum != other.nt_panum)
			return false;
		if (nt_stat != other.nt_stat)
			return false;
		if (nt_title == null) {
			if (other.nt_title != null)
				return false;
		} else if (!nt_title.equals(other.nt_title))
			return false;
		if (ntcont_stat != other.ntcont_stat)
			return false;
		if (ntgu_code == null) {
			if (other.ntgu_code != null)
				return false;
		} else if (!ntgu_code.equals(other.ntgu_code))
			return false;
		if (user_id == null) {
			if (other.user_id != null)
				return false;
		} else if (!user_id.equals(other.user_id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "NoticeVo [nt_num=" + nt_num + ", ntgu_code=" + ntgu_code + ", user_id=" + user_id + ", nt_stat="
				+ nt_stat + ", ntcont_stat=" + ntcont_stat + ", nt_dt=" + nt_dt + ", nt_cont=" + nt_cont + ", nt_title="
				+ nt_title + ", nt_panum=" + nt_panum + ", level=" + level + "]";
	}
	
}
