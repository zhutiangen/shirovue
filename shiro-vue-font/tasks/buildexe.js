const createWindowsInstaller = require('electron-winstaller').createWindowsInstaller
const path = require('path')
const config = require('../config')

getInstallerConfig()
  .then(createWindowsInstaller)
  .catch((error) => {
    console.error(error.message || error)
    process.exit(1)
})

function getInstallerConfig () {
  console.log(config.building.name + config.building.appVersion + '.exe 构建中...');
  
  return Promise.resolve({
    appDirectory: path.join(__dirname, '../builds/' + config.building.name + '-win32-'+config.building.arch),
    authors: config.building.author,
    noMsi: true,
    outputDirectory: path.join(__dirname, '../builds/' + config.building.name + '-win32-'+config.building.arch+'-exe'),
    exe: config.building.name+'.exe',
    title: config.name,
    setupExe: config.building.name + '-' + config.building.appVersion+'.exe',
    setupIcon: path.join(__dirname, '../app/icons/icon.ico'),
    iconUrl: path.join(__dirname, '../app/icons/icon.ico'),
    loadingGif: path.join(__dirname, '../app/icons/loading.gif')
  })
}