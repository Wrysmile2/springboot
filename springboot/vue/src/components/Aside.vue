<template>
  <el-aside :width="sideWith + 'px'" style="background-color: rgb(238, 241, 246);box-shadow: 2px 0 6px rgb(0 21 40)">
    <el-menu :default-openeds="['1']"
             style="min-height: 100%;overflow:hidden;"
             background-color="rgb(48,65,86)"
             text-color="#fff"
             active-text-color="#ffd04b"
             :collapse-transition="false"
             :collapse="isCollapse"
             router
             >
      <div style="height: 60px ;line-height: 60px;text-align: center">
        <img src="../assets/logo.png" alt="" style="width: 20px;position: relative; top: 5px;margin-right: 5px">
        <b style="color: #fff" v-show="loginText">后台管理系统</b>
      </div>

      <el-menu-item index="/">
        <template slot="title">
          <i class="el-icon-message"></i>
          <span slot="title">主页</span>
        </template>
      </el-menu-item>

      <el-submenu index="2">
        <template slot="title">
          <i class="el-icon-menu"></i>
          <span slot="title">系统管理</span>
        </template>
        <el-menu-item index="/user">
          <i class="el-icon-s-custom"></i>
          <span slot="title">用户管理</span>
        </el-menu-item>
        <el-menu-item index="/userInfo">
          <i class="el-icon-s-custom"></i>
          <span slot="title">管理员列表</span>
        </el-menu-item>
        <el-menu-item index="/add">
          <i class="el-icon-s-custom"></i>
          <span slot="title">添加新闻</span>
        </el-menu-item>
      </el-submenu>

    </el-menu>
  </el-aside>
</template>

<script>
  export default {
    name:'Aside',
    props:{
      isCollapse:Boolean,
      loginText:Boolean,
      sideWith:length,
    }
  }
</script>