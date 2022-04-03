<template>

  <button @click="getContacts">Accept</button>
</template>

<script>
export default {
  name: "ContactList",

  methods:{
    getContacts(){
        const userToken = localStorage.token;
        const authString = "Bearer ".concat(userToken);
        fetch("/contact",{
          method:"GET",
          headers: {
            Authorization: authString,
          }
        })
       .then((response)=>response.json())
       .then((data)=>{
         console.log(data)
       })
    }
  }
}
</script>

<style scoped>

</style>