<template>
	<div>
		<el-row>
			<el-radio v-model="sex" label=-1>性别无限制</el-radio>
			<el-radio v-model="sex" label=0>女性</el-radio>
			<el-radio v-model="sex" label=1>男性</el-radio>
		</el-row>
		<el-row>
			<el-radio v-model="smiling" label=-1>表情无限制</el-radio>
			<el-radio v-model="smiling" label=0>非微笑</el-radio>
			<el-radio v-model="smiling" label=1>微笑</el-radio>
		</el-row>
		<el-row>
			<el-button type="primary" @click="generate()">生成人脸图像</el-button>
		</el-row>
		<el-row type="flex">
			<el-col :span="4">
				<el-image style="width: 128px; height: 128px" :src="url">
					<div slot="error" class="image-slot">
						<i class="el-icon-picture-outline"></i>
					</div>
				</el-image>
			</el-col>
		</el-row>
	</div>
</template>

<script>
	import GLOBAL from "@/api/global_variables.js"
	export default {
		data() {
			return {
				javaBackendURL:GLOBAL.javaBackendURL,
				databaseDirectoryUrl:GLOBAL.databaseDirectoryUrl,
				sex: "-1",
				smiling: "-1",
				url:null
			}
		},
		methods: {
			generate() {
				var formData = new FormData()
				formData.append("sex", this.sex)
				formData.append("smiling", this.smiling)
				this.axios.post(this.javaBackendURL+"/generate_gan4",formData,{
					headers: {
						"Content-Type":"multipart/form-data"
					}
				})
				.then(response => {
					if(response.data.code === 200) {
						this.url = this.databaseDirectoryUrl + "temporary_images/face4.png"
							+ '?' + new Date().getTime()
					}
					else {
						this.$alert('获取图片文件名列表失败', '失败', {
							confirmButtonText: '确定',
						})
					}
				})
				.catch(error => {
					console.log(error);
				});
			},
		}
	}
</script>

<style>
</style>
