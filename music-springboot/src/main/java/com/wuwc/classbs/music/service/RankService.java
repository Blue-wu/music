package com.wuwc.classbs.music.service;

import com.wuwc.classbs.music.domain.Rank;
import com.wuwc.classbs.music.domain.Song;

import java.util.List;

/**
 * 歌手service接口
 */
public interface RankService {
    /**
     * 增加
     */
    public boolean insert(Rank rank);

    /**
     * 查总分
     */
    public int selectScoreSum(Integer songListId);

    /**
     * 查总评分人数
     */
    public int selectRankNum(Integer songListId);

    /**
     * 计算平均分
     */
    public int rankOfSongListId(Integer songListId);
}
