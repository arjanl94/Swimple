<template>
  <div id="app">
    <div class="bg-blue text-white h-64 flex justify-center items-center flex-col mb-4">
      <h1>Hello Swimple</h1>
      <p>This app is built with vue.js</p>
    </div>
    <TrainingList />
  </div>
</template>

<script>
import TrainingList from './components/TrainingList.vue';

export default {
  name: 'app',

    components: { TrainingList }
}
</script>
