<template>
  <div class="fixed w-full z-50">
    <transition name="fade">
      <div
        v-for="(notice, index) in notices"
        v-bind:key="index"
        class="bg-blue block text-center p-4 text-white"
      >{{ notice }}</div>
    </transition>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "notices",
  computed: {
    ...mapState({
      notices: state => state.notices.all
    })
  }
};
</script>

<style>
.fade-enter-active,
.fade-leave-active {
  transition: transform 0.3s ease-in-out;
}
.fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
  transform: translateY(-100%);
}
</style>
