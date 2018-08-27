/**
   to import all modules files
 */

/** require.context 创建自己的上下文，有三个参数
 * 要搜索的文件目录
 * 是否搜索子目录
 * 匹配的文件的正则表达式
 */
const files = require.context('.', false, /\.js$/);

const modules = {}

files.keys().forEach(key => {
    if (key === './index.js') return
    let file =  files(key).default;
    modules[key.replace(/(\.\/|\.js)/g, '')] = files(key).default
})
export default modules