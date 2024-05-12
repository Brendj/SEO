<template>
  <v-layout class="rounded rounded-md">
    <v-app-bar color="teal-lighten-5" dark>
      <v-toolbar-title>
        <router-link to="/">
          <v-btn color="black">
            <span>Информационная система сбора и анализа данных</span>
          </v-btn>
        </router-link>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-toolbar-items class="hidden-sm-and-down">
        <v-btn href="/profile">
          <span v-if="profile">{{ profile.firstName + " " + profile.lastName }}</span>
        </v-btn>
        <v-btn v-if="profile" icon="mdi-export" href="/logout">
        </v-btn>
      </v-toolbar-items>
    </v-app-bar>

    <v-main class="pa-4 pt-10" style="min-height: 300px; background: #f5f5f5;">
      <RouterView />
    </v-main>
  </v-layout>
</template>

<script>
import { addHandler } from "../util/ws";
import { mapState, mapMutations } from 'vuex'
  export default {
    computed: mapState(['profile']),
    methods: mapMutations(['addMessageMutation', 'updateMessageMutation', 'removeMessageMutation']),
    created() {
      addHandler(data => {
        console.log(data.objectType, " ", data.eventType)
        if (data.objectType === 'MESSAGE') {
          switch (data.eventType) {
            case 'CREATE' :
              this.addMessageMutation(data.body)
              break
            case 'UPDATE' :
              this.updateMessageMutation(data.body)
              break
            case 'REMOVE' :
              this.removeMessageMutation(data.body)
              break
            default:
              console.error(`event type is unknown "${data.eventType}"`)
          }
        } else console.error(`object type is unknown "${data.objectType}"`)
      })
    }
  }
</script>

<style>

</style>