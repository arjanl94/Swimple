<template>
  <button @click="select(group)"
          :class="`${isSelected(group) ? 'bg-blue text-white' : 'bg-grey-lighter text-grey-dark'} rounded-full overflow-hidden px-4 py-2 flex items-center mr-2`">
    <span>{{ group ? group.name : 'Everyone' }}</span>
  </button>
</template>
<script>
    import {mapState} from "vuex";

    export default {
        name: 'group-list-item',

        props: {
            group: Object
        },

        computed: {
            ...mapState({
                selected: state => state.groups.selected
            })
        },

        methods: {
            select(group) {
                this.$store.dispatch('groups/select', group);
            },

            isSelected(group) {
                return group === this.selected;
            }
        },
    }
</script>