import { createApp } from 'vue';
import App from 'pages/App.vue';
import { connect } from "./util/ws";
import { vuetify } from "./vuetify/vuetify"
import '@babel/polyfill'
import { createStore } from 'vuex'
import router from 'route/router'


const store = createStore({
    state : {
        messages: frontendData.messages,
        profile: frontendData.profile
    },
    getters :{
        sortedMessages: state => state.messages.sort((a, b) => -(a.id - b.id))
    },
    mutations: {
        addMessageMutation(state, message) {
            state.messages = [
                ...state.messages,
                message
            ]
        },
        updateMessageMutation(state, message) {
            const updateIndex = state.messages.findIndex(item => item.id === message.id)
            state.messages = [
                ...state.messages.slice(0, updateIndex),
                message,
                ...state.messages.slice(updateIndex + 1)

            ]
        },
        removeMessageMutation(state, message) {
            const deleteIndex = state.messages.findIndex(item => item.id === message.id)
            if (deleteIndex > -1) {
                state.messages = [
                    ...state.messages.slice(0, deleteIndex),
                    ...state.messages.slice(deleteIndex + 1)
                ]
            }
        }
    },
    actions: {
        async addMessageAction({commit}, message) {
            const response = await fetch('/seo', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(message),
            });

            if (response.ok) {
                const data = await response.json();
                commit('addMessageMutation', data);
            } else {
                console.error('Ошибка:', response.status);
            }
        },
        async updateMessageAction({commit}, message) {
            const response = await fetch(`/seo/${message.id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(message),
            });

            if (response.ok) {
                const data = await response.json();
                commit('updateMessageMutation', data);
            } else {
                console.error('Ошибка:', response.status);
            }
        },
        async removeMessageAction({commit}, message) {
            const response = await fetch(`/seo/${message.id}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(message),
            });

            if (response.ok) {
                commit('removeMessageMutation', message);
            } else {
                console.error('Ошибка:', response.status);
            }
        }
    }
})


if (frontendData.profile) {
    connect();
}

const app = createApp(App);
app.use(router)
app.use(vuetify)
app.use(store)
app.mount('#app');
