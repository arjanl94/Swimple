<template>
  <div class="max-w-sm mx-auto">
    <div class="bg-white rounded p-6 border-grey-lighter border mb-8" @submit.prevent="handleSubmit">
      <h1 class="text-center mb-8">Create your account</h1>
      <form>
        <div class="mb-4">
          <label class="block mb-2">Name</label>
          <input type="text" placeholder="Name" class="bg-grey-lightest p-4 block rounded w-full" v-model="name">
        </div>
        <div class="mb-4">
          <label class="block mb-2">Email</label>
          <input type="text" placeholder="Email" class="bg-grey-lightest p-4 block rounded w-full" v-model="email">
        </div>
        <div class="mb-8">
          <label class="block mb-2">Password</label>
          <input type="password" placeholder="Password" class="bg-grey-lightest p-4 block rounded w-full" v-model="password">
        </div>
        <button :disabled="signingUp" class="bg-blue hover:bg-blue-dark w-full p-4 text-white rounded">Create your account</button>
      </form>
    </div>
    <router-link to="/login" class="block no-underline text-center bg-grey-lighter p-6 rounded-lg text-black">
      <span>Already have an account? </span>
      <span class="text-blue">Log in</span>
    </router-link>
  </div>
</template>

<script>
    export default {
        name: "register",

        data() {
            return {
                name: '',
                email: '',
                password: '',
                submitted: false,
            };
        },

        computed: {
            signingUp () {
                return this.$store.state.auth.status.siginigUp;
            }
        },

        // created() {
        //     this.$store.dispatch('auth/logout');
        // },
        //
        methods: {
            handleSubmit(e) {
                this.submitted = true;
                const { name, email, password } = this;
                const { dispatch } = this.$store;

                if (name && email && password) {
                    dispatch('auth/register', { name, email, password });
                }
            }
        }
    }
</script>
