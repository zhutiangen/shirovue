export function generateTitle(title) {
  var retStr = this.$t('route.' + title);
  return retStr.split('.').length > 1 ? title : retStr  // $t :this method from vue-i18n ,inject in @/lang/index.js
}
