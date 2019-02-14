<template>
  <div v-if="training" class="max-w-lg mx-auto bg-white rounded border border-grey-lighter">
    <form action="#">
      <div class="p-6">
        <div>
          <label for="description" class="mb-2 block">Description</label>
          <input
            id="description"
            type="text"
            placeholder="Description"
            class="block bg-grey-lightest p-4 rounded w-full mb-4"
            v-model="training.description"
            autocomplete="off"
          >
        </div>
        <date-picker :training="training" :on-change="datepickerDidChange"/>
        <div class="flex -mx-2">
          <div class="w-1/2 px-2">
            <label class="mb-2 block">Location</label>
            <input
              type="text"
              v-model="training.location"
              class="block w-full bg-grey-lightest p-4 rounded"
              autocomplete="off"
            >
          </div>
          <div class="w-1/2 px-2">
            <label class="mb-2 block">Coaches</label>
            <input
              type="text"
              class="block w-full bg-grey-lightest p-4 rounded"
              placeholder="Coaches"
              autocomplete="off"
            >
          </div>
        </div>
      </div>
      <div>
        <textarea
          class="block w-full p-6 font-mono border-grey-lighter border-t"
          v-model="training.workout"
          data-autosize
        ></textarea>
      </div>
      <div class="p-2 border-grey-lighter border-t flex flex-row-reverse">
        <button
          type="submit"
          @click.prevent="didPressSave"
          class="px-4 py-2 bg-blue hover:bg-blue-dark rounded text-white"
        >Save</button>
        <router-link
          :to="{name: 'trainings#show', params: { id: this.$route.params.id }}"
          class="inline-block no-underline px-4 py-2 text-grey hover:bg-grey-light hover:text-grey-dark bg-grey-lighter rounded mr-2"
        >Cancel</router-link>
      </div>
    </form>
  </div>
</template>

<script>
    import autosize from "autosize";
    import DatePicker from "../components/DatePicker";

    export default {
  name: "trainings-edit",
  components: {DatePicker},
  data: function() {
    return {
      training: null,
    };
  },

  methods: {
    didPressSave() {
      this.$store.dispatch("trainings/updateTraining", this.training);
    },

    setTraining() {
      this.training = JSON.parse(
        JSON.stringify(this.$store.state.trainings.entity)
      );
    },

      datepickerDidChange(values) {
        this.training.startDate = values.startTime;
        this.training.endDate = values.endTime;
      }
  },

  async created() {
    if (!this.$store.state.trainings.entity) {
      await this.$store.dispatch(
        "trainings/getTraining",
        this.$route.params.id
      );
    }

    this.setTraining();
  },

  mounted() {
    autosize(document.querySelectorAll('[data-autosize]'));
  }
};
</script>

<style>
.calendar-picker {
  border: none;
  @apply shadow-lg;
  @apply rounded-lg;
  @apply p-4;
  @apply mt-2;
}

.calendar-picker .cell {
  @apply rounded;
  @apply mb-1;
}

.calendar-picker .cell.selected {
  @apply bg-blue;
  @apply text-white;
}

.calendar-picker .cell.selected:hover {
  @apply bg-blue;
  @apply text-white;
}

.calendar-picker .cell:not(.blank):not(.disabled).day:hover {
  border-color: transparent;
  @apply bg-grey-lighter;
}
</style>
