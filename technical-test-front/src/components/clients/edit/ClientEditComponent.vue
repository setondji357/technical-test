<template>
  <div class="container">
    <div class="card">
      <div class="card-header">
        <h6>Modifier client</h6>
      </div>
      <div class="card-body">
         <Form @submit="onSubmit" :validation-schema="schema" v-slot="{ errors }">
          <div v-if="isLoading">
            <div class="text-center">
              <div class="spinner-border text-primary" role="status">
                <span class="sr-only">Loading...</span>
              </div>
              <br />
              Détail client en cours de chargement...
            </div>
          </div>
          <div v-if="updatingClient !== null && !isLoading">
             <div class="form-group row">
                    <div class="col-6">
                         <label>Nom:</label>
                          <Field
                            id="lastName"
                            name="lastName"
                            type="text"
                            class="form-control"
                            v-model="updatingClient.lastName"
                            :class="{ 'is-invalid': errors.lastName }"
                          />
                       <div class="invalid-feedback">{{errors.lastName}}</div>
                    </div>
                    <div class="col-6">
                        <label>Prénoms:</label>
                        <Field
                          id="firstName"
                          name="firstName"
                          type="text"
                          class="form-control"
                          v-model="updatingClient.firstName"
                          :class="{ 'is-invalid': errors.firstName }"
                        />
                        <div class="invalid-feedback">{{errors.firstName}}</div>
                    </div>
                    
                </div>
                <div class="form-group row">
                    <div class="col-12">
                         <label>Email:</label>
                        <Field
                          id="email"
                          name="email"
                          type="text"
                          class="form-control"
                          v-model="updatingClient.email"
                          :class="{ 'is-invalid': errors.email }"
                        />
                        <div class="invalid-feedback">{{errors.email}}</div>
                    </div>
                 </div>
                <div class="form-group row">
                    <div class="col-12">
                       <label>Téléphone:</label>
                        <Field
                          id="phoneNumber"
                          name="phoneNumber"
                          type="text"
                          class="form-control"
                          v-model="updatingClient.phoneNumber"
                          :class="{ 'is-invalid': errors.phoneNumber }"
                        />
                    </div>
                     <div class="invalid-feedback">{{errors.phoneNumber}}</div>
                </div>
                <div class="form-group row">
                    <div class="col-12">
                        <label>Adresse:</label>
                        <Field
                          id="address"
                          name="address"
                          type="text"
                          class="form-control"
                          v-model="updatingClient.address"
                          :class="{ 'is-invalid': errors.address }"
                        />
                        <div class="invalid-feedback">{{errors.address}}</div>
                    </div>
                   
                </div>
               
             <div class="d-grid gap-2 d-md-flex mt-3">
                <router-link to="/clients" class="btn btn-secondary mr-2"
                  >Annuler</router-link
                >
                <input
                  type="submit"
                  class="btn btn-primary"
                  value="Modifier"
                  v-if="!isUpdating"
                />
                <button class="btn btn-primary mr-2 ml-2" type="button" disabled v-if="isUpdating">
                  <span
                    class="spinner-border spinner-border-sm"
                    role="status"
                    aria-hidden="true"
                  ></span>
                  Mise à jour...
                </button>
              </div>
          </div>
        </Form>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
import { Field, Form } from "vee-validate";
import * as Yup from "yup";

export default {
    data() {
      const schema = Yup.object().shape({
      
          firstName: Yup.string()
              .required('First Name is required'),
          lastName: Yup.string()
              .required('Last name is required'),
          email: Yup.string()
              .required('Email is required')
              .email('Email is invalid'),
          phoneNumber: Yup.string()
              .required('Phone is required'),
          address: Yup.string()
              .required('Adress is required'),
      });
      return { 
          schema,
          id: null, 
          updatingClient: null
      }
    },
  components: {
    Field,
    Form,
  },
  
  mounted: async function () {
    this.id = this.$route.params.id;
    
    await this.fetchDetailclient(this.id);
    this.updatingClient = {...this.client};
    console.log('this.updatingClient:', this.updatingClient);
  },

  computed: { ...mapGetters(["isUpdating", "updatedData", "client", "isLoading"]) },

  methods: {
    ...mapActions(["updateClient", "updateClientInput", "fetchDetailclient"]),
    onSubmit() {
      const { lastName, firstName, email, phoneNumber, address} = this.updatingClient;
      this.updateClient({
        id: this.id,
        lastName: lastName,
        firstName: firstName,
        email: email,
        phoneNumber: phoneNumber,
        address: address,
      });
    },
    updateClientInputAction(e) {
      this.updateClientInput(e);
    },
  },

  watch: {
    updatedData: function () {
      if (this.updatedData !== null && !this.isUpdating) {
        this.$swal.fire({
          text: "Success, Info client modifiées !",
          icon: "success",
          position: "top-end",
          timer: 1000,
        });

        this.$router.push({ name: "Clients" });
      }
    },
  },
};
</script>
