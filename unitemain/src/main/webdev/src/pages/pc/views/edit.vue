<template>
  <div class="main-page">
    <div class="div3">
      <iview-custom-breadcrumb separator=">"
                               :BreadcrumbItemList="iviewBread"></iview-custom-breadcrumb>
      <div class="div2">
        <div class="div">
					<span class="goText">
						{{isNaN($route.query.id)?"新建":"编辑"}}
					</span>
          <Button @click="goback" class="button">
						<span class="goText1">返回</span>
          </Button>
        </div>

        <Form :label-width='90' :inline='false' ref="params"
              :model="params" :rules="ruleValidate" class="form">
          <FormItem label="公司名称" prop="name">
            <Input placeholder="请输入公司名称（60字以内）" :maxlength='60'
                   v-model.trim="params.name"/>
          </FormItem>
          <FormItem label="公司地址" prop="address">
            <Input placeholder="请输入公司地址（60字以内）" :maxlength='60'
                   v-model.trim="params.address"/>
          </FormItem>
          <FormItem label="联系电话" prop="telephone">
            <Input placeholder="请输入联系电话（15字以内）" :maxlength='15'
                   v-model.trim="params.telephone"
                   @on-keyup="changePhone"/>
          </FormItem>
          <FormItem label="公司封面" prop="frontCover">
            <iview-custom-select-img action="/pc/pcUploadFile" accept="image/gif,image/jpeg,image/png"
                                     :format="iviewImg" :maxSize='2048'
                                     :imgsLength='1' :heightImg='70' :imgSize='20' imgType="ios-camera"
                                     :defaultList="defaultList"
                                     @handleSuccess="getUploaddata"
                                     @handleRemove="moveUploadimg"></iview-custom-select-img>
            <p style="font-size: 14px">上传2M以内且格式为jpg/jpeg/png/bmp格式的图片</p>
          </FormItem>
          <FormItem  label="公司介绍" class="textarea">
            <iview-custom-editor ref="editor" :uploadImg='true' :showLinkImg='true' :upFileBase64='false'
                                 :imgServer='true' uploadUrl="/pc/pcUploadFile"
                                 :uploadImgMaxLength='1' :pasteFilterStyle='false'
                                 :initData="params.compayIntroduce"
                                 :menus="iviewCustomEditorMenus"
                                 @change="getContentdata"></iview-custom-editor>
            <div style="float: right">
              <!--<span :class="{hlight : editorTxtLen > POLICY_CONTENT_MAX_LENGTH}" class="counter" style="right: 20px; bottom: auto;">{{editorTxtLen}}/{{POLICY_CONTENT_MAX_LENGTH}}</span>-->
              <span class="counter" style="right: 20px; bottom: auto;">最多输入150字</span>
            </div>

            <div class="ivu-form-item-error-tip" v-if="showDetailTextError">请输入公司介绍</div>
            <div class="ivu-form-item-error-tip" v-if="params.detailText && params.detailText.length>150">最多输入150字</div>
          </FormItem>
        </Form>
        <div class="div1">
          <Button type="primary" @click="saveInfo(2, 'params')" class="button1">
            保存并发布
          </Button>
          <Button type="primary" @click="saveInfo(1, 'params')">
            保存
          </Button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import { dateFormat, setTit } from '@/libs/util'
  export default {
    components: {},
    props: {},
    data(){
      return {
        // editorTxtLen: 0,
        // POLICY_CONTENT_MAX_LENGTH: 150,
        ruleValidate: {
          name: [
            {required: true, message: '请输入公司名称', trigger: 'blur'}
          ],
          address: [
            {required: true, message: '请输入公司地址', trigger: 'blur'}
          ],
          telephone: [
            {required: true, message: '请输入联系电话', trigger: 'blur'}
          ],
          frontCover: [
            {required: true, message: '请添加公司封面', trigger: 'change'}
          ],
/*          detailText: [
            {required: true, message: '请输入公司介绍', trigger: 'click'},
            {type: 'string', max: 150, message: '最多输入150字', trigger: 'click'}
          ]*/
        },
        showDetailTextError: false,  //是否显示文本域输入错误
        "iviewBread": [
          {
            "to": "/express",
            "text": "快递速寄",
            "replace": false,
            "target": "_self",
            "append": false
          },
          {
            "text": isNaN(this.$route.query.id)?"新建":"编辑",
            "replace": false,
            "target": "_self",
            "append": false
          }
        ],
        "iviewImg": [
          "jpg",
          "jpeg",
          "png",
          "bmp",
        ],
        "iviewCustomEditorMenus": [
          "head",
          "bold",
          "fontSize",
          // "fontName",
          "italic",
          "underline",
          "strikeThrough",
          "foreColor",
          "backColor",
          // "link",
          // "list",
          "justify",
          // "quote",
          // "emoticon",
          // "image",
          // "table",
          // "video",
          // "code",
          // "undo",
          // "redo"
        ],
/*        "tourInformationUpdateByIdParams": {
          "id": null,
          "title": null,
          "frontCover": null,
          "videoContent": null,
          "detail": null,
          "pageview": null,
          "category": null,
          "pubTime": null,
          "pubStatus": null,
          "deleteFlag": null,
          "createBy": null,
          "createTime": null,
          "updateBy": null,
          "updateTime": null
        },*/
        // "tourInformationUpdateByIdData": {},
        "defaultList": [],
/*        "tourInformationSelectByIdParams": {
          "id": null,
          "title": null,
          "frontCover": null,
          "videoContent": null,
          "detail": null,
          "pageview": null,
          "category": null,
          "pubTime": null,
          "pubStatus": null,
          "deleteFlag": null,
          "createBy": null,
          "createTime": null,
          "updateBy": null,
          "updateTime": null
        },*/
        // "tourInformationSelectByIdData": {},
        "params": {
          "id": null,
          "title": null,
          "frontCover": null,
          "videoContent": null,
          "detail": null,
          "pageview": null,
          "category": 1,
          "pubTime": null,
          "pubStatus": null,
          "deleteFlag": null,
          "createBy": null,
          "createTime": null,
          "updateBy": null,
          "updateTime": null
        },
        // "tourInformationInsertData": {},
      }
    },
    created(){
      setTit(isNaN(this.$route.query.id)?"新建":"编辑");
      this.getData();
    },
    methods: {
      /**
       * 返回上一级
       */
      goback(){
        this.$Modal.confirm({
          title: '提示',
          content: '返回后已输入的内容将会丢失，确认返回？',
          okText: '确认',
          cancelText: '取消',
          onOk: () => {
            this.$router.go(-1);
          }
        })
      },
      saveInfo(index, name){  //保存信息
        setTimeout(() => {
          this.$refs[name].validate((valid) => {
            this.showDetailTextError = !this.params.detailText;  //未输入时，提示错误
            if(valid && !this.showDetailTextError && this.params.detailText.length<=150){
/*              if(!this.params.frontCover){
                this.$Message.error('请上传攻略封面!');
                return;
              }*/
              // let artical = this.params.detail.replace(/<img[^>]*>|<\/?.+?\/?>|&nbsp;|\s/gi, '')
/*              if(!artical.length){
                this.$Message.error('请输入攻略详情!');
                return;
              }*/
/*              if(artical.length > this.POLICY_CONTENT_MAX_LENGTH){
                this.$Message.error(`攻略详情不能大于${this.POLICY_CONTENT_MAX_LENGTH}字`);
                return
              }*/
              this.params.pubStatus = index;  //状态  --  1-下架, 2-发布
              if(this.params.id){  //更新信息
                this.updateById();
              }else{    //新建信息
                let params = Object.assign({}, this.params);
                delete params.detailText;
                return this.$http.post("/pc/expressCompany/insert", params)
                  .then((res) => {
                    if(res.meta.code == 0){
                      this.$router.go(-1);
                    }else{
                      this.$Message.error(res.meta.message);
                    }
                    return Promise.resolve(res)
                  }).catch((e) => {
                    return Promise.reject(e)
                  })
              }
            }
          });
        },10)
      },
      /**
       * 获取图片地址
       */
      getUploaddata(data){
        console.log("获取图片地址")
        this.params.frontCover = data.res.data.fileUrl;
      },
      moveUploadimg(){
        this.params.frontCover = '';
      },
      /**
       * 获取富文本信息
       */
      getContentdata(data){
        // this.params.compayIntroduce = data.editorContent;
        // this.params.detailText = data.editorContent.replace(/<[^>]+>|[&nbsp;]/ig, '');
        this.$set(this.params, 'compayIntroduce', data.editorContent);
        this.$set(this.params, 'detailText', data.editorContent.replace(/<[^>]+>|[&nbsp;]/ig, ''));
        // console.log(this.params)
/*        if(this.editorTxtLen > this.POLICY_CONTENT_MAX_LENGTH){
          this.$Message.error(`攻略详情不能大于${this.POLICY_CONTENT_MAX_LENGTH}字`);
          return ;
        }*/
      },
      getData(){  //获取信息
        if(this.$route.query.id){
          this.params.id = this.$route.query.id;
          let params = Object.assign({}, this.params);
          delete params.detailText;
          return this.$http.post("/pc/expressCompany/selectById", params)
            .then((res) => {
              this.params = res.data;
              this.params.detailText = res.data.compayIntroduce.replace(/<[^>]+>/ig, '') || '';
              this.$nextTick(()=>{
                this.$refs.editor && this.$refs.editor.eidtor.txt.html(res.data.compayIntroduce)
              })
              this.defaultList.push(this.params.frontCover);
              // let txt = res.data.detail.replace(/<img[^>]*>|<\/?.+?\/?>|&nbsp;|\s/gi, '');
              // this.editorTxtLen = txt.length;
              return Promise.resolve(res)
            }).catch((e) => {
              return Promise.reject(e)
            })
        }
      },
      updateById(){  //更新信息
        let params = Object.assign({}, this.params);
        delete params.detailText;
        return this.$http.post("/pc/expressCompany/updateById", params)
          .then((res) => {
            this.$router.go(-1);
            return Promise.resolve(res)
          }).catch((e) => {
            return Promise.reject(e)
          })
      },
      changePhone(e){
        let str = e.target.value.replace(/[^0-9-]/ig, '');
        this.$set(this.params, 'telephone', str)
      }
    }
  }
</script>

<style lang="less" scoped>
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

  .button1 {
    margin: 0px 50px 0px 0px;
  }

  .div1 {
    border-top: 1px solid rgb(241, 241, 241);
    padding: 20px 0px 10px 90px;
  }

  .div2 {
    padding: 10px;
    background-color: rgba(255, 255, 255, 1);
  }

  .div3 {
    padding: 10px;
  }

  .hlight {
    color: red;
  }
  .form{
    & /deep/ .ivu-form-item-content{
      max-width: 400px;
    }
    .textarea /deep/ .ivu-form-item-content{
      max-width: 700px;
    }
  }
</style>
