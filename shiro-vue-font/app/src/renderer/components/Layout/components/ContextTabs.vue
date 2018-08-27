<template>
    <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane v-for="view in views" :key="view.name" :name="view.path">
            <div slot="label" class="scroll-container">
                {{generateTitle(view.name)}}
            </div>
            <!--<router-view></router-view>-->
        </el-tab-pane>
        <div class="content-wrap">
            <router-view/>
        </div>
    </el-tabs>
</template>

<script>
import { mapGetters } from "vuex"
import { generateTitle } from '@/utils/i18n'
// import test2 from "./../../../views/dashboard/test2"
export default {
    name: "context-tabs",
    // components: {
    //   test2
    // },
    data() {
        return {
            activeName: ""
        };
    },
    computed: {
        ...mapGetters([
            'views'
        ])
    },
    methods: {
        // 点击tab页事件
        handleClick(tab, event) {

        },
        generateTitle
    }
}
</script>

<style scoped>

</style>