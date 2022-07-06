<template>
	<div>
		<el-radio v-model="imageClass" label=-1>无限制</el-radio>
		<el-radio v-model="imageClass" label=0>女性</el-radio>
		<el-radio v-model="imageClass" label=1>男性</el-radio>
		<br>
		<el-button type="primary" @click="generate()">生成人脸图像</el-button>
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
				imageClass: "-1",
				url:null
			}
		},
		methods: {
			generate() {
				var formData = new FormData()
				formData.append("imageClass", this.imageClass)
				this.axios.post(this.javaBackendURL+"/generate_gan2",formData,{
					headers: {
						"Content-Type":"multipart/form-data"
					}
				})
				.then(response => {
					if(response.data.code === 200) {
						this.url = this.databaseDirectoryUrl + "temporary_images/face2.png"
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
