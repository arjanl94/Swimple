<template>
  <div>
    <div class="bg-white rounded border border-grey-lighter">
      <training-list-item
        v-for="training in trainings"
        v-bind:key="training.id"
        v-bind:training="training"
      />
      <router-link
        to="/calendar"
        class="block text-center py-4 no-underline uppercase text-sm text-grey hover:text-blue tracking-wide"
      >See all upcoming events</router-link>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import TrainingListItem from "./TrainingListItem";

export default {
  name: "training-list",
  components: { TrainingListItem },
  computed: {
    ...mapState({
      trainings: state => state.trainings.all
    })
  },

  created() {
    this.$store.dispatch("trainings/getAllTrainings");
  }
};
</script>
