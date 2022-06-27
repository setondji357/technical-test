// import { GET_ALL_clients } from "./Types";
import axios from 'axios';

// initial state
const state = () => ({
  clients: [],
  clientsPaginatedData: null,
  client: null,
  isLoading: false,
  isCreating: false,
  createdData: null,
  isUpdating: false,
  updatedData: null,
  isDeleting: false,
  deletedData: null
})

// getters
const getters = {
  clientList: state => state.clients,
  clientsPaginatedData: state => state.clientsPaginatedData,
  client: state => state.client,
  isLoading: state => state.isLoading,
  isCreating: state => state.isCreating,
  isUpdating: state => state.isUpdating,
  createdData: state => state.createdData,
  updatedData: state => state.updatedData,

  isDeleting: state => state.isDeleting,
  deletedData: state => state.deletedData
};

// actions
const actions = {
  async fetchAllClients({ commit }, query = null) {
    let page = '1';
    let search = '';
    if(query !== null){
      page = query.page;
      search = query.search;
    }
    commit('setClientIsLoading', true);
    let url = `${process.env.VUE_APP_API_BASE_URL}/clients?page=${page}`;
    if (search === null) {
      url = `${url}?page=${page}`;
    } else {
      url = `${process.env.VUE_APP_API_BASE_URL}/clients?page=${page}`;
    }

    await axios.get(url)
      .then(res => {
        
        const clients = res.data;
        commit('setclients', clients);
        const pagination = {
         
          total: res.data.length,  
          per_page: 10, 
          current_page: 1, 
          total_pages: 1
        }
        res.data.pagination = pagination;
        console.log('clients: ', clients);
        commit('setClientsPaginated', res.data);
        commit('setClientIsLoading', false);
      }).catch(err => {
        console.log('error', err, url);
        commit('setClientIsLoading', false);
      });
  },

  async fetchDetailclient({ commit }, id) {
    
    commit('setClientIsLoading', true);
    await axios.get(`${process.env.VUE_APP_API_BASE_URL}/clients/${id}`)
      .then(res => {
        console.log('fetchDetailclientd res:', res);
        commit('setClientDetail', res.data);
        commit('setClientIsLoading', false);
      }).catch(err => {
        console.log('error', err);
        commit('setClientIsLoading', false);
      });
  },

  async storeclient({ commit }, client) {
    commit('setClientIsCreating', true);
    await axios.post(`${process.env.VUE_APP_API_BASE_URL}/clients`, client)
      .then(res => {
        commit('saveNewClients', res.data);
        commit('setClientIsCreating', false);
      }).catch(err => {
        console.log('error', err);
        commit('setClientIsCreating', false);
      });
  },

  async updateClient({ commit }, client) {
    commit('setClientIsUpdating', true);
    commit('setClientIsUpdating', true);
    await axios.put(`${process.env.VUE_APP_API_BASE_URL}/clients/${client.id}`, client)
      .then(res => {
        commit('saveUpdatedClient', res.data);
        commit('setClientIsUpdating', false);
      }).catch(err => {
        console.log('error', err);
        commit('setClientIsUpdating', false);
      });
  },


  async deleteClient({ commit }, id) {
    console.log('Start deleteClient OK')
    
    await axios.delete(`${process.env.VUE_APP_API_BASE_URL}/clients/${id}`)
      .then(res => {
        commit('setDeleteClient', res.data.id);
        commit('setClientIsDeleting', false);
        // commit('deleteClient', id);
      }).catch(err => {
        console.log('error', err);
        commit('setClientIsDeleting', false);
      });
  },

  updateClientInput({ commit }, e) {
    commit('setClientDetailInput', e);
  }
}

// mutations
const mutations = {
  setclients: (state, clients) => {
    state.clients = clients
  },

  setClientsPaginated: (state, clientsPaginatedData) => {
    state.clientsPaginatedData = clientsPaginatedData
  },

  setClientDetail: (state, client) => {
    state.client = client
  },

  setDeleteClient: (state, id) => {
    state.clientsPaginatedData.filter(x => x.id !== id);
  },

  setClientDetailInput: (state, e) => {
    let client = state.client;
    client[e.target.name] = e.target.value;
    state.client = client
  },

  saveNewClients: (state, client) => {
    state.clients.unshift(client)
    state.createdData = client;
  },

  saveUpdatedClient: (state, client) => {
    state.clients.unshift(client)
    state.updatedData = client;
  },

  setClientIsLoading(state, isLoading) {
    state.isLoading = isLoading
  },

  setClientIsCreating(state, isCreating) {
    state.isCreating = isCreating
  },

  setClientIsUpdating(state, isUpdating) {
    state.isUpdating = isUpdating
  },

  setClientIsDeleting(state, isDeleting) {
    state.isDeleting = isDeleting
  },

}

export default {
  // namespaced: true,
  state,
  getters,
  actions,
  mutations
}