<template>
  <div>
    <div class="bg-white rounded border border-grey-lighter">
      <div v-for="training in trainings" v-bind:key="training.id" class="p-6 flex border-grey-lighter border-b">
        <div class="mr-6 pr-6 border-grey-lighter border-r flex flex-col items-center">
          <div class="uppercase text-sm text-grey font-semibold tracking-wide">Wed</div>
          <div class="text-3xl">26</div>
        </div>
        <div>
          <h2 class="text-lg mb-1">{{ training.description }}</h2>
          <span class="text-sm text-grey-darker">Wed 26 Apr &middot; 20:00 – 21:00 &middot; Zwembad Olympia</span>
        </div>
      </div>
      <a href="#" class="block text-center py-4 no-underline uppercase text-sm text-grey hover:text-blue tracking-wide">See all upcoming events</a>
    </div>
  </div>
</template>

<script>
  import { mapState } from 'vuex';

  export default {
    name: "training-list",

    computed: mapState({
        trainings: state => state.trainings.all
    }),

      created() {
        this.$store.dispatch('trainings/getAllTrainings');
      }
  }
</script>