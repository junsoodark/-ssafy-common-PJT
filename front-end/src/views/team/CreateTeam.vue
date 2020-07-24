<template>
  <div>
    <b-form @submit="createTeam" @reset="onReset" v-if="show">
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
        <b-button id="input-3">오프라인</b-button>
        <b-button id="input-3">온라인</b-button>
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
        <b-button id="input-5">매월</b-button>
        <b-button id="input-5">매주</b-button>
        <b-button id="input-5">추후협의</b-button>
        <b-form-spinbutton
          id="input-5"
          v-model="count"
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

      <b-form-froup id="input-group-8" label="스터디 기간:" laebl-for="input-8">
        <v-md-date-range-picker></v-md-date-range-picker>
      </b-form-froup>

      <b-button type="submit" variant="primary">Submit</b-button>
      <b-button type="reset" variant="danger">Reset</b-button>
    </b-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {
        studyname: "",
        field: null,
        area: null,
        count: null,
        checked: [],
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
      counts: [{ text: "횟수", value: null }],
      show: true,
    };
  },
  methods: {
    createTeam(evt) {
      evt.preventDefault();
      alert(JSON.stringify(this.form));
    },
    onReset(evt) {
      evt.preventDefault();
      // Reset our form values
      this.form.email = "";
      this.form.name = "";
      this.form.food = null;
      this.form.checked = [];
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
