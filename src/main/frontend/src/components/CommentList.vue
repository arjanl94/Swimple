<template>
  <div>
    <form class="flex items-center mb-4" @submit.prevent="submitComment" v-on:keyup="submitComment">
      <img src="https://avatars3.githubusercontent.com/u/1022998?s=80&v=4" class="w-8 rounded-full mr-3" />
      <input v-model="body" class="w-full p-2 rounded border border-grey-lighter" type="text" placeholder="Leave a comment...">
    </form>
    <div v-if="status.loaded">
      <comment-list-item v-for="comment in comments" v-bind:key="comment.id" :comment="comment"/>
    </div>
  </div>
  
</template>

<script>
    import {mapState} from "vuex";
    import CommentListItem from "./CommentListItem";

    let ws;

    export default {
        name: "comment-list",

        components: {CommentListItem},

        data() {
            return {
                body: null,
            }
        },

        props: {
          training: {
              type: Object,
              required: true
          }
        },

        computed: {
            ...mapState({
                comments: state => state.comments.all,
                status: state => state.comments.status
            })
        },

        methods: {
          submitComment(e) {
              if (e.key !== 'Enter') return;
              this.$store.dispatch("comments/createComment", { training: this.training, body: this.body });
              this.body = null;
          }
        },

        mounted() {
            this.$store.dispatch("comments/getAllComments", this.training);

            const wsURI = `ws://localhost:8080/swimple/ws-comments/${this.training.id}`
            ws = new WebSocket(wsURI);

            const that = this;
            const refresh = function() {
                that.$store.dispatch("comments/getAllComments", that.training);
            }

            ws.onopen = function() {
                console.log('Opened up connection');
            };

            ws.onmessage = function(e) {
                console.log('--MESSAGE', e.data);
                refresh();
            };
        },

        beforeDestroy() {
            ws.close();
        }
    }
</script>

