<template>
  <div>
    <group-list/>
    <transition-group v-if="loaded" name="list" tag="div" class="-mx-2 flex flex-wrap flex-col sm:flex-row">
      <div v-for="user in users" v-bind:key="user.id" class="px-2 mb-2 sm:mb-4 sm:w-1/2 md:w-1/3 lg:w-1/4">
        <button @click="" class="w-full text-left flex bg-white rounded p-6 border-grey-lighter border">
          <div class="bg-orange w-12 h-12 text-xl font-semibold mr-4 flex items-center justify-center text-white rounded-full">
            {{ user.name.charAt(0) }}
          </div>
          <div>
            <h2 class="text-lg mb-1">{{ user.name }}</h2>
            <span class="text-grey-dark">Senioren</span>
          </div>
        </button>
      </div>
    </transition-group>
  </div>
</template>

<script>
    import {mapGetters, mapState} from "vuex";
    import GroupList from "../components/GroupList";

    export default {
        name: "users",
        components: {GroupList},
        computed: {
            ...mapState({
                loading: state => state.users.status.loading,
                loaded: state => state.users.status.loaded,
                // users: state => state.users.all
            }),
            ...mapGetters({users: 'users/getUsers'})
        },

        created() {
            this.$store.dispatch('users/getAllUsers');
        }
    }
</script>

<style>
  .list-enter-active, .list-leave-active {
    transition: all .3s;
  }
  .list-enter, .list-leave-to /* .list-leave-active below version 2.1.8 */ {
    opacity: 0;
    transform: translateY(10px);
  }

  .flip-list-move {
    transition: transform 1s;
  }
</style>