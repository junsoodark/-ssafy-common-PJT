<template>
  <b-container>
    <br>
    <h1>마이페이지</h1>
    <div>
      <hr>
      <b-media>
        <template v-slot:aside>
          <b-row>
            <!-- <b-img v-if="image === undefined" blank blank-color="#abc" width="300" rounded="circle" alt="aaa"></b-img>
            <b-img v-else-if="image === null" blank blank-color="#abc" width="300" rounded="circle" alt="bbb"></b-img> -->
            <!-- <b-img v-if="image === null" id="myimg" src="" width="300" rounded="circle" alt="aaa"></b-img>
            <b-img v-else-if="image === undefined" id="myimg" src="" width="300" rounded="circle" alt="bbb"></b-img> -->
            <b-img id="myimg" :src="imgUrl" blank blank-color="#abc" width="300" rounded="circle" alt="ccc"></b-img>
            <!-- <img src=""  id="myimg" alt=""> -->
          </b-row>
          <b-row>
            <div class="group group_upload">
              <button class="btn_upload" type="button" @click="onPickFile">
                이미지 업로드
              </button>
              <input
                id="refresh"
                type="file"
                value="upload"
                style="display: none;"
                ref="fileInput"
                accept="image/*"
                @change="onFilePicked"
              />
              <!-- <progress value="0" max="100" id="uploader">0%</progress> -->
            </div>
          </b-row>
        </template>
        <b-row>
          <b-col cols="3" class="text-center font-weight-bold"><p>이메일</p> </b-col>
          <b-col cols="9" class="text-center font-weight-bold"><p>{{ email }}</p></b-col>
        </b-row>
        <hr>
        <b-row>
          <b-col cols="3" class="text-center font-weight-bold"><p>이름</p></b-col>
          <b-col cols="9" class="text-center font-weight-bold"><p>{{ userInfo.name }}</p></b-col>
        </b-row>
        <hr>
        <b-row>
          <b-col cols="3" class="text-center font-weight-bold"><p>나이</p></b-col>
          <b-col cols="9" class="text-center font-weight-bold"><p>{{ userInfo.age }}</p></b-col>
        </b-row>
        <hr>
        <b-row>
          <b-col cols="3" class="text-center font-weight-bold"><p>성별</p></b-col>
          <b-col cols="9" class="text-center font-weight-bold"><p v-if="userInfo.sex == 1">남자</p><p v-if="userInfo.sex == 2">여자</p></b-col>
        </b-row>
        <hr>
          <b-row align-h="start" class="text-left">
            <b-col cols="4">참여중인 스터디 {{ countStudy }}</b-col>
            <b-col cols="4">개설한 스터디 0</b-col>
          </b-row>
          <b-row>
            <b-col>
              <b-progress :value="value" :max="max" show-progress animated></b-progress>
            </b-col>
          </b-row>
          <b-row align-h="start" class="text-left">
            <b-col cols="4">관심사</b-col>
            <b-col cols="8">#Web</b-col>
          </b-row>
          <b-row align-h="start" class="text-left">
            <b-col cols="4">한마디</b-col>
            <b-col cols="8">올해안에 취업한다!!!</b-col>
          </b-row>
          <br>
          <b-row align-h="end" class="text-right">
            <div class="badge text-wrap mr-2" style="width: 9rem;"><b-button route :to="{name: 'UpdateUserInfo'}" variant="warning">회원정보 수정</b-button></div>
            <div class="badge text-wrap mr-2" style="width: 9rem;"><b-button route :to="{name: 'EditPassword'}" variant="success">비밀번호 변경</b-button></div>
            <div class="badge text-wrap mr-2" style="width: 7rem;"><b-button variant="danger" v-b-modal.my-modal>회원탈퇴</b-button></div>
          </b-row>
      </b-media>
      <hr>
      </div>
      <div>
      <b-row class="text-left">
        <h3>내 스터디</h3>
      </b-row>
      <br>
      <b-row>
        <b-col class="text-left">
          <h6>현재 스터디</h6>
        </b-col>
      </b-row>

      <b-list-group>
        <b-row>
          <b-col cols="4" class="p-0"><b-list-group-item >TITLE</b-list-group-item></b-col>
          <b-col cols="8" class="p-0"><b-list-group-item >CONTENT</b-list-group-item></b-col>
        </b-row>
        <hr>
        <b-row v-for="item in items" :key="item.studyId">
          <b-col cols="4" class="p-0"><b-list-group-item route :to="{ name: 'StudyDetail', params: {id:item.studyId} }">{{ item.title }}</b-list-group-item></b-col>
          <b-col cols="8" class="p-0"><b-list-group-item route :to="{ name: 'StudyDetail', params: {id:item.studyId} }">{{ item.content }}</b-list-group-item></b-col>
        </b-row>
      </b-list-group>

      <br>
      <br>
    </div>

    <!-- 모달 -->
    <b-modal id="my-modal">
      <template v-slot:modal-header>
        <h5>회원탈퇴 하시겠습니까?</h5>
      </template>

      <template v-slot:default="">
        <b-form-input
          id="input-2"
          required
          type="password"
          v-model="confirmPassword"
          placeholder="비밀번호를 입력하세요."
        ></b-form-input>
      </template>

      <template v-slot:modal-footer="{ deleteUserAccount, cancel }">
        <!-- Emulate built in modal footer ok and cancel button actions -->
        <b-button size="sm" variant="danger" @click="deleteUserAccount(confirmPassword)">
          회원탈퇴
        </b-button>
        <b-button size="sm" variant="primary" @click="cancel()">
          취소
        </b-button>
      </template>
    </b-modal>
  </b-container>
