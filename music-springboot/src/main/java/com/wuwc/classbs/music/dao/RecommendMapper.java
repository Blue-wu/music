package com.wuwc.classbs.music.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 管理员Dao
 */
@Repository
public interface RecommendMapper {

/*
   public int insert(Recommend singer);

   public int update(Recommend singer);

   */
/*
   * 删除
   * *//*

public  int delete(Integer id);

*/
/*
* 根据主键查询整个对象
* *//*

   public Singer selectByPrimary(Integer id);


   */
/*查询所有歌手*//*

   public List<Recommend> allSinger();
*/

   /*根据歌手名字模糊查询列表*/
   public List<RecommendMapper> recommendOfType(Integer type);



}

