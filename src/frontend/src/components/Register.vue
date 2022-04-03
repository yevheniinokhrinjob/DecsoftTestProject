<template>
  <form @submit.prevent="register">
    <p>
      <label for="firstName">First name: </label>
      <input id="firstName" v-model="firstName" type="text" >
    </p>
    <p>
      <label for="lastName">Last name: </label>
      <input  id="lastName" v-model="lastName" type="text" >
    </p>
    <p>
      <label for="homePhoneNumber">homePhoneNumber: </label>
      <input  id="homePhoneNumber" v-model="homePhoneNumber" type="text"  >
    </p>
    <p>
      <label for="workPhoneNumber">workPhoneNumber: </label>
      <input  id="workPhoneNumber" v-model="workPhoneNumber" type="text"  >
    </p>
    <p>
      <label for="email">Email: </label>
      <input id="email" v-model="email" type="email"  >
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
      firstName: '',
      lastName: '',
      homePhoneNumber: '',
      workPhoneNumber: '',
      email: '',
      password: ''
    }
  },
  methods: {
    register() {
      /*  fetch("/getIt")
          .then((response)=>response.json())
          .then((data)=>{
            console.log(data)
          })*/
      const postData = {
        firstName: this.firstName,
        lastName: this.lastName,
        homePhoneNumber: this.homePhoneNumber,
        workPhoneNumber: this.workPhoneNumber,
        password: this.password,
        email: this.email
      };

      fetch("/contact/register", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",

        },
        body: JSON.stringify(postData)
      }).then(response => console.log(response))
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
