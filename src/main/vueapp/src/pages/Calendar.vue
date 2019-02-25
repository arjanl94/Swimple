<template>
  <div>
    <div class="flex justify-between mb-4">
      <h1 class="text-xl">Calendar</h1>
      <router-link
        :to="{ name: 'trainings#new' }"
        class="bg-blue text-white inline-block px-4 py-2 rounded no-underline"
        v-if="showNewButton">
        Create training
      </router-link>
    </div>
    <div>
      <full-calendar :events="events" class="max-w-full rounded border-grey-lighter border" @eventClick="eventClick"></full-calendar>
    </div>
  </div>
</template>

<script>
  import fullCalendar from 'vue-fullcalendar';
  import {userIsAdmin} from "../helpers";

    export default {
        name: "calendar",

        components: {
            fullCalendar
        },

        computed: {
            events() {
                return this.$store.state.trainings.all.map(training => {
                    return {
                        title: training.description,
                        start: training.startDate.replace('[UTC]', ''),
                        end: training.endDate.replace('[UTC]', ''),
                        id: training.id
                    };
                });
            },

            showNewButton() {
                return userIsAdmin(this.$store.state.auth.user);
            }

        },

        methods: {
          eventClick(event) {
              this.$router.push({ name: 'trainings#show', params: { id: event.id }});
          }
        },

        created() {
            this.$store.dispatch('trainings/getAllTrainings');
        }
    }
</script>
