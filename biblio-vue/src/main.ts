
import * as VueRouter from "vue-router";
import Home from './pages/Home.vue'
import Catalog from './pages/Catalog.vue'
import {createApp} from "vue";
import App from "./App.vue";

const routes = [
    { path: "/", component: Home },
    { path: "/catalog", component: Catalog },
];

const router = VueRouter.createRouter({
    history: VueRouter.createWebHistory(),
    routes,
})

const app = createApp(App);
app.use(router);