package com.callor.gallery.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.callor.gallery.model.GalleryDTO;
import com.callor.gallery.model.GalleryFilesDTO;
import com.callor.gallery.model.MemberVO;
import com.callor.gallery.service.GalleryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Controller
@RequestMapping(value="/gallery")
public class GalleryController {
	
	
	@Qualifier("galleryServiceV2")
	protected final GalleryService gaService;
	
	/*
	 *주소창에 직접 입력 후 Enter로 요청할 때 Request를 처리
	 * 	http://localhost:8080/rootPath/gallery/dumy로 요청을 했을 때 request를 처리
	 * a tag를 클릭했을 때
	 * 	<a href="${rootPath}/gallery/dumy>열기</a>
	 * 
	 * JS
	 * location.href="${rootPath}/gallery/dumy"가 실행될 때
	 * 
	 * 
	 */
	
	@RequestMapping(value="/dumy", method=RequestMethod.GET)
	public String dumy() {
		return "home";
	}
	/*
	 * <form action ="${rootPath}/dumy" method="POST">
	 * 	<input name="str">
	 * 	<button type="submit">전송<button>
	 * 	button type은 설정하지 않으면 기본값이 submit이나 정석때로 써주는 것이 좋다.
	 * <form>
	 */
	
	@RequestMapping(value="/dumy", method=RequestMethod.POST)
	public String dumy(String str) {
		return "home";
	}
	
	// localhost:8080/rootPath/gallery/ 또는
	// localhost:8080/rootPath/gallery 로 전송했을 때
	@RequestMapping(value={"/",""}, method=RequestMethod.GET)
	public String list(Model model) throws Exception {
		
		List<GalleryDTO> gaList = gaService.selectAll();
		model.addAttribute("GALLERYS",gaList);
		model.addAttribute("BODY","GA-LIST");
		return "home";
	}

   @RequestMapping(value="input", method=RequestMethod.GET)
   public String input(Model model,HttpSession session) {

	   //로그인이 되지 않으면 다시 로그인창으로 보내기
	   MemberVO mVO = (MemberVO) session.getAttribute("MEMBER");
	   if(mVO == null) {
		   return "redirect:/member/login";
	   }
	   
      Date date = new Date(System.currentTimeMillis()); //java.util의 date
      SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
      SimpleDateFormat st = new SimpleDateFormat("hh:mm:ss");
      
      String curDate = sd.format(date);
      String curTime = st.format(date);
      
      
      GalleryDTO gaDTO = GalleryDTO.builder()
                     .g_date(curDate)
                     .g_time(curTime)
                     .g_writer("sksk")
                     .build();
      
      model.addAttribute("CMD", gaDTO);
      model.addAttribute("BODY", "GA-INPUT");
      return "home";
   }
   
   @RequestMapping(value="/input", method=RequestMethod.POST)
   public String input(GalleryDTO gaDTO, 
                  MultipartFile one_file,
                  MultipartHttpServletRequest m_file,
                  Model model) throws Exception {
      
      log.debug("갤러리 정보 {}", gaDTO.toString());
      log.debug("싱글 파일 {}", one_file.getOriginalFilename());
      log.debug("멀티 파일 {}", m_file.getFileMap().toString());
      
//      gaService에 전달하기
      gaService.input(gaDTO, one_file, m_file);
      
      return "redirect:/gallery";
   }
   //value 값으로 seq값을 보내면 @PathVariable("seq")로 받아라 라는 의미
   @RequestMapping(value = "/detail/{seq}", method=RequestMethod.GET)
   public String detail(@PathVariable("seq") String seq, Model model, HttpSession session) {
	   
	   Long g_seq =0L;
	   try {
		g_seq = Long.valueOf(seq);
	} catch (NumberFormatException e) {
		return "redirect:/gallery";
	}
	   List<GalleryFilesDTO> gfList = gaService.findByGalleryFiles(g_seq);
	   
	   model.addAttribute("GFLIST",gfList);
	   model.addAttribute("BODY","GA-DETAIL");
	   
	   return "home";
   }
   
   @RequestMapping(value="/detail2/{seq}", method=RequestMethod.GET)
   
   public String detail(@RequestParam("g_seq") String seq, HttpSession session,Model model) {
	   

		Long g_seq = 0L;
		try {
			g_seq = Long.valueOf(seq);
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("갤러리 ID 값 오류");
			return "redirect:/";
		}

		GalleryDTO galleryDTO = gaService.findByIdGellery(g_seq);
		model.addAttribute("GALLERY",galleryDTO);
		model.addAttribute("BODY","GA-DETAIL-V2");
		return "home";
		
	}
	/*
	 * 첨부파일이 있는 게시물의 삭제
	 * 
	 */
   
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String delete(
		 @RequestParam("g_seq")	String seq,HttpSession session) {

		   //로그인을 했을 때의 값
		   // 삭제를 요구하면 
		   // 1. 로그인이 되었는지 확인
		   MemberVO memVO = (MemberVO) session.getAttribute("MEMBER");
		   if(memVO == null) { // 로그인이 안될 경우에는 로그인 화면으로 이동하게 한다.
			   return "redirect:/member/login";
		   }

		Long g_seq = 0L;
		try {
			g_seq = Long.valueOf(seq);
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("갤러리 SEQ 오류");
			return "redirect:/gallery";
		}

		int ret = gaService.delete(g_seq);

		return "redirect:/gallery";
	}
	
}
