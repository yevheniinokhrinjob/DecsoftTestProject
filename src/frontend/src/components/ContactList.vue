<template>
  <div>
    <div>
      <Navigation/>
    </div>
    <div class="row justify-content-center myclass" >
      <div class="col-sm-6">
        <div class="form-outline mb-4">
          <div class="myclass">
            <div class="row justify-content-md-center">
              <div class="col col-lg-2">
            <button @click="getContacts">Search</button>
              </div>


            <div class="col col-lg-2">
          <input type="checkbox" class="big-checkbox" id="checkbox" v-model="isNumber">
            </div>
          </div>
          </div>
          <input v-if="isNumber" class="form-control" v-model="searchData" placeholder="Search by number">
          <input v-if="!isNumber" class="form-control" v-model="searchData" placeholder="Search by last Name">

        </div>
        <div>
          <Contact
              v-for="item of items"
              v-bind:item="item"
              :key="item.id"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Contact from "@/components/Contact";
import Navigation from "@/components/Navigation";

export default {
  name: "ContactList",
  components: {
    Contact,
    Navigation
  },
  data() {
    return {
      userToken: "",
      isNumber: true,
      searchData: "",
      items: []
    }
  },

  methods: {
    getContacts() {
      let vm = this;
      if (!localStorage.token) {
        this.goToLogin()
      } else {
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
              vm.items = data
            })
      }
    }, goToLogin() {
      this.$router.push({path: "/login"});
    }

  }
}
</script>

<style scoped>

.myclass{
  padding-top: 20px;
  padding-bottom: 20px;
}
.big-checkbox {
  width: 30px;
  height: 30px;
}
</style>