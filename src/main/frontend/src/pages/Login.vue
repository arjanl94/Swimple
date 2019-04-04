<template>
  <div class="max-w-sm mx-auto">
    <div class="bg-white rounded-lg p-6 border-grey-lighter border mb-8">
      <h1 class="text-center mb-8">Login</h1>
      <button @click="didPressGoogleLogin" type="button" class="w-full block bg-blue rounded p-4 no-underline text-white text-center">Sign in with Google</button>
      <div class="flex items-center my-8">
        <div class="bg-grey-light h-px w-full"></div>
        <span class="inline-block px-4 text-grey-dark">or</span>
        <div class="bg-grey-light h-px w-full"></div>
      </div>
      <form @submit.prevent="handleSubmit">
        <div class="mb-4">
          <label class="block mb-2">Email</label>
          <input type="text" placeholder="Email" class="bg-grey-lightest p-4 block rounded w-full" v-model="email">
        </div>
        <div class="mb-8">
          <label class="block mb-2">Password</label>
          <input type="password" placeholder="Password" class="bg-grey-lightest p-4 block rounded w-full" v-model="password">
        </div>
        <button :disabled="loggingIn" class="w-full bg:transparent hover:bg-blue border-2 border-grey-lighter p-4 rounded text-grey-dark hover:text-white hover:border-blue">Log in</button>
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
            },

            didPressGoogleLogin() {
              this.$store.dispatch('auth/signInGoogle');
            }
        }
    }
</script>
