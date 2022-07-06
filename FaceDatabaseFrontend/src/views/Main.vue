<template>
	<div>
		<el-container>
			<el-header>
			</el-header>
			<el-main>
				<el-tabs tab-position="left">
					<el-tab-pane label="2分类人脸图像浏览">
						<Face2Classes>
						</Face2Classes>
					</el-tab-pane>
					<el-tab-pane label="4分类人脸图像浏览">
						<Face4Classes>
						</Face4Classes>
					</el-tab-pane>
					<el-tab-pane label="8属性人脸图像浏览">
						<Face8Attributes>
						</Face8Attributes>
					</el-tab-pane>
					<el-tab-pane label="15属性人脸图像浏览">
						<Face15Attributes>
						</Face15Attributes>
					</el-tab-pane>
					<el-tab-pane label="2分类人脸生成">
						<Face2Generation>
						</Face2Generation>
					</el-tab-pane>
					<el-tab-pane label="4分类人脸生成">
						<Face4Generation>
						</Face4Generation>
					</el-tab-pane>
					<el-tab-pane label="8属性人脸生成">
						<Face8Generation>
						</Face8Generation>
					</el-tab-pane>
					<el-tab-pane label="15属性人脸生成">
						<Face15Generation>
						</Face15Generation>
					</el-tab-pane>
				</el-tabs>
			</el-main>
		</el-container>
	</div>
</template>

<script>
	import GLOBAL from "@/api/global_variables.js"
	import Face2Classes from '@/components/Face2Classes.vue'
	import Face4Classes from '@/components/Face4Classes.vue'
	import Face8Attributes from '@/components/Face8Attributes.vue'
	import Face15Attributes from '@/components/Face15Attributes.vue'
	import Face2Generation from '@/components/Face2Generation.vue'
	import Face4Generation from '@/components/Face4Generation.vue'
	import Face8Generation from '@/components/Face8Generation.vue'
	import Face15Generation from '@/components/Face15Generation.vue'
	export default{
		components: {
			Face2Classes,
			Face4Classes,
			Face8Attributes,
			Face15Attributes,
			Face2Generation,
			Face4Generation,
			Face8Generation,
			Face15Generation
		},
		data() {
			return {
				javaBackendURL:GLOBAL.javaBackendURL,
				databaseDirectoryUrl:GLOBAL.databaseDirectoryUrl
			}
		},
		methods: {

		}
	}
</script>

<style>
</style>