<template>
  <div>
    <div class="wrapC table" id="login-window">
        <form @submit.prevent="check" class="middle">
            <h1>SS_log</h1>
            <div class="input-wrap">
                <input v-model="email"
                    id="email" 
                    placeholder="이메일을 입력해주세요"
                    type="text"/>
            </div>
            <div class="input-wrap">
                <input v-model="password" type="password"
                    id="password"
                    placeholder="비밀 번호를 입력해주세요"/>
            </div>
            <button type="submit" class="btn btn--back btn--login">
                정보 조회 하기
            </button>
        </form>
    </div>
    <div id="info-window" class="d-none">
        <div class="" id="user-info">
        <h1> 닉네임: {{ nickname }}</h1>
        <h1> 이메일: {{ email }}</h1>
        <b-button @click="readyModify">회원정보 수정 </b-button>
        </div>
        <div class="wrapC table d-none" id="modify-window">
            <form @submit.prevent="modify" class="middle">
                <h1>회원 정보 수정</h1>
                <div class="form-wrap">
                    <div class="input-wrap">
                        <input v-model="nickname"
                            id="nickname"
                            placeholder="닉네임을 입력해주세요" type="text"/>
                    </div>

                    <div class="input-wrap">
                        <input v-model="email" 
                            id="email"
                            placeholder="이메일을 입력해주세요"
                            type="text" disabled/>
                    </div>

                    <div class="input-wrap password-wrap">
                        <input v-model="password"
                            id="password" 
                            :type="passwordType"
                            placeholder="비밀번호를 입력해주세요"/>
                        <span :class="{active : passwordType==='text'}">
                                <i class="fas fa-eye"></i>
                            </span>
                    </div>

                    <div class="input-wrap password-wrap">
                        <input v-model="passwordConfirm" 
                            id="password-confirm"
                            :type="passwordConfirmType"
                            placeholder="비밀번호를 한번 더 입력해주세요"/>
                        <span :class="{active : passwordConfirmType==='text'}">
                                <i class="fas fa-eye"></i> 
                            </span>
                    </div>
                </div>
                <button class="btn">
                    <span>
                        작성완료
                    </span>
                </button>
            </form>
        </div> 
    </div>
  </div>
</template>

<script>
import '../../assets/css/user.scss'
import constants from '../../lib/constants'
import axios from 'axios'

export default {
    data: function(){
            return {
                constants,
                email: '',
                password: '',
                nickname: '',
                passwordConfirm: '',
                passwordType:"password",
                passwordConfirmType:"password",
            }
        },
    methods: {
        check() {
            const email = this.email
            const password = this.password
            if (!email || !password) {
                    alert('빈칸을 채워주세요!')
                    return false
                }
            
            axios.get('http://localhost:3000/account/userinfo',{
                    params: {
                        email: email,
                        password: password
                    }
                })
                .then(res => {
                    this.nickname = res.data.object.nickname
                    const InfoWindow = document.querySelector('#info-window')
                    InfoWindow.className = ''
                    const LoginWindow = document.querySelector('#login-window')
                    LoginWindow.className = 'd-none'
                })
                .catch(err => {console.log(err)})
        },
        readyModify() {
            const modifyWindow = document.querySelector('#modify-window')
            modifyWindow.className = 'wrapC table'
            const InfoWindow = document.querySelector('#user-info')
            InfoWindow.className = 'd-none'
        },
        modify () {
                const email = this.email
                const nickname = this.nickname
                const password = this.password
                const passwordConfirm = this.passwordConfirm
                if (password!=passwordConfirm) {
                    alert('비밀번호 확인과 비밀번호가 다릅니다!')
                    return false
                }

                axios.post('http://localhost:3000/account/modify',{ email, nickname, password })
                .then(res => {
                    if (res.status == 200) {
                        alert('수정성공')
                        console.log(res)
                        this.$router.push({name:'login'})
                    }
                })
                .catch(err => {
                    alert('수정실패')
                    console.log(err)
                })
            }
    }
}
</script>

<style>

</style>