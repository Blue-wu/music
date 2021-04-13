package com.wuwc.classbs.music.service.impl;

import com.wuwc.classbs.music.dao.SongMapper;
import com.wuwc.classbs.music.domain.Song;
import com.wuwc.classbs.music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 管理员service实现类
 */
@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongMapper songMapper;

    @Override
    public boolean insert(Song song) {
        return songMapper.insert(song)>0;
    }

    @Override
    public boolean update(Song song) {
        return songMapper.update(song)>0;
    }

    @Override
    public boolean delete(Integer id) {
        return songMapper.delete(id)>0;
    }

    @Override
    public Song selectByPrimaryKey(Integer id) {
        return songMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Song> allSong() {
        return songMapper.allSong();
    }

    @Override
    public List<Song> songOfName(String name) {
        return songMapper.songOfName(name);
    }

    @Override
    public List<Song> songOfSingerId(Integer singerId) {
        return songMapper.songOfSingerId(singerId
        );
    }

    @Override
    public List<Song> likeSongOfName(String songName) {
        List<Song> song2=songMapper.likeSongOfName("%"+songName+"%");
      return   songMapper.likeSongOfName("%"+songName+"%");
    }


}