<template>
  <div style="min-height: 100%">
    <el-container style="height: 100vh;">

        <Aside :sideWith="sideWith" :isCollapse="isCollapse" :loginText="loginText"></Aside>

      <el-container>

        <el-header style="border-bottom: 1px solid #ccc">
          <Header :collapseBtnClass="collapseBtnClass" :isCollapse="isCollapse"></Header>
        </el-header>

        <el-main>
          <router-view></router-view>
        </el-main>

      </el-container>
    </el-container>
  </div>
</template>

<script>
// import axios from "axios";
import Aside from "../components/Aside"
import Header from "../components/Header"
export default {
  name: 'Home',
  components: {
    Aside,
    Header,
  },
  data() {
    return {
      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      sideWith: 200,
      loginText: true,
    }
  },
  methods: {
    collapse() { //点击按钮触发
      this.isCollapse = !this.isCollapse
      if (this.isCollapse) { //收缩
        this.sideWith = 64
        this.collapseBtnClass = 'el-icon-s-unfold'
        this.loginText = false
      } else { //展开
        this.sideWith = 200
        this.collapseBtnClass = 'el-icon-s-fold'
        this.loginText = true
      }
    },
  }
}
</script>
