import { createApp } from 'vue';
import App from 'pages/App.vue';
import { connect } from "./util/ws";

connect();

const app = createApp(App);
app.mount('#app');