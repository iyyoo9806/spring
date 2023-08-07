package com.wise.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import com.wise.domain.BoardVO;
import com.wise.service.BoardService;

@Controller
public class BoardController {
	
	@Inject
	BoardService service;
	
	//게시물 작성
	@RequestMapping(value = "/writing")
	public String writing(BoardVO vo, @RequestParam("title") String title,
			@RequestParam("contents") String contents, HttpServletRequest request,
			ModelMap model, MultipartFile[] file) throws Exception {
			
		try {
			String id = request.getSession().getAttribute("id").toString();
			String name = request.getSession().getAttribute("name").toString();
			String degree = request.getSession().getAttribute("degree").toString();
			vo.setId(id);
			vo.setTitle(title);
			vo.setContents(contents);
			vo.setName(name);
			vo.setDegree(degree);
			String idx = service.nextIdx();
			System.out.print("idx = " +idx);
			System.out.println("id =" +id);

			ServletContext servletContext = request.getSession().getServletContext();
			String contextPath = servletContext.getRealPath("/");
			String uploadPath = contextPath + "resources/file";
			File firstDir = new File(uploadPath);
			if(!firstDir.exists()) {
				firstDir.mkdir();
			}
			
			System.out.println(contextPath);
			System.out.print(uploadPath);
            int board_idx = Integer.parseInt(idx);
            
            String uploadIdx = String.valueOf(board_idx);
            uploadPath = uploadPath + "/" + id + uploadIdx;
            File destDir = new File(uploadPath);
            vo.setFilepath(uploadPath);
            if(!destDir.exists()) {
            	destDir.mkdir();
            }
			for(int i=0; i<file.length; i++) {
				if(file[i] != null) {
		            String orgFileName = file[i].getOriginalFilename();
		            if (orgFileName != null && !orgFileName.isEmpty()) {
			            String orgFileExtension = orgFileName.substring(orgFileName.lastIndexOf("."));
			            String saveFileName = UUID.randomUUID().toString().replaceAll("-", "") + orgFileExtension;
			            Long saveFileSize = file[i].getSize();
			            System.out.println("================== file start ==================");
			            System.out.println("파일 실제 이름: "+orgFileName);
			            System.out.println("파일 저장 이름: "+saveFileName);
			            System.out.println("파일 크기: "+saveFileSize);
			            System.out.println("content type: "+file[i].getContentType());
			            System.out.println("================== file   END ==================");
			            
			            File saveFile = new File(uploadPath, file[i].getOriginalFilename());
			            
			            try {
							file[i].transferTo(saveFile);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
			service.write(vo);
			model.addAttribute("message", "글작성 성공");
			model.addAttribute("url", "/board?num=1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("message", "실패");
			model.addAttribute("url", "/board?num=1");
		}
		
		return "/alert";
	}

	//게시물 수정
	@RequestMapping(value = "/modifying", method = RequestMethod.POST)
	public String postModify(BoardVO vo, @RequestParam("title") String title,
			@RequestParam("contents") String contents, @RequestParam("idx") String idx
			,HttpServletRequest request, ModelMap model, MultipartFile[] file) throws Exception {

		try {
			vo.setIdx(idx);
			vo.setTitle(title);
			vo.setContents(contents);
			System.out.println(title);
			System.out.println(idx);

			BoardVO modi = service.read(idx);
		    String filePath = modi.getFilepath();
			for(int i=0; i<file.length; i++) {
				if(file[i] != null) {
					String orgFileName = file[i].getOriginalFilename();
					if (orgFileName != null && !orgFileName.isEmpty()) {
						String orgFileExtension = orgFileName.substring(orgFileName.lastIndexOf("."));
						String saveFileName = UUID.randomUUID().toString().replaceAll("-", "") + orgFileExtension;
						Long saveFileSize = file[i].getSize();
						System.out.println("================== file start ==================");
						System.out.println("파일 실제 이름: "+orgFileName);
						System.out.println("파일 저장 이름: "+saveFileName);
						System.out.println("파일 크기: "+saveFileSize);
						System.out.println("content type: "+file[i].getContentType());
						System.out.println("================== file   END ==================");
						
						File saveFile = new File(filePath, file[i].getOriginalFilename());
						
						try {
							file[i].transferTo(saveFile);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
			service.modify(vo);
			model.addAttribute("message", "수정 성공");
			model.addAttribute("url", "/board?num=1");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			model.addAttribute("message", "실패");
			model.addAttribute("url", "/board?num=1");
		}
		return "/alert";
	}
	
	//게시물 삭제
	@RequestMapping(value = "delete")
	public String delete(@RequestParam("idx") String idx, ModelMap model) throws Exception {
		try {
			service.delete(idx);
			model.addAttribute("message", "삭제가 완료되었습니다.");
			model.addAttribute("url", "/board?num=1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			model.addAttribute("message", "실패");
			model.addAttribute("url", "/board?num=1");
		}
		return "/alert";		
	}
	
	@RequestMapping(value = "/download")
	public ResponseEntity<Resource> downloadFile(@RequestParam("idx") String idx, 
			@RequestParam("filename") String filename) throws Exception {
		BoardVO vo = service.read(idx);
	    String filePath = vo.getFilepath();

	    // filePath와 filename을 합쳐서 파일 경로를 만듭니다.
	    String fullFilePath = filePath + File.separator + filename;

	    // 파일 경로로부터 파일을 읽어옵니다.
	    Resource resource = new FileSystemResource(fullFilePath);
	    
	    String encodedFilename = UriUtils.encode(filename, "UTF-8");

	    // 파일을 다운로드할 때 사용할 헤더를 설정합니다.
	    HttpHeaders headers = new HttpHeaders();
	    headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedFilename + "\"");

	    // ResponseEntity를 사용하여 파일과 헤더를 전송합니다.
	    return ResponseEntity.ok()
	            .headers(headers)
	            .contentLength(resource.contentLength())
	            .contentType(MediaType.APPLICATION_OCTET_STREAM)
	            .body(resource);
	}
	
	//어드민 엑셀 다운로드
		@RequestMapping(value = "/excel")
		public void excel(HttpServletResponse response, ModelMap model,
				@RequestParam(value = "searchType",required = false, defaultValue = "title") String searchType,
				@RequestParam(value = "keyword",required = false, defaultValue = "")  String keyword,
				HttpServletRequest request)   throws Exception {

				XSSFWorkbook wb=null;
				Sheet sheet=null;
				Row row=null;
				Cell cell=null; 
				wb = new XSSFWorkbook();
				sheet = wb.createSheet("sheet");

				List<BoardVO> excelList = service.excel(searchType, keyword);

				//첫행   열 이름 표기 
				int cellCount=0;
				row = sheet.createRow(0); //0번째 행
				cell=row.createCell(cellCount++);
				cell.setCellValue("idx");
				cell=row.createCell(cellCount++);
				cell.setCellValue("title");
				cell=row.createCell(cellCount++);
				cell.setCellValue("contents");
				cell=row.createCell(cellCount++);
				cell.setCellValue("name");
				cell=row.createCell(cellCount++);
				cell.setCellValue("degree");
				cell=row.createCell(cellCount++);
				cell.setCellValue("filepath");
				cell=row.createCell(cellCount++);
				cell.setCellValue("date");

				for(int i=0; i<excelList.size(); i++) {
					row = sheet.createRow(i+1);
					cellCount=0;
					cell = row.createCell(cellCount++);
					cell.setCellValue(excelList.get(i).getIdx());
					cell = row.createCell(cellCount++);
					cell.setCellValue(excelList.get(i).getTitle());
					cell = row.createCell(cellCount++);
					cell.setCellValue(excelList.get(i).getContents());
					cell = row.createCell(cellCount++);
					cell.setCellValue(excelList.get(i).getName());
					cell = row.createCell(cellCount++);
					cell.setCellValue(excelList.get(i).getDegree());
					cell = row.createCell(cellCount++);
					cell.setCellValue(excelList.get(i).getFilepath());
					cell = row.createCell(cellCount++);
					cell.setCellValue(excelList.get(i).getDate());
				}
				// 컨텐츠 타입과 파일명 지정
				response.setContentType("ms-vnd/excel");
				response.setHeader("Content-Disposition", "attachment;filename=sheet.xlsx");  //파일이름지정.
				//response OutputStream에 엑셀 작성
				wb.write(response.getOutputStream());
				wb.close();
		}
	
}
