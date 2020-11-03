<template>
	<div>

		<vant-custom-list-refresh :autoLoad='false' :pageSize='10' :url="dataUrl" pageSizeKey="pageSize" pageNoKey="pageNo" records="records" v-show="list && list.length>0" :param="pullParam" @getData="getListCallback" ref="listRefresh">
			<div class="list">
				<div class="item border-after-bottom"
						 @click="companyDetail(item.id)"
						 v-for="(item, i) in list" :key="i">
					<img :src="item.frontCover" class="company-logo" />
					<div class="text">
						<div>{{item.name}}</div>
						<div class="location">
							<img src="@/assets/location.png" />
							<span>{{item.address}}</span>
						</div>
					</div>
					<img src="@/assets/call_phone.png" class="call-phone"
							 @click.stop="callPhone(item.telephone)"/>
				</div>
			</div>
		</vant-custom-list-refresh>
		<div v-show="list && list.length===0" class="noData">
			<img src="@/assets/no.png" alt="">
			<div class="text">暂无数据</div>
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
				list: null,
				dataUrl: '/app/expressCompany/selectPage',//分页查询地址
				pullParam: {
					name: ''
				}
			}
		},
		created(){
			this.$nextTick(()=>{
				this.$refs.listRefresh.onRefresh();
			})
			// this.getList()
		},
		methods: {
/*			getList() {
				let	params = {
					pageNo: 1,
					pageSize: 10,
					name: ''
				}
				this.list = [{},{},{}]
				this.$http.post(this.dataUrl, params).then((res) => {
					if (+res.meta.code === 0) {
						this.list = res.data.records || []
					}else{
						this.list = []
					}
				}).catch((e) => {
				}).catch((e) => {
					this.list = []
				})
			},*/
			getListCallback(list){  //分页查询的回调
				this.list = list || []
			},
			callPhone(phone){
				window.location.href = 'tel:'+phone
			},
			companyDetail(id){  //公司详情
				this.$router.push({
					name:'companyDetail',
					query: {id: id},
				})
			}
		}
	}
</script>

<style lang="less" scoped>
	.list{
		.item{
			padding: 10px 11px 10px 16px;  //16-5=11
			display: flex;
			align-items: center;
			.company-logo{
				width: 90px;
				height: 60px;
				vertical-align: middle;
				flex-grow: 0;
				flex-shrink: 0;
				margin-right: 10px;
			}
			.text{
				display: flex;
				flex-direction: column;
				justify-content: space-between;
				flex-grow: 1;
				flex-shrink: 1;
				font-size: 16px;
				color: #333333;
				align-self: stretch;
				overflow: hidden;
				&>div:first-child,
				.location{
					overflow: hidden;
					white-space:nowrap;
					text-overflow:ellipsis;
				}

			}
			.location{
				font-size: 14px;
				color: #999999;
				img{
					width: 16px;
					height: 16px;
					vertical-align: middle;
					margin-right: 3px;
				}
			}
			.call-phone{
				width: 42px;
				height: 42px;
				padding: 5px;
				flex-grow: 0;
				flex-shrink: 0;
				margin-left: 10px;
			}
		}
	}
/*	.border-after-bottom:last-child:after{
		display: none;
	}*/
	.noData {
		text-align: center;
		position: fixed;
		width: 200px;
		height: 150px;
		top: 50%;
		left: 50%;
		transform: translate(-100px,-75px);
		img {
			width: 100px;
			height: 100px;
		}
		.text {
			font-size: 16px;
		}
	}
</style>
