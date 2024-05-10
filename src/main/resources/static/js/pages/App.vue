<template>
  <v-layout class="rounded rounded-md">
    <v-app-bar color="teal-lighten-5" dark>
      <v-toolbar-title>
        <span>Информационная система сбора и анализа данных</span>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-toolbar-items class="hidden-sm-and-down">
        <v-btn text>
          <span v-if="profile">{{ profile.name }}</span>
        </v-btn>
        <v-btn v-if="profile" icon="mdi-export" href="/logout">
        </v-btn>
      </v-toolbar-items>
    </v-app-bar>

    <v-main class="pa-4 pt-10" style="min-height: 300px; background: #f5f5f5;">
        <v-container v-if="!profile">
          Необходимо авторизоваться
          <a href="/login">здесь</a>
        </v-container>
        <v-container v-if="profile">
          <messages-list :messages="messages" />
        </v-container>
    </v-main>
  </v-layout>
</template>

<script>
import MessagesList from "../components/messages/MessagesList.vue";
import {addHandler} from "../util/ws";
import { getIndex } from "../util/collection";
  export default {
    components: {
      MessagesList
    },
    data() {
      return {
        messages: frontendData.messages,
        profile: frontendData.profile
      }
    },
    created() {
      addHandler(data => {
        let index = getIndex(this.messages, data.id)
        if (index > 1) {
          this.messages.splice(index, 1, data)
        } else {
          this.messages.push(data)
        }
      })
    }
  }
</script>

<style>

</style>