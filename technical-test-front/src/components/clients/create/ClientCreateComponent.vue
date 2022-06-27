<template>
  <div class="container">
    <div class="card">
      <div class="card-header">
        <h6>Ajouter un client</h6>
      </div>
      <div class="card-body">
             <Form @submit="onSubmit" :validation-schema="schema" v-slot="{ errors }">
                <div class="form-group row">
                    <div class="col-6">
                         <label>Nom:</label>
                          <Field
                            id="lastName"
                            name="lastName"
                            type="text"
                            class="form-control"
                            v-model="client.lastName"
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
                          v-model="client.firstName"
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
                          v-model="client.email"
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
                          v-model="client.phoneNumber"
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
                          v-model="client.address"
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
              value="Ajouter client"
              v-if="!isCreating"
            />
            <button v-if="isCreating"  disabled class="btn btn-primary" type="button"   @click="onSubmit">
              <span
                class="spinner-border spinner-border-sm"
                role="status"
                aria-hidden="true"
              ></span>
              Enregistrement...
            </button>
          </div>
            </Form>
         
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
import { Form, Field } from 'vee-validate';
import * as Yup from "yup";

export default {
  components: {
        Form,
        Field,
  },
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
            client: {}
        }
    },

  computed: { ...mapGetters(["isCreating", "createdData"]) },

  methods: {
    ...mapActions(["storeclient"]),
    onSubmit() {
      const { lastName, firstName, email, phoneNumber, address} = this.client;
      this.storeclient({
        lastName: lastName,
        firstName: firstName,
        email: email,
        phoneNumber: phoneNumber,
        Address: address,
      });
    }
  },

  watch: {
    createdData: function () {
      if (this.createdData !== null && !this.isCreating) {
        this.$swal.fire({
          text: "Success, Nouveau client enregistré!",
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
