<template>
  <v-layout column class="d-flex align-center">
    <v-text-field
        label="Новое событие"
        variant="outlined"
        v-model="text"
    ></v-text-field>

    <v-select
        label="Выберите вид спорта"
        :items="['Футбол', 'Легкая атлетика']"
        v-model="sport"
    ></v-select>

    <v-text-field
        v-if="sport === 'Легкая атлетика'"
        label="Очередность мест"
        variant="outlined"
        v-model="placeOrder"
    ></v-text-field>

    <v-row v-if="sport === 'Футбол'">
      <v-col>
        <v-text-field
            label="Имя игрока"
            variant="outlined"
            v-model="playerName"
        ></v-text-field>
      </v-col>
      <v-col>
        <v-text-field
            label="Место команды"
            variant="outlined"
            v-model="teamPlace"
        ></v-text-field>
      </v-col>
      <v-col>
        <v-text-field
            label="Количество голов"
            variant="outlined"
            v-model="goals"
        ></v-text-field>
      </v-col>
    </v-row>

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
      id: '',
      sport: '',
      placeOrder: '',
      playerName: '',
      teamPlace: '',
      goals: ''
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
        text: this.text,
        sport: this.sport,
        placeOrder: this.placeOrder,
        playerName: this.playerName,
        teamPlace: this.teamPlace,
        goals: this.goals
      }

      if (this.id) {
        await this.updateMessageAction(message)
      } else {
        await this.addMessageAction(message)
      }

      this.text = '';
      this.id = '';
      this.sport = '';
      this.placeOrder = '';
      this.playerName = '';
      this.teamPlace = '';
      this.goals = '';
    }
  }
}
</script>

<style>

</style>
