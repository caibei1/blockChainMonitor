package com.fuzamei.service.serviceImpl;

import com.fuzamei.entity.Answer;
import com.fuzamei.mapperInterface.TestMapper;
import com.fuzamei.service.TestService;
import com.fuzamei.util.PageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author huang
 * 2018/4/11
 */
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestMapper testMapper;

    @Override
    public List<Answer> queryAnswer() {
        PageDTO pageDTO = new PageDTO();
        List<Answer> answers = testMapper.queryAnswer();
        return answers;
    }

}
