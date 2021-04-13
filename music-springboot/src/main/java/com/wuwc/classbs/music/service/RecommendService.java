package com.wuwc.classbs.music.service;

import com.wuwc.classbs.music.dao.RecommendMapper;

import java.util.List;

/**
 * 歌手service接口
 */
public interface RecommendService {

    public List<RecommendMapper> recommendOfType(Integer type);



}
