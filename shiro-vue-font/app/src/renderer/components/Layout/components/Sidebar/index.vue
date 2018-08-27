<template>
  <scroll-bar :backgroundColor="sidebarTheme.backgroundColor">
    <!--#2D2D30  304156-->
    <!--background-color="#2D2D30" text-color="#fff" active-text-color="#409EFF"-->
    <el-menu mode="vertical" :default-active="$route.path" :collapse="isCollapse" :background-color="sidebarTheme.backgroundColor"
             :text-color="sidebarTheme.textColor" :active-text-color="sidebarTheme.activeTextColor">
      <sidebar-item :routes="allRouters"></sidebar-item>
    </el-menu>
  </scroll-bar>
</template>

<script>
import { mapGetters } from 'vuex'
import SidebarItem from './SidebarItem'
import ScrollBar from '@/components/ScrollBar'

export default {
  components: { SidebarItem, ScrollBar },
  props: {
      sidebarTheme: Object
  },
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
<style rel="stylesheet/scss" scoped>
  @import "../../../../styles/mixin.scss";
</style>
