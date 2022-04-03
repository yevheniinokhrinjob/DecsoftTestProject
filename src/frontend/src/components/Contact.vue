<template>
  <div>

    <b-row>
      <b-col>{{item.firstName}}</b-col>
      <b-col>{{item.lastName}}</b-col>
      <b-col>{{ item.homePhoneNumber }}</b-col>
      <b-col>{{ item.email }}</b-col>
    </b-row>
  </div>
</template>

<script>
export default {
  name: "Contact",
  props: {
    item: {
      type: Object,
      required: true
    }
  }
}
</script>

<style scoped>

</style>