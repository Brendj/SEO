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
import {mapActions} from "vuex";

export default {
  props: ['messageAttr'],
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
    ...mapActions(['updateMessageAction', 'addMessageAction']),
    async save() {
      const message = {
        id: this.id,
        text: this.text
      }

      if (this.id) {
        await this.updateMessageAction(message)
      } else {
        await this.addMessageAction(message)
      }

      this.text = '';
      this.id = '';
    }
  }
}
</script>

<style>

</style>