<template>
  <div class="container">
    <div class="row justify-content-center mt-2 mb-2">
      <div class="col-8">
        <h4 class="text-left mb-2">Liste des clients</h4>
      </div>
      <div class="col-4">
        <input
          type="text"
          class="form-control"
          placeholder="Search clients..."
          @input="searchClients"
          v-model="query.search"
        />
      </div>
    </div>
    <div class="">
      <div class="" v-if="!isLoading">
        <div class="row border-bottom border-top p-2 bg-light">
          <div class="col-1">ID</div>
          <div class="col-3">Noms & Prénoms</div>
          <div class="col-2">Email</div>
          <div class="col-3">Téléphone</div>
          <div class="col-2">Actions</div>
        </div>

        <div v-for="(item, index) in clientsPaginatedData" :key="item.id">
          <client-detail :index="index" :client="item" />
        </div>
      </div>

      <div v-if="isLoading" class="text-center mt-5 mb-5">
        Chargement des clients...
        <div class="spinner-grow" role="status">
          <span class="sr-only">Loading...</span>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
import ClientDetail from "../list/ClientDetail";

export default {
  data() {
    return {
      query: {
        page: 1,
        search: "",
      },
    };
  },
  components: {
    ClientDetail,
  },
  computed: { ...mapGetters(["clientList", "clientsPaginatedData", "isLoading"]) },

  methods: {
    ...mapActions(["fetchAllClients"]),

    getResults() {
      this.fetchAllClients(this.query);
    },
    searchClients() {
      this.fetchAllClients(this.query);
    },
  },

  created() {
    this.fetchAllClients(this.query);
  },
};
</script>
