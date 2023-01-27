<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default  {
  name:  'App',
  created() {
    if(this.$router.path !== '/home'){
      this.$router.replace('/home')
    }
  }
}
</script>


<style>
#app{
  min-height: 100%;
}
</style>

