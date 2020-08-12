<template>
  <b-container>
    <br>
    <h1>스터디 수정 페이지</h1>
    <br>
    <b-form @submit.prevent="studyUpdate">
      <!-- 스터디 이름 -->
      <b-row>
        <b-col class="input-group input-group-lg">
          <div class="input-group-prepend">
            <span class="input-group-text" style="width: 9rem;" id="inputGroup-sizing-default">스터디 이름</span>
          </div>
          <input v-model="form.title" type="text" class="form-control text-center">
        </b-col>
      </b-row>
      <br>
      <!-- 지역 -->
      <b-row>
        <b-col class="input-group input-group-lg">
          <div class="input-group-prepend">
            <span class="input-group-text" style="width: 9rem;" id="inputGroup-sizing-default">지역</span>
          </div>
          <b-form-select v-model="form.si" :options="siAreas" required></b-form-select>
          <b-form-select v-model="form.gu" :options="guAreas" required></b-form-select>
        </b-col>
      </b-row>
      <br>
      <!-- 인원수 -->
      <b-row>
        <b-col class="input-group input-group-lg">
          <div class="input-group-prepend">
            <span class="input-group-text" style="width: 9rem;" id="inputGroup-sizing-default">인원수</span>
          </div>
          <b-form-spinbutton
            v-model="form.maxMembers"
            :min="form.numMembers"
            max="100"
            required
          ></b-form-spinbutton>
        </b-col>
      </b-row>
      <br>
      <!-- 분야 -->
      <b-row>
        <b-col class="input-group input-group-lg">
          <div class="input-group-prepend">
            <span class="input-group-text" style="width: 9rem;" id="inputGroup-sizing-default">분야</span>
          </div>
          <b-form-select v-model="form.field" :options="fields" required></b-form-select>
        </b-col>
      </b-row>
      <br>
      <!-- 온/오프 -->
      <b-row>
        <b-col class="input-group input-group-lg">
          <div class="input-group-prepend">
            <span class="input-group-text" style="width: 9rem;" id="inputGroup-sizing-default">온/오프라인</span>
          </div>
          <b-form-select v-model="form.contact" :options="contacts" required></b-form-select>
        </b-col>
      </b-row>
      <br>
      <!-- 기간 -->
      <b-row>
        <b-col class="input-group input-group-lg">
          <div class="input-group-prepend">
            <span class="input-group-text" style="width: 9rem;" id="inputGroup-sizing-default">기간</span>
          </div>
          <b-form-datepicker v-model="form.startDate"></b-form-datepicker>
          <b-form-datepicker v-model="form.endDate"></b-form-datepicker>
        </b-col>
      </b-row>
      <br>
      <!-- 일정 -->
      <b-row>
        <b-col class="input-group input-group-lg">
          <div class="input-group-prepend">
            <span class="input-group-text" style="width: 9rem;" id="inputGroup-sizing-default">일정</span>
          </div>
          <b-form-select v-model="form.schedule" :options="schedules" required></b-form-select>
          <!-- <b-form-radio-group class="mx-2" v-model="form.schedule" :options="schedules"></b-form-radio-group> -->
          <b-form-spinbutton
            v-model="form.count"
            :options="counts"
            min="1"
            max="100"
            required
          ></b-form-spinbutton>
        </b-col>
      </b-row>
      <br>
      <!-- 요일 -->
      <b-row>
        <b-col class="input-group input-group-lg">
          <div class="input-group-prepend">
            <span class="input-group-text" style="width: 9rem;" id="inputGroup-sizing-default">요일</span>
          </div>
          <b-form-radio-group v-model="form.day" :options="days"></b-form-radio-group>
        </b-col>
      </b-row>
      <br>
      <!-- 소개글 -->
      <b-row>
        <b-col class="input-group input-group-lg">
          <div class="input-group-prepend">
            <span class="input-group-text" style="width: 9rem;" id="inputGroup-sizing-default">소개글</span>
          </div>
          <b-form-textarea v-model="form.content" rows="3" no-resize></b-form-textarea>
        </b-col>
      </b-row>
      <br>


    <b-button type="submit" v-b-modal.modal-prevent-closing block size="lg" variant="info">수정하기</b-button>
    </b-form>
  </b-container>
