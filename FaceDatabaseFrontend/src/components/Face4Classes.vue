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
				sex: "-1",
				smiling: "-1"
			}
		},
		watch: {
			sex() {
				this.getFilenames()
			},
			smiling() {
				this.getFilenames()
			}
		},
		created: function() {
			this.getFilenames()
		},
		methods: {
			getFilenames() {
				var formData = new FormData()
				formData.append("sex", this.sex)
				formData.append("smiling", this.smiling)
				this.axios.post(this.javaBackendURL+"/get_database_4",formData,{
					headers: {
						"Content-Type":"multipart/form-data"
					}
				})
				.then(response => {
					if(response.data.code === 200) {
						var filename_list = response.data.data
						this.url_groups = this.getUrls('face_4/',filename_list)
						console.log(this.url_groups)
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
