<template>
    <div class="user" id="login">
        <div class="wrapC table">
            <form @submit.prevent="signIn" class="middle">
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
                        placeholder="영문, 숫자 혼용 8자 이상"/>
                </div>
                <button type="submit" class="btn btn--back btn--login">
                    로그인 하기
                </button>
                <div class="add-option">
                    <div class="wrap">
                        <p>아직 회원이 아니신가요?</p>
                        <router-link v-bind:to="{name:constants.URL_TYPE.USER.JOIN}" class="btn--text">
                            회원가입
                        </router-link>
                    </div>

                </div>
            </form>
            
        </div>

    </div>
</template>

<script>
    import '../../assets/css/user.scss'
    import constants from '../../lib/constants'
    import axios from 'axios'

    export default {
        components: {
        },
        created(){
        },
        watch: {
        },
        methods: {
            signIn () {
                const email = this.email
                const password = this.password
                if (!email || !password) {
                    alert('빈칸을 채워주세요!')
                    return false
                }

                axios.get('http://localhost:3000/account/login',{
                    params: {
                        email: email,
                        password: password
                    }
                })
                .then(res => {
                    if (res.status == 200) {
                        console.log(res)
                        document.cookie = `accessToken=${res.data.data.accessToken}`
                        axios.default.headers.common['x-access-token'] = res.data.data.accessToken
                        this.$route.push({'name':'main'})
                    } else { alert('로그인 실패') }
                })
                .catch(err => {
                    alert('로그인 실패!')
                    console.log(err)
                })
            }
        },
        data: () => {
            return {
                constants,
                email: '',
                password: '',

            }
        }

    }

</script>


