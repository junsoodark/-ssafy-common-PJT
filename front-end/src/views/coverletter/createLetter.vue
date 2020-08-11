<template>
  <div class="my-3">
    <h1>자소서 작성</h1>
    <b-container>
      <hr>
      <b-row>
        <b-form-select v-model="category" :options="options" class="col-4 my-1"></b-form-select>
        <b-form-input v-model="title" placeholder="글 제목을 입력해주세요" class="col-8 my-1"></b-form-input>
        <b-form-input v-model="company" placeholder="회사를 입력해주세요" class="col-4"></b-form-input>
        <b-form-input v-model="job" placeholder="직무를 입력해주세요" class="col-8"></b-form-input>
      </b-row>
      <b-button class="my-2" @click="addQuestion">자소서 항목 추가</b-button>
      <div v-for="item in items" :key="item" class="my-3">
        <b-button v-b-toggle="'my-'+item.num">
            <span class="when-open">{{item.num+1}}번 질문 닫기</span><span class="when-closed">{{item.num+1}}번 질문 열기</span>
        </b-button>
        <b-collapse :id="'my-'+item.num" class="my-1">
          <div class="my-3">
            <b-form-input v-model="item.title" placeholder="질문을 입력해주세요"></b-form-input>
            <b-form-textarea
              v-model="item.answer"
              placeholder="Tall textarea"
              rows="8"
            ></b-form-textarea>
          </div>
        </b-collapse>
      </div>
      <br>
      <b-button>제출하기</b-button>
    </b-container>
  </div>
</template>

<script>
export default {
  data () {
    return {
      items: [],
      question:0,
      title: null,
      company: null,
      job: null,
      category: '자소서 컨펌',
      options: [
        { value: null, text: '항목을 선택해주세요' },
        { value: '자소서 컨펌', text: '자소서 컨펌'},
        { value: '자소서 면접', text: '자소서 면접'}
      ]
    }
  },
  methods: {
    addQuestion () {
      var cover = new Object
      const numCover = this.question
      cover = {num: numCover, title: null, answer: null}
      this.question += 1
      this.items.push(cover)
      console.log(this.items)
    }
  }
}
</script>

<style>
.collapsed > .when-open,
.not-collapsed > .when-closed {
  display: none;
}
</style>