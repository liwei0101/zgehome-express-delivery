<template>
	<div class="express-list">
		<div class="div9">
			<iview-custom-breadcrumb separator=">"
															 :BreadcrumbItemList="iviewBread"></iview-custom-breadcrumb>
			<div class="div8">
				<div class="condition">
					<Input placeholder="请输入快递公司名称" class="search" v-model="params.name">
						<Icon type="ios-search" slot="prefix" />
					</Input>
					<div class="right">
						<Button type="info" @click="getData">查询</Button>
<!--						<Button @click="resetData">重置</Button>-->
					</div>
				</div>
				<div class="div1">
					<div @click="edit" class="div">
						<Icon type="md-add" class="icon"></Icon>
						新建内容
					</div>
				</div>
				<Table :columns="tableColumns" :data="info.records" class="table">
					<div slot="action" slot-scope="{row, index}">
<!--            pubStatus状态  &#45;&#45;  1-下架, 2-发布-->
						<div v-if="row.pubStatus == 2" class="div2 colorblue" @click="publish(row, 1)">
							下架
						</div>
						<div v-if="row.pubStatus == 1" class="div3 colorblue" @click="publish(row, 2)">
							发布
						</div>
						<div @click="detail(row)" class="div4 colorblue">
							查看
						</div>
						<div @click="edit(row)" class="div5" :class="row.pubStatus == 2?'colorgray':'colorblue'">
							编辑
						</div>
						<div class="div6" @click="deleteItem(row)" :class="row.pubStatus == 2?'colorgray':'colorblue'">
							删除
						</div>
					</div>
				</Table>
				<Page :page-size-opts="[10, 20,	30,	40]" :show-total='true'
							:show-elevator='true' :show-sizer='true' :transfer='false'
							:total="+info.total" :current="+info.current"
							@on-change="onChange" @on-page-size-change="onChangeSize"
							class="page-div"></Page>
			</div>
		</div>
	</div>
</template>

