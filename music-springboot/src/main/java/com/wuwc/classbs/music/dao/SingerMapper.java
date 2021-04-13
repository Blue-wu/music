package com.wuwc.classbs.music.dao;

import com.wuwc.classbs.music.domain.Singer;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 管理员Dao
 */
@Repository
public interface SingerMapper {

   public int insert(Singer singer);

   public int update(Singer singer);

   /*
   * 删除
   * */
public  int delete(Integer id);

/*
* 根据主键查询整个对象
* */
   public Singer selectByPrimary(Integer id);


   /*查询所有歌手*/
   public List<Singer> allSinger();

   /*根据歌手名字模糊查询列表*/
   public List<Singer> singerOfName(String name);

/*根据性别查询*/
   public List<Singer> singerOfSex(Integer sex);

}

