package com.wuwc.classbs.music.dao;

import com.wuwc.classbs.music.domain.Consumer;
import com.wuwc.classbs.music.domain.Song;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 歌曲Dao
 */
@Repository
public interface ConsumerMapper {

   public int insert(Consumer consumer);

   public int update(Consumer consumer);

   /*
   * 删除
   * */
public  int delete(Integer id);

/*
* 根据主键查询整个对象
* */
   public Consumer selectByPrimaryKey(Integer id);

   /*
    * 根据主键查询整个对象username
    * */
   public int selectConsumerId(Integer id);

   /*查询所有歌曲*/
   public List<Consumer> allConsumer();

   /*验证密码*/
   public int verifyPassword(String username,String password);

   /**
    * 根据账号查询
    */
   public Consumer getByUsername(String username);
}

