<template>
  <div>
  <div v-if="!profile">Необходимо авторизоваться
      <a href="/login">здесь</a>
  </div>
  <div v-else>
    <div>{{profile.name}}&nbsp;<a href="/logout">выход</a> </div>
    <messages-list :messages="messages" />
    </div>
  </div>
</template>

<script>
import MessagesList from "../components/messages/MessagesList.vue";
  export default {
    components: {
      MessagesList
    },
    data() {
      return {
        messages: frontendData.messages,
        profile: frontendData.profile
      }
    }
  }
</script>

<style>

</style>