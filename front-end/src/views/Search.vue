<template>
  <div>
    <h1>검색결과!</h1>
  </div>
</template>

<script>
//import Axios from 'axios'
export default {
  data () {
    return {
      searchData: this.$route.params.search
    }
  },
  mounted () {
    //Axios
    console.log(this.searchData)
  }
}
</script>

<style>

</style>