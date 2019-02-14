<template>
  <div class="max-w-sm mx-auto">
    <div class="bg-white rounded-lg p-6 border-grey-lighter border mb-8" @submit.prevent="handleSubmit">
      <h1 class="text-center mb-8">Login</h1>
      <form>
        <div class="mb-4">
          <label class="block mb-2">Email</label>
          <input type="text" placeholder="Email" class="bg-grey-lightest p-4 block rounded w-full" v-model="email">
        </div>
        <div class="mb-8">
          <label class="block mb-2">Password</label>
          <input type="password" placeholder="Password" class="bg-grey-lightest p-4 block rounded w-full" v-model="password">
        </div>
        <button :disabled="loggingIn" class="bg-blue hover:bg-blue-dark w-full p-4 text-white rounded">Log in</button>
      </form>
    </div>
    <router-link to="/register" class="block no-underline text-center bg-grey-lighter p-6 rounded-lg text-black">
      <span>Don't have an account? </span>
      <span class="text-blue">Sign up</span>
    </router-link>
  </div>
</template>

<script>
    export default {
        name: "login",

        data() {
            return {
                email: '',
                password: '',
                submitted: false,
            };
        },

        computed: {
            loggingIn () {
                return this.$store.state.auth.status.loggingIn;
            }
        },

        created() {
            this.$store.dispatch('auth/logout');
        },

        methods: {
            handleSubmit(e) {
                this.submitted = true;
                const { email, password } = this;
                const { dispatch } = this.$store;
                console.log({email, password});

                if (email && password) {
                    dispatch('auth/login', { email, password });
                }
            }
        }
    }
</script>
