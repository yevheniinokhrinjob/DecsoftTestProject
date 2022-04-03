<template>
  <form @submit.prevent="register">
    <div class="row justify-content-center">
      <div class="col-sm-6">
        <div class="form-outline mb-4">
          <label for="firstName" class="form-label">First name: </label>
          <input id="firstName" class="form-control" v-model="firstName" type="text">
        </div>
        <div class="form-outline mb-4">
          <label for="lastName" class="form-label">Last name: </label>
          <input id="lastName" class="form-control" v-model="lastName" type="text">
        </div>
        <div class="form-outline mb-4">
          <label for="homePhoneNumber" class="form-label">homePhoneNumber: </label>
          <input id="homePhoneNumber" class="form-control" v-model="homePhoneNumber" type="text">
        </div>
        <div class="form-outline mb-4">
          <label for="workPhoneNumber" class="form-label">workPhoneNumber: </label>
          <input id="workPhoneNumber" class="form-control" v-model="workPhoneNumber" type="text">
        </div>
        <div class="form-outline mb-4">
          <label for="email" class="form-label">Email: </label>
          <input id="email" class="form-control" v-model="email" type="email">
        </div>
        <div class="form-outline mb-4">
          <label for="password" class="form-label">Password: </label>
          <input id="password" class="form-control" v-model="password" type="password">
        </div>
        <div class="message">
          {{ message }}
        </div>
        <button type="submit">Accept</button>
        <div class="message">
          <a href="/login">Login</a>
        </div>

      </div>
    </div>
  </form>

</template>

<script>
export default {
  name: 'HelloWorld',
  data() {
    return {
      firstName: '',
      lastName: '',
      homePhoneNumber: '',
      workPhoneNumber: '',
      email: '',
      password: '',
      message: ''
    }
  },
  methods: {
    register() {

      let vm = this;
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
      }).then(response => {
            if (response.status == 400) {
              this.message = "Invalid data"
            } else {
              this.message = ""
              vm.goToLogin();
            }
          }
      )

    },
    goToList() {
      this.$router.push({path: "/"});
    },
    goToLogin() {
      this.$router.push({path: "/login"});
    }
  },

  mounted() {
    if (localStorage.token) {
      this.goToList()
    }
  }
}
</script>

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

.message {
  color: red;
  font-size: 32px;
}
</style>
