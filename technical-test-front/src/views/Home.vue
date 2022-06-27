<template>
  <div class="home">
    <div class="p-4">
      <div class="row">
        <div class="col-md-2">
          <router-link to="/clients" class="btn btn-primary p-4 dashboard-widget">
            Liste des clients
            <span class="badge badge-success" v-if="clientsPaginatedData !== null"> 
              {{ clientsPaginatedData.pagination.total }}  
            </span>
          </router-link>
        </div>
        <div class="col-md-2">
          <router-link to="/clients/create" class="btn btn-primary p-4 dashboard-widget">
            Créer nouveau client <i class="fa fa-plus-circle"></i>
          </router-link>
        </div>
      </div>
    </div>
    <div>
      <client-list-component></client-list-component>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
import ClientListComponent from '../components/clients/list/ClientListComponent.vue';
export default {
  components: { ClientListComponent },
  name: "Home",

  computed: { ...mapGetters(["clientsPaginatedData"]) },

  methods: {
    ...mapActions(["fetchAllClients"]),
  },

  created() {
    this.fetchAllClients(1);
  },
};
</script>
