import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";
import VueAwesomeSwiper from "vue-awesome-swiper";
import CKEditor from "ckeditor4-vue";
import VModal from "vue-js-modal";
import VueSlideUpDown from "vue-slide-up-down";

Vue.use(VModal, { dynamic: true });
// Use CKEditor  4
Vue.use(CKEditor);
// Install BootstrapVue
Vue.use(BootstrapVue);
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin);

Vue.use(VueAwesomeSwiper /* { default options with global component } */);

Vue.component("vue-slide-up-down", VueSlideUpDown);

import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

Vue.config.productionTip = false;

new Vue({
    router,
    store,
    render: (h) => h(App),
}).$mount("#app");