</template>

<script>
import { mapState, mapActions } from 'vuex'
import Axios from 'axios';
import router from "@/router";
import VueCookies from "vue-cookies";
import firebase from 'firebase'
// import cors from 'cors'

const API_URL = process.env.VUE_APP_LOCAL_URL

export default {
  data() {
    return {
      mainProps: { blank: true, blankColor: '#777', width: 75, height: 75, class: 'm1' },
      value: 90,
      max: 100,
      items: [],
      countStudy: 0,
      confirmPassword: null,
      userInfo: {
        sex: null,
        age: null,
        name: null,
      },
      progressUpload: 0,
      uploadTask: '',
      imgUrl: 'https://previews.123rf.com/images/salamatik/salamatik1801/salamatik180100019/92979836-%ED%94%84%EB%A1%9C%ED%95%84-%EC%9D%B5%EB%AA%85%EC%9D%98-%EC%96%BC%EA%B5%B4-%EC%95%84%EC%9D%B4%EC%BD%98-%ED%9A%8C%EC%83%89-%EC%8B%A4%EB%A3%A8%EC%97%A3-%EC%82%AC%EB%9E%8C%EC%9E%85%EB%8B%88%EB%8B%A4-%EB%82%A8%EC%84%B1-%EA%B8%B0%EB%B3%B8-%EC%95%84%EB%B0%94%ED%83%80-%EC%82%AC%EC%A7%84-%EC%9E%90%EB%A6%AC-%ED%91%9C%EC%8B%9C-%EC%9E%90-%ED%9D%B0%EC%83%89-%EB%B0%B0%EA%B2%BD%EC%97%90-%EA%B3%A0%EB%A6%BD-%EB%B2%A1%ED%84%B0-%EC%9D%BC%EB%9F%AC%EC%8A%A4%ED%8A%B8-%EB%A0%88%EC%9D%B4-%EC%85%98.jpg',

    }
  },

  computed: {
    ...mapState({
      email: state => state.moduleName.email,
      imageUrl: state => state.moduleName.imageUrl,
    }),
  },

  methods: {
    onPickFile() {
      this.$refs.fileInput.click()
    },
    onFilePicked(event) {
      // 업로드
      var file = event.target.files[0];
      var storageRef = firebase.storage().ref(`images/${this.email}/${this.email}`);
      console.log('file', file)
      var task = storageRef.put(file);
      console.log(this.uploadTask)

      var uploader = document.getElementById('uploader');      

      task.on('state_changed',
        //progress Bar
        function progess(snapshot){
          var pct = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
          uploader.value = pct;
        },
        // error
        function error(err){
          console.log(err)
        },
        // complete
        function (){
          task.snapshot.ref.getDownloadURL().then(function(downloadURL) {
            var img = document.getElementById('myimg');
            img.src = downloadURL;
          })
        }
      )
    },

    ...mapActions(['authDelete']),
    deleteUserAccount(data) {
      const params = {
        email: this.email,
        password: data,
      }
      Axios({method:'DELETE', url:`${API_URL}user`,params:params,headers:{'Content-Type': 'application/json; charset=utf-8'}})
      .then(res => {
        alert(res.data)
        VueCookies.remove("auth-token")
        this.$store.dispatch('logout')
        router.push({ name: "Home" })
      })
      .catch(err => {
        console.log(err)
        alert('삭제 실패. 비밀번호를 확인해주세요!')
      })
    },
  },
  created () {
    // studyMember 가져오기
    Axios.get(`${API_URL}study/email?email=${this.email}`)
    .then(res => {
      this.items = res.data
      this.countStudy = res.data.length
    })
    .catch(err => {
      console.log(err)
    })
    // userInfo 가져오기
    Axios.get(`${API_URL}user/${this.email}`)
    .then(res => {
      this.userInfo = res.data
    })
    .catch(err => {
      console.log(err)
    })
    // 프로필 이미지 가져오기
    firebase.storage().ref(`images/${this.email}/${this.email}`).getDownloadURL()
    .then(function(url) {
      var xhr = new XMLHttpRequest();
      xhr.responseType = 'blob';
      xhr.onload = function() {};
      xhr.open('GET', url);
      xhr.send();
      var img = document.getElementById('myimg');
      img.src = url;
    })
    .catch(function(err) {
      console.log(err)    
    })
    
  },

}
</script>

<style>

</style>