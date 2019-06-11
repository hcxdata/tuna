import Mock from 'mockjs'
import { param2Obj } from '@/utils'

const List = []
const count = 100

const baseContent = '<p>我是测试数据我是测试数据</p><p><img src="https://wpimg.wallstcn.com/4c69009c-0fd4-4153-b112-6cb53d1cf943"></p>'
const image_uri = 'https://wpimg.wallstcn.com/e4558086-631c-425c-9430-56ffb46e70b3'

for (let i = 0; i < count; i++) {
  List.push(Mock.mock({
    id: '@increment',
    avatar: {
      url: 'static/img/avatars/@integer(1, 7).jpg',
      'status|1': ['', 'success', 'danger', 'warning'],
    },
    user: {
      name: '@title(1, 3)',
      'new|1': [true, false],
      registered: '@datetime'
    },
    country: {
      'name|1': ['Brazil','USA'],
      'flag|1': ['br', 'us']
    },
    usage: {
      value: '@integer(1, 100)',
      period: '@datetime',
    },
    payment: {
      'name|1': ['Mastercard','Visa'],
      'icon|1': ['fa fa-cc-mastercard', 'fa fa-cc-visa']
    },
    activity: '@integer(1, 200) minutes ago'
  }))
}

export default {
  getSysUserList: config => {
    const { importance, type, title, page = 1, size = 20, sort } = param2Obj(config.url)

    let mockList = List.filter(item => {
      if (importance && item.importance !== +importance) return false
      if (type && item.type !== type) return false
      if (title && item.title.indexOf(title) < 0) return false
      return true
    })

    if (sort === '-id') {
      mockList = mockList.reverse()
    }

    const pageList = mockList.filter((item, index) => index < size * page && index >= size * (page - 1))

    return {
      totalElements: mockList.length,
      content: pageList
    }
  }
}
