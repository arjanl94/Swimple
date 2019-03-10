<template>
  <div v-if="training" class="flex flex-col md:flex-row -mx-4">
    <div class="w-full md:w-3/4 px-4 mb-8 md:mb-0">
      <div class="mb-8 flex justify-between items-center">
        <div>
          <h1 class="mb-2">{{ training.description }}</h1>
          <span
            class="text-base text-grey-darker"
          >{{ startDate.format('ddd D MMMM')}} &middot; {{ startDate.format('HH:mm') }} – {{ endDate.format('HH:mm') }} &middot; {{ training.location }}</span>
        </div>
        <router-link
          :to="{ name: 'trainings#edit', params: { id: $route.params.id } }"
          class="block no-underline px-4 py-2 text-grey border-grey-light hover:border-blue hover:bg-blue hover:text-white border-2 rounded"
        >Edit</router-link>
      </div>
      <div class="markdown bg-white rounded border border-grey-lighter p-6 text-lg leading-normal">
        <div v-if="!training.workout" class="text-center text-grey my-16">No workout</div>
        <div v-html="markdown" class="markdown"></div>
      </div>
    </div>
    <div class="w-full md:w-1/4 px-4">
      <h2 class="text-xl mb-4">Comments</h2>
      <div class="flex flex-col justify-center items-center py-32">
        <svg
          class="w-16 h-16 stroke-current text-grey-light mb-4"
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
          <circle cx="12" cy="12" r="10"></circle>
          <path d="M16 16s-1.5-2-4-2-4 2-4 2"></path>
          <line x1="9" y1="9" x2="9.01" y2="9"></line>
          <line x1="15" y1="9" x2="15.01" y2="9"></line>
        </svg>
        <p class="text-grey text-center">There are no comments yet...</p>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import moment from "moment";
import marked from "marked";

export default {
  name: "trainings-show",
  computed: {
    ...mapState({
      training: state => state.trainings.entity
    }),
    startDate: function() {
      if (!this.training) return;
      return moment(this.training.startDate);
    },
    endDate: function() {
      if (!this.training) return;
      return moment(this.training.endDate);
    },
    markdown() {
      if (!this.training.workout) return;
      return marked(this.training.workout, { sanitize: true });
    }
  },
  created() {
    this.$store.dispatch("trainings/getTraining", this.$route.params.id);
  }
};
</script>

<style>
  .markdown p {
    margin-bottom: 1rem;
  }

  .markdown li p {
    margin-bottom: 0;
  }

  .markdown ul {
    margin-bottom: 1rem;
  }

  .markdown h2 {
    margin-bottom: 1.25rem;
  }
</style>
