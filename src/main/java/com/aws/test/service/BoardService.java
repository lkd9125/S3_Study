package com.aws.test.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aws.test.MimeConstant;
import com.aws.test.common.AwsS3Upload;
import com.aws.test.dao.BoardDao;
import com.aws.test.vo.BoardVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardService {

    @Autowired
    private BoardDao boardDao;

    @Autowired
    private AwsS3Upload awsS3Upload;
    
    public ArrayList<BoardVO> getList() {
        log.info("BoardService - getList()");
        
        return boardDao.getList();
    }

    public void imgInsert(BoardVO vo) throws IOException {
        String oriName = vo.getAwss3().getOriginalFilename();

        if(mimeCheck(Files.probeContentType(Paths.get(oriName)))) return;
        String sysname = System.currentTimeMillis() + oriName.substring(oriName.lastIndexOf("."));

        File file = new File(sysname);
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(vo.getAwss3().getBytes());
        fos.close();
        
        HashMap<String, String> parameter = new HashMap<>();
        parameter.put("oriname", oriName);
        parameter.put("sysname", sysname);
        
        awsS3Upload.upload(file, sysname);
        boardDao.insertImage(parameter);

        log.info("success!");
    }
    
    private boolean mimeCheck(String mimeType){
        log.error("파일 타입 -> {}", mimeType);
        MimeConstant mimeTypes[] = MimeConstant.values();

        for(int i = 0; i < mimeTypes.length; i++){
            if(mimeType.equals(mimeTypes[i].getVal())){                
                return false;
            }
        }

        return true;
    }

}
