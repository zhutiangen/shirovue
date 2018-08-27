<template>
	<div>
		<TopBar></TopBar>
		<SideBar></SideBar>
		<div class="main-container">
			<NavBar></NavBar>
			<TagsView></TagsView>
		</div>
	</div>
</template>

<script>
import TopBar from './TopBar'
import SideBar from './SideBar'
import NavBar from './NavBar'
import TagsView from '.'
export default {
  name: 'layout',
  components: {
      TopBar,
      SideBar,
      NavBar,

  },
	data() {
	},
	computed: {
	},
	methods: {
	}
}
</script>

<style rel="stylesheet/scss" scoped>
	.main-container{
		margin-left: 180px;
	}
</style>
