<template>
  <div v-if="loaded" class="flex mb-6">
    <group-list-item :group="null" />
    <group-list-item v-for="group in groups" :group="group" v-bind:key="group.id"></group-list-item>
  </div>
</template>
<script>
    import {mapState} from "vuex";
    import GroupListItem from "./GroupListItem";

    export default {
        name: 'group-list',
        components: {GroupListItem},
        computed: {
            ...mapState({
                users: state => state.users.all,
                groups: state => state.groups.all,
                loading: state => state.groups.status.loading,
                loaded: state => state.groups.status.loaded,
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

        created() {
            this.$store.dispatch('groups/getAllGroups');
            this.$store.dispatch('groups/select', null);
        }
    }
</script>