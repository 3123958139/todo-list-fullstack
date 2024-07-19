
<script>
    import serverApi from '../services/server-api';
    export default {
        data() {
            return {
                username: '',
                password: '',
                rememberMe: false,
                errorMessage: null
            }
        },
        methods: {
            async login() {
                const loginResult = await serverApi.login(this.username, this.password, this.rememberMe);

                if (!loginResult) {
                    this.errorMessage = 'Invalid username or password';
                } else {
                    this.$router.push('/');
                }
            }
        }    
    }
</script>

<template>
    <form @submit.prevent="login">
        <h1>ToDo Login</h1>
        <p class="error" v-if="errorMessage">{{ errorMessage }}</p>

        <label> 
            <span>Username </span>
            <input type="username" id="username" v-model="username" required>
        </label>
        <label>
            <span>Password </span>
            <input type="password" id="password" v-model="password" required>
        </label>
        <label>
            <input type="checkbox" id="rememberMe" v-model="rememberMe">
            <span>Remember me</span>
        </label>
        <button type="submit">Login</button>
    </form>
</template>

<style scoped>
    form {
        width: 500px;
        margin: 50px auto 0 auto;
        padding: 50px;
        border: 1px solid #777;
        border-radius: 10px;
        display: flex;
        flex-direction: column;
        align-items: center;
        gap: 1rem;
        box-shadow: var(--shadow-3);
    }
    input[type="checkbox"] {
        margin-right: 10px;
    }
    button {
        width: 100%;
    }
    .error {
        color: red;
    }
</style>