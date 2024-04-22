import { createApp } from 'vue'
import VueResource from 'vue-resource'
import App from 'pages/App.vue'

createApp(VueResource)
createApp(App).mount('#app')
/*

function getIndex(list, id) {
    for (var i = 0; i < list.length; i++) {
        if (list[i].id === id) {
            return i;
        }
    }

    return -1;
}

const messageApi = Vue.resource('/seo{/id}');

Vue.component('message-form', {

});
*/
