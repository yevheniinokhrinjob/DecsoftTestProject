import Vue from 'vue'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import App from './App.vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import VueRouter from 'vue-router';
import Login from "@/components/Login";
import Register from "@/components/Register";
import ContactList from "@/components/ContactList";
import Edit from "@/components/Edit";


Vue.use(VueRouter);
Vue.config.productionTip = false

Vue.use(BootstrapVue)
Vue.use(IconsPlugin)

const router = new VueRouter({
  mode: 'history',
  base: __dirname,
  routes: [
    { path: '/login', component: Login },
    { path: '/register', component: Register },
    { path: '/', component: ContactList },
    { path: '/edit', component: Edit }
  ]
});

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')

