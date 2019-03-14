const path = require("path");
const CopyPlugin = require('copy-webpack-plugin');

module.exports = {
    outputDir: path.resolve(__dirname, "../webapp"),
    publicPath: '/swimple',
    devServer: {
        proxy: 'http://localhost:8080'
    },
    configureWebpack: {
        plugins: [
            new CopyPlugin([
                { from: 'jsf_admin', to: 'admin' },
                { from: 'jsf_resources', to: 'resources' },
                { from: 'jsf_WEB-INF', to: 'WEB-INF' }
            ]),
        ]
    }
}
