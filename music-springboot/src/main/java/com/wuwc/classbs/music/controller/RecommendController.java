package com.wuwc.classbs.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.wuwc.classbs.music.domain.Collect;
import com.wuwc.classbs.music.domain.ListSong;
import com.wuwc.classbs.music.domain.Song;
import com.wuwc.classbs.music.domain.SongList;
import com.wuwc.classbs.music.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/recommend")
public class RecommendController {
    @Autowired
    private RecommendService recommendService;
    @Autowired
    private CollectService collectService;
    @Autowired
    private ListSongService listSongService;
    @Autowired
    private SongListService songListService;
    @Autowired
    private SongService songService;


    /*根据性别查询歌手*/
    @RequestMapping(value = "/recommendOfType",method = RequestMethod.GET)
    public Object recommendOfType(HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        String type=request.getParameter("type").trim();
        return recommendService.recommendOfType(Integer.parseInt(type));
    }

    /*获取个性推荐*/
    @RequestMapping(value = "/GetRankSong",method = RequestMethod.GET)
    public Object GetRankSong(HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        String userId=request.getParameter("userId").trim();
        List<Collect> list=collectService.collectOfUserId(Integer.parseInt(userId));
        List<List<ListSong>> list1=new ArrayList();
        List<List<SongList>> list2=new ArrayList();
        List array=new ArrayList();
        List<List<SongList>> list3=new ArrayList();
        for (int i=0;i<list.size();i++) {
            int songId=list.get(i).getSongId();
            list1.add(listSongService.listSongOfSongId(songId));
            System.out.println(list1);
        }
        //循环遍历得到songListId
        for (int i=0;i<list1.size();i++) {
            int l=list1.get(i).size();
            for (int j=0;j<list1.get(i).size();j++) {
                int songListId = list1.get(i).get(j).getSongListId();
                array.add(songListId);
               // list3.add(songListService.GetSongListId(songListId));
                //System.out.println(list2);
            }
        }
//获取前三类型
        List<SongList> list4=songListService.GetSongListId(array);
//遍历获取歌单
        //List<SongList> listSongIds=new ArrayList<>();
        List<SongList> listSongIds1=songListService.likeStyle(list4.get(0).getStyle());
        List<SongList> listSongIds2=songListService.likeStyle(list4.get(1).getStyle());
        List<SongList> listSongIds3=songListService.likeStyle(list4.get(2).getStyle());
       /* listSongIds.add((SongList) listSongIds1);
        listSongIds.add((SongList) listSongIds2);
        listSongIds.add((SongList) listSongIds3);*/
        //获取歌单id
        List array1=new ArrayList();
        for (int i = 0; i <listSongIds1.size() ; i++) {
            array1.add(listSongIds1.get(i).getId());
        }
        List array2=new ArrayList();
        for (int i = 0; i <listSongIds2.size() ; i++) {
            array2.add(listSongIds2.get(i).getId());
        }
        List array3=new ArrayList();
        for (int i = 0; i <listSongIds3.size() ; i++) {
            array3.add(listSongIds3.get(i).getId());
        }
        //根据歌单id查询歌曲
        List<ListSong> songIds=listSongService.selectBySongId(array1);
        List<ListSong> songId2=listSongService.selectBySongId(array2);
        List<ListSong> songId3=listSongService.selectBySongId(array3);
List<Song> allSongs=new ArrayList<>();
        for (int i= 0; i <10 ; i++) {
            int j= (int) (1+Math.random()*(songIds.size()-1+1));
            Song song=songService.selectByPrimaryKey(songIds.get(j).getSongId());
            allSongs.add(song);
        }
        for (int i= 0; i <4 ; i++) {
            int j= (int) (1+Math.random()*(songId2.size()-1+1));
            Song song=songService.selectByPrimaryKey(songIds.get(j).getSongId());
            allSongs.add(song);
        }
        for (int i= 0; i <1 ; i++) {
            int j= (int) (1+Math.random()*(songId3.size()-1+1));
            Song song=songService.selectByPrimaryKey(songIds.get(j).getSongId());
            allSongs.add(song);
        }

        System.out.println(allSongs);
        //List<Song> songIds=songService.selectBySongId(array1);


        return allSongs;
    }

}



