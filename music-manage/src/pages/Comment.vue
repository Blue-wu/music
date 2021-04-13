    
    <template>
    <div class="table">
        <div class="crumbs">
            <i class="el-icon-tickets"></i>评价信息
        </div>
        <div class="container">
            <div class="handle-box">
                <el-button type="primary" size="mini" @click="delAll">批量删除</el-button>
                <el-input v-model="select_word" size="mini" placeholder="请输入内容" class="handle-input"></el-input>
                
            </div>
        </div>
        <el-table size="mini" ref="multipleTable" border style="width:100%" height="680px" :data="data" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="40"></el-table-column>
            <el-table-column label="类型" width="110" align="center">
             <template slot-scope="scope">
                    {{changeTpye(scope.row.type)}}
                </template>
            </el-table-column>
            <el-table-column prop="content" label="内容" width="240" align="center"></el-table-column>
            <el-table-column prop="up" label="点赞" width="150" align="center"></el-table-column>
            <el-table-column prop="createTime" label="评价时间" align="center">
                
               
            </el-table-column>

            <el-table-column label="操作" width="150" align="center">
                <template slot-scope="scope">
                    <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
                    <el-button size="mini" type="danger" @click="handleDelete(scope.row.id)">删除</el-button> 
                </template>
            </el-table-column>
        </el-table>


        <div class="pagination">
            <el-pagination
                background
                layout = "total,prev,pager,next"
                :current-page="currentPage"
                :page-size="pageSize"
                :total="tableData.length"
                @current-change="handleCurrentChange"
                >
            </el-pagination>
        </div>



        <el-dialog title="修改评价" :visible.sync="editVisible" width="400px" center>
            <el-form :model="form" ref="form" label-width="80px">
                <el-form-item prop="content" label="内容" size="12px">
                    <el-input v-model="form.content" style="height:35px" placeholder="内容" type="textarea" ></el-input>
               <br> <br></el-form-item> 
                <el-form-item prop="up" label="点赞" size="mini">
                    <el-input v-model="form.up" placeholder="点赞数" ></el-input>
                </el-form-item> 
                
            </el-form>
            <span slot="footer">
                <el-button size="mini" @click="editVisible = false">取消</el-button>
                <el-button size="mini" @click="editSave">确定</el-button>                
            </span>
        </el-dialog>

        <el-dialog title="删除歌曲" :visible.sync="delVisible" width="300px" center>
            <div align="center">删除不可恢复，是否确定删除？</div>
            <span slot="footer">
                <el-button size="mini" @click="delVisible = false">取消</el-button>
                <el-button size="mini" @click="deleteRow">确定</el-button>                
            </span>
        </el-dialog>
    </div>
</template>

<script>
import { mixin } from '../mixins/index';
import {mapGetters} from 'vuex';
import '@/assets/js/iconfont.js';
import {getCommentById,songOfSingerId,updateComment,delComment,delSong} from '../api/index';

export default {
    mixins: [mixin],
    data(){
        return{
            singerId: '',               //歌手id
            singerName: '',             //歌手名
            centerDialogVisible: false, //添加弹窗是否显示
            editVisible: false,         //编辑弹窗是否显示
            delVisible: false,          //删除弹窗是否显示
            form:{      //编辑框
                content: '',
                up: '',   
            },
            tableData: [],
            tempData: [],
            select_word: '',
            pageSize: 5,    //分页每页大小
            currentPage: 1,  //当前页
            idx: -1,          //当前选择项
            multipleSelection: [],   //哪些项已经打勾
            toggle: false           //播放器的图标状态
        }
    },
    computed:{
        ...mapGetters([
            'isPlay'
        ]),
        //计算当前搜索结果表里的数据
        data(){
            return this.tableData.slice((this.currentPage - 1) * this.pageSize,this.currentPage * this.pageSize)
        }
    },
    watch:{
        //搜索框里面的内容发生变化的时候，搜索结果table列表的内容跟着它的内容发生变化
        select_word: function(){
            if(this.select_word == ''){
                this.tableData = this.tempData;
            }else{
                this.tableData = [];
                for(let item of this.tempData){
                    if(item.content.includes(this.select_word)){
                        this.tableData.content(item);
                    }
                }
            }
        }
    },
    created(){
        this.singerId = this.$route.query.id;
        console.log(this.singerId);
        //this.singerName = this.$route.query.name;
        this.getData();
    },
    destroyed() {
        this.$store.commit('setIsPlay',false);
    },
    methods:{
        //获取当前页
        handleCurrentChange(val){
            this.currentPage = val;
        },
        //查询所有歌手
        getData(){
            this.tempData = [];
            this.tableData = [];
            getCommentById(this.singerId).then(res => {
               //  for(let i in res){
                //this.tableData.push(res[i]);
                //this.tempData.push(res[i]);
              //}
               this.tempData = res;
               this.tableData = res;
                console.log(this.tableData);
                console.log(res);
                this.currentPage = 1;
            })
        },

        //弹出编辑页面
        handleEdit(row){
            this.editVisible = true;
            this.form = {
                content: row.content,
                up: row.up,
                id:row.id,
            }
        },
        //保存编辑页面修改的数据
        editSave(){
            let params = new URLSearchParams();
             params.append('id',this.form.id);
            params.append('content',this.form.content);
            params.append('up',this.form.up);
    

            updateComment(params)
            .then(res => {
                if(res.code == 1){
                    this.getData();
                    this.notify("修改成功","success");
                }else{
                    this.notify("修改失败","error");
                }
            })
            .catch(err => {
                console.log(err);
            });
            this.editVisible = false;
        },
        //删除一个评价
        deleteRow(){
            delComment(this.idx)
            .then(res => {
                if(res){
                    this.getData();
                    this.notify("删除成功","success");
                }else{
                    this.notify("删除失败","error");
                }
            })
            .catch(err => {
                console.log(err);
            });
            this.delVisible = false;
        },
    }   
}
</script>

<style scoped>
    .handle-box{
        margin-bottom: 20px;
    }
    .song-img{
        width: 100%;
        height: 80px;
        border-radius: 5px;
        margin-bottom: 5px;
        overflow: hidden;
    }
    .handle-input{
        width: 300px;
        display: inline-block;
    }
    .pagination{
        display: flex;
        justify-content: center;
    }
    .play {
        position: absolute;
        z-index: 100;
        width: 80px; 
        height: 80px;
        display: flex;
        align-items: center;
        justify-content: center;
        cursor: pointer;
        top: 18px;
        left: 15px;
    }

    .icon {
        width: 2em;
        height: 2em;
        color: white;
        fill: currentColor;
        overflow: hidden;
    }
</style>