'use strict'

process.env.BABEL_ENV = 'renderer'

const path = require('path')
const pkg = require('./app/package.json')
const settings = require('./config.js')
const webpack = require('webpack')

const BabiliWebpackPlugin = require('babili-webpack-plugin')
const CopyWebpackPlugin = require('copy-webpack-plugin')
const ExtractTextPlugin = require('extract-text-webpack-plugin')
const HtmlWebpackPlugin = require('html-webpack-plugin')

let whiteListedModules = ['vue']

let rendererConfig = {
  devtool: '#eval-source-map',
  devServer: { overlay: true  },
  entry: {
      renderer: path.join(__dirname, 'app/src/renderer/main.js')
  },
  externals: Object.keys(pkg.dependencies || {}).filter(d => !whiteListedModules.includes(d)),
  module: {
    rules: [
        {
            test: /\.css$/,
            use: ExtractTextPlugin.extract({
                fallback: 'style-loader',
                use: 'css-loader'
            })
        },
        {
            test: /\.scss$/,
            use: ExtractTextPlugin.extract({
                fallback: 'style-loader',
                use: ['css-loader', 'sass-loader']
            })
        },
        {
            test: /\.html$/,
            use: 'vue-html-loader'
        },
        {
            test: /\.js$/,
            use: 'babel-loader',
            include: [path.resolve(__dirname, 'app/src/renderer')],
            exclude: /node_modules/
        },
        {
            test: /\.json$/,
            use: 'json-loader'
        },
        {
            test: /\.node$/,
            use: 'node-loader'
        },
        {
            test: /\.vue$/,
            use: {
                loader: 'vue-loader',
                options: {
                loaders: {
                    sass: 'vue-style-loader!css-loader!sass-loader?indentedSyntax=1',
                    scss: 'vue-style-loader!css-loader!sass-loader',
                    stylus: 'vue-style-loader!css-loader!stylus-loader'
                }
                }
            }
        },
        {
            test: /\.svg$/,
            loader: 'svg-sprite-loader',
            include: [path.resolve(__dirname, 'app/src/renderer/assets/icons')],
            options: {
              symbolId: 'icon-[name]'
            }
        },
        {
            test: /\.(png|jpe?g|gif|svg)(\?.*)?$/,
            loader: 'url-loader',
            exclude: [path.resolve(__dirname, 'app/src/renderer/assets/icons')],
            options: {
              limit: 10000,
              name: 'img/[name].[hash:7].[ext]'
            }
        },
        {
            test: /\.(mp4|webm|ogg|mp3|wav|flac|aac)(\?.*)?$/,
            loader: 'url-loader',
            options: {
                limit: 10000,
                name: 'media/[name].[hash:7].[ext]'
            }
        },
        {
            test: /\.(woff2?|eot|ttf|otf)(\?.*)?$/,
            loader: 'url-loader',
            options: {
                limit: 10000,
                name: 'fonts/[name].[hash:7].[ext]'
            }
        }
    ]
  },
  plugins: [
      new ExtractTextPlugin('styles.css'),
      new HtmlWebpackPlugin({
          filename: 'index.html',
          template: path.resolve(__dirname, 'app/src/index.ejs'),
          minify: {
            collapseWhitespace: true,
            removeAttributeQuotes: true,
            removeComments: true
          },
          nodeModules: process.env.NODE_ENV !== 'production' ?
            path.resolve(__dirname, 'app/node_modules') : false
      }),
      new webpack.NoEmitOnErrorsPlugin()
  ],
  output: {
      filename: '[name].js',
      libraryTarget: 'commonjs2',
      path: path.join(__dirname, 'app/dist')
  },
  resolve: {
    alias: {
        '@': path.join(__dirname, 'app/src/renderer'),
        'vue$': 'vue/dist/vue.esm.js'       
    },
    modules: [
        path.join(__dirname, 'app/node_modules'),
        path.join(__dirname, 'node_modules')
    ],
    extensions: ['.js', '.vue', '.json', '.css', '.scss', '.node']
  },
  target: 'electron-renderer'
}

/**
 * Adjust rendererConfig for development settings
 */
if (process.env.NODE_ENV !== 'production') {
    rendererConfig.plugins.push(
      new webpack.DefinePlugin({
        '__static': `"${path.join(__dirname, 'app/static').replace(/\\/g, '\\\\')}"`,
        'process.env': {
            BASE_API: JSON.stringify(settings.BASE_API)
        }
      })
    )
  }

/**
 * Adjust rendererConfig for production settings
 */
if (process.env.NODE_ENV === 'production') {
    rendererConfig.devtool = ''

    rendererConfig.plugins.push(
        new BabiliWebpackPlugin(),
        new CopyWebpackPlugin([
            {
              from: path.join(__dirname, 'app/static'),
              to: path.join(__dirname, 'app/dist/static'),
              ignore: ['.*']
            }
        ]),
        new CopyWebpackPlugin([
            {
              from: path.join(__dirname, 'app/icons'),
              to: path.join(__dirname, 'app/dist/icons'),
              ignore: ['.*']
            }
        ]),
        new webpack.DefinePlugin({
            'process.env':{
                NODE_ENV: JSON.stringify('production'),
                BASE_API: JSON.stringify(settings.BASE_API)
            }
        }),
        new webpack.LoaderOptionsPlugin({
            minimize: true
        }),
        new webpack.optimize.UglifyJsPlugin({
            compress: {
                warnings: false
            }
        })
    )
}

module.exports = rendererConfig
