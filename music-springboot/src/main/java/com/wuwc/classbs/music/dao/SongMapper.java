package com.wuwc.classbs.music.dao;

import com.wuwc.classbs.music.domain.Song;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 歌曲Dao
 */
@Repository
public interface SongMapper {

   public int insert(Song song);

   public int update(Song song);

   /*
   * 删除
   * */
public  int delete(Integer singerId);

/*
* 根据主键查询整个对象
* */
   public Song selectByPrimaryKey(Integer singerId);


   /*查询所有歌曲*/
   public List<Song> allSong();

   /*根据歌曲名字模糊查询列表*/
   public List<Song> songOfName(String name);

   /*根据歌手id查询*/
   public List<Song> songOfSingerId(Integer singerId);

   public List<Song> likeSongOfName(String songName);


}

