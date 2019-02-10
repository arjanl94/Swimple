<template>
  <div id="app">
    <navigation-bar />
    <div class="container mx-auto px-4">
      <router-view />
    </div>
    <div class="h-16"></div>
  </div>
</template>

<script>
  import NavigationBar from  './components/NavigationBar';

export default {
  name: 'app',
    components: { NavigationBar }
}
</script>
