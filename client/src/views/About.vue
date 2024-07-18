<script>
    import { BIconBatteryFull } from 'bootstrap-icons-vue';
    import { VRangeSlider } from 'vuetify/components';
    
    export default {
        components: {
            BIconBatteryFull,
            VRangeSlider
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
        <h1>About</h1>

        <h2>Third-party Component Demos</h2>
        

        <h3>Bootstrap Icon Demo</h3>
        <p>Here is a Bootstrap Icon: <b-icon-battery-full style="color: red" /></p>
        <p><a href="https://www.npmjs.com/package/bootstrap-icons-vue">Bootstrap Icons Vue Library</a></p> 
        <p><a href="https://icons.getbootstrap.com/">Bootstrap Icons Lookup Page</a></p>

        <h3>Vuetify Components Demo (Range Slider)</h3>
        <v-range-slider v-model="ageRange" thumb-label="always" />
        <p><a href="https://vuetifyjs.com/en/components/all/">Vuetify Components Page</a></p>

        <p>First Value: {{ Math.floor(ageRange[0]) }}</p>
        <p>First Value: {{ Math.floor(ageRange[1]) }}</p>


    </div>
</template>