<template>
  <v-container>
    <v-col cols="12">
      <message-form :messageAttr="message" />
      <messages-row v-for="message in sortedMessages"
                    :key="message.id"
                    :message="message"
                    :editMessage="editMessage" />
    </v-col>
  </v-container>
</template>

<script>
import MessagesRow from "../components/messages/MessagesRow.vue";
import MessageForm from "../components/messages/MessageForm.vue";
import { mapGetters } from 'vuex'
export default {
  components: {
    MessagesRow,
    MessageForm
  },
  data() {
    return {
      message: null
    }
  },
  computed: mapGetters(['sortedMessages']),
  methods: {
    editMessage(message) {
      this.message = message;
    }
  }
}
</script>

<style>

</style>