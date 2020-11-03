<template>
  <div id="app">
		<keep-alive v-if="$route.meta && $route.meta.keepAlive">
			<router-view></router-view>
		</keep-alive>
		<router-view v-else></router-view>
  </div>
</template>

<script>
export default {
  name: 'app'
}
</script>
<style>
	#app{
		height: 100%;
		width: 100%;
		/*font-family:PingFangSC-Regular;*/
		/*font-size:16px;*/
		-webkit-overflow-scrolling: touch;
		font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
		font-size: 12px;
		line-height: 1.5;
		color: #515a6e;
		background-color: #fff;
		-webkit-font-smoothing: antialiased;
		-moz-osx-font-smoothing: grayscale
	}
</style>