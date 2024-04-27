<template>
  <div style="position: relative; width: 300px">
    <message-form :messages="messages" :messageAttr="message" />
    <messages-row v-for="message in messages"
                  :key="message.id"
                  :message="message"
                  :editMessage="editMessage"
                  :deleteMessage="deleteMessage"
                  :messages="messages" />
  </div>
</template>

<script>
import MessagesRow from "./MessagesRow.vue";
import MessageForm from "./MessageForm.vue";
export default {
  props: ['messages'],
  components: {
    MessagesRow,
    MessageForm
  },
  data() {
    return {
      message: null
    }
  },
  methods: {
    editMessage(message) {
      this.message = message;
    },
    async deleteMessage(message) {
      const response = await fetch(`/seo/${message.id}`, { method: 'DELETE' });

      if (response.ok) {
        this.messages.splice(this.messages.indexOf(message), 1);
      }
    }
  }
}
</script>

<style>

</style>