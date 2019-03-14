<template>
  <div class="flex mb-6">
    <div
      class="bg-orange w-6 h-6 flex justify-center items-center rounded-full text-white text-sm mr-3 mt-1 font-semibold flex-no-shrink">
      {{ comment.user.name.charAt(0) }}
    </div>
    <div>
      <p class="leading-normal mb-2">
        <strong>{{ comment.user.name }}</strong> {{comment.body}}
      </p>
      <span class="text-grey">9m ago</span>
    </div>
  </div>
</template>
<script>
    export default {
        name: 'comment-list-item',
        props: {
            comment: {
                type: Object,
                required: true
            }
        }
    }
</script>
