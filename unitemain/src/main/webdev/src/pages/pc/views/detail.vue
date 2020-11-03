<template>
	<div>
		<div class="div8">
			<iview-custom-breadcrumb separator=">" :BreadcrumbItemList="iviewBread"
									 class="iviewCustomBreadcrumb"></iview-custom-breadcrumb>
			<div class="div7">
				<div class="div">
					<span class="goText">详情</span>
					<Button @click="goback" class="button">
						<span class="goText1">返回</span>
					</Button>
				</div>
				<div class="div1">
					<span class="goText2">公司名称</span>
					<span class="goText3">{{infoData.name}}</span>
				</div>
				<div class="div2">
					<span class="goText4">公司地址</span>
					<span class="goText3">{{infoData.address}}</span>
				</div>
				<div class="div3">
					<span class="goText5">联系电话</span>
					<span class="goText6">
						{{infoData.telephone}}
					</span>
				</div>
				<div class="div2">
					<span class="goText4">公司封面</span>
					<img :src="infoData.frontCover" class="img"/>
				</div>
				<div class="div6">
					<span class="goText11">公司介绍</span>
					<span v-html='infoData.compayIntroduce' class="goText12"></span>
				</div>
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
        "iviewBread": [
					{
						"to": "/express",
						"text": "快递速寄",
						"replace": false,
						"target": "_self",
						"append": false
					},
					{
						"text": "详情",
						"replace": false,
						"target": "_self",
						"append": false
					}
        ],
        "params": {
          "id": null
        },
        "infoData": {},
      }
    },
    created() {
      this.getData();
    },
    methods: {
      goback() {  //返回上一级
        console.log("返回上一级");
        this.$router.go(-1)
      },
			getData() {  //获取信息
        console.log(this.$route.query.id);
        this.params.id = this.$route.query.id;
        return this.$http.post("/pc/expressCompany/selectById", this.params)
          .then((res) => {
            this.infoData = res.data;
            // this.infoData.pubTime = dateFormat('yyyy-MM-dd hh:mm', this.infoData.pubTime);
            return Promise.resolve(res)
          }).catch((e) => {
            return Promise.reject(e)
          })
      },

    }
  }
</script>

<style lang="less" scoped>
	.iviewCustomBreadcrumb {
		margin: 0px 0px 0px 0px;
	}

	.goText {
		font-size: 16px;
		font-weight: bolder;
	}

	.goText1 {
		font-size: 14px;
	}

	.button {
		float: right;
	}

	.div {
		margin: 0px 0px 20px 0px;
		height: 40px;
		border-bottom: 1px solid rgb(241, 241, 241);
	}

	.goText2 {
		color: #8E8E93;
		display: inline-block;
		width: 100px;
		text-align: right;
		font-size: 16px;
		margin: 0px 10px 0px 0px;
	}

	.goText3 {
		font-size: 16px;
	}

	.div1 {
		padding: 10px;
	}

	.goText4 {
		color: #8E8E93;
		display: inline-block;
		width: 100px;
		text-align: right;
		font-size: 16px;
		margin: 0px 10px 0px 0px;
		vertical-align: top;
	}

	.img {
		width: 50%;
	}

	.div2 {
		padding: 10px;
	}

	.goText5 {
		color: #8E8E93;
		display: inline-block;
		width: 100px;
		text-align: right;
		font-size: 16px;
		margin: 0px 10px 0px 0px;
	}

	.goText6 {
		font-size: 16px;
	}

	.div3 {
		padding: 10px;
	}

	.goText7 {
		color: #8E8E93;
		display: inline-block;
		width: 100px;
		text-align: right;
		font-size: 16px;
		margin: 0px 10px 0px 0px;
	}

	.goText8 {
		font-size: 16px;
	}

	.div4 {
		padding: 10px;
	}

	.goText9 {
		color: #8E8E93;
		display: inline-block;
		width: 100px;
		text-align: right;
		font-size: 16px;
		margin: 0px 10px 0px 0px;
	}

	.goText10 {
		font-size: 16px;
	}

	.div5 {
		padding: 10px;
	}

	.goText11 {
		color: #8E8E93;
		display: inline-block;
		width: 100px;
		text-align: right;
		font-size: 16px;
		margin: 0px 10px 0px 0px;
		vertical-align: top;
	}

	.goText12 {
		font-size: 16px;
		display: inline-block;
		width: 80%;
		word-break: break-all;
		word-wrap: break-word;
		text-align: justify;
		*{
			word-break: break-all;
			word-wrap: break-word;
		}
	}

	.div6 {
		padding: 10px;
	}

	.div7 {
		padding: 10px;
		background-color: rgba(255, 255, 255, 1);
	}

	.div8 {
		padding: 10px;
	}
</style>
