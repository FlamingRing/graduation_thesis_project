module.exports = {
	// publicPath:"./"
	devServer: {
		proxy: {
		  // 配置跨域
			'/api': {
			target: 'http://localhost:8888',//后端接口地址
			changeOrigin: true,
			pathRewrite: {
			  '^/api': ''
			}
		}
    },
  }
};