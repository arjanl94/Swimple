<template>
  <div class="flex -mx-2 mb-4">
    <div class="w-1/2 px-2">
      <label class="mb-2 block">Date</label>
      <datepicker
        id="date"
        input-class="bg-grey-lightest p-4 rounded w-full"
        v-model="date"
        calendar-class="calendar-picker"
        @selected="didChange"
      ></datepicker>
    </div>
    <div class="w-1/4 px-2">
      <label class="mb-2 block">Start</label>
      <input type="time" class="block w-full bg-grey-lightest p-4 rounded" v-model="startTime" v-on:change="didChange">
    </div>
    <div class="w-1/4 px-2">
      <label class="mb-2 block">End</label>
      <input type="time" class="block w-full bg-grey-lightest p-4 rounded" v-model="endTime" v-on:change="didChange">
    </div>
  </div>
</template>
<script>
    import Datepicker from "vuejs-datepicker/src/components/Datepicker"
    import moment from 'moment';

    export default {
        name: 'date-picker',

        components: {Datepicker},

        data() {
            return {
              date: null,
              startTime: null,
              endTime: null
            }
        },

        props: {
            training: Object,
            onChange: Function,
        },

        methods: {
            didChange() {
                const startTime = moment(moment(this.date).format('Y-M-D') + " " + this.startTime).format();
                const endTime = moment(moment(this.date).format('Y-M-D') + " " + this.endTime).format();

                this.onChange({startTime, endTime});
            }
        },

        created() {
            const startMoment = moment(this.training.startDate.replace("[UTC]", ""));
            const endMoment = moment(this.training.endDate.replace("[UTC]", ""));

            this.startTime = startMoment.format('H:mm');
            this.endTime = endMoment.format('H:mm');

            this.date = startMoment.format('Y-M-D');
        }
    }
</script>