<script>
	import { dateFormat } from '@/libs/util'

	export default {
		components: {},
		props: {},
		data() {
			return {
				tartTime: '',
				endTime: '',
				optionsDate: {
					disabledDate: function (date) {
						var disabledDay = date.getFullYear();
						return disabledDay < 2010 || date.valueOf() > Date.now();
					},
				},
				"iviewBread": [
					{
						"text": "快递速寄",
						"replace": false,
						"target": "_self",
						"append": false
					},
				],
				"tableColumns": [
					{
						"title": "公司名称",
						"key": "name",
						className:'no-wrap',
					},
          {
            "title": "联系电话",
            "key": "telephone",
            className:'no-wrap',
          },
/*					{
						"title": "内容详情",
						className:'no-wrap',
						render (h,params) {
							return h('div',[
								h('span',{},
									params.row.detail.replace(/<\/?.+?>/g,"").replace(/&nbsp;/g, '').replace(/&lt;/g, '<').replace(/&gt;/g, '>').replace(/&amp;/g, '&')?params.row.detail.replace(/<\/?.+?>/g,"").replace(/&nbsp;/g, '').replace(/&lt;/g, '<').replace(/&gt;/g, '>').replace(/&amp;/g, '&'):'--'
								)
							])
						}
					},*/
/*					{
						"title": "发布时间",
						render (h,params) {
							return h('div',[
								h('span',{},
									params.row.pubStatus == 1?'--':dateFormat('yyyy-MM-dd hh:mm', params.row.pubTime)
								)
							])
						}
					},*/
/*					{
						"title": "浏览量",
						"key": "pageview",
					},*/
					{
						"title": "状态",
						"key": "pubStatus",
						render (h,params) {
							return h('div',[
								h('span',{
										style: {
											color:  (params.row.pubStatus == 1) ? "#df3031" :"#66bc6f"
										}
									},
                  // 状态  --  1-下架, 2-发布
									params.row.pubStatus==1?'已下架':'已发布'
								)
							])
						}
					},
					{
						"title": "操作",
						"slot": "action",
						"width": 200
					}
				],
				"params": {
					// "category": 2,
					"pageNo": 1,
					"pageSize": 10,
					"name": '',
				},
				"info": {
					total: 0,
					current: 1,
				},
			}
		},
		created() {
			this.getData();
		},
		methods: {
			edit(row) {  //新建、编辑
        // pubStatus状态  --  1-下架, 2-发布
				if (row.pubStatus == 2) {
					return;
				}
				this.$router.push({
					name: "edit",
					query: {
						id: row.id
					}
				})
			},
			detail(row) {  //查看详情
				this.$router.push({
					name: "detail",
					query: {
						id: row.id
					}
				})
			},
			getData() {  //获取列表信息
				let params = this.params
				return this.$http.post("/pc/expressCompany/selectPage", params)
					.then((res) => {
					  if(+res.meta.code===0){
              this.info = res.data;
              // 如果获得的数据为空且当前不是第一页,往前翻一页
              if (this.info.records && this.info.records.length === 0 && this.params.pageNo > 1) {
                this.params.pageNo--;
                this.getData();
                return;
              }
            }
						return Promise.resolve(res)
					}).catch((e) => {
						return Promise.reject(e)
					})
			},
			/**
			 * 页码变化
			 */
			onChange(index) {
				this.params.pageNo = index;
				this.getData();
				console.log("页码变化")
			},
			/**
			 * 页码变化
			 */
			onChangeSize(index) {
				this.params.pageSize = index;
				this.getData();
				console.log("页码变化")
			},
			deleteItem(row) {  //删除
				if (row.pubStatus == 2) {
					return;
				}
				this.$Modal.confirm({
					title: '提示',
					content: '确定删除吗？',
					okText: '确定',
					cancelText: '取消',
					onOk: () => {
						let params = {id: row.id}
						return this.$http.post("/pc/expressCompany/deleteById", params)
							.then((res) => {
								this.getData();
								return Promise.resolve(res)
							}).catch((e) => {
								return Promise.reject(e)
							})
					}
				})
			},
			publish(row, pubStatus) {  //上、下架
        // pubStatus状态  --  1-下架, 2-发布
				let params = {id: row.id, pubStatus: pubStatus}
				return this.$http.post("/pc/expressCompany/updateForPublish", params)
					.then((res) => {
						if (res.meta.code == '0') {
							this.$Message.success('操作成功!');
							this.getData();
						} else {
							this.$Message.error('操作失败!');
						}
						return Promise.resolve(res)
					}).catch((e) => {
						return Promise.reject(e)
					})
			},
		}
	}
</script>

<style lang="less" scoped>
	.icon {
		position: relative;
		top: -2px;
		margin: 0px 3px 0px 0px;
	}

	.div {
		display: inline-block;
		cursor: pointer;
	}

	.div1 {
		text-align: right;
		color: #2D8CF0;
		font-size: 16px;
	}

	.div2 {
		display: inline-block;
		margin: 0px 10px 0px 0px;
		cursor: pointer;
	}

	.div3 {
		display: inline-block;
		margin: 0px 10px 0px 0px;
		cursor: pointer;
	}

	.div4 {
		display: inline-block;
		margin: 0px 10px 0px 0px;
		cursor: pointer;
	}

	.div5 {
		display: inline-block;
		margin: 0px 10px 0px 0px;
		cursor: pointer;
	}

	.div6 {
		display: inline-block;
		margin: 0px 10px 0px 0px;
		cursor: pointer;
	}

	.div7 {
		display: inline-block;
		cursor: pointer;
	}

	.table {
		margin: 20px 0px 30px 0px;
	}

	.page-div {
		text-align: right;
	}

	.div8 {
		padding: 10px;
		background-color: rgba(255, 255, 255, 1);
		margin: 10px 0px 0px 0px;
		.condition {
			.search {
				width: 30%;
			}
		}
		.time-select {
			display: inline-block;
			margin-left: 50px;
			.lable {
				display: inline-block;
				padding-right: 20px;
			}
		}
		.right {
			display: inline-block;
			button {
				margin-left: 30px;
			}
		}
	}

	.div9 {
		padding: 10px;
	}
	.colorblue{
		color: #2D8CF0;
	}
	.colorgray{
		color: #8e8e93;
	}

</style>
<style>
	.express-list .no-wrap *{
		width:100%;
		overflow: hidden;
		text-overflow:ellipsis !important;
		white-space: nowrap !important;
	}
</style>