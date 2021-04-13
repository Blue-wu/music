package com.wuwc.classbs.music.dao;

import com.wuwc.classbs.music.domain.ListSong;
import com.wuwc.classbs.music.domain.SongList;
import org.springframework.stereotype.Repository;

import java.util.List;
/*歌单DAO*/
@Repository
public interface SongListMapper {
    //增加
    public int insert(SongList songList);
    //修改
    public  int update(SongList songList);
    //删除
    public int delete(Integer id);
    /*根据给钱id和歌单id删除*/
    public SongList selectByPrimaryKey(Integer id);
    /*查询所有歌单里面的所有歌曲*/
    public List<SongList> allSongList();
    /*根据标题查询所有的歌曲*/
    public List<SongList> songListOfTitle(String title);
    /*根据标题模糊查询歌单列表*
     */
    public List<SongList> likeTitle(String title);
    /*根据风格模糊查询歌单列表*/
    public List<SongList> likeStyle(String style);
    public List<SongList> GetSongListId(List id);
    public List<SongList> StyleOfSongId(String style,Integer num);
}
