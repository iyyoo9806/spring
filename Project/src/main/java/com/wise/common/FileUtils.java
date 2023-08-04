package com.wise.common;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component("fileUtils")
public class FileUtils {
    
    
	@Inject
    private String uploadPath;
 
    public List<Map<String, Object>> parseFileInfo(Map<String, Object> map, MultipartFile[] file) throws Exception {
        
        String boardIDX = String.valueOf(map.get("idx"));
        String creaID = (String) map.get("crea_id");
        
        List<Map<String, Object>> fileList = new ArrayList<Map<String, Object>>();
 
        File target = new File(uploadPath);
        if(!target.exists()) target.mkdirs();
        
        for(int i=0; i<file.length; i++) {
 
            String orgFileName = file[i].getOriginalFilename();
            String orgFileExtension = orgFileName.substring(orgFileName.lastIndexOf("."));
            String saveFileName = UUID.randomUUID().toString().replaceAll("-", "") + orgFileExtension;
            Long saveFileSize = file[i].getSize();
            
            System.out.println("================== file start ==================");
            System.out.println("파일 실제 이름: "+orgFileName);
            System.out.println("파일 저장 이름: "+saveFileName);
            System.out.println("파일 크기: "+saveFileSize);
            System.out.println("content type: "+file[i].getContentType());
            System.out.println("================== file   END ==================");
 
            target = new File(uploadPath, saveFileName);
            file[i].transferTo(target);
            
            Map<String, Object> fileInfo = new HashMap<String, Object>();
 
            fileInfo.put("BOARD_IDX", boardIDX);
            fileInfo.put("ORG_FILE_NAME", orgFileName);
            fileInfo.put("SAVE_FILE_NAME", saveFileName);
            fileInfo.put("FILE_SIZE", saveFileSize);
            fileInfo.put("CREA_ID", creaID);
            fileList.add(fileInfo);
            
        }
        return fileList;
    }
}
