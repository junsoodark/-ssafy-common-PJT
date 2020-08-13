<template>
  <b-container><br>
    <h1>스터디 생성 페이지</h1>
    <br>
    <b-form @submit.prevent="studyCreate">
      <!-- 스터디 이름 -->
      <b-row>
        <b-col class="input-group input-group-lg">
          <div class="input-group-prepend">
            <span class="input-group-text" style="width: 9rem;">스터디 이름</span>
          </div>
          <input v-model="form.title" type="text" class="form-control text-center" placeholder="스터디 이름을 입력해주세요">
        </b-col>
      </b-row>
      <br>
      <!-- 지역 -->
      <b-row>
        <b-col class="input-group input-group-lg">
          <div class="input-group-prepend">
            <span class="input-group-text" style="width: 9rem;">지역</span>
          </div>
          <b-form-select v-model="form.si" :options="siAreas" @change="changeGu" required></b-form-select>
          <b-form-select v-model="form.gu" :options="guAreas" required></b-form-select>
        </b-col>
      </b-row>
      <br>
      <!-- 인원수 -->
      <b-row>
        <b-col class="input-group input-group-lg">
          <div class="input-group-prepend">
            <span class="input-group-text" style="width: 9rem;">인원수</span>
          </div>
          <b-form-spinbutton
            v-model="form.maxMembers"
            min="1"
            max="100"
            required
            placeholder="최대 인원수를 정하세요" 
          ></b-form-spinbutton>
        </b-col>
      </b-row>
      <br>
      <!-- 분야 -->
      <b-row>
        <b-col class="input-group input-group-lg">
          <div class="input-group-prepend">
            <span class="input-group-text" style="width: 9rem;">분야</span>
          </div>
          <b-form-select v-model="form.categoryId" :options="category" required></b-form-select>
        </b-col>
      </b-row>
      <br>
      <!-- 온/오프 -->
      <b-row>
        <b-col class="input-group input-group-lg">
          <div class="input-group-prepend">
            <span class="input-group-text" style="width: 9rem;">온/오프라인</span>
          </div>
          <b-form-select v-model="form.placeId" :options="place" required></b-form-select>
        </b-col>
      </b-row>
      <br>
      <!-- 기간 -->
      <b-row>
        <b-col class="input-group input-group-lg">
          <div class="input-group-prepend">
            <span class="input-group-text" style="width: 9rem;">기간</span>
          </div>
          <b-form-datepicker placeholder="시작 기간" v-model="form.startDate"></b-form-datepicker>
          <b-form-datepicker placeholder="마침 기간" v-model="form.endDate"></b-form-datepicker>
        </b-col>
      </b-row>
      <br>
      <!-- 일정 -->
      <b-row>
        <b-col class="input-group input-group-lg">
          <div class="input-group-prepend">
            <span class="input-group-text" style="width: 9rem;">일정</span>
          </div>
          <!-- <b-form-select v-model="form.periodId" :options="period" required></b-form-select> -->
          <b-form-radio-group v-model="form.periodId" :options="period" required></b-form-radio-group>
          <b-form-spinbutton
            v-model="form.numMeetings"
            min="1"
            max="31"
            required
            placeholder="횟수를 입력해주세요"
          ></b-form-spinbutton>
        </b-col>
      </b-row>
      <br>
      <!-- 주중/시간 -->
      <b-row>
        <b-col class="input-group input-group-lg">
          <div class="input-group-prepend m-0 p-0">
            <span class="input-group-text" style="width: 9rem;">주중/시간</span>
          </div>
          <b-col cols="5">
            <b-form-radio-group v-model="form.weekId" :options="week"></b-form-radio-group>
          </b-col>
          <b-col style="font-size: 30px;">|</b-col>
          <b-col cols="5">
            <b-form-radio-group v-model="form.shiftId" :options="shift"></b-form-radio-group>
          </b-col>
        </b-col>
      </b-row>
      <br>
      <!-- 소개글 -->
      <b-row>
        <b-col class="input-group input-group-lg">
          <div class="input-group-prepend">
            <span class="input-group-text" style="width: 9rem;">소개글</span>
          </div>
          <b-form-textarea v-model="form.content" placeholder="스터디에 관련된 소개글을 입력해주세요" rows="3" no-resize></b-form-textarea>
        </b-col>
      </b-row>
      <br>


    <b-button type="submit" v-b-modal.modal-prevent-closing block size="lg" variant="info">생성하기</b-button>
    <br>
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
        email: this.email,
        categoryId: null,
        content: null,
        endDate: null,
        gu: null,
        maxMembers: null,
        periodId: null,
        placeId: null,
        shiftId: null,
        si: null,
        startDate: null,
        title: null,
        weekId: null,
        numMeetings: null,
      },
      category: [
        { text: "원하는 분야를 선택해주세요", value: null },
        { text: "면접", value: 1 },
        { text: "인적성/NCS", value: 2},
        { text: "코딩 테스트", value: 4},
        { text: "기타", value: 4},
      ],
      siAreas: [
        { text: "시를 선택해주세요", value: null}
      ],
      guAreas: [
        { text: "구를 선택해주세요", }
      ],
      place: [
        { text: "온/오프라인을 선택해주세요", value: null},
        { text: "오프라인", value: 1 },
        { text: "온라인", value: 2 },
        { text: "추후협의", value: 3 },
      ],
      period: [
        { text: "매일", value: 1 },
        { text: "매주", value: 2 },
        { text: "매월", value: 3 },
        { text: "추후협의", value: 4 },
      ],
      week: [
        { text: "평일", value: 1 },
        { text: "주말", value: 2 },
        { text: "혼합", value: 3 },
        { text: "추후협희", value: 4 },
      ],
      shift: [
        { text: "오전", value: 1 },
        { text: "오후", value: 2 },
        { text: "야간", value: 3 },
        { text: "추후협의", value: 4 },
      ],
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
        categoryId: this.form.categoryId,
        content: this.form.content,
        endDate: this.form.endDate,
        gu: this.form.gu,
        maxMembers: this.form.maxMembers,
        periodId: this.form.periodId,
        placeId: this.form.placeId,
        shiftId: this.form.shiftId,
        si: this.form.si[0],
        startDate: this.form.startDate,
        title: this.form.title,
        numMeetings: this.form.numMeetings,
        weekId: this.form.weekId,
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
        // alert(err.response.data);
        console.log(err);
      })
    },
    changeGu() {
      Axios.get(`${API_URL}address/${this.form.si}`)
      .then((res) => {
        this.guAreas = res.data
      })
      .catch((err) => {
        this.guAreas = [
          { text: "구를 선택해주세요", }
        ],
        console.log(err);
      });
    },
  },
  created() {
    Axios.get(`${API_URL}address/`)
      .then((res) => {
        this.siAreas.push(res.data)
      })
      .catch((err) => {
        console.log(err)
      });
  },
}
</script>

<style></style>
