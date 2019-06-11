// translate router.meta.title, be used in breadcrumb sidebar tagsview
import i18n from '@/lang' // Internationalization

export function generateTitle(title) {
  // const hasKey = this.$te('route.' + title)
  const hasKey = i18n.te('route.' + title)

  if (hasKey) {
    // $t :this method from vue-i18n, inject in @/lang/index.js
    // const translatedTitle = this.$t('route.' + title)
    const translatedTitle = i18n.t('route.' + title)

    return translatedTitle
  }
  return title
}

export function convert2i18n(k) {
  // const hasKey = this.$te('route.' + title)
  const hasKey = i18n.te(k)

  if (hasKey) {
    // $t :this method from vue-i18n, inject in @/lang/index.js
    // const translatedTitle = this.$t('route.' + title)
    const translatedTitle = i18n.t(k)

    return translatedTitle
  }
  return title
}
