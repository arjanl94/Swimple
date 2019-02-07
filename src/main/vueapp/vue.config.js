const path = require("path");

module.exports = {
    outputDir: path.resolve(__dirname, "../webapp"),
    publicPath: '/swimple',
    devServer: {
        proxy: 'http://localhost:8080'
    }
}