package com.wuwc.classbs.music.service.impl;

import com.wuwc.classbs.music.dao.RecommendMapper;
import com.wuwc.classbs.music.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 管理员service实现类
 */
@Service
public class RecommendServiceImpl implements RecommendService {
    @Autowired
    private RecommendMapper recommendMapper;

    @Override
    public List<RecommendMapper> recommendOfType(Integer type) {
        return recommendMapper.recommendOfType(type);
    }
}
