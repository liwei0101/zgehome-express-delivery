<template>
	<div class="main-page">
		<div class="div4">
			<div class="div1">
				<div class="div">
					<img src="@/assets/logo.png" class="img"/>
				</div>
				<span class="goText">快递速寄</span>
			</div>
			<div class="div2">
				<iview-custom-menu :active-name="activeName"
													 :menuList="iviewCustomMenuMenuList"
													 :open-names="iviewCustomMenuOpenNames"
													 theme="dark" :accordion='false'
													 mode="vertical"
													 @on-select="goPath"></iview-custom-menu>
			</div>
			<div class="div3">
				<router-view></router-view>
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		components: {
		},
		props: {},
		data() {
      return {
				imgUrl:'',
				activeName: 'express',
				"iviewCustomMenuMenuList": [
					{
						"name": "express",
						"menuTitle": "快递速寄"
					},
/*					{
						"name": "not2",
						"menuTitle": "住宿\u002F美食",
						"menuItemList": [
							{
								"name": "hotel",
								"title": "酒店管理"
							},
							{
								"name": "food",
								"title": "美食管理"
							}
						]
					},*/
				],
				"iviewCustomMenuOpenNames": [
					"express"
				],
			}
		},
		created() {
			this.imgUrl = window.sessionStorage.getItem('imgUrl');
			this.activeName = window.sessionStorage.getItem('activeName');
		},
		methods: {
			goPath(name) {  //去子页面
				window.sessionStorage.setItem('activeName', name);
				if (name.indexOf('not') != -1) return;
				this.$router.push({path: name})
			},
		}
	}
</script>

<style lang="less" scoped>
	.img {
		max-width: 100%;
		width: 44px;
		height: 44px;
	}

	.div {
		display: inline-block;
		width: 44px;
		height: 44px;
		border-radius: 50%;
		overflow: hidden;
		position: relative;
		top: 8px;
	}

	.goText {
		font-size: 24px;
		color: #FFFFFF;
		margin: 0 0 0 20px;
		position: relative;
		top: -5px;
	}

	.div1 {
		padding: 10px;
		height: 80px;
		width: 100%;
		background-color: rgba(0, 21, 42, 1);
	}

	.div2 {
		width: 220px;
		position: absolute;
		bottom: 0;
		top: 80px;
		background-color: rgba(0, 21, 42, 1);
	}

	.div3 {
		overflow: auto;
		position: fixed;
		top: 80px;
		bottom: 0;
		left: 220px;
		right: 0;
		background-color: rgba(243, 248, 254, 1);
	}

	.div4 {
		position: fixed;
		top: 0;
		bottom: 0;
		left: 0;
		right: 0;
	}

	.main-page /deep/ .ivu-menu-dark {
		background-color: rgba(0, 21, 42, 1);
	}
</style>

