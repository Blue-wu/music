package com.wuwc.classbs.music.dao;

import com.wuwc.classbs.music.domain.ListSong;
import com.wuwc.classbs.music.domain.Song;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ListSongMapper {
    //增加
    public int insert(ListSong listSong);
    //修改
    public  int update(ListSong listSong);
    //删除
    public int delete(Integer id);
    /*根据给歌曲id和歌单id删除*/
    public int deleteBySongIdAndSongListId(Integer songId,Integer songListId);
    /**
     * 根据主键查询整个对象
     */
    public ListSong selectByPrimaryKey(Integer id);
    /*查询所有歌单里面的所有歌曲*/
    public List<ListSong> allListSong();
    /*根据歌单id查询所有的歌曲*/
    public List<ListSong> listSongOfSongListId(Integer songListId);
    /*根据歌单id查询所有的歌曲*/
    public List<ListSong> listSongOfSongId(Integer songId);
    public List<ListSong> selectBySongId(List id);

}
