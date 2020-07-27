<template>
  <b-container>
    <b-form @submit.prevent="createTeam" @reset="onReset" v-if="show">
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
        <b-form-select
          id="input-2"
          v-model="form.field"
          :options="fields"
          required
        ></b-form-select>
      </b-form-group>

      <b-form-group id="input-group-3" label="방식:" label-for="input-3">
        <b-form-radio v-model="form.contact" name="some-radios" value="오프라인"
          >오프라인</b-form-radio
        >
        <b-form-radio v-model="form.contact" name="some-radios" value="온라인"
          >온라인</b-form-radio
        >
      </b-form-group>

      <b-form-group id="input-group-4" label="지역:" label-for="input-4">
        <b-form-select
          id="input-4"
          v-model="form.area"
          :options="areas"
          required
        ></b-form-select>
      </b-form-group>

      <b-form-group id="input-group-5" label="스터디 일정:" label-for="input-5">
        <b-form-radio-group
          id="input-5"
          v-model="form.schedule"
          :options="schedules"
        >
        </b-form-radio-group>
        <b-form-spinbutton
          id="input-5"
          v-model="form.count"
          :option="counts"
          min="1"
          max="7"
          required
        ></b-form-spinbutton>
      </b-form-group>

      <b-form-group id="input-group-6" label="요일:" label-for="input-6">
        <b-button id="input-6">평일</b-button>
        <b-button id="input-6">주말</b-button>
        <b-button id="input-6">혼합</b-button>
        <b-button id="input-6">추후협의</b-button>
      </b-form-group>

      <b-form-group id="input-group-7" label="시간대:" label-for="input-7">
        <b-button id="input-7">오전</b-button>
        <b-button id="input-7">오후</b-button>
        <b-button id="input-7">저녁</b-button>
        <b-button id="input-7">추후협의</b-button>
      </b-form-group>

      <b-form-group id="input-group-8" label="스터디 기간:" laebl-for="input-8">
        <v-md-date-range-picker></v-md-date-range-picker>
      </b-form-group>

      <b-form-group id="input-group-9" label="인원:" laebl-for="input-9">
        <vue-slider
          v-model="form.value"
          :min="1"
          :max="30"
          :interval="1"
        ></vue-slider>
      </b-form-group>

      <b-button type="submit" variant="primary">Submit</b-button>
      <b-button type="reset" variant="danger">Reset</b-button>
    </b-form>
  </b-container>
</template>

<script>
import VueSlider from "vue-slider-component";
import "vue-slider-component/theme/default.css";
import { mapActions } from "vuex";

export default {
  components: {
    VueSlider,
  },
  data() {
    return {
      form: {
        studyname: "",
        field: null,
        contact: null,
        area: null,
        schedule: "",
        count: null,
        day: null,
        time: null,
        period: null,
        value: 0,
      },
      fields: [
        { text: "원하는 분야를 선택해주세요", value: null },
        "공기업",
        "사기업",
      ],
      areas: [
        { text: "원하는 지역을 선택해주세요", value: null },
        "강남구",
        "강북구",
        "종로구",
      ],
      schedules: [
        { text: "매월", value: "first" },
        { text: "매주", value: "second" },
        { text: "추후협의", value: "third" },
      ],
      counts: [{ text: "횟수", value: null }],
      show: true,
    };
  },
  methods: {
    ...mapActions(["createTeam"]),
    onReset(evt) {
      evt.preventDefault();
      // Reset our form values
      this.form.email = "";
      this.form.name = "";
      this.form.food = null;
      // Trick to reset/clear native browser form validation state
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    },
  },
};
</script>

<style></style>
