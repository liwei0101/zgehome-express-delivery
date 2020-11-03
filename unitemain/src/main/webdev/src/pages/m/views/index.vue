<template>
	<div>
		<div class="banner">
			<img src="@/assets/banner.png" alt="方便快捷 实时追踪">
		</div>
		<div class="express-company" @click="$router.push({name:'expressCompany' })">
			<div>
				<span>快递公司</span>
				<span>可查看附近快递公司</span>
			</div>
			<img src="@/assets/right.png" >
		</div>
		<div class="ems border-top-FBFBFB">
			<div class="title">EMS服务</div>
			<div class="ems-list">
				<div @click="building">
					<img src="@/assets/ems1.png" />
					<span>EMS邮寄</span>
				</div>
				<div @click="building">
					<img src="@/assets/ems2.png" />
					<span>EMS查询</span>
				</div>
				<div @click="goOutside(postageUrl)">
					<img src="@/assets/ems3.png" />
					<span>邮资查询</span>
				</div>
				<div @click="goOutside(postalCodeUrl)">
					<img src="@/assets/ems4.png" />
					<span>邮编查询</span>
				</div>
			</div>
		</div>
		<div class="express-guide border-top-FBFBFB">
			<div class="title">第三方快递导览</div>
			<div class="express-list">
				<div @click="goOutside(item.url)" v-for="(item,i) in list" :key="i">
					<img :src="item.logo" alt="">
					<span>{{item.name}}</span>
				</div>
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
      	list: [
/*					{
						name: '快递100查询',
						logo: '',
						url: 'http://www.kuaidi100.com/?openid=ofaKJv7_Im0BkKWSCsQh6ua4b_68',
					},
					{
						name: '顺丰快递',
						logo: '',
						url: 'https://www.sf-express.com/mobile/cn/sc/',
					},
					{
						name: '中通快递',
						logo: '',
						url: 'https://m.zto.com/home',
					},
					{
						name: '圆通快递查询',
						logo: '',
						url: 'http://www.yto.net.cn/',
					},
					{
						name: '韵达快递查询',
						logo: '',
						url: 'http://mobile.yundasys.com:2137/mobileweb/pages/index.html',
					},
					{
						name: '申通快递查询',
						logo: '',
						url: 'http://wap.sto.cn/',
					}*/
				],
				postalCodeUrl: '',
				postageUrl: '',
			}
		},
		created(){
			this.list = JSON.parse(window.sessionStorage.getItem('thirdPartyCompanyJson') || '[]')
			this.postalCodeUrl = window.sessionStorage.getItem('postalCodeUrl') || ''
			this.postageUrl = window.sessionStorage.getItem('postageUrl') || ''
		},
		methods: {
			goOutside(url){  //跳转到外部链接
				window.location.href = url;
			},
			building(){  //正在建设中
				this.$Toast('功能正在建设中~')
			}
		}
	}
</script>

<style lang="less" scoped>
	.banner{
		width: 100%;
		min-height: 100px;
		img{
			width: 100%;
			vertical-align: bottom;
		}
	}
	.express-company{
		height: 73px;
		padding: 0 16px;
		display: flex;
		align-items: center;
		justify-content: space-between;
		font-family: PingFangSC-Medium;
		font-size: 18px;
		color: #333333;
		&>div{
			span{
				display: block;
			}
			&>span:nth-child(2){
				font-family: PingFangSC-Regular;
				font-size: 14px;
				color: #999999;
			}
		}
		img{
			width: 12px;
			height: 13px;
		}
	}

	.border-top-FBFBFB{
		border-top: 10px solid #FBFBFB;
	}
	.ems{
		.title{
			font-family: PingFangSC-Medium;
			font-size: 16px;
			color: #333333;
			font-weight: bold;
			padding: 0 16px;
		}
		.ems-list{
			padding: 0 0 25px 0;
			font-family: PingFangSC-Regular;
			font-size: 14px;
			color: #333333;
			img{
				width: 46px;
				height: 46px;
				vertical-align: bottom;
			}
			span{
				display: block;
				margin-top: 8px;
			}
			div{
				width: 33.333%;
				text-align: center;
				display: inline-block;
				margin-top: 18px;
			}
		}
	}

	.express-guide{
		padding: 14px 16px;
		.title{
			font-family: PingFangSC-Medium;
			font-weight: bold;
			font-size: 16px;
			color: #333333;
		}
		.express-list{
			padding: 4px 0;
			display: flex;
			flex-wrap: wrap;
			justify-content: space-between;
			font-family: PingFangSC-Regular;
			font-size: 14px;
			color: #333333;
			div{
				width: 162px;
				height: 50px;
				padding: 0 5px 0 13px;
				background: #EDF4FF;
				border-radius: 2px;
				margin-top: 10px;
				display: flex;
				align-items: center;
				img{
					width: 40px;
					height: 40px;
					border-radius: 6px;
					margin-right: 13px;
				}
			}
		}
	}
</style>
