<template>
  <div>
    <div>
      <input v-if="isNumber" v-model="searchData" placeholder="Search by number">
      <input v-if="!isNumber" v-model="searchData" placeholder="Search by last Name">
      <button @click="getContacts">Search</button>
      <input type="checkbox" id="checkbox" v-model="isNumber">
    </div>
    <div>
      <Contact
          v-for="item of items"
          v-bind:item="item"
          :key="item.id"
      />
    </div>

  </div>
</template>

<script>
import Contact from "@/components/Contact";

export default {
  name: "ContactList",
  components: {Contact},
  data() {
    return {
      isNumber: true,
      searchData: "",
      items: []
    }
  },

  methods: {
    getContacts() {
      let vm = this;
      const userToken = localStorage.token;
      const authString = "Bearer ".concat(userToken);
      let inputLine = "";
      if (!this.isNumber) {
        inputLine = "/contact/lastName/" + this.searchData;
      } else {
        inputLine = "/contact/number/" + this.searchData;
      }
      fetch(inputLine, {
        method: "GET",
        headers: {
          Authorization: authString,
        }
      })
          .then((response) => response.json())
          .then((data) => {
            //   console.log(data)
            vm.items = data
          })
    },goToLogin() {
      this.$router.push({ path: "/login" });
    }

  },
  mounted() {
    if(!localStorage.token){
      this.goToLogin()
    }
  }
}
</script>

<style scoped>

</style>