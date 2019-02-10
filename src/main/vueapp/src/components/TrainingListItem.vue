<template>
  <router-link :to="{ name: 'trainings#show', params: { id: training.id }}" class="no-underline text-black hover:text-grey-darker">
    <div class="p-6 flex border-grey-lighter border-b">
      <div class="mr-6 pr-6 border-grey-lighter border-r flex flex-col items-center w-16">
        <div class="uppercase text-sm text-grey font-semibold tracking-wide">{{ startDate.format('ddd') }}</div>
        <div class="text-3xl text-black">{{ startDate.format('D') }}</div>
      </div>
      <div>
        <h2 class="text-lg mb-1">{{ training.description }}</h2>
        <span class="text-sm text-grey-darker">
          {{ startDate.format('D MMMM') }} &middot; {{ startDate.format('HH:mm') }} - {{ endDate.format('HH:mm') }} &middot; {{ training.location }}
        </span>
      </div>
    </div>
  </router-link>
</template>

<script>
    import moment from 'moment';

    export default {
        name: "training-list-item",
        props: {
            training: Object
        },
        computed: {
            startDate: function() {
                if (!this.training) return;
                return moment(this.training.startDate.replace('[UTC]', ''));
            },
            endDate: function() {
                if (!this.training) return;
                return moment(this.training.endDate.replace('[UTC]', ''));
            }
        },
        filters: {
            moment: function(date) {
                return moment(date).format("MMMM Do YYYY, h:mm:ss a")
            }
        }
    }
</script>
