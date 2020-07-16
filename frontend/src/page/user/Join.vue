<template>
    <div class="user" id="join"> 
        <div class="wrapC table">
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
                            type="email"/>
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
            }
        },
        watch: {
        },
        data: () => {
            return {
                email: '',
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


