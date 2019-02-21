<template>
  <div class="max-w-lg mx-auto bg-white border-grey-lighter border rounded">
    <form @submit.prevent="submit">
    <div class="p-6 border-b border-grey-lighter">
        <input type="text" placeholder="Group Name" class="block bg-grey-lightest p-4 rounded w-full" v-model="name"/>
    </div>
    <div class="pl-6 h-64 overflow-y-scroll">
      <button type="button" @click="select(user)" v-for="user in users" v-bind:key="user.id" class="w-full py-4 border-b border-grey-lighter flex items-center">
        <div :class="`${isSelected(user) ? 'bg-green shadow' : 'bg-grey-lighter'} w-6 h-6 rounded-full mr-4 flex items-center justify-center`">
          <svg v-if="isSelected(user)" xmlns="http://www.w3.org/2000/svg" class="h-4 w-4 text-white mt-px" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><polyline points="20 6 9 17 4 12"></polyline></svg>
        </div>
        <span>{{ user.name }}</span>
      </button>
    </div>
    <div class="px-6 py-4 border-grey-lighter border-t flex flex-row-reverse items-center">
      <button
        type="submit"
        class="px-4 py-2 bg-blue hover:bg-blue-dark rounded text-white"
      >Save</button>
      <router-link
        :to="{ name: 'users#index' }"
        class="inline-block no-underline px-4 py-2 text-grey hover:bg-grey-light hover:text-grey-dark bg-grey-lighter rounded mr-2"
      >Cancel</router-link>
      <span v-if="selected.length > 0" class="flex-1 text-grey-dark">{{ selected.length }} {{ selected.length > 1 ? 'users' : 'user' }} selected</span>
    </div>
    </form>
  </div>
</template>

<script>
import {mapState} from "vuex";

export default {
    name: "groups-new",

    computed: {
        ...mapState({
            users: state => state.users.all
        })
    },

    data() {
        return {
          name: null,
          selected: []
        }
    },

    methods: {
        select(user) {
            if (this.isSelected(user)) {
                this.selected = this.selected.filter(u => {
                    return u.id != user.id;
                });

                return;
            }

            this.selected.push(user);
        },

        isSelected(user) {
            return this.selected.find(u => {
                return u.id == user.id;
            });
        },

        submit() {
          this.$store.dispatch('groups/createGroup', {
              name: this.name,
              users: this.selected
          });
        }
    },

    created() {
        this.$store.dispatch('users/getAllUsers');
    }
}
</script>

<style scoped>

</style>