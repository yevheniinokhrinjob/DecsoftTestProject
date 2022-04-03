<template>

  <b-navbar toggleable="lg" type="dark" variant="info">


    <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

    <b-collapse id="nav-collapse" is-nav>
      <b-navbar-nav class="nav-padding">
        <b-nav-item v-if="!userToken" to="/login">Login</b-nav-item>
        <b-nav-item v-if="!userToken" to="/register">Register</b-nav-item>
        <b-nav-item v-if="userToken" to="/list">List</b-nav-item>
        <b-nav-item v-if="userToken" to="/edit">Edit</b-nav-item>
        <b-nav-item v-if="userToken" v-on:click="logout">Logout</b-nav-item>
      </b-navbar-nav>


    </b-collapse>
  </b-navbar>

</template>

<script>
export default {
  data(){
    return{
      userToken: localStorage.token
    }
  },
  methods:{
    logout(){
      localStorage.removeItem('token')
      localStorage.removeItem('username')
      this.userToken=localStorage.token
      this.goToLogin()
    },
    goToLogin() {
      this.$router.push({ path: "/login" });
    }

  }
}
</script>

<style scoped>
.bg-info {
  background-color: #535252 !important;
}

#nav a.router-link-exact-active {
  color: white;
}

.nav-padding {
  padding-left: 11%;
}
</style>