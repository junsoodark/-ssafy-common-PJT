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
      <b-carousel-slide
        caption="First slide"
        text="Nulla vitae elit libero, a pharetra augue mollis interdum."
        img-src="https://picsum.photos/1024/480/?image=52"
      ></b-carousel-slide>

      <!-- Slides with custom text -->
      <b-carousel-slide img-src="https://picsum.photos/1024/480/?image=54">
        <h1>Hello world!</h1>
      </b-carousel-slide>

      <!-- Slides with image only -->
      <b-carousel-slide img-src="https://picsum.photos/1024/480/?image=58"></b-carousel-slide>

      <!-- Slides with img slot -->
      <!-- Note the classes .d-block and .img-fluid to prevent browser default image alignment -->
      <b-carousel-slide>
        <template v-slot:img>
          <img
            class="d-block img-fluid w-100"
            width="1024"
            height="480"
            src="https://picsum.photos/1024/480/?image=55"
            alt="image slot"
          >
        </template>
      </b-carousel-slide>

      <!-- Slide with blank fluid image to maintain slide aspect ratio -->
      <b-carousel-slide caption="Blank Image" img-blank img-alt="Blank image">
        <p>
          Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eros felis, tincidunt
          a tincidunt eget, convallis vel est. Ut pellentesque ut lacus vel interdum.
        </p>
      </b-carousel-slide>
    </b-carousel>

    <p class="mt-4">
      Slide #: {{ slide }}<br>
      Sliding: {{ sliding }}
    </p>
    </div>
    <div id="bot-div" ref="porto">
      <HelloWorld msg="Welcome to Your Vue.js App"/>
      <TeamListItem v-for="team in TeamList" :key="team"></TeamListItem>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'
import TeamListItem from '../components/TeamlistItem.vue'
// 팀 리스트 조회 뜨면 밑에 주석 제거 하고 axios과정 추가
// import Axios from 'axios

export default {
  name: 'Home',
  components: {
    HelloWorld,
    TeamListItem
  },
  methods: {
    scrollMeTo(refName) {
    var element = this.$refs[refName];
    var top = element.offsetTop;
    window.focus()
    window.scrollTo({top:top,left:0,behavior:'smooth'});
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
      TeamList: [],
    }
  },
  created () {

  }
}
</script>
