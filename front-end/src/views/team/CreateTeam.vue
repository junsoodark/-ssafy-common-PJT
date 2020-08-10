<template>
  <b-container>
    <b-form @submit.prevent="studyCreate" @reset="onReset" v-if="show">
      <b-form-group id="input-group-1" label="스터디 이름:" label-for="input-1">
        <b-form-input
          id="input-1"
          v-model="form.studyname"
          type="text"
          required
          placeholder="스터디 이름을 적어 주세요."
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-2" label="분야:" label-for="input-2">
        <b-form-select id="input-2" v-model="form.field" :options="fields" required></b-form-select>
      </b-form-group>

      <b-form-group id="input-group-3" label="방식:" label-for="input-5">
        <b-form-radio-group id="input-3" v-model="form.contact" :options="contacts"></b-form-radio-group>
      </b-form-group>

      <b-form-group id="input-group-4" label="지역:" label-for="input-4">
        <b-form-select id="input-4" v-model="form.city" :options="cities" @change="getGu" required></b-form-select>
        <b-form-select id="input-4" v-model="form.guArea" :options="guAreas" required></b-form-select>
      </b-form-group>

      <b-form-group id="input-group-5" label="스터디 일정:" label-for="input-5">
        <b-form-radio-group id="input-5" v-model="form.schedule" :options="schedules"></b-form-radio-group>
        <b-form-spinbutton
          id="input-5"
          v-model="form.count"
          :options="counts"
          min="1"
          max="7"
          required
        ></b-form-spinbutton>
      </b-form-group>-->
      <b-form-group id="input-group-6" label="요일:" label-for="input-6">
        <b-form-radio-group id="input-6" v-model="form.day" :options="days"></b-form-radio-group>
      </b-form-group>

      <b-form-group id="input-group-7" label="시간대:" label-for="input-7">
        <b-form-radio-group id="input-7" v-model="form.time" :options="times"></b-form-radio-group>
        <vue-slider
          v-model="form.time"
          :min-range="0"
          :max-range="24"
          :min="0"
          :max="24"
          :interval="1"
        ></vue-slider>
      </b-form-group>

      <b-form-group id="input-group-8" label="스터디 기간:" laebl-for="input-8">
        <label for="start-datepicker">시작날짜</label>
        <b-form-datepicker id="start-datepicker" v-model="form.startdate" class="mb-2"></b-form-datepicker>
        <label for="end-datepicker">종료날짜</label>
        <b-form-datepicker id="end-datepicker" v-model="form.enddate" class="mb-2"></b-form-datepicker>
      </b-form-group>

      <b-form-group id="input-group-9" label="인원:" laebl-for="input-9">
        <vue-slider v-model="form.maxMembers" :min="1" :max="30" :interval="1"></vue-slider>
      </b-form-group>

      <b-form-group id="input-group-10" label="스터디 소개:" laebl-for="input-10">
        <b-form-textarea
          v-model="form.content"
          id="input-10"
          placeholder="스터디 소개 글을 입력해주세요."
          rows="3"
          no-resize
        ></b-form-textarea>
      </b-form-group>

      <b-button type="submit" variant="primary">스터디 생성</b-button>
      <b-button type="reset" variant="danger">초기화</b-button>
    </b-form>
  </b-container>
</template>

<script>
// import VueSlider from "vue-slider-component";
import "vue-slider-component/theme/default.css";
import { mapActions, mapState } from "vuex";
import Axios from "axios";
import router from "@/router";

const API_URL = process.env.VUE_APP_LOCAL_URL;

export default {
  components: {
    // VueSlider,
  },
  data() {
    return {
      form: {
        studyname: null,
        field: null,
        contact: null,
        city: null,
        guArea: null,
        schedule: null,
        count: null,
        day: null,
        period: null,
        maxMembers: 1,
        startdate: null,
        enddate: null,
        content: null,
        time: [0, 2],
      },
      fields: [
        { text: "원하는 분야를 선택해주세요", value: null },
        "공기업",
        "사기업",
      ],
      cities: [],
      guAreas: [],
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
      show: true,
    };
  },
  computed: {
    ...mapState({
      email: (state) => state.moduleName.email,
    }),
  },
  methods: {
    ...mapActions(["createTeam"]),
    studyCreate() {
      const params = {
        content: this.form.content,
        email: this.email,
        endDate: this.form.enddate,
        gu: this.form.guArea,
        si: this.form.city,
        startDate: this.form.startdate,
        title: this.form.studyname,
        maxMembers: this.form.maxMembers
      };
      console.log("params", params);

      const JsonParams = JSON.stringify(params);
      Axios({
        method: "POST",
        url: `${API_URL}study`,
        params: params,
        data: JsonParams,
        headers: { "Content-Type": "application/json; charset=utf-8",
                  'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
                  'user-email': sessionStorage.getItem('user-email')},
      })
        .then((res) => {
          alert("스터디 생성 성공");

          router.push({ name: "StudyList" });
          console.log(res);
        })
        .catch((err) => {
          alert(err.response.data);
          console.log(err);
        });
    },
    onReset(evt) {
      evt.preventDefault();

      // Reset our form valuess
      this.form.email = "";
      // this.form.name = "";
      // this.form.food = null;
      // Trick to reset/clear native browser form validation state
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    },
    getGu() {
      Axios.get(`${API_URL}address/${this.form.city}`)
        .then((res) => {
          this.guAreas = res.data;
          // console.log(this.guAreas);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  created() {
    const self = this;
    Axios.get(`${API_URL}address/`)
      .then((res) => {
        self.cities = res.data;
        // console.log(this.cities);
      })
      .catch((err) => {
        console.log(err);
      });
  },
}
</script>

<style></style>
