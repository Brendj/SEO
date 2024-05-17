<template>
  <v-container fluid>
    <v-row class="d-flex flex-column align-center" style="max-width: 800px; margin: 0 auto;">
      <v-col cols="12">
        <v-text-field
            class="mb-4"
            label="Новое событие"
            outlined
            v-model="text"
        ></v-text-field>
      </v-col>
      <v-col cols="12">
        <v-select
            class="mb-4"
            label="Выберите вид спорта"
            :items="['Футбол', 'Легкая атлетика']"
            v-model="sport"
            @change="resetPlayers"
        ></v-select>
      </v-col>

      <v-expansion-panels v-if="sport" multiple class="w-100">
        <v-expansion-panel v-if="sport === 'Футбол'">
          <v-expansion-panel-header>
            <span class="header-text">Добавить игроков</span>
          </v-expansion-panel-header>
          <v-expansion-panel-content>
            <v-row v-for="(player, index) in footballPlayers" :key="index" class="player-card mb-4">
              <v-col cols="12" class="card-header">
                <span class="subtitle-1">Игрок {{ index + 1 }}</span>
                <v-btn icon @click="removeFootballPlayer(index)" color="error">
                  <v-icon>mdi-close</v-icon>
                </v-btn>
              </v-col>
              <v-col cols="12">
                <v-text-field
                    v-model="player.email"
                    outlined
                    label="Email"
                ></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field
                    v-model="player.teamPlace"
                    outlined
                    label="Место команды"
                ></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field
                    v-model="player.goals"
                    outlined
                    label="Количество голов"
                ></v-text-field>
              </v-col>
            </v-row>
            <v-col cols="12" class="d-flex justify-center mb-4">
              <v-btn @click="addFootballPlayer" color="success" outlined>
                Добавить игрока
              </v-btn>
            </v-col>
          </v-expansion-panel-content>
        </v-expansion-panel>

        <v-expansion-panel v-if="sport === 'Легкая атлетика'">
          <v-expansion-panel-header>
            <span class="header-text">Добавить участников</span>
          </v-expansion-panel-header>
          <v-expansion-panel-content>
            <v-row v-for="(participant, index) in athleticsParticipants" :key="index" class="player-card mb-4">
              <v-col cols="12" class="card-header">
                <span class="subtitle-1">Участник {{ index + 1 }}</span>
                <v-btn icon @click="removeAthleticsParticipant(index)" color="error">
                  <v-icon>mdi-close</v-icon>
                </v-btn>
              </v-col>
              <v-col cols="12">
                <v-text-field
                    v-model="participant.email"
                    outlined
                    label="Email"
                ></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field
                    v-model="participant.position"
                    outlined
                    label="Занятое место"
                ></v-text-field>
              </v-col>
            </v-row>
            <v-col cols="12" class="d-flex justify-center mb-4">
              <v-btn @click="addAthleticsParticipant" color="success" outlined>
                Добавить участника
              </v-btn>
            </v-col>
          </v-expansion-panel-content>
        </v-expansion-panel>
      </v-expansion-panels>

      <v-col cols="12" class="d-flex justify-center mt-4">
        <v-btn @click="save" color="primary" outlined>
          Создать
        </v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { mapActions } from "vuex";

export default {
  props: ['messageAttr'],
  data() {
    return {
      text: '',
      id: '',
      sport: '',
      footballPlayers: [],
      athleticsParticipants: [],
    }
  },
  watch: {
    messageAttr(newVal) {
      this.text = newVal.text;
      this.id = newVal.id;
    }
  },
  methods: {
    ...mapActions(['updateMessageAction', 'addMessageAction']),
    resetPlayers() {
      this.footballPlayers = [];
      this.athleticsParticipants = [];
    },
    addFootballPlayer() {
      this.footballPlayers.push({ email: '', teamPlace: '', goals: '' });
    },
    removeFootballPlayer(index) {
      this.footballPlayers.splice(index, 1);
    },
    addAthleticsParticipant() {
      this.athleticsParticipants.push({ email: '', position: '' });
    },
    removeAthleticsParticipant(index) {
      this.athleticsParticipants.splice(index, 1);
    },
    async save() {
      const message = {
        id: this.id,
        text: this.text,
        sport: this.sport,
        footballPlayers: this.footballPlayers,
        athleticsParticipants: this.athleticsParticipants
      };

      if (this.id) {
        await this.updateMessageAction(message);
      } else {
        await this.addMessageAction(message);
      }

      this.text = '';
      this.id = '';
      this.sport = '';
      this.footballPlayers = [];
      this.athleticsParticipants = [];
    }
  }
}
</script>

<style scoped>
.mb-4 {
  margin-bottom: 16px !important;
}
.mt-4 {
  margin-top: 16px !important;
}
.header-text {
  font-weight: bold;
  font-size: 18px;
}
.subtitle-1 {
  font-weight: 500;
}
.player-card {
  border: 1px solid #e0e0e0;
  border-radius: 8px;
  padding: 16px;
  background-color: #fafafa;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: 1px solid #e0e0e0;
  padding-bottom: 8px;
  margin-bottom: 16px;
}
</style>