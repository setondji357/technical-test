
import { createStore, createLogger } from 'vuex';
import client from './modules/Client';

const debug = process.env.NODE_ENV !== 'production'

const Store = createStore({
  modules: {
    client
  },
  strict: debug,
  plugins: debug ? [createLogger()] : []
})

export default Store;