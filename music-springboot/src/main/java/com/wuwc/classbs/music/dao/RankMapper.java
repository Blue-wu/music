package com.wuwc.classbs.music.dao;

import com.wuwc.classbs.music.domain.Comment;
import com.wuwc.classbs.music.domain.Rank;
import com.wuwc.classbs.music.domain.Song;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评论Dao
 */
@Repository
public interface RankMapper {

   public int insert(Rank rank);

   /*查总分*/
   public  int selectScoreSum(Integer songListId);


   /**
    * 查总评分人数
    */
   public int selectRankNum(Integer songListId);



}

