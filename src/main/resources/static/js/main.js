import { createApp } from 'vue';
import App from 'pages/App.vue';
import { connect } from "./util/ws";
import { createVuetify } from 'vuetify'
import '@mdi/font/css/materialdesignicons.css' // Ensure you are using css-loader

const vuetify = createVuetify({
    icons: {
        defaultSet: 'mdi',
    },
})

if (frontendData.profile) {
    connect();
}

const app = createApp(App);
app.use(vuetify)
app.mount('#app');