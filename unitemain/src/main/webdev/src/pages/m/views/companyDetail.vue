<template>
	<div class="main-page">
		<div class="banner">
			<img :src="infoData.frontCover" alt="方便快捷 实时追踪" v-if="infoData.frontCover">
		</div>
		<customer-news :title="infoData.name" :labelLeft="infoData.address" :labelRight="''">
			<template slot="label">
				<img src="@/assets/location.png" class="location" />
			</template>
		</customer-news>

		<div class="container">
			<div class="hairline-bottom title">
				<span class="line"></span>
				<span class="text">公司简介</span>
			</div>
			<div class="detail" v-html="infoData.compayIntroduce"></div>
		</div>

		<div class="btn">
			<van-button type="info" size="large"
									@click.stop="callPhone(infoData.telephone)">电话联系</van-button>
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
      	infoData: {},
			}
		},
    created(){
		  this.getDetail();
    },
		methods: {
			getDetail(){
				let params = {id: this.$route.query.id};
				return this.$http.post('/app/expressCompany/selectById', params)
					.then((res) => {
						if(+res.meta.code === 0){
							// let detailTxt = res.data.detail.replace(/<img[^>]*>|<\/?.+?\/?>|&nbsp;|\s/gi, '');
							this.infoData = res.data
						}
					}).catch((e) => {})
			},
			callPhone(phone){
				window.location.href = 'tel:'+phone
			},
		}
	}
</script>

<style lang="less" scoped>
	.main-page{
		min-height: 100vh;
		display: flex;
		flex-direction: column;
	}
	.banner{
		width: 100%;
		height: 156px;
		display: flex;
		align-items: center;
		justify-content: center;
		overflow: hidden;
		img{
			width: 100%;
			vertical-align: bottom;
		}
	}
	.location{
		width: 16px;
		height: 16px;
		vertical-align: middle;
		margin-right: 3px;
	}

	.container {
		flex-grow: 1;
		border-top:7px solid  #f8f8fb;
		padding:0px 16px 0px 16px;
		.title {
			padding:8px 0px 8px 0px;
			.line {
				display:inline-block;
				width:3px;
				height:15px;
				background-color:rgba(223,48,49,1);
				margin:0px 7px 0px 0px;
				position:relative;
				top: 3px;
			}
			.text {
				font-size:16px;
				color:#222222;
			}
		}
		.detail {
			padding:10px 0px 10px 0px;
			font-size:14px;
			color:#222222;
		}
	}

	.btn{
		padding: 10px 16px;
	}
</style>
