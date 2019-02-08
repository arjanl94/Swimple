<template>
  <div id="app">
    <navigation-bar />
    <div class="container mx-auto px-4">
      <dashboard />
    </div>
  </div>
</template>

<script>
  import NavigationBar from  './components/NavigationBar';
import TrainingList from './components/TrainingList.vue';
  import Dashboard from "./components/Dashboard";

export default {
  name: 'app',
    components: {Dashboard, TrainingList, NavigationBar }
}
</script>
