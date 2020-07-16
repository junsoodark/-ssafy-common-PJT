<template>
    <div class="user" id="join">
        <div class="wrapC table" id="email-form">
            <h1>회원 가입</h1>
            <h1>이메일 인증을 위해 이메일을 입력해주세요</h1>
            <div>
                <b-form-input v-model="email" placeholder="이메일을 입력해주세요" type="email" @keypress.enter="checkEmail"></b-form-input>
                <b-button type="submit" @click="checkEmail">제출</b-button>
            </div>
        </div>
        <div class="wrapC table d-none" id="code-form">
            <div>
                <b-form-input v-model="code" placeholder="이메일로 간 코드를 입력해주세요" type="text" @keypress.enter="checkCode"></b-form-input>
                <b-button type="submit" @click="checkCode">제출</b-button>
            </div>
        </div>
        <div class="wrapC table d-none" id="join-form">
            <form @submit.prevent="signUp" class="middle">
                <h1>회원가입</h1>
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
                            type="email"
                            disabled/>
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

                <label>
                    <input v-model="isTerm" type="checkbox" id="term"/>
                    <span>약관에 동의합니다</span>
                </label>

                <span class="go-term">약관 보기</span>

                <button class="btn" type="submit">
                    <span>
                        작성완료
                    </span>
                </button>
            </form>


        </div> 
        

    </div>
</template>

<script>
    import '../../assets/css/user.scss'
    import axios from 'axios'

    export default {
        components: {
        },
        created(){


        },
        methods: {
            signUp () {
                const email = this.email
                const nickname = this.nickName
                const password = this.password
                const passwordConfirm = this.passwordConfirm
                const isTerm = this.isTerm
                if (password!=passwordConfirm) {
                    alert('비밀번호 확인과 비밀번호가 다릅니다!')
                    return false
                } else if (!isTerm) {
                    alert('동의를 해주세요!')
                    return false
                } else if (email.indexOf('@')===-1) {
                    alert('이메일 형식을 사용해야합니다!')
                    return false
                }

                axios.post('http://localhost:3000/account/signup',{ email, nickname, password })
                .then(res => {
                    if (res.status == 200) {
                        alert('가입성공')
                        console.log(res)
                        this.$router.push({name:'login'})
                    }
                })
                .catch(err => {
                    alert('가입실패')
                    console.log(err)
                })
            },
            checkEmail () {
                var params = new URLSearchParams()
                const email = this.email
                params.append('email',email)
                axios.post('http://localhost:3000/email/signup/send',params)
                .then(res => {
                    const EmailForm = document.querySelector('#email-form')
                    EmailForm.className = 'd-none'
                    const CodeForm = document.querySelector('#code-form')
                    CodeForm.className = 'wrapC table'
                    console.log(res)
                })
                .catch(err => {console.log(err)})
            },
            checkCode () {
                var params = new URLSearchParams()
                const code = this.code
                params.append('code',code)
                const email = this.email
                params.append('email',email)
                axios.post('http://localhost:3000/email/signup/check',params)
                .then(res => {
                    console.log(res)
                    const CodeForm = document.querySelector('#code-form')
                    CodeForm.className = 'd-none'
                    const JoinForm = document.querySelector('#join-form')
                    JoinForm.className = 'wrapC table'
                })
                .catch(err => {console.log(err)})
            }
        },
        watch: {
        },
        data: () => {
            return {
                email: '',
                code: '',
                nickName: '',
                password: '',
                passwordConfirm: '',
                isTerm: false,
                passwordType:"password",
                passwordConfirmType:"password",
            }
        }

    }

</script>


