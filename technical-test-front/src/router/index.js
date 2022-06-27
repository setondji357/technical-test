import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue';
import NProgress from 'nprogress';

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/clients',
    name: 'Clients',
    component: () => import('../views/clients/ClientList.vue')
  },
  {
    path: '/clients/create',
    name: 'ClientCreate',
    component: () => import('../views/clients/ClientCreate.vue')
  },
  {
    path: '/clients/edit/:id',
    name: 'ClientEdit',
    component: () => import('../views/clients/ClientEdit.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

router.beforeResolve((to, from, next) => {
  if (to.name) {
      NProgress.start()
  }
  next()
});

router.afterEach(() => {
  NProgress.done()
});

export default router
