<template>
  <div>
    <b-navbar toggleable="lg" type="dark" variant="info">
      <router-link to="/">Home</router-link>
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
        <b-collapse id="nav-collapse" is-nav>

        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">
          <b-nav-form>
            <b-form-input size="sm" class="mr-sm-2" placeholder="Search"></b-form-input>
            <b-button size="sm" class="my-2 my-sm-0" type="submit">Search</b-button>
          </b-nav-form>

            <!-- Using 'button-content' slot -->
          <div v-if="IsLoggedIn">
            <a>회원정보</a> |
            <a @click="Logout">로그아웃</a>
          </div>
          <div v-else>
            <router-link to="/login">Login</router-link> |
            <router-link to="/signup">Signup</router-link>
          </div>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
export default {
    props: {
        IsLoggedIn: Boolean
    },
    methods: {
        Logout () {
            this.$cookies.remove('auth-token')
            this.$emit('try-logout')
            this.$store.dispatch('Logout')
        }
    }
}
</script>

<style>

</style>