<template>

  <b-navbar toggleable="lg" type="dark" variant="info">


    <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

    <b-collapse id="nav-collapse" is-nav>
      <b-navbar-nav class="nav-padding">
        <b-nav-item to="/">Login</b-nav-item>
        <b-nav-item to="/register">Register</b-nav-item>
        <b-nav-item to="/list">List</b-nav-item>
      </b-navbar-nav>


    </b-collapse>
  </b-navbar>

</template>

<script>
export default {}
</script>

<style scoped>
.bg-info {
  background-color: #17a2b8 !important;
}

#nav a.router-link-exact-active {
  color: white;
}

.nav-padding {
  padding-left: 11%;
}
</style>