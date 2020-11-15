package kr.or.ddit.notice.model;

import java.util.Date;

public class ReplyVo {
	private int rep_num;
	private int nt_num;
	private int rep_stat;
	private Date rep_dt;
	private String rep_cont;
	private String user_id;
	
	public int getRep_num() {
		return rep_num;
	}
	public void setRep_num(int rep_num) {
		this.rep_num = rep_num;
	}
	public int getNt_num() {
		return nt_num;
	}
	public void setNt_num(int nt_num) {
		this.nt_num = nt_num;
	}
	public int getRep_stat() {
		return rep_stat;
	}
	public void setRep_stat(int rep_stat) {
		this.rep_stat = rep_stat;
	}
	public Date getRep_dt() {
		return rep_dt;
	}
	public void setRep_dt(Date rep_dt) {
		this.rep_dt = rep_dt;
	}
	public String getRep_cont() {
		return rep_cont;
	}
	public void setRep_cont(String rep_cont) {
		this.rep_cont = rep_cont;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + nt_num;
		result = prime * result + ((rep_cont == null) ? 0 : rep_cont.hashCode());
		result = prime * result + ((rep_dt == null) ? 0 : rep_dt.hashCode());
		result = prime * result + rep_num;
		result = prime * result + rep_stat;
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
		ReplyVo other = (ReplyVo) obj;
		if (nt_num != other.nt_num)
			return false;
		if (rep_cont == null) {
			if (other.rep_cont != null)
				return false;
		} else if (!rep_cont.equals(other.rep_cont))
			return false;
		if (rep_dt == null) {
			if (other.rep_dt != null)
				return false;
		} else if (!rep_dt.equals(other.rep_dt))
			return false;
		if (rep_num != other.rep_num)
			return false;
		if (rep_stat != other.rep_stat)
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
		return "ReplyVo [rep_num=" + rep_num + ", nt_num=" + nt_num + ", rep_stat=" + rep_stat + ", rep_dt=" + rep_dt
				+ ", rep_cont=" + rep_cont + ", user_id=" + user_id + "]";
	}
	
}
