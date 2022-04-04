<template>
  <form @submit.prevent="loginAction">
    <div class="row justify-content-center">
      <div class="col-sm-6">
        <div class="form-outline mb-4">
          <label for="email" class="form-label">Email: </label>
          <input id="email" class="form-control" v-model="username" type="email">
        </div>
        <div class="form-outline mb-4">
          <label for="password" class="form-label">Password: </label>
          <input id="password" class="form-control" v-model="password" type="password">
        </div>
    <button type="submit">Accept</button>
        <div class="form-outline mb-4">
      <a href="/register">Register</a>
        </div>
      </div>
    </div>
  </form>

</template>

<script>
export default {
  name: 'HelloWorld',
  data(){
    return{
      username: '',
      password: ''
    }
  },
  methods: {

    loginAction() {

      const postData = {
        username: this.username,
        password: this.password,

      };
      localStorage.username=this.username
      fetch("/authenticate", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",

        },
        body: JSON.stringify(postData)
      }).then(response=>response.json()).then(data=> localStorage.token=data.token)
      this.goToList()

    },
    goToList() {
      this.$router.push({ path: "/" });
    },
  },
  mounted() {
    if(localStorage.token){
      this.goToList()
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
  font-size: 22px;
}
</style>
