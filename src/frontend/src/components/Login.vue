<template>
  <form @submit.prevent="loginAction">
    <p>
      <label for="username">Email: </label>
      <input id="username" v-model="username" type="email"  >
    </p>
    <p>
      <label for="password">Password: </label>
      <input id="password" v-model="password" type="password" >
    </p>
    <button type="submit">Accept</button>
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
      this.$router.push({ path: "/list" });
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
}
</style>
