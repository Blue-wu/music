package com.wuwc.classbs.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.wuwc.classbs.music.dao.ListSongMapper;
import com.wuwc.classbs.music.domain.ListSong;
import com.wuwc.classbs.music.service.ListSongService;
import com.wuwc.classbs.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/listSong")
public class ListSongController {
    @Autowired
    private ListSongService listSongService;
    /*给歌单添加歌曲*/
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public  Object addListSong(HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        String songId=request.getParameter("songId");
        String songListId=request.getParameter("songListId");
        ListSong listSong=new ListSong();
        listSong.setSongId(Integer.parseInt(songId));
        listSong.setSongListId(Integer.parseInt(songListId));
        boolean flag=listSongService.insert(listSong);
        if(flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"保存成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"保存失败");
        return jsonObject;
    }
    /*根据歌曲id查询歌曲*/
    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    public Object detail(HttpServletRequest request){
        String songListId = request.getParameter("songListId");
        return listSongService.listSongOfSongListId(Integer.parseInt(songListId));
    }

    /**
     * 删除歌单里的歌曲
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object delete(HttpServletRequest request){
        String songId = request.getParameter("songId").trim();                 //歌曲id
        String songListId = request.getParameter("songListId").trim();        //歌单id
        boolean flag = listSongService.deleteBySongIdAndSongListId(Integer.parseInt(songId),Integer.parseInt(songListId));
        return flag;
    }

}
