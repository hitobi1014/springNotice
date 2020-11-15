package kr.or.ddit.notice.model;

public class NoticeFileVo {
	private int filenum;
	private int nt_num;
	private String filename;
	private String filepath;
	
	public int getFilenum() {
		return filenum;
	}
	public void setFilenum(int filenum) {
		this.filenum = filenum;
	}
	public int getNt_num() {
		return nt_num;
	}
	public void setNt_num(int nt_num) {
		this.nt_num = nt_num;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((filename == null) ? 0 : filename.hashCode());
		result = prime * result + filenum;
		result = prime * result + ((filepath == null) ? 0 : filepath.hashCode());
		result = prime * result + nt_num;
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
		NoticeFileVo other = (NoticeFileVo) obj;
		if (filename == null) {
			if (other.filename != null)
				return false;
		} else if (!filename.equals(other.filename))
			return false;
		if (filenum != other.filenum)
			return false;
		if (filepath == null) {
			if (other.filepath != null)
				return false;
		} else if (!filepath.equals(other.filepath))
			return false;
		if (nt_num != other.nt_num)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "NoticeFileVo [filenum=" + filenum + ", nt_num=" + nt_num + ", filename=" + filename + ", filepath="
				+ filepath + "]";
	}
	
}
