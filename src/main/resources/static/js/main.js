import { createApp } from 'vue';
import App from 'pages/App.vue';
import { connect } from "./util/ws";

if (frontendData.profile) {
    connect();
}

const app = createApp(App);
app.mount('#app');