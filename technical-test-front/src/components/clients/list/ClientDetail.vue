<template>
  <div class="row border-1 p-2">
    <div class="col-1 text-left">{{ index + 1 }}</div>
    <div class="col-3 text-left">{{ client.lastName }} {{ client.firstName }}</div>
    <div class="col-3">
      <strong>{{ client.email }}</strong>
    </div>
    <div class="col-3">
      <span>{{ client.phoneNumber }}</span>
    </div>
    <div class="col-2 text-center" >
      <router-link
        :to="{ name: 'ClientEdit', params: { id: client.id } }"
        class="btn btn-primary"
        title="Modfifier client"
        >
        <i class="fa fa-pencil"></i></router-link
      >
      <button class="btn btn-danger mr-2 ml-2" @click="deleteClientModal(client.id)" title="Supprimer client">
         <i class="fa fa-trash"></i>
      </button>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapActions } from "vuex";

export default {
  name: "ClientDetail",
  props: {
    client: {
      type: Object,
    },
    index: {
      type: Number,
    },
  },

  computed: { ...mapGetters(["isDeleting", "deletedData"]) },

  methods: {
    ...mapActions(["deleteClient", "fetchAllClients"]),
   async deleteClientModal(id) {
     let isConfirmed=false;
     await this.$swal
        .fire({
          text: "Voulez vous vraiment supprimer ce client ?",
          icon: "error",
          cancelButtonText: "ANNULER",
          confirmButtonText: "OUI",
          showCancelButton: true,
        })
        .then((result) => {
          isConfirmed=result["isConfirmed"];
          if (result["isConfirmed"]) {
            
            this.deleteClient(id);
            this.$swal.fire({
              text: "Success, Client supprimé!",
              icon: "success",
              position: 'top-end',
              timer: 1000,
            });
          }
        });
       
    },
  },
};
</script>
