package com.wuwc.classbs.music.service;

import com.wuwc.classbs.music.domain.Song;

import java.util.List;

/**
 * 歌手service接口
 */
public interface SongService {
/*增加*/
    public boolean insert(Song song);
    /*修改*/
    public boolean update(Song song);
    /*删除*/
    public boolean delete(Integer id);
    /*根据主键查询整个对象*/
    public Song selectByPrimaryKey(Integer id);
    /*查询所有歌手*/
    public List<Song> allSong();
    /*根据歌手名字精确查询列表*/
    public List<Song> songOfName(String name);
    /*根据性别查询*/
    public List<Song> songOfSingerId(Integer singId);


    public List<Song> likeSongOfName(String songName);



}
