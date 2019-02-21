<template>
  <div v-if="loaded" class="flex flex-no-wrap mb-6 overflow-x-scroll">
    <group-list-item :group="null" />
    <group-list-item v-for="group in groups" :group="group" v-bind:key="group.id"></group-list-item>
    <router-link :to="{ name: 'groups#new' }" class="flex items-center border rounded-full border-grey-light no-underline pl-3 pr-5 py-1 text-grey hover:bg-blue hover:border-blue hover:text-white">
      <svg xmlns="http://www.w3.org/2000/svg" class="w-4 h-4 mr-2 stroke-current" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><line x1="12" y1="5" x2="12" y2="19"></line><line x1="5" y1="12" x2="19" y2="12"></line></svg>
      <span>New Group</span>
    </router-link>
  </div>
</template>
<script>
    import { mapState } from "vuex";
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