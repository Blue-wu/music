package com.wuwc.classbs.music.service;

import com.wuwc.classbs.music.domain.Comment;
import com.wuwc.classbs.music.domain.Rank;

import java.util.List;

/**
 * 歌手service接口
 */
public interface CommentService {
    /**
     * 增加
     */
    public boolean insert(Comment comment);
    /**
     *修改
     */
    public boolean update(Comment comment);

    /**
     * 删除
     */
    public boolean delete(Integer id);

    /**
     * 根据主键查询整个对象
     */
    public List<Comment> selectByPrimaryKey(Integer id);

    /**
     * 查询所有评论
     */
    public List<Comment> allComment();

    /**
     * 查询某个歌曲下的所有评论
     */
    public List<Comment> commentOfSongId(Integer songId);

    /**
     * 查询某个歌单下的所有评论
     */
    public List<Comment> commentOfSongListId(Integer songListId);
}