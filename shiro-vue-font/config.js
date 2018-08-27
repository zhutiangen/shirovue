'use strict'

const path = require('path');
const pkg = require('./package.json');

let config = {
  // Name of electron app
  // Will be used in production builds
  name: pkg.cnname,

  // webpack-dev-server port
  port: 9080,
  BASE_API:'http://cps-server:8080',
  // electron-packager options
  // https://github.com/electron-userland/electron-packager/blob/master/docs/api.md
  // Docs: https://simulatedgreg.gitbooks.io/electron-vue/content/docs/building_your_app.html
  building: {
    name: pkg.name,
    author: pkg.author,
  //  arch: 'x64',
    arch: 'ia32',
    asar: true,
    appVersion: pkg.version,
    buildVersion: pkg.version,
    dir: path.join(__dirname, 'app'),
    icon: path.join(__dirname, 'app/icons/icon'),
    ignore: /(^\/(app|test|\.[a-z]+|README|yarn|static|dist\/web|node_modules\/electron-*|node_modules\/.bin))|\.gitkeep/,
    out: path.join(__dirname, 'builds'),
    overwrite: true,
    platform: process.env.PLATFORM_TARGET || 'all',
    download: {
      mirror: 'https://npm.taobao.org/mirrors/electron/'
    }
  }
};

module.exports = config;
