<template>
  <div style="position: relative; width: 300px">
    <input type="text" placeholder="Write" v-model="text" />
    <input type="button" value="Push" @click="save" />
  </div>
</template>

<script>
function getIndex(list, id) {
  for (var i = 0; i < list.length; i++) {
    if (list[i].id === id) {
      return i
    }
  }

  return -1;
}
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
      const message = { text: this.text }

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
      }
    }
  }
}
</script>

<style>

</style>