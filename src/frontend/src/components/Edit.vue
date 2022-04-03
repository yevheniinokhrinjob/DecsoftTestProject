<template>
  <div>
  <div>
    <form @submit.prevent="editItem">
      <p>
        <label for="firstName">First name: </label>
        <input id="firstName" v-model="editObject.firstName" type="text">
      </p>
      <p>
        <label for="lastName">Last name: </label>
        <input id="lastName" v-model="editObject.lastName" type="text">
      </p>
      <p>
        <label for="homePhoneNumber">homePhoneNumber: </label>
        <input id="homePhoneNumber" v-model="editObject.homePhoneNumber" type="text">
      </p>
      <p>
        <label for="workPhoneNumber">workPhoneNumber: </label>
        <input id="workPhoneNumber" v-model="editObject.workPhoneNumber" type="text">
      </p>
      <button type="submit">Edit</button>
    </form>
  </div>
  <div>
    <button @click="deleteItem">Delete</button>
  </div>
  </div>
</template>

<script>
export default {
  name: "Edit",
  data() {
    return {
      editObject: {
        firstName: '',
        lastName: '',
        homePhoneNumber: '',
        workPhoneNumber: ''
      }
    }
  },

  methods: {
    editItem() {
      const userToken = localStorage.token;
      const authString = "Bearer ".concat(userToken);
      fetch("/contact", {
        method: "PATCH",
        headers: {
          Authorization: authString,
          "Content-Type": "application/json",

        },
        body: JSON.stringify(this.editObject)
      })
    },
    deleteItem(){
      const userToken = localStorage.token;
      const authString = "Bearer ".concat(userToken);
      fetch("/contact/email/"+localStorage.username, {
        method: "DELETE",
        headers: {
          Authorization: authString,
        }
      })
      localStorage.removeItem('token')
      localStorage.removeItem('username')
      this.goToLogin()
      location.reload()
    },
    goToLogin() {
      this.$router.push({ path: "/login" });
    }
  },
  mounted() {
    if(!localStorage.token){
      this.goToLogin()
    }
    let vm = this;
    const userToken = localStorage.token;
    const authString = "Bearer ".concat(userToken);
    fetch("/contact/email/"+localStorage.username, {
      method: "GET",
      headers: {
        Authorization: authString,
      }
    })
        .then((response) => response.json())
        .then((data) => {
          vm.editObject = data
        })
  }

}
</script>

<style scoped>

</style>