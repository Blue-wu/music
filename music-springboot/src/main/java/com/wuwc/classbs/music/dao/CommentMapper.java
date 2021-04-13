package com.wuwc.classbs.music.dao;

import com.wuwc.classbs.music.domain.Comment;
import com.wuwc.classbs.music.domain.Song;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评论Dao
 */
@Repository
public interface CommentMapper {

   public int insert(Comment comment);

   public int update(Comment comment);

   /*
   * 删除
   * */
public  int delete(Integer singerId);

/*
* 根据主键查询整个对象
* */
   public List<Comment> selectByPrimaryKey(Integer singerId);


   /*查询所有评价*/
   public List<Comment> allComment();

   /*查询某个歌曲下的所有评论*/
   public List<Comment> commentOfSongId(Integer songId);

   /*查询歌单下所有评论*/
   public List<Comment> commentOfSongListId(Integer songListId);


}

