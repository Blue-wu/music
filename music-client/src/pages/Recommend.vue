<template>
    <div class="singer">
        <ul class="singer-header">
            <li v-for="(item,index) in TypeStyle" :key="index" @click="handleChangeView(item)"
                :class="{active:item.name==activeName}">
                {{item.name}}
            </li>
        </ul>
        <div>


             <div class="content">
       <h1 class="title">
           <slot name="title"></slot>
           <hr/>
       </h1>
       <ul v-show="tf">
           <li>
               <div class="song-item">
                   <span class="item-index"></span>
                   <span class="item-title">歌曲名</span>
                   <span class="item-name">歌手</span>
                   
               </div>
           </li>
           <li v-for="(item,index) in albumDatas" :key="index">
               <div class="song-item" @click="toplay(item.id,item.url,item.pic,index,item.name,item.lyric)">
                   <span class="item-index">
                       {{index + 1}}
                   </span>
                   <span class="item-title">{{replaceFName(item.name)}}</span>
                   <span class="item-name">{{replaceLName(item.name)}}</span>
               </div>
                
           </li>
       </ul>
       
       <ul v-show="tf==false">
           <li>
               <div class="song-item">
                   <span class="item-index"></span>
                   <span class="item-title">歌曲名</span>
                   <span class="item-name">歌手</span>
                 
               </div>
           </li>
           <li  v-for="(item,index) in albumDatas" :key="index">
               <div class="song-item" @click="totplay(item.id,item.url,pic,index,item.name+'-'+item.singer,lyric)">
                   <span class="item-index">
                       {{index + 1}}
                   </span>
                   <span class="item-title">{{item.name}}</span>
                   <span class="item-name">{{item.singer}}</span>
                   
               </div>
           </li>
       </ul>
    </div>         
            <div class="pagination">
                <el-pagination @current-change="handleCurrentChange" background layout="total,prev,pager,next"
                        :current-page="currentPage" :page-size="pageSize" :total="albumDatas.length">
                </el-pagination>
            </div>
        </div>
    </div>
</template>
<script>
import {mixin} from "../mixins";
import ContentList from '../components/ContentList';
import {getRecommennd,getSingerOfSex,GetRankSong} from '../api/index';
import {TypeStyle} from '../assets/data/recommend';
import {mapGetters} from 'vuex';

export default {
    name: 'singer',
    mixins: [mixin],
    components:{
        ContentList
    },
    data(){
        return{
            albumDatas: [],      //歌手数据
            pageSize: 20,        //页面大小，一页有15条数据
            currentPage: 1,      //当前页，默认第一页
            TypeStyle: [],           //风格
            activeName: '云音乐飙升榜' ,   //当前风格
            pic:'/img/songPic/haikuotiankong.jpg',
            lyric:'',
            tf:'true'
        }
    },
    computed:{
        ...mapGetters([
            'userId',           //当前登录用户id
        ]),
        //计算当前表格中的数据
        data(){
            return this.albumDatas.slice((this.currentPage - 1)*this.pageSize,this.currentPage*this.pageSize)
        },
         
    },
    mounted(){
        this.lyric='';
        this.userId=this.userId;
        this.TypeStyle = TypeStyle;
        this.getSingerList();
    },
    created(){
     this.lyric=''
        
    },

    methods:{
        getSingerList(){            
            
                console.log(this.userId);
              GetRankSong(this.userId).then(res =>{

              console.log(res);
              this.currentPage = 1;
                    this.albumDatas = res;
              })
                         
        }, 
        getRecommendList(type){
            
            getRecommennd(type).then(res =>{
                    console.log(res);
                    this.currentPage = 1;
                    this.albumDatas = res;
                   this.$store.commit('setListOfSongs',this.albumDatas);
                })  
            
        },
    
        //获取当前页
        handleCurrentChange(val){
            this.currentPage = val;
        },
        //根据类型显示对应的歌手
        handleChangeView(item){
            this.tf=false;
            this.activeName = item.name;
            this.albumDatas = [];
             if(item.name == '个性推荐'){
                this.getSingerList();
            }else{
            this.getRecommendList(item.type);
            }
        },


    }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/singer.scss';
@import '../assets/css/album-content.scss'
</style>