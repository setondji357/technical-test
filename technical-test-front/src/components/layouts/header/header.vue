<template>
  <nav class="navbar navbar-expand navbar-light bg-light">
    <div class="container">
      <router-link to="/" class="navbar-brand">TECHNICAL ASSESSMENT | FRONT END </router-link>
      <div class="navbar-nav mr-auto">
        <li class="nav-item">
          <router-link to="/clients" class="nav-link">
            <i class="fa fa-list"></i>
            Clients
          </router-link>
        </li>
        <li class="nav-item">
          <router-link to="/clients/create" class="nav-link">
            <i class="fa fa-plus-circle"></i> Ajouter
          </router-link>
        </li>
  
      </div>
    </div>
  </nav>
</template>

<script>
export default {
  name: "Header",
};
</script>
