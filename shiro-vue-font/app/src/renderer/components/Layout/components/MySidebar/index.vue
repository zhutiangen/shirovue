<template>
    <scroll-bar>
        <!--#2D2D30  304156-->
        <el-menu mode="vertical" :default-active="$route.path" :collapse="isCollapse" background-color="#2D2D30" text-color="#fff" active-text-color="#409EFF">
            <sidebar-item :routes="allRouters"></sidebar-item>
        </el-menu>
    </scroll-bar>
</template>

<script>
    import { mapGetters } from 'vuex'
    import SidebarItem from './MySidebarItem'
    import ScrollBar from '@/components/ScrollBar'

    export default {
        components: { SidebarItem, ScrollBar },
        computed: {
            ...mapGetters([
                'allRouters',
                'sidebar'
            ]),
            isCollapse() {
                return !this.sidebar.opened
            }
        }
    }
</script>
