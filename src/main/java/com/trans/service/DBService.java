package com.trans.service;

import com.trans.entity.DB;
import com.trans.mapper.DBMapper;
import com.util.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
public class DBService {
    @Autowired
    private DBMapper dbMapper;
    public RestResult getDbs(){
        return RestResult.SUCCESS_REST_RESULT(dbMapper.selectObjs(null));
    }

    @Transactional
    public RestResult pugDbs(DB db){
        return RestResult.SUCCESS_REST_RESULT(null);
    }

}
