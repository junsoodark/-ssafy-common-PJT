<template>
  <div class="home">
    <div id="top-carousel" ref="toporto" class="mb-3">
    <b-carousel
      id="carousel-1"
      v-model="slide"
      :interval="4000"
      controls
      indicators
      background="#ababab"
      img-width="1024"
      img-height="480"
      style="text-shadow: 1px 1px 2px #333;"
      @sliding-start="onSlideStart"
      @sliding-end="onSlideEnd"
    >
      <!-- Text slides with image -->
      <b-carousel-slide>
        <template v-slot:img>
          <img
            class="d-block img-fluid"
            style="width:100%; height:90vh;"
            src="../assets/12314.png"
            alt="image slot"
          >
        </template>
      </b-carousel-slide>

      <!-- Slides with custom text -->
      <b-carousel-slide text="같이 공부할 친구들도 찾고">
        <template v-slot:img>
          <img
            class="d-block img-fluid"
            style="width:100%; height:90vh;"
            src="../assets/first.jpg"
            alt="image slot"
          >
        </template>
      </b-carousel-slide>

      <!-- Slides with image only -->
      <b-carousel-slide text="이런 멋진 장소도 대여해서">
        <template v-slot:img>
          <img
            class="d-block img-fluid"
            style="width:100%; height:90vh;"
            src="../assets/second.jpg"
            alt="image slot"
          >
        </template>
      </b-carousel-slide>

      <!-- Slides with img slot -->
      <!-- Note the classes .d-block and .img-fluid to prevent browser default image alignment -->
      <b-carousel-slide text="성공적인 스터디를 해보자">
        <template v-slot:img>
          <img
            class="d-block img-fluid"
            style="width:100%; height:90vh;"
            src="../assets/third.jpg"
            alt="image slot"
          >
        </template>
      </b-carousel-slide>

      <!-- Slide with blank fluid image to maintain slide aspect ratio -->
      <b-carousel-slide text="성공적인 스터디를 위해">
        <template v-slot:img>
          <img
            class="d-block img-fluid"
            style="width:100%; height:90vh;"
            src="../assets/7e2565951387291c85127b653e4c9919.jpg"
            alt="image slot"
          >
        </template>
      </b-carousel-slide>
    </b-carousel>

    <p class="mt-4 d-none">
      Slide #: {{ slide }}<br>
      Sliding: {{ sliding }}
    </p>
    </div>
    <div id="bot-div" ref="porto">
      <b-container class="bv-example-row">
        <b-row>
          <TeamListItem class="col-4" v-for="team in TeamList" :key="team" v-bind:team="team"></TeamListItem>
        </b-row>
      </b-container>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import TeamListItem from '../components/TeamlistItem.vue'
// 팀 리스트 조회 뜨면 밑에 주석 제거 하고 axios과정 추가
import Axios from 'axios'
const API_URL = process.env.VUE_APP_LOCAL_URL
export default {
  name: 'Home',
  components: {
    TeamListItem
  },
  methods: {
    scrollMeTo (refName) {
    var element = this.$refs[refName];
    var top = element.offsetTop;
    window.focus()
    window.scrollTo({top:top,left:0,behavior:'smooth'});
    },
    onSlideStart () {
      this.sliding = true
    },
    onSlideEnd () {
      this.sliding = false
    }
  },
  mounted () {
    const topWindow = document.querySelector('#top-carousel')
    let scrollMeTo = this.scrollMeTo
    topWindow.addEventListener('mousewheel',function(event){
      if (event.deltaY>0) {
        setTimeout(function(){scrollMeTo('porto')},0)
      } 
    })
    const botWindow = document.querySelector('#bot-div')
    botWindow.addEventListener('mousewheel',function(event){
      if (event.deltaY<0) {
        setTimeout(function(){scrollMeTo('toporto')},0)
      } 
    })
  },
  data () {
    return {
      TeamList: [0,1,2,3,4,5,6,7,8],
      slide: 0,
      sliding: null
    }
  },
  created() {
    for (var j=0; j<this.TeamList.length; j++) {
      Axios.get(`${API_URL}study/{study_id}?study_id=${this.i+j}`)
      .then(res => {console.log(res)})
      .catch(err => {console.log(err)})
    }
  }
}
</script>
