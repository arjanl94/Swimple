<template>
  <div class="flex justify-between">
    <h1 class="text-xl">Calendar</h1>
    <router-link :to="{ name: 'trainings#new' }" class="bg-blue text-white inline-block px-4 py-2 rounded no-underline">Create training</router-link>
  </div>
</template>

<script>
    export default {
        name: "calendar"
    }
</script>
