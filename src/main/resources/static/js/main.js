// Определяем новый компонент под именем todo-item
Vue.component('messages-list', {
    props: ['messages'],
    template: '<div><div v-for="message in messages">{{ message.text }}</div></div>'
});

var app = new Vue({
    el: '#app',
    template: '<messages-list :messages="messages" />',
    data: {
        messages: [
            { id:'1', text:'text1' },
            { id:'2', text:'text2' },
            { id:'3', text:'text3' }
        ]
    }
});