package com.callor.gallery.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.callor.gallery.model.FileDTO;
import com.callor.gallery.model.GalleryDTO;
import com.callor.gallery.model.GalleryFilesDTO;
import com.callor.gallery.model.PageDTO;
import com.callor.gallery.persistance.ext.FileDao;
import com.callor.gallery.persistance.ext.GalleryDao;
import com.callor.gallery.service.FileService;
import com.callor.gallery.service.GalleryService;
import com.callor.gallery.service.PageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/* 
 * final로 선언된 inject 변수의 초기화를 시키는데 필요한 생성자를 자동으로 만들어주는 lombok기능이다.
 * 
 * 클래스를 상속하면 @RequiredArgsConstructor는 
 * 상속받은 클래스에서 사용이 불가하다.
 * 최초는 상관없으나 상속받는 v2같은 경우는 불가하다
 *
 */

@RequiredArgsConstructor
@Slf4j
@Service("galleryServiceV1")
public class GalleryServiceImplV1 implements GalleryService {

	protected final GalleryDao gaDao;
	protected final FileDao fDao;

	@Qualifier("fileServiceV2")
	protected final FileService fService;
	protected final PageService pageService;

	/*
	 * @Autowired가 설정된 변수, method, 객체 등을 만나면 Spring framwork는 변수를 초기화, method를 실행하여
	 * 또 변수 초기화 이미 생성되어 준비된 객체에 주입 등을 수행한다.
	 * 
	 * (생성자에 이런걸쓰면 오류가 많이 발생할 수 있어서 이렇게 Spring을 속여 자동으로 생성되는 코드를쓴다.)
	 * 
	 * 프로젝트가 Run함과 동시에 실행되며 Dao에 있는 create를 실행하게 된다.
	 * 
	 */
	@Autowired
	public void create_table(GalleryDao gDao) { // 매개변수를 설정하지 않으면 경고 또는 오류가 발생하므로 필요없는 매개변수를 설정해놓음
		Map<String, String> maps = new HashMap<String, String>();
		gaDao.create_table(maps);
		fDao.create_table(maps);
	}

