<template>
	<div>
		<el-checkbox v-model="bigLips">大嘴唇</el-checkbox>
		<el-checkbox v-model="bigNose">大鼻子</el-checkbox>
		<el-checkbox v-model="eyeglasses">戴眼镜</el-checkbox>
		<el-checkbox v-model="male">男性</el-checkbox>
		<el-checkbox v-model="noBeard">无胡须</el-checkbox>
		<el-checkbox v-model="smiling">微笑</el-checkbox>
		<el-checkbox v-model="wearingHat">戴帽子</el-checkbox>
		<el-checkbox v-model="young">年轻</el-checkbox>
		<el-container style="height: 768px" width="768px">
			<ul class="infinite-list" v-infinite-scroll="load" infinite-scroll-delay=0
				style="overflow:auto">
				<el-row type="flex" v-for="url_group in url_groups.slice(0,group_count)" :key="url_group">
					<div v-for="url in url_group" :key="url">
						<el-col :span="4">
							<el-image style="width: 128px; height: 128px" :src="url" lazy>
							</el-image>
						</el-col>
					</div>
				</el-row>
			</ul>
		</el-container>
	</div>
</template>

<script>
	import GLOBAL from "@/api/global_variables.js"
	export default {
		data() {
			return {
				javaBackendURL:GLOBAL.javaBackendURL,
				databaseDirectoryUrl:GLOBAL.databaseDirectoryUrl,
				url_groups: [],
				count_per_row: 6,
				group_count: 6,
				bigLips:false,
				bigNose:false,
				eyeglasses:false,
				male:false,
				noBeard:false,
				smiling:false,
				wearingHat:false,
				young:false
			}
		},
		watch: {
			bigLips() {
				this.getFilenames()
			},
			bigNose() {
				this.getFilenames()
			},
			eyeglasses() {
				this.getFilenames()
			},
			male() {
				this.getFilenames()
			},
			noBeard() {
				this.getFilenames()
			},
			smiling() {
				this.getFilenames()
			},
			wearingHat() {
				this.getFilenames()
			},
			young() {
				this.getFilenames()
			}
		},
		created: function() {
			this.getFilenames()
		},
		methods: {
			getFilenames() {
				var formData = new FormData()
				if(this.bigLips) {
					formData.append("bigLips", 1)
				}
				else {
					formData.append("bigLips", 0)
				}
				if(this.bigNose) {
					formData.append("bigNose", 1)
				}
				else {
					formData.append("bigNose", 0)
				}
				if(this.eyeglasses) {
					formData.append("eyeglasses", 1)
				}
				else {
					formData.append("eyeglasses", 0)
				}
				if(this.male) {
					formData.append("male", 1)
				}
				else {
					formData.append("male", 0)
				}
				if(this.noBeard) {
					formData.append("noBeard", 1)
				}
				else {
					formData.append("noBeard", 0)
				}
				if(this.smiling) {
					formData.append("smiling", 1)
				}
				else {
					formData.append("smiling", 0)
				}
				if(this.wearingHat) {
					formData.append("wearingHat", 1)
				}
				else {
					formData.append("wearingHat", 0)
				}
				if(this.young) {
					formData.append("young", 1)
				}
				else {
					formData.append("young", 0)
				}
				this.axios.post(this.javaBackendURL+"get_database_8",formData,{
					headers: {
						"Content-Type":"multipart/form-data"
					}
				})
				.then(response => {
					if(response.data.code === 200) {
						var filename_list = response.data.data
						this.url_groups = this.getUrls('face_8/',filename_list)
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
			getUrls(subdir, filename_list) {
				var url_groups = []
				var group_idx = 0
				var inner_idx = 0
				url_groups[group_idx] = []
				for(var i=0;i<filename_list.length;i++) {
					if(inner_idx === this.count_per_row) {
						inner_idx = 0
						group_idx++
						url_groups[group_idx] = []
					}
					url_groups[group_idx][inner_idx] = 
						this.databaseDirectoryUrl + subdir + filename_list[i]
						+ '?' + new Date().getTime()
					inner_idx++
				}
				return url_groups
			},
			load () {
				this.group_count += 3
			}
		}
	}
</script>

<style>
</style>
