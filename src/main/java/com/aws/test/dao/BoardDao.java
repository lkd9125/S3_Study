package com.aws.test.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.aws.test.vo.BoardVO;

@Mapper
public interface BoardDao {

    public ArrayList<BoardVO> getList();

    public void insertImage(HashMap<String, String> parameter);
    
}