	@Override
	public int insert(GalleryDTO galleryDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	// fileServiceImplV2에서 리턴 한 값을 여기서 사용
	@Override
	public void input(GalleryDTO gaDTO, MultipartFile one_file, MultipartHttpServletRequest m_file) throws Exception {
		// TODO Auto-generated method stub

		// strUUID 범용고유식별자
		// 대표 이미지가 업로드 되면...
		// 이미지를 서버에 저장하고 저장된 파일의 이름을 return 받기
		String strUUID = fService.fileUp(one_file);

		// DTO에 이미지 이름을 저장하기
		gaDTO.setG_image(strUUID);

		log.debug(" INSERT 전 seq {} ", gaDTO.getG_seq());
		// GalleryDTO에 담긴 데이터를 tbl_gallery table에 insert하기

		// mapper에서 insert를 수행한 후 새로 생성된 g_seq값을 selectKey 하여 gaDTO의 g_seq 변수에 담아 놓은
		// 상태이다.
		// GalleryDTO에 담긴 데이터를 tbl_gallery table에 insert하기
		gaDao.insert(gaDTO);

		log.debug(" INSERT 후 seq {} ", gaDTO.getG_seq());

		// 갤러리 게시판의 seq값과 파일들을 묶음으로 insert하기 위한 준비하기
		Long g_seq = gaDTO.getG_seq();

		List<FileDTO> files = new ArrayList<FileDTO>();

		// 업로드 된 멀티 파일을 서버에 업로드 하고 원래 파일이름과 UUID가 첨가 된 파일이름을 추출하여 fileDTO에 담고
		// 다시 List에 담아 놓는다.

		List<MultipartFile> mFile = m_file.getFiles("m_file");

		// for문을 계속 실행하면서 m_file.getFiles("m_file")를 계속 실행하기 때문에 좋은 코드는 아님 그래서
		// List로 값을 담아서 for 문을 실행하게 함
//		for(MultipartFile file : m_file.getFiles("m_file")) {
		for (MultipartFile file : mFile) {

			// 업로드 하기전에 파일 이름을 추출하고
			String fileOriginName = file.getOriginalFilename();

			// 업로드 후
			String fileUUName = fService.fileUp(file);

			FileDTO fDto = FileDTO.builder().file_gseq(g_seq) // 갤러리 데이터의 PK값 , mapper에서 새로 생성한 seq값을 리턴해 다시 받아서 여기에 등록함
					.file_original(fileOriginName).file_upname(fileUUName).build();

			// 각각의 파일을 저장해 준다.
			files.add(fDto);
		}

		log.debug("이미지들 {}:", files.toString());

		fDao.insertOrUpdateWithList(files);

	}

	@Override
	public List<GalleryDTO> selectAll() throws Exception {
		// TODO Auto-generated method stub

		List<GalleryDTO> gaList = gaDao.selectAll();

		log.debug("gaList:{}", gaList.toString());
		return gaList;
	}

	@Override
	public List<GalleryFilesDTO> findByGalleryFiles(Long g_seq) {
		// TODO Auto-generated method stub

		List<GalleryFilesDTO> gfList = gaDao.findByIdGalleryFiles(g_seq);

		/*
		 * dao로 부터 select를 한 후 데이터 검증 하기 위해 사용하는 코드 gfList가 데이터가 조회되지 않아 null이 발생할수 있다
		 */
		if (gfList != null && gfList.size() > 0) {
			log.debug(gfList.toString());
		} else {
			log.debug("조회된 데이터가 없음");
		}

		return gfList;
	}

	@Override
	public GalleryDTO findByIdGellery(Long g_seq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Long g_seq) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int file_delete(Long g_seq) {
		// TODO Auto-generated method stub

		// 파일을 삭제하기 위하여 저장된 파일 정보를 SELECT 하기
		FileDTO fDTO = fDao.findById(g_seq);
		// 업로드되어 저장된 파일을 삭제
		int ret = fService.delete(fDTO.getFile_upname());

		// 파일이 정상적으로 삭제되면(ret 가 0보다 크면)
		if (ret > 0) {
			// tbl_files table에서 데이터를 삭제하기
			ret = fDao.delete(g_seq);
		}
		return ret;
	}
	/*
	 * pageNum을 매개변수로 받아서 selectAll한 데이터를 잘라내고 pageNum에 해당하는 list 부분만 return 하기
	 * 
	 * 한 페이지에 보여줄 list = 10 개
	 */

	@Override // 처음에 알려주신 것
	public List<GalleryDTO> selectAllPage(int pageNum) throws Exception {
		// TODO Auto-generated method stub

		// 1.전체 데이터 SELECT 하기
		List<GalleryDTO> gaListAll = gaDao.selectAll(); //

		// 2. pageNum이 1이라면 list에서 0번째 요소 ~ 9번째 요소까지 추출하기
		// 만약 pageNum가 2라면 list에서 10번째 요소 ~ 19번째 요소까지 추출하기
		// 만약 pageNum가 3라면 list에서 20번째 요소 ~ 29번째 요소까지 추출하기

		int totalCount = gaListAll.size();
		// 페이지를 잘라내기
		int start = (pageNum - 1) * 10;
		int end = pageNum * 10;

		if (pageNum * 10 > totalCount - 10) {
			end = totalCount;
			start = end - 10;
		}

		List<GalleryDTO> pageList = new ArrayList<GalleryDTO>();
		for (int i = start; i < end; i++) {

			pageList.add(gaListAll.get(i));
//			gaListAll.get(i); // 0번부터 9번까지 추출
		}
		return pageList;
	}

	@Override // 변경버전
	public List<GalleryDTO> selectAllPage(int intPageNum, Model model) throws Exception {
		// TODO Auto-generated method stub
//intPageNum = currentpage
		List<GalleryDTO> galleryAll = gaDao.selectAll();

		
		//전체 게시물의 개수
		int totalListSize = galleryAll.size();

		PageDTO pageDTO = pageService.makePagination(totalListSize, intPageNum);

		List<GalleryDTO> pageList = new ArrayList<>();
		
		// 시작은 offset , 끝은 limit , 배열의 시작값을 getoffset으로 하고 배열의 끝을 getlimit로 했음
		// 만약에 30개의 리스트가 있으면 3페이지에서 시작값인 20이 offset이고 29가 limit , 배열은 0부터 시작한다 기억좀하자
		for (int i = pageDTO.getOffset(); i < pageDTO.getLimit(); i++) {
			pageList.add(galleryAll.get(i)); // 한페이지에서 보일수 있게 시작값과 끝 값을 잘라서 add해주기

		}

		model.addAttribute("PAGE_NAV", pageDTO);
		model.addAttribute("GALLERYS", pageList);

		return null;
	}

	@Override
	public List<GalleryDTO> findBySearchOrderPage(int pageNum, String search, String column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GalleryDTO> findBySearchPage(int pageNum, String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GalleryDTO> findBySearchPage(String search_column, String search_text, int pageNum, Model model) {
		// TODO Auto-generated method stub
		
		List<GalleryDTO> galleryList = gaDao.findBySearch( search_column, search_text);
		
		int totalListSize = galleryList.size();
		PageDTO pageDTO = pageService.makePagination(totalListSize, pageNum);
		
		List<GalleryDTO> pageList = new ArrayList<>();
		
		if(pageDTO == null) {
			model.addAttribute("GALLERYS",galleryList);
			return null;
		}
		
		for(int i = pageDTO.getOffset(); i < pageDTO.getLimit() ; i++) {
			pageList.add(galleryList.get(i));
		}
				
		model.addAttribute("GALLERYS",galleryList);
		return null;
	}

}