</template>

<script>
// import VueSlider from "vue-slider-component";
// import "vue-slider-component/theme/default.css";
import { mapActions, mapState } from "vuex";
import Axios from "axios";
import router from "@/router";

const API_URL = process.env.VUE_APP_LOCAL_URL;

export default {
  data() {
    return {
      form: {
        content: null,
        email: this.email,
        endDate: null,
        gu: null,
        maxMembers: null,
        si: null,
        startDate: null,
        studyId: this.$route.params.id,
        title: null,
        numMembers: null,

        field: null,
        contact: null,
        schedule: null,
        count: null,
        day: null,
        time: null,
        period: null,
        value: 0,
      },
        fields: [
          "공기업",
          "사기업",
        ],
        siAreas: [{ text: "시를 선택해주세요", value: null }, "서울"],
        guAreas: [
          { text: "구를 선택해주세요", value: null },
          "강남구",
          "강동구",
          "강북구",
          "강서구",
          "관악구",
          "광진구",
          "구로구",
          "금천구",
          "노원구",
          "도봉구",
          "동대문구",
          "동작구",
          "마포구",
          "서대문구",
          "서초구",
          "성동구",
          "성북구",
          "송파구",
          "양천구",
          "영등포구",
          "용산구",
          "은평구",
          "중구",
          "중랑구",
        ],
        contacts: [
          { text: "오프라인", value: "오프라인" },
          { text: "온라인", value: "온라인" },
        ],
        schedules: [
          { text: "매월", value: "매월" },
          { text: "매주", value: "매주" },
          { text: "추후협의", value: "추후협의" },
        ],
        days: [
          { text: "평일", value: "평일" },
          { text: "주말", value: "주말" },
          { text: "혼합", value: "혼합" },
          { text: "추후협희", value: "추후협희" },
        ],
        times: [
          { text: "오전", value: "오전" },
          { text: "오후", value: "오후" },
          { text: "저녁", value: "저녁" },
          { text: "추후협의", value: "추후협의" },
        ],
        counts: [{ text: "횟수", value: null }],
    };
  },
  computed: {
    ...mapState({
      email: (state) => state.moduleName.email,
    }),
  },
  created () {
    Axios.get(`${API_URL}study/${this.form.studyId}`, {
      headers: {
        'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
        'user-email': sessionStorage.getItem('user-email')
      }
    })
    .then(res => {
      if (this.email != res.data.mgrEmail) {
        alert('팀 매니저가 아닙니다')
        this.$router.push({ name: "Home" })
      }
      this.form = res.data
      this.form.studyId = this.$route.params.id
    })
    .catch(err => {
      console.log(err)
    })
  },
  methods: {
    ...mapActions(["createTeam"]),
    studyUpdate() {
      const params = {
        content: this.form.content,
        email: this.email,
        endDate: this.form.endDate,
        gu: this.form.gu,
        maxMembers: this.form.maxMembers,
        si: this.form.si,
        startDate: this.form.startDate,
        studyId: this.form.studyId,
        title: this.form.title,
      };

      const JsonParams = JSON.stringify(params);
      Axios({
        method: "PUT",
        url: `${API_URL}study`,
        params: params,
        data: JsonParams,
        headers: { 
          "Content-Type": "application/json; charset=utf-8",
          'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
          'user-email': sessionStorage.getItem('user-email') 
        },
      })
      .then(() => {
        alert("스터디 수정 성공")
        router.push({ name: 'StudyDetail', params: {id:this.form.studyId}})
      })
      .catch((err) => {
        console.log(err.response.data);
      });
    },
  },
};
</script>

<style></style>
