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
		watch: {
			beard() {
				this.getFilenames()
			},
			bald() {
				this.getFilenames()
			},
			eyeglasses() {
				this.getFilenames()
			},
			smiling() {
				this.getFilenames()
			},
			wearingHat() {
				this.getFilenames()
			},
			male() {
				this.getFilenames()
			},
			shadow() {
				this.getFilenames()
			},
			mustache() {
				this.getFilenames()
			},
			bangs() {
				this.getFilenames()
			},
			blackHair() {
				this.getFilenames()
			},
			blondeHair() {
				this.getFilenames()
			},
			brownHair() {
				this.getFilenames()
			},
			grayHair() {
				this.getFilenames()
			},
			straightHair() {
				this.getFilenames()
			},
			wavyHair() {
				this.getFilenames()
			}
		},
		created: function() {
			this.getFilenames()
		},
		methods: {
			getFilenames() {
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
				this.axios.post(this.javaBackendURL+"get_database_15",formData,{
					headers: {
						"Content-Type":"multipart/form-data"
					}
				})
				.then(response => {
					if(response.data.code === 200) {
						var filename_list = response.data.data
						this.url_groups = this.getUrls('face_15/',filename_list)
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
