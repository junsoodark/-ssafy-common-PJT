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

      <!-- <b-form-group id="input-group-2" label="분야:" label-for="input-2">
        <b-form-select id="input-2" v-model="form.field" :options="fields" required></b-form-select>
      </b-form-group>-->

      <!-- <b-form-group id="input-group-3" label="방식:" label-for="input-5">
        <b-form-radio-group id="input-3" v-model="form.contact" :options="contacts"></b-form-radio-group>
      </b-form-group>-->

      <b-form-group id="input-group-4" label="지역:" label-for="input-4">
        <b-button @click="getSi"></b-button>
        <b-form-select id="input-4" v-model="form.si" :options="siAreas" required></b-form-select>
        <b-form-select id="input-4" v-model="form.guArea" :options="guAreas" required></b-form-select>
      </b-form-group>

      <!-- <b-form-group id="input-group-5" label="스터디 일정:" label-for="input-5">
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

      <!-- <b-form-group id="input-group-6" label="요일:" label-for="input-6">
        <b-form-radio-group id="input-6" v-model="form.day" :options="days"></b-form-radio-group>
      </b-form-group>-->

      <!-- <b-form-group id="input-group-7" label="시간대:" label-for="input-7">
        <b-form-radio-group id="input-7" v-model="form.time" :options="times"></b-form-radio-group>
      </b-form-group>-->

      <b-form-group id="input-group-8" label="스터디 기간:" laebl-for="input-8">
        <label for="start-datepicker">시작날짜</label>
        <b-form-datepicker id="start-datepicker" v-model="form.startdate" class="mb-2"></b-form-datepicker>
        <label for="end-datepicker">종료날짜</label>
        <b-form-datepicker id="end-datepicker" v-model="form.enddate" class="mb-2"></b-form-datepicker>
      </b-form-group>

      <!-- <b-form-group id="input-group-9" label="인원:" laebl-for="input-9">
        <vue-slider v-model="form.value" :min="1" :max="30" :interval="1"></vue-slider>
      </b-form-group>-->

      <b-form-group id="input-group-10" label="스터디 소개:" laebl-for="input-10">
        <b-form-textarea
          v-model="form.content"
          id="input-10"
          placeholder="스터디 소개 글을 입력해주세요."
          rows="3"
          no-resize
        ></b-form-textarea>
      </b-form-group>

      <b-button type="submit" variant="primary">Submit</b-button>
      <b-button type="reset" variant="danger">Reset</b-button>
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
  components: {
    // VueSlider,
  },
  data() {
    return {
      form: {
        studyname: "",
        field: null,
        contact: null,
        si: null,
        guArea: null,
        schedule: null,
        count: null,
        day: null,
        time: null,
        period: null,
        value: 0,
        startdate: null,
        enddate: null,
        content: null,
      },
      fields: [
        { text: "원하는 분야를 선택해주세요", value: null },
        "공기업",
        "사기업",
      ],
      siAreas: [{ text: "원하는 지역을 선택해주세요", value: null }],
      guAreas: [
        { text: "원하는 지역을 선택해주세요", value: null },
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
        si: "서울",
        startDate: this.form.startdate,
        title: this.form.studyname,
      };

      console.log("params", params);

      const JsonParams = JSON.stringify(params);
      Axios({
        method: "POST",
        url: `${API_URL}study`,
        params: params,
        data: JsonParams,
        headers: { "Content-Type": "application/json; charset=utf-8" },
      })
        .then((res) => {
          alert("스터디 생성 성공");

          router.push({ name: "StudyList" });
          console.log(res);
        })
        .catch((err) => {
          alert("스터디 생성 실패");
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
    getSi() {
      Axios.get(`${API_URL}address/`)
        .then((res) => {
          this.form.siAreas = res.data;
          console.log(res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style></style>
