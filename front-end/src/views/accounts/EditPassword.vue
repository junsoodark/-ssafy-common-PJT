<script src="https://kit.fontawesome.com/958a5175c9.js" crossorigin="anonymous"></script>
<template>
  <b-container>
    <h1>회원정보 수정 페이지</h1>
    <div style="max-width:800px;" class="mx-auto middle"></div>
    <!-- 현재 비밀번호 -->
    <b-form class="row">
      <b-input-group class="mb-4;">
        <label class="col-2" style="text-align:right" for="password">현재 비밀번호</label>
        <b-input-group-prepend variant is-text>
          <b-icon icon="lock"></b-icon>
        </b-input-group-prepend>
        <b-form-input
          v-model="nowPassword"
          type="password"
          class="form-control text-center"
          aria-label="Sizing example input"
          aria-describedby="inputGroup-sizing-default"
        ></b-form-input>
        <b-button @click="verifyPassword">확인</b-button>
      </b-input-group>
    </b-form>
    <!-- 새 비밀번호 -->
    <b-form @submit.stop.prevent="editPassword">
      <b-row>
        <b-col class="input-group input-group-lg">
          <div class="input-group-prepend">
            <span
              class="input-group-text"
              style="width: 10rem;"
              id="inputGroup-sizing-default"
            >새 비밀번호</span>
          </div>
          <input
            v-model="newPassword"
            type="password"
            class="form-control text-center"
            aria-label="Sizing example input"
            aria-describedby="inputGroup-sizing-default"
          />
        </b-col>
      </b-row>
      <br />
      <!-- 새 비밀번호 확인 -->
      <b-row>
        <b-col class="input-group input-group-lg">
          <div class="input-group-prepend">
            <span
              class="input-group-text"
              style="width: 10rem;"
              id="inputGroup-sizing-default"
            >비밀번호 확인</span>
          </div>
          <input
            v-model="newPasswordConfirm"
            type="password"
            class="form-control text-center"
            aria-label="Sizing example input"
            aria-describedby="inputGroup-sizing-default"
          />
        </b-col>
      </b-row>
      <br />
      <b-button type="submit" block size="lg" variant="info">변경하기</b-button>
    </b-form>
  </b-container>
</template>

<script>
import { mapState } from "vuex";
import Axios from "axios";
import router from "@/router";
import firebase from "firebase";
const API_URL = process.env.VUE_APP_LOCAL_URL;

export default {
  data() {
    return {
      nowPasswordVerifyState: false,
      nowPassword: "",
      newPassword: "",
      newPasswordConfirm: "",
      sex: "",
      name: "",
      age: null,
    };
  },
  computed: {
    ...mapState({
      email: (state) => state.moduleName.email,
    }),
  },
  methods: {
    verifyPassword() {
      const params = {
        email: this.email,
        password: this.nowPassword,
      };
      var JsonForm = JSON.stringify(params);

      Axios({
        method: "POST",
        url: `${API_URL}login`,
        params: params,
        data: JsonForm,
        headers: {
          "Content-Type": "application/json; charset=utf-8",
        },
      })
        .then(() => {
          alert("비밀번호 인증되었습니다.");
          this.nowPasswordVerifyState = true;

          //get 요쳥
          Axios.get(`${API_URL}user/${this.email}`, {
            headers: {
              "jwt-auth-token": sessionStorage.getItem("jwt-auth-token"),
              "user-email": sessionStorage.getItem("user-email"),
            },
          })
            .then((res) => {
              this.name = res.data.name;
              this.age = res.data.age;
              this.sex = res.data.sex;
            })
            .catch((err) => {
              console.log(err);
            });
        })
        .catch((err) => {
          alert("비밀번호를 확인해주세요");
          console.log(err.response.data);
        });
    },
    editPassword() {
      if (this.newPassword !== this.newPasswordConfirm) {
        alert("비밀번호 확인과 비밀번호가 다릅니다!");
        return false;
      }

      if (!this.nowPasswordVerifyState) {
        alert("현재 비밀번호 확인을 해주세요!");
        return false;
      }

      if (this.newPassword === this.nowPassword) {
        alert("현재 비밀번호와 새 비밀번호가 같습니다.");
        return false;
      }

      const params = {
        name: this.name,
        sex: this.sex,
        age: this.age,
        email: this.email,
        password: this.newPassword,
      };

      Axios.put(`${API_URL}user`, params, {
        headers: {
          "jwt-auth-token": sessionStorage.getItem("jwt-auth-token"),
          "user-email": sessionStorage.getItem("user-email"),
        },
      })
        .then(() => {
          var user = firebase.auth().currentUser;
          var newPassword = this.newPassword;

          user
            .updatePassword(newPassword)
            .then(function () {
              console.log("firebase password success");
            })
            .catch(function (error) {
              console.log("firebase new password error", error);
            });

          alert("비밀번호 수정을 완료하였습니다.");

          router.push({ name: "Mypage" });
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>
  
<style>
</style>