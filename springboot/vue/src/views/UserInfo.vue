<template>
  <div>
    <!--搜索框-->
    <div style="padding: 10px 0">
      <el-input style="width: 200px" placeholder="请输入名字" suffix-icon="el-icon-search" v-model="username"></el-input>
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
    </div>
    <!--增删改查-->
    <div style="margin: 10px 0">
      <el-button type="primary" @click="handleAdd">新增<i class="el-icon-circle-plus-outline"></i></el-button>
      <el-popconfirm
          class="ml-5"
          confirm-button-text='确定'
          cancel-button-text='我在想想'
          icon="el-icon-info"
          icon-color="red"
          title="您确定要删除这些数据吗？"
          @Confirm="deleteBatch"
      >
        <el-button type="danger" slot="reference">批量删除<i class="el-icon-remove-outline"></i></el-button>
      </el-popconfirm>
      <el-upload action="http://localhost:9090/userInfo/import"
                 :show-file-list="false" accept="xlsx" :on-success="handleExcelImportSuccess" style="display: inline-block">
        <el-button type="primary" class="ml-5">导入<i class="el-icon-bottom"></i></el-button>
      </el-upload>
      <el-button type="primary" @click="exp" class="ml-5">导出<i class="el-icon-top"></i></el-button>
    </div>

    <el-table :data="tableData" border stripe :header-row-name="headerBg" @selection-change="handleSelectionChange">
      <el-table-column prop="userId" label="ID" width="180"></el-table-column>
      <el-table-column prop="username" label="姓名" width="180"></el-table-column>
      <el-table-column prop="DateAndTime" label="创建时间"></el-table-column>
      <el-table-column lable="操作" width="200" align="center">
        <template slot-scope="scope">
          <el-button type="success" @click="handleEdit(scope.row)">编辑<i class="el-icon-edit"></i></el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='我在想想'
              icon="el-icon-info"
              icon-color="red"
              title="您确定要删除吗？"
              @Confirm="handleDelete(scope.row.id)"
          >
            <el-button type="danger" slot="reference">删除<i class="el-icon-remove-outline"></i></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <!--页码-->
    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[2, 5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>

    <!--管理员新增-->
    <el-dialog title="管理员信息" :visible.sync="dialogFormVisible" width="40%">
      <el-form label-width="80px">
        <el-form-item label="用户名">
          <el-input v-model="form.username" autocomplete="off"></el-input>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
 export default {
   name:'UserInfo',
   data(){
     return{
       tableData:[],
       total: 0,
       pageNum: 1,
       pageSize: 2,
       username: "",
       form:{},
       dialogFormVisible: false,
       multipleSelection:false,
       headerBg: 'headerBg',
     }
   },
   created(){
     this.load()
   },
   methods:{
     load() {
       //请求分页查询数据
       //?pageNum=" + this.pageNum + "&pageSize=" + this.pageSize + "&username=" + this.username
       this.axios.get("http://localhost:9090/userInfo/page",{
         params:{
           pageNum: this.pageNum,
           pageSize: this.pageSize,
           username: this.username,
         }
       }).then(res => {
         // console.log(res)
         this.tableData = res.data.records
         this.total = res.data.total
       })
     },
     handleSelectionChange(val) {
       console.log(val)
       this.multipleSelection = val;
     },
     handleEdit(row){
       this.form = row
       this.dialogFormVisible = true
     },
     handleDelete(id){
       this.axios.delete("http://localhost:9090/userInfo/"+id).then(res => {
         if (res){
           this.$message.success("删除成功")
           this.load()
         }else {
           this.$message.error("删除失败")
         }
       })
     },

     /*管理员的crud*/
     handleAdd(){
       this.dialogFormVisible = true
       this.form = {}
     },
     deleteBatch(){
       let ids = this.multipleSelection.map(v => v.id)
       this.axios.post("http://localhost:9090/userInfo/delete/batch",ids).then(res => {
         if (res){
           this.$message.success("删除成功")
           this.load()
         }else {
           this.$message.error("删除失败")
         }
       })
     },
     save(){
       this.axios.post("http://localhost:9090/userInfo",this.form).then(res => {
         if (res){
           this.$message.success("保存成功")
           this.dialogFormVisible = false;
           this.load()
         }else {
           this.$message.error("保存失败")
         }
       })
     },

     /*文件导入导出*/
     exp(){
       window.open("http://localhost:9090/userInfo/export")
     },
     handleExcelImportSuccess(){
       this.$message.success("文件导入成功")
       this.load()
     },

     handleSizeChange(pageSize) {
       this.pageSize = pageSize
       this.load()
     },
     handleCurrentChange(pageNum) {
       this.pageNum = pageNum
       this.load()
     },
   }
 }
</script>

<style>

</style>




