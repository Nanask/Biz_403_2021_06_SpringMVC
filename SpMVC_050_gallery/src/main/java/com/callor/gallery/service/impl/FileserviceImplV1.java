package com.callor.gallery.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.callor.gallery.service.FileService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@RequiredArgsConstructor
@Service("fileServiceV1")
public class FileserviceImplV1 implements FileService{

	// 서버에 특별한 폴더에 접근하기 위하여 서버의 정보를 가져오기 위한 helper class
//	protected final ServletContext context;
	@Autowired
	private ResourceLoader resLoader;
	
	@Override
	public String fileUp(MultipartFile file) throws Exception {
		// TODO Auto-generated method stub
		
		// 파일정보에서 파일이름 추출하기
		String originaFileName = file.getOriginalFilename();
		
		//파일이 비어있으면 null을 return
		if(originaFileName.isEmpty()) {
			return null;
		}
		
		log.debug("파일이름 :{}",originaFileName);
		
		/*
		 * file-context.xml에 설정된 files폴더 정보를 가져오기
		 * 여기에 파일을 Upload 저장할 예정
		 */
		// context.xml에 설정된 files 정보를 가져오기
		Resource res = resLoader.getResource("/files");
		
//		log.debug("path : {}", res.getURI().getPath());
		
		String filePath = res.getURI().getPath();
		/*
		 * 파일을 업로드 할때 보안 주의하기
		 * 실제 파일이름으로 업로드를 수행하면
		 * 쉽게 업로드 구현이 된다.
		 * 
		 * 하지만 만약 같은 이름의 파일을 중복해서 업로드하면 
		 * 먼저 업로드 된 파일이 변경되는 문제가 발생한다.
		 * 
		 * 해커에 의해 같은 이름으로 파일들을 업로드 해버리면 저장된 원래 파일들이 모두 변조 될 수 있다.
		 * 
		 * 이러한 문제를 방지하기 위하여 UUID 문자열을 생성하고 UUID + 원래 파일이름.원래확장자 형식으로 업로드를 수행한다.
		 * 이런방식으로 파일을 저장하면 해킹의 위험을 다소 막을 수 있다.
		 */
		
		String strUUID = UUID.randomUUID().toString();
		strUUID += originaFileName;
		
		
		// 파일업로드 할 path와 파일이름을 +하여 업로드 준비하기
		// /files + "/" + originalFileName 과 같은 의미
//		File uploadPathAndFile = new File(filePath, originaFileName);
		File uploadPathAndFile = new File(filePath, strUUID);
		
		// file에 담긴 file정보를 사용하여 uploadPathAndfFile에 전송하라(복사하라,업로드하라)
		file.transferTo(uploadPathAndFile);
		
//		return originaFileName;
		return strUUID;
	}

	@Override
	public List<String> filesUp(MultipartHttpServletRequest files) throws Exception {
		// TODO Auto-generated method stub
		
		List<String> fileNames = new ArrayList<String>();
		String tagName = "m_file";
		
		List<MultipartFile> fileList = files.getFiles(tagName);
		for(MultipartFile file : fileList) {
			
			String fileName = this.fileUp(file);
			fileNames.add(fileName);
		}
		
		return fileNames;
		
	}

}
