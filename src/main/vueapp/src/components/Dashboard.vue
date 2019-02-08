<template>
  <div class="flex flex-col -mx-4 md:flex-row">
    <div class="md:w-3/4 px-4 mb-4">
      <h1 class="text-xl mb-4">Upcoming events</h1>
      <training-list />
    </div>
    <div class="md:w-1/4 px-4">
      <h1 class="text-xl mb-4">Calendar</h1>
      <div class="bg-white rounded border-grey-lighter border p-6"></div>
    </div>
  </div>
</template>

<script>
    import TrainingList from "./TrainingList";
    export default {
        name: "dashboard",
        components: { TrainingList }
    }
</script>