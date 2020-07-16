<template>
    <div class="user" id="join"> 
        <div class="wrapC table">
            <form @submit.prevent="modify" class="middle">
                <h1>회원 정보 수정</h1>
                <div class="form-wrap">
                    <div class="input-wrap">
                        <input v-model="nickName"
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
</template>

<script>
import axios from 'axios'
export default {
    props: ['email'],
    data: () => {
        return {
            nickName: '',
            password: '',
            passwordConfirm: '',
            passwordType:"password",
            passwordConfirmType:"password",
        }
    },
    methods: {
        signUp () {
                const email = this.email
                const nickname = this.nickName
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