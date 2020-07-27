<template>
  <b-container>
    <h1>비밀번호 확인 페이지</h1>
    <br>
    <b-row class="my-1">
      <b-col sm="3">
        <label for="input-none">현재 이메일</label>
      </b-col>
      <b-col sm="9">
        <b-list-group>
          <b-list-group-item href="#">aa@aa.com</b-list-group-item>
        </b-list-group>
      </b-col>
    </b-row>
    <b-row class="my-1">
      <b-col sm="3">
        <label for="input-none">No State:</label>
      </b-col>
      <b-col sm="9">
        <b-form-input id="input-none" :state="null" placeholder="No validation"></b-form-input>
      </b-col>
    </b-row>

  </b-container>
  
</template>

<script>
export default {
}
</script>
  
<style>

</style>