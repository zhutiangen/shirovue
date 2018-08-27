<template>
	<div id="app"  style="overflow-y: auto">
		<router-view></router-view>
		<!--router-view 最顶层出口，每个router-view都会与route中的children进行匹配。-->
	</div>
</template>

<script>
  export default{
    name: 'APP',
	created() {
        this.$router.push('./login')
	}
  }
</script>
