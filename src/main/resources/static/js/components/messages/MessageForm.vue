<template>
  <v-layout row class="d-flex align-center">
    <v-text-field
        label="Новое событие"
        variant="outlined"
        v-model="text"
    ></v-text-field>

    <v-btn @click="save">
      Создать
    </v-btn>
  </v-layout>
</template>

<script>
import {sendMessage} from "util/ws";
import {getIndex} from "../../util/collection";

export default {
  props: ['messages', 'messageAttr'],
  data() {
    return {
      text: '',
      id: ''
    }
  },
  watch: {
    messageAttr(newVal) {
      this.text = newVal.text
      this.id = newVal.id
    }
  },
  methods: {
    async save() {

      sendMessage({id: this.id, text: this.text})

      this.text = '';
      this.id = '';
      /*const message = { text: this.text }

      let response;
      if (this.id) {
        response = await fetch(`/seo/${this.id}`, {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(message),
        });
      } else {
        response = await fetch('/seo', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(message),
        });
      }

      if (response.ok) {
        const data = await response.json();
        if (this.id) {
          const index = this.messages.findIndex(msg => msg.id === data.id);
          this.messages.splice(index, 1, data);
        } else {
          this.messages.push(data);
        }
        this.text = '';
        this.id = '';
      } else {
        console.error('Ошибка:', response.status);
      }*/
    }
  }
}
</script>

<style>

</style>