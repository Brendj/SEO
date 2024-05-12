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
          <v-list-item>
            <v-list-item-content>
              <v-list-item-title>Активность</v-list-item-title>
              <v-list-item-subtitle>{{ profile.activity ? 'Активен' : 'Неактивен' }}</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
        </v-list-item-group>
      </v-card-text>
    </v-card>
  </v-container>
</template>

<script>
import { mapState } from 'vuex'

export default {
  name: 'Profile',
  computed: mapState(['profile']),
  methods: {

  }
}
</script>

<style scoped>
/* Здесь можно добавить свои стили */
</style>
