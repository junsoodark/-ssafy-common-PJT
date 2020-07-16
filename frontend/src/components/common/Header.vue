<template>
    <div id="header" v-if="isHeader">
        <h1>
            <router-link v-bind:to="{name:constants.URL_TYPE.POST.MAIN}">
                SS_log
            </router-link>
        </h1>
        <div class="right">
            <div class="search-input">
                <i class="fas fa-search"></i>
                <input v-model="keyword" type="text"/>
            </div> 
            <button v-if="isLoggedIn" @click="Logout">로그아웃</button>
            <router-link v-else v-bind:to="{name:constants.URL_TYPE.USER.LOGIN}" class="login-btn">
                로그인 
            </router-link>
            <b-button v-b-modal.modal-1>Launch demo modal</b-button>
        </div>  
        <b-modal id="modal-1" title="BootstrapVue">
            <form @submit.prevent="signIn" class="middle">
                <h1>로그인 창 입니다</h1>
                <div class="input-wrap my-3">
                    <b-form-input v-model="email"
                        id="email" 
                        placeholder="이메일을 입력해주세요"
                        type="text"/>
                </div>
                <div class="input-wrap my-3">
                    <b-form-input v-model="password" type="password"
                        id="password"
                        placeholder="영문, 숫자 혼용 8자 이상"/>
                </div>
                <b-button type="submit" class="my-3" block variant="primary">
                    로그인 하기
                </b-button>
                <div class="add-option">
                    <div class="wrap">
                        <p>아직 회원이 아니신가요?</p>
                        <router-link v-bind:to="{name:constants.URL_TYPE.USER.JOIN}" class="btn--text">
                            <b-button pill variant="outline-danger">회원가입</b-button>
                        </router-link>
                    </div>

                </div>
            </form>
        </b-modal>
        
    </div>
</template>   

<script>
    import '../../assets/css/user.scss'
    import constants from '../../lib/constants'
    import axios from 'axios'

    export default {
        name: 'Header',
        components: { 

        },
        props: ['isHeader','isLoggedIn'],
        computed:{
        },
        watch: {
        },
        created() {
        },
        methods : {
            Logout() {
                this.$emit('try-logout')
                },
            signIn () {
                const email = this.email
                const password = this.password
                if (!email || !password) {
                    alert('빈칸을 채워주세요!')
                    return false
                }
                console.log('hi')
                axios.get('http://localhost:3000/account/login',{
                    params: {
                        email: email,
                        password: password
                    }
                })
                .then(res => {
                    if (res.status == 200) {
                        console.log(res)
                        this.$emit('submit-login',res.data.object)
                    } else { alert('로그인 실패!!!') }
                })
                .catch(err => {
                    alert('로그인 실패!')
                    console.log(err)
                })
            }
        },
        data: function() {
           return {
               constants,
               keyword : "",
               email: '',
               password: '',
           }
        },

    }
</script>
