<template>
    <div>
        <svg-icon :icon-class="dashboard"></svg-icon>
        <span>ssss</span>

    </div>
</template>

<script>
    export default {
        name: "test",
        data() {
            return {
                dashboard: "dashboard"
            }
        },
        created: () => {
            debugger
            console.log(__dirname);
        }
    }
</script>

<style scoped>

</style>