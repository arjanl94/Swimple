<template>
  <div class="flex flex-col -mx-4 md:flex-row">
    <div class="md:w-3/4 px-4 mb-4">
      <h1 class="text-xl mb-4">Upcoming events</h1>
      <training-list />
    </div>
    <div class="md:w-1/4 px-4">
      <h1 class="text-xl mb-4">Calendar</h1>
      <div class="bg-white rounded border-grey-lighter border text-center">
        <div class="p-6">
        <div class="flex justify-between text-grey mb-2">
          <span class="w-1/7">M</span>
          <span class="w-1/7">T</span>
          <span class="w-1/7">W</span>
          <span class="w-1/7">T</span>
          <span class="w-1/7">F</span>
          <span class="w-1/7">S</span>
          <span class="w-1/7">S</span>
        </div>
        <div class="flex justify-between h-8 items-center">
          <span class="w-1/7 text-grey">28</span>
          <span class="w-1/7 text-grey">29</span>
          <span class="w-1/7 text-grey">30</span>
          <span class="w-1/7 text-grey">31</span>
          <span class="w-1/7">1</span>
          <span class="w-1/7">2</span>
          <span class="w-1/7">3</span>
        </div>
        <div class="flex justify-between items-center mb-2">
          <span class="w-1/7">4</span>
          <span class="w-1/7">5</span>
          <span class="w-1/7">6</span>
          <span class="w-1/7">7</span>
          <span class="w-1/7">8</span>
          <span class="w-1/7">9</span>
          <span class="w-1/7">10</span>
        </div>
        <div class="flex justify-between h-8 items-center">
          <span class="p-1 w-1/7 bg-blue rounded-full text-white">11</span>
          <span class="w-1/7">12</span>
          <span class="w-1/7">13</span>
          <span class="w-1/7">14</span>
          <span class="w-1/7">15</span>
          <span class="w-1/7">16</span>
          <span class="w-1/7">17</span>
        </div>
        <div class="flex justify-between h-8 items-center">
          <span class="w-1/7">18</span>
          <span class="w-1/7">19</span>
          <span class="w-1/7">20</span>
          <span class="w-1/7">21</span>
          <span class="w-1/7">22</span>
          <span class="w-1/7">23</span>
          <span class="w-1/7">24</span>
        </div>
        <div class="flex justify-between h-8 items-center">
          <span class="w-1/7">25</span>
          <span class="w-1/7">26</span>
          <span class="w-1/7">27</span>
          <span class="w-1/7">28</span>
          <span class="w-1/7 text-grey">1</span>
          <span class="w-1/7 text-grey">2</span>
          <span class="w-1/7 text-grey">3</span>
        </div>
        </div>
        <router-link to="/calendar" class="block text-center py-4 no-underline uppercase text-sm text-grey hover:text-blue tracking-wide border-grey-lighter border-t">Full Calendar</router-link>
      </div>
    </div>
  </div>
</template>

<script>
    import TrainingList from "../components/TrainingList";

    export default {
        name: "dashboard",
        components: { TrainingList }
    }
</script>