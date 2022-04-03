<template>
  <div>
    <div>
      <Navigation/>
    </div>
  <div>
    <form @submit.prevent="editItem">
      <div class="row justify-content-center">
        <div class="col-sm-6">
          <div class="form-outline mb-4">
            <label for="firstName" class="form-label">First name: </label>
            <input id="firstName" class="form-control" v-model="editObject.firstName" type="text">
          </div>
          <div class="form-outline mb-4">
            <label for="lastName" class="form-label">Last name: </label>
            <input id="lastName" class="form-control" v-model="editObject.lastName" type="text">
          </div>
          <div class="form-outline mb-4">
            <label for="homePhoneNumber" class="form-label">homePhoneNumber: </label>
            <input id="homePhoneNumber" class="form-control" v-model="editObject.homePhoneNumber" type="text">
          </div>
          <div class="form-outline mb-4">
            <label for="workPhoneNumber" class="form-label">workPhoneNumber: </label>
            <input id="workPhoneNumber" class="form-control" v-model="editObject.workPhoneNumber" type="text">
          </div>
          <div class="form-outline mb-4">
      <button type="submit">Edit</button>
          </div>
        </div>
        </div>
    </form>
  </div>
  <div>
    <button @click="deleteItem">Delete</button>
  </div>
  </div>
</template>

<script>
import Navigation from "@/components/Navigation";

export default {
  name: "Edit",
  components:{Navigation},
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