<template>
  <v-container>
    <v-card>
      <v-card-title>
        <h1>Профиль пользователя</h1>
      </v-card-title>
      <v-card-text>
        <v-list-item-group>
          <v-list-item>
            <v-list-item-content>
              <v-list-item-title>Имя</v-list-item-title>
              <v-list-item-subtitle>{{ profile.firstName }}</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
          <v-list-item>
            <v-list-item-content>
              <v-list-item-title>Фамилия</v-list-item-title>
              <v-list-item-subtitle>{{ profile.lastName }}</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
          <v-list-item>
            <v-list-item-content>
              <v-list-item-title>Email</v-list-item-title>
              <v-list-item-subtitle>{{ profile.email }}</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
          <v-list-item>
            <v-list-item-content>
              <v-list-item-title>Роль</v-list-item-title>
              <v-list-item-subtitle>{{ profile.role }}</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
          <template v-if="profile.activity === false || profile.role === 'ADMIN'">
            <v-layout column class="d-flex align-center flex-wrap">
              <v-container class="col-4">
                <v-select
                    label="Вид спорта"
                    :items="['Футбол', 'Легкая атлетика']"
                    variant="outlined"
                    v-model="sport"
                    @change="sport = $event"
                ></v-select>
              </v-container>
              <v-container class="col-4" v-if="sport === 'Футбол'">
                <v-container class="col-4">
                  <v-select
                      label="Позиция"
                      :items="['Вратарь', 'Защитник', 'Полузащитник', 'Нападающий']"
                      variant="outlined"
                      v-model="position"
                      @change="position = $event"
                  ></v-select>
                </v-container>
              </v-container>
              <v-container class="col-4">
                <v-text-field
                    label="Вес"
                    variant="outlined"
                    v-model="weight"
                ></v-text-field>
              </v-container>
              <v-container class="col-4">
                <v-text-field
                    label="Рост"
                    variant="outlined"
                    v-model="height"
                ></v-text-field>
              </v-container>
              <v-container class="col-4">
                <v-select
                    label="Разряд"
                    :items="['1', '2', '3', '4', '5', '6']"
                    variant="outlined"
                    v-model="discharge"
                    @change="discharge = $event"
                ></v-select>
              </v-container>
              <v-container class="col-4">
                <v-text-field
                    label="Возраст"
                    variant="outlined"
                    v-model="age"
                ></v-text-field>
              </v-container>
              <v-container class="col-4">
                <v-text-field
                    label="Колличество соревнований"
                    variant="outlined"
                    v-model="numberOfCompetitions"
                ></v-text-field>
              </v-container>
              <v-container class="col-4">
                <v-text-field
                    label="Текущее место"
                    variant="outlined"
                    v-model="currentLocation"
                ></v-text-field>
              </v-container>

              <v-btn class="ma-3" @click="save">
                Сохранить
              </v-btn>
            </v-layout>
          </template>
          <template v-else>
            <v-list-item>
              <v-list-item-content>
                <v-list-item-title>Коэффициент статистики</v-list-item-title>
                <v-slider
                    v-model="profile.statistic"
                    :color="getColor(profile.statistic)"
                    thumb-label
                    :max="1"
                    :step="0.01"
                    readonly
                >

                </v-slider>
              </v-list-item-content>
            </v-list-item>
            <v-list-item>
              <v-list-item-content>
                <v-list-item-title>Вид спорта</v-list-item-title>
                <v-list-item-subtitle>{{ profile.sportType }}</v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
            <v-list-item>
              <v-list-item-content>
                <v-list-item-title>Вес</v-list-item-title>
                <v-list-item-subtitle>{{ profile.weight }}</v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
            <v-list-item>
              <v-list-item-content>
                <v-list-item-title>Рост</v-list-item-title>
                <v-list-item-subtitle>{{ profile.height }}</v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
            <v-list-item>
              <v-list-item-content>
                <v-list-item-title>Разряд</v-list-item-title>
                <v-list-item-subtitle>{{ profile.discharge }}</v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
            <v-list-item>
              <v-list-item-content>
                <v-list-item-title>Возраст</v-list-item-title>
                <v-list-item-subtitle>{{ profile.age }}</v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
            <v-list-item>
              <v-list-item-content>
                <v-list-item-title>Текущее место</v-list-item-title>
                <v-list-item-subtitle>{{ profile.currentLocation }}</v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
            <v-list-item>
              <v-list-item-content>
                <v-list-item-title>Предыдущее место</v-list-item-title>
                <v-list-item-subtitle>{{ profile.previousLocation }}</v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
            <v-list-item>
              <v-list-item-content>
                <v-list-item-title>Позиция</v-list-item-title>
                <v-list-item-subtitle>{{ profile.position }}</v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
            <v-list-item>
              <v-list-item-content>
                <v-list-item-title>Количество соревнований</v-list-item-title>
                <v-list-item-subtitle>{{ profile.numberOfCompetitions }}</v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
          </template>
        </v-list-item-group>
      </v-card-text>
    </v-card>
  </v-container>
</template>

<script>
import { mapState } from 'vuex'

export default {
  name: 'Profile',
  data: () => ({
    firstName: null,
    lastName: null,
    email: null,
    role: null,
    sport: null,
    position: null,
    weight: null,
    height: null,
    discharge: null,
    age: null,
    numberOfCompetitions: null,
    currentLocation: null
  }),
  computed: mapState(['profile']),
  methods: {
    getColor(value) {
      if (value < 0.33) {
        return 'red';
      } else if (value < 0.66) {
        return 'yellow';
      } else {
        return 'green';
      }
    },
    async save() {
      const userData = {
        sportType: this.sport,
        position: this.position,
        weight: this.weight,
        height: this.height,
        discharge: this.discharge,
        age: this.age,
        numberOfCompetitions: this.numberOfCompetitions,
        currentLocation: this.currentLocation,
      };

      console.log(userData)

      const response = await fetch(`/user/${this.profile.id}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(userData),
      });

      if (response.ok) {
        alert('Данные успешно обновлены');
      } else {
        alert('Произошла ошибка при обновлении данных');
      }
    },
  }

}
</script>

<style>

</style>
