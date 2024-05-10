import { createApp } from 'vue';
import App from 'pages/App.vue';
import { connect } from "./util/ws";
import { vuetify } from "./vuetify/vuetify"


if (frontendData.profile) {
    connect();
}

const app = createApp(App);

app.use(vuetify)

app.mount('#app');
