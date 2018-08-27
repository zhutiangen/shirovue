<template>
	<div class="app-wrapper" :class="{hideSidebar:!sidebar.opened}">
		<sidebar :sidebarTheme="sidebarTheme" class="sidebar-container"></sidebar>
		<div class="main-container">
			<navbar @change-theme="changeTheme"></navbar>
			<!--<ContextTabs></ContextTabs>-->
			<tags-view></tags-view>
			<app-main></app-main>
		</div>
	</div>
</template>

<script>
import {AppMain, Navbar, TagsView, Sidebar, ContextTabs} from "./components/index";
export default {
  name: 'layout',
  components: {
    Navbar,
    Sidebar,
    AppMain,
    TagsView,
    ContextTabs
  },
	data() {
      return {
          sidebarTheme: {
              backgroundColor: '#2D2D30',
              // backgroundColor: '#24FFD3',
              textColor: '#fff',
              activeTextColor: '#17A1E0',
          }
	  }
	},
	computed: {
		sidebar() {
			return this.$store.state.app.sidebar
		}
	},
	methods: {
        changeTheme(theme) {
            this.sidebarTheme = theme.sideColor
		}
	}
}
</script>

<style rel="stylesheet/scss" scoped>
	@import "../../styles/mixin.scss";
	.app-wrapper {
	@include clearfix;
		position: relative;
		height: 100%;
		width: 100%;
	}
</style>
