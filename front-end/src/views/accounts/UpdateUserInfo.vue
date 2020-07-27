<template>
  <b-container class="bv-example-row my-5">
    <div>
      <h1>회원정보 수정 페이지</h1>
      
      <b-list-group>
        <b-list-group-item>{{ email }}</b-list-group-item>
      </b-list-group>
      <b-form @submit.prevent="signup">
        <b-form-group id="input-group-6" label="닉네임:" label-for="input-6">
          <b-form-input
            id="input-6"
            v-model="nickname"
            type="text"
            required
            placeholder="닉네임을 입력해주세요"
          ></b-form-input>
        </b-form-group>

        <b-form-group id="input-group-7" label="나이:" label-for="input-7">
          <b-form-input
            id="input-7"
            v-model="age"
            type="number"
            required
            placeholder="나이를 입력해주세요"
          ></b-form-input>
        </b-form-group>

        <b-form-group id="input-group-8" label="성별:">
          <b-form-radio v-model="sex" type="number" name="some-radios" value="1">남자</b-form-radio>
          <b-form-radio v-model="sex" type="number" name="some-radios" value="2">여자</b-form-radio>
        </b-form-group>


        <b-button type="submit" variant="primary">수정하기</b-button>
      </b-form>
    </div>
  </b-container>
</template>

<script>
import { mapState } from 'vuex';
export default {
  data() {
    return {
      name: "",
      age: 0,
      sex: 1,
    };
  },
  computed: {
    ...mapState({
      email: state => state.moduleName.email,
      name: state => state.moduleName.name,
      sex: state => state.moduleName.sex,
      age: state => state.moduleName.age
    })
  }

}
</script>

<style>

</style>