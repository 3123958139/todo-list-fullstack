<script>
    import { BIconBatteryFull } from 'bootstrap-icons-vue';
    import { VRangeSlider } from 'vuetify/components';
    
    export default {
        components: {
            BIconBatteryFull,
            VRangeSlider
        },
        methods: {
            increment() {
                this.$store.commit('increment');
            }
        },
        data() {
            return {
                ageRange: [18, 30]
            }
        },
    }
</script>
<template>
    <div>
        <b-icon-battery-full />
        <h1>About</h1>
        <p>Welcome to the About page</p>
        <p>{{ $store.state.count }}</p>
        <button @click="increment">Increment</button>     
        <v-range-slider v-model="ageRange" thumb-label="always" />
    </div>
</template>