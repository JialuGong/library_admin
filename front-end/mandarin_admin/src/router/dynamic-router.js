/* 书籍管理 */
const Book = () => import('@/pages/book-manage')
const FineValue = () => import('@/pages/book-manage/fine-value')
const ReturnPeriod = () => import('@/pages/book-manage/return-period')

/* 读者管理 */
const Reader = () => import('@/pages/reader-manage')
const Deposit = () => import('@/pages/reader-manage/deposit')

// 图书管理员管理
const Librarian = () => import('@/pages/librarian')
const UserManage = () => import('@/pages/librarian/user-manage')
/* 需要权限判断的路由 */
/**
 * temporary mapping:{
 * order :book management
 * product:reader management
 * permission:librarian management
 * }
 */
const dynamicRoutes = [
    {
        path: 'books',
        component: Book,
        name: 'order-manage',
        meta: {
            name: 'manage book',
            icon: 'book'
        },
        children: [
            {
                path: 'fineValue',
                name: 'order-list',
                component: FineValue,
                meta: {
                    name: 'fine value',
                    icon: 'money'
                }
            },
            {
                path: 'returnGoods',
                name: 'return-goods',
                component: ReturnPeriod,
                meta: {
                    name: 'return period',
                    icon: 'period'
                }
            }
        ]
    },
    {
        path: '/reader',
        component: Reader,
        name: 'goods',
        meta: {
            name: 'manage reader',
            icon: 'reader'
        },
        children: [
            {
                path: 'deposit',
                name: 'goods-list',
                component: Deposit,
                meta: {
                    name: 'deposit',
                    icon: 'deposit'
                }
            }
        ]
    },
    {
        path: '/librarian',
        component: Librarian,
        name: 'permission',
        meta: {
            name: 'manage librarian',
            icon: 'user'
        },
        children: [
            {
                path: 'user',
                name: 'user-manage',
                component: UserManage,
                meta: {
                    name: 'librarian list',
                    icon: 'table'
                }
            }
        ]
    }
]

export default dynamicRoutes
