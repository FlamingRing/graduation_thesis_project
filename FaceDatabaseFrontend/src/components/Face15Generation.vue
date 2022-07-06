<template>
	<div>
		<el-checkbox v-model="beard">有胡子</el-checkbox>
		<el-checkbox v-model="bald">秃顶</el-checkbox>
		<el-checkbox v-model="eyeglasses">戴眼镜</el-checkbox>
		<el-checkbox v-model="smiling">微笑</el-checkbox>
		<el-checkbox v-model="wearingHat">戴帽子</el-checkbox>
		<el-checkbox v-model="male">男性</el-checkbox>
		<el-checkbox v-model="shadow">胡渣</el-checkbox>
		<el-checkbox v-model="mustache">八字胡</el-checkbox>
		<br>
		<el-checkbox v-model="bangs">刘海</el-checkbox>
		<el-checkbox v-model="blackHair">黑发</el-checkbox>
		<el-checkbox v-model="blondeHair">金发</el-checkbox>
		<el-checkbox v-model="brownHair">棕发</el-checkbox>
		<el-checkbox v-model="grayHair">灰发</el-checkbox>
		<el-checkbox v-model="straightHair">直发</el-checkbox>
		<el-checkbox v-model="wavyHair">波浪发</el-checkbox>
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
				url:null,
				
				beard:false,
				bald:false,
				eyeglasses:false,
				smiling:false,
				wearingHat:false,
				male:false,
				shadow:false,
				mustache:false,
				bangs:false,
				blackHair:false,
				blondeHair:false,
				brownHair:false,
				grayHair:false,
				straightHair:false,
				wavyHair:false
			}
		},
		methods: {
			generate() {
				var formData = new FormData()
				formData.append("beard", this.beard?1:0)
				formData.append("bald", this.bald?1:0)
				formData.append("eyeglasses", this.eyeglasses?1:0)
				formData.append("smiling", this.smiling?1:0)
				formData.append("wearingHat", this.wearingHat?1:0)
				formData.append("male", this.male?1:0)
				formData.append("shadow", this.shadow?1:0)
				formData.append("mustache", this.mustache?1:0)
				formData.append("bangs", this.bangs?1:0)
				formData.append("blackHair", this.blackHair?1:0)
				formData.append("blondeHair", this.blondeHair?1:0)
				formData.append("brownHair", this.brownHair?1:0)
				formData.append("grayHair", this.grayHair?1:0)
				formData.append("straightHair", this.straightHair?1:0)
				formData.append("wavyHair", this.wavyHair?1:0)
				this.axios.post(this.javaBackendURL+"/generate_gan15",formData,{
					headers: {
						"Content-Type":"multipart/form-data"
					}
				})
				.then(response => {
					if(response.data.code === 200) {
						this.url = this.databaseDirectoryUrl + "temporary_images/face15.png"
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
