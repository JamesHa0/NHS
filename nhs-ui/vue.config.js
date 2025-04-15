const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  lintOnSave: false,
  transpileDependencies: true,
  devServer: {
    proxy: {
      // 代理
      "/lyy": {
        target: "http://localhost:8088/",     //要代理访问的路径
        changeOrigin: true,//开启代理：在本地会创建一个虚拟服务端，然后发送请求的数据，并同时接收请求的数据，这样服务端和服务端进行数据的交互就不会有跨域问题
        pathRewrite: (path) => path.replace(/^\/lyy/, '/') // 重写路径
      }
    }
  }
})