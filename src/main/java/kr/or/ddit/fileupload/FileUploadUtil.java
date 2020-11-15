package kr.or.ddit.fileupload;

public class FileUploadUtil {
	//form-data; name="img"; filename="contentType.jpg" 이 문자를 받아서
	//==> sally.png  .. 이렇게 반환해주는 메서드 작성
	
	//FileUploadUtilTest 
	public static String getFileName(String contentDisPosition) {
		String[] contents= contentDisPosition.split(";");
		String[] names = {};
		String fileName="";
		for(int i=0; i<contents.length; i++) {
			if(contents[i].trim().startsWith("filename")) {
				names = contents[i].split("=");
				fileName = names[1].replaceAll("\"", "");
			}
		}
		return fileName;
	}
	
	// filename : sally.png ==> png
	public static String getExtension(String filename) {
		if(filename == null || filename.indexOf(".")== -1)
			return "";
		else {
			return filename.split("\\.")[1];
		}
	}
}
