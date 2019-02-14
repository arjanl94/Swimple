<template>
  <div class="flex items-center">
    <svg
      class="stroke-current text-grey mr-6"
      xmlns="http://www.w3.org/2000/svg"
      width="24"
      height="24"
      viewBox="0 0 24 24"
      fill="none"
      stroke="currentColor"
      stroke-width="2"
      stroke-linecap="round"
      stroke-linejoin="round"
    >
      <path d="M18 8A6 6 0 0 0 6 8c0 7-3 9-3 9h18s-3-2-3-9"></path>
      <path d="M13.73 21a2 2 0 0 1-3.46 0"></path>
    </svg>
    <div class="relative perspective">
      <button v-click-outside="close" @click.prevent="isOpen = !isOpen" class="flex items-center justify-center w-8 h-8 bg-orange rounded-full">
        <span class="text-white block font-semibold uppercase">{{ user.name.charAt(0) }}</span>
      </button>
      <transition name="open">
        <div v-if="isOpen" class="absolute bg-black rounded text-white pin-r mt-2 w-32 px-2 py-3 shadow-lg">
          <ul class="list-reset">
            <li>
              <router-link to="/users" class="block no-underline text-white py-2 px-4 hover:bg-grey-darkest rounded">My Profile</router-link>
            </li>
            <li>
              <router-link to="/" class="block no-underline text-white py-2 px-4 hover:bg-grey-darkest rounded">Settings</router-link>
            </li>
            <li>
              <router-link to="/login" class="block no-underline text-white py-2 px-4 hover:bg-grey-darkest rounded">Log out</router-link>
            </li>
          </ul>
        </div>
      </transition>
    </div>
  </div>
</template>
<script>
    import { mapState } from "vuex";
    import ClickOutside from "vue-click-outside";

    export default {
        name: 'current-user',

        data() {
            return {
                isOpen: false,
            }
        },

        computed: {
            ...mapState({
                user: state => state.auth.user
            })
        },

        directives: {
            ClickOutside
        },

        methods: {
            close() {
                if (this.isOpen) {
                    this.isOpen = false;
                }
            }
        }
    }
</script>

<style>
  .perspective {
    perspective: 1000px;
  }

  .open-enter-active, .open-leave-active {
    transition: all .2s ease-in-out;
    transform-origin: top center;
  }
  .open-enter, .open-leave-to /* .fade-leave-active below version 2.1.8 */ {
    opacity: 0;
    transform: rotateX(-30deg);
  }
</style>