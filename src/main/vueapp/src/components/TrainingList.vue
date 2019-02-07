<template>
  <div>
    <ul>
      <li v-for="(training, index) in trainings" v-bind:key="index">
        {{ training.description }}
      </li>
    </ul>
  </div>

</template>

<script>
  import { mapState } from 'vuex';

  export default {
    name: "TrainingList",

    computed: mapState([
        'title',
        'trainings'
    ]),

      created() {
        this.$store.dispatch('getAllTrainings');
      }
  }
</script>