package kr.or.ddit.notice.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.or.ddit.notice.model.NoticeFileVo;
import kr.or.ddit.notice.model.NoticeGubunVo;
import kr.or.ddit.notice.model.NoticeVo;
import kr.or.ddit.notice.model.ReplyVo;
import kr.or.ddit.notice.service.NoticeServiceI;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	@Resource(name="noticeService")
	private NoticeServiceI noticeService;
	
	@RequestMapping(path ="detail")
	public String noticeDetailView(int nt_num, Model model) {
		Map<String, Object> map = noticeService.getNotice(nt_num);
		NoticeVo noticeVo = (NoticeVo) map.get("nvo");
		List<ReplyVo> replyList = (List<ReplyVo>) map.get("replyList");
		Map<String, Object> ngMap = noticeService.getAllNotice(noticeVo.getNtgu_code());
		NoticeGubunVo ngvo = (NoticeGubunVo) ngMap.get("ngvo");
		
		model.addAttribute("nvo", noticeVo);
		model.addAttribute("replyList", replyList);
		model.addAttribute("ngvo", ngvo);
		
		List<NoticeFileVo> nfvoList = noticeService.getAllFile(nt_num);
		model.addAttribute("nfvoList", nfvoList);
		return "tiles/notice/noticeDetail";
	}

	@RequestMapping(path ="write", method=RequestMethod.GET)
	public String noticeWriteView() {
		return "tiles/notice/noticeWrite";
	}
	
	@RequestMapping(path="write",method=RequestMethod.POST)
	public String noticeWrite(NoticeVo ngvo, NoticeGubunVo ntgu, String editordata,
			@RequestParam(name="nt_panum", required = false) String nt_panum,
			MultipartHttpServletRequest multiReq) {
		if(nt_panum != null) {
			int np = Integer.parseInt(nt_panum);
			ngvo.setNt_panum(np);
		}
		ngvo.setNt_stat(1);
		ngvo.setNtcont_stat(1);
		ngvo.setNt_cont(editordata);
		int insertCnt = noticeService.insertNotice(ngvo);
		int nt_num = ngvo.getNt_num();
		List<MultipartFile> fileList = multiReq.getFiles("nt_file");
		if(fileList != null && !fileList.equals("")) {
			for(MultipartFile file : fileList) {
				String fileName = UUID.randomUUID().toString();
				String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
				String filepath = "d:\\upload\\"+fileName + "."+extension;
				File uploadFile = new File(filepath);
				if(file.getSize() > 0) {
					try {
						file.transferTo(uploadFile);
						NoticeFileVo nfvo = new NoticeFileVo();
						nfvo.setFilename(file.getOriginalFilename());
						nfvo.setFilepath(filepath);
						nfvo.setNt_num(nt_num);
						noticeService.insertFile(nfvo);
					} catch (IllegalStateException | IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		return "redirect:/noticeGubun/gubun?ntgu_code="+ntgu.getNtgu_code();
	}
	
	@RequestMapping(path="delete")
	public String noticeDelete(NoticeVo nvo) {
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("user_id", nvo.getUser_id());
		info.put("nt_num",nvo.getNt_num());
		noticeService.deleteNotice(info);
		noticeService.deleteAllFile(nvo.getNt_num());
		return "redirect:/noticeGubun/gubun?ntgu_code="+nvo.getNtgu_code();
	}
	
	@RequestMapping(path="modify",method=RequestMethod.GET)
	public String noticeModifyView(NoticeVo noticeVo, Model model) {
		Map<String, Object> getNotice = noticeService.getNotice(noticeVo.getNt_num());
		NoticeVo nvo = (NoticeVo) getNotice.get("nvo");
		
		Map<String, Object> getNtgu = noticeService.getAllNotice(nvo.getNtgu_code());
		NoticeGubunVo ngvo = (NoticeGubunVo) getNtgu.get("ngvo");
		model.addAttribute("nvo", nvo);
		model.addAttribute("ngvo", ngvo);
		
		//파일가져오기
		List<NoticeFileVo> nfvoList = noticeService.getAllFile(noticeVo.getNt_num());
		model.addAttribute("nfvoList", nfvoList);
		return "tiles/notice/noticeModify";
	}
	
	@RequestMapping(path="modify",method=RequestMethod.POST)
	public String noticeModify(NoticeVo nvo, NoticeGubunVo ntgu, String editordata,
			MultipartHttpServletRequest multiReq) {
		nvo.setNt_cont(editordata);
		logger.debug("수정 글 정보 : {}",nvo);
		int updateCnt = noticeService.updateNotice(nvo);
		if(updateCnt > 0) {
			logger.debug("등록성공");
		}else {
			logger.debug("등록실패");
		}
		
		List<MultipartFile> fileList = multiReq.getFiles("nt_file");
		if(fileList != null && !fileList.equals("")) {
			for(MultipartFile file : fileList) {
				String fileName = UUID.randomUUID().toString();
				String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
				String filepath = "d:\\upload\\"+fileName + "."+extension;
				File uploadFile = new File(filepath);
				if(file.getSize() > 0) {
					try {
						file.transferTo(uploadFile);
						NoticeFileVo nfvo = new NoticeFileVo();
						nfvo.setFilename(file.getOriginalFilename());
						nfvo.setFilepath(filepath);
						nfvo.setNt_num(nvo.getNt_num());
						noticeService.insertFile(nfvo);
					} catch (IllegalStateException | IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return "redirect:/noticeGubun/gubun?ntgu_code="+nvo.getNtgu_code();
	}
	
	@RequestMapping(path="fileDownload")
	public void fileDown(NoticeFileVo nfvo, HttpServletResponse response) throws IOException {
//		logger.debug("파일 경로 : {}",nfvo);
		NoticeFileVo nf = noticeService.getFile(nfvo.getFilenum());
		logger.debug("가져온 파일 : {}",nf);
		response.setHeader("Content-Disposition", "attachment; filename=\""+nf.getFilepath()+"\"");
		response.setContentType("application/octet-stream");
		FileInputStream fis =new FileInputStream(nf.getFilepath()); 
		ServletOutputStream sos = response.getOutputStream();
		byte[] buffer = new byte[512];
		while(fis.read(buffer) != -1) {
			sos.write(buffer);
		}
		fis.close();
		sos.flush();
		sos.close();
	}
	
	@RequestMapping(path="answer",method=RequestMethod.GET)
	public String noticeAnswerView(NoticeVo nvo, Model model) {
		Map<String, Object> map = noticeService.getNotice(nvo.getNt_num());
		NoticeVo noticeVo = (NoticeVo) map.get("nvo");
		Map<String, Object> ngMap = noticeService.getAllNotice(noticeVo.getNtgu_code());
		NoticeGubunVo ngvo = (NoticeGubunVo) ngMap.get("ngvo");
		model.addAttribute("nvo", nvo);
		model.addAttribute("ngvo", ngvo);
		model.addAttribute("nt_panum", nvo.getNt_num());
		return "tiles/notice/noticeAnswer";
	}
	
	@RequestMapping(path="answer",method = RequestMethod.POST)
	public String noticeAnswer(NoticeVo noticeVo,String editordata,
			MultipartHttpServletRequest multiReq) {
//		logger.debug("부모글 번호 {}",noticeVo);
		noticeVo.setNt_stat(1);
		noticeVo.setNtcont_stat(1);
		noticeVo.setNt_cont(editordata);
		
		int insertCnt = noticeService.insertNotice(noticeVo);
		int nt_num = noticeVo.getNt_num();
		List<MultipartFile> fileList = multiReq.getFiles("nt_file");
		for(MultipartFile file : fileList) {
			String fileName = UUID.randomUUID().toString();
			String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
			String filepath = "d:\\upload\\"+fileName + "."+extension;
			File uploadFile = new File(filepath);
			try {
				file.transferTo(uploadFile);
				NoticeFileVo nfvo = new NoticeFileVo();
				nfvo.setFilename(file.getOriginalFilename());
				nfvo.setFilepath(filepath);
				nfvo.setNt_num(nt_num);
				noticeService.insertFile(nfvo);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		return "redirect:/noticeGubun/gubun?ntgu_code="+noticeVo.getNtgu_code();
	}
}
