<template>
  <div id="app">
    <notices></notices>
    <navigation-bar />
    <div class="container mx-auto px-4">
      <router-view />
    </div>
    <div class="h-16"></div>
  </div>
</template>

<script>
  import NavigationBar from  './components/NavigationBar';
  import Notices from "./components/Notices";

export default {
  name: 'app',
    components: { Notices, NavigationBar }
}
</script>
