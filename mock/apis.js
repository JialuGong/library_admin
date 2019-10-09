const express = require('express')
const Mock = require('mockjs')
const app = express()

app.use('/apis/mandarin/admin/login', (req, res, next) => {
    // res.header("Access-Control-Allow-Origin", "*");
    // res.header('Access-Control-Allow-Methods', 'PUT, GET, POST, DELETE, OPTIONS');
    // res.header("Access-Control-Allow-Headers", "X-Requested-With");
    // res.header('Access-Control-Allow-Headers', 'Content-Type');
    // next();
    var resString = '{"message":"获取token成功","code":1,"data":{"token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6ImFkbWluIiwiaWF0IjoxNTcwMzY1NTA3LCJleHAiOjE1NzA0NTE5MDd9.H0ruFdYbVequdatBXb-6sdS8KXB5gqQwdcibTvZzb34"}}'
    var resJson = JSON.parse(resString)
    res.json(Mock.mock(
        resJson
    ))
})

app.use('/apis/mandarin/admin/info', (req, res, next) => {
    // res.header("Access-Control-Allow-Origin", "*");
    // res.header('Access-Control-Allow-Methods', 'PUT, GET, POST, DELETE, OPTIONS');
    // res.header("Access-Control-Allow-Headers", "X-Requested-With");
    // res.header('Access-Control-Allow-Headers', 'Content-Type');
    // next();
    var resString = '{"code":1,"data":{"admin_name":"hhhhhhhh吃蛋挞"}}'
    var resJson = JSON.parse(resString)
    res.json(Mock.mock(resJson))

})
app.use('/apis/mandarin/admin/getAllLib', (req, res, next) => {
    // res.header("Access-Control-Allow-Origin", "*");
    // res.header('Access-Control-Allow-Methods', 'PUT, GET, POST, DELETE, OPTIONS');
    // res.header("Access-Control-Allow-Headers", "X-Requested-With");
    // res.header('Access-Control-Allow-Headers', 'Content-Type');
    // next();
    res.json(Mock.mock({
        "code": 1,
        "data": [
            {
                "librarian_id": "1",
                "librarian_name": "ghl",
                "librarian_password": "admin",
                "librarian_phone": "17792034948",
                "librarian_email": "123@gmail.com"


            }, {
                "librarian_id": "2",
                "librarian_name": "hhhh",
                "auth_admin_name": "admin",
                "librarian_phone": "1779",
                "librarian_email": "1ddd@gmail.com"

            }
        ]
    }))
})
app.use('/apis/mandarin/admin/getReaderDeposit', (req, res, next) => {
    res.json(Mock.mock({
        // "code": 1,
        // "data": [
        //     {
        //        "reader_deposit":"2.3",
        //        "timestamp":"2018-04-14"
        //     }, {
        //         "reader_deposit":"4.5",
        //         "timestamp":"2019-05-13"
        //     }, {
        //         "reader_deposit":"7.9",
        //         "timestamp":"2019-09-13"
        //     }
        // ]
        "code": 1,
        "data": [
            {
                "reader_deposit": "2.3",
                "timestamp": "2018-04-13"
            },
            {
                "reader_deposit": "1.4",
                "timestamp": "2018-04-15"
            },
            {
                "reader_deposit": "2.5",
                "timestamp": "2018-04-17"
            }
        ]
    }))
})

app.use('/apis/mandarin/admin/getBookFine', (req, res, next) => {
    res.json(Mock.mock({
        "code": 1,
        "data": [
            {
                "book_fine_value": "2.3",
                "timestamp": "2018-04-13"
            },
            {
                "book_fine_value": "1.4",
                "timestamp": "2018-04-15"
            },
            {
                "book_fine_value": "2.5",
                "timestamp": "2018-04-17"
            }
        ]
    }))
})

app.use('/apis/mandarin/admin/getBookPeriod', (req, res, next) => {
    res.json(Mock.mock({
        "code": 1,
        "data": [
            {
                "book_period": "2.3",
                "timestamp": "2018-04-13"
            },
            {
                "book_period": "1.4",
                "timestamp": "2018-04-15"
            },
            {
                "book_period": "2.5",
                "timestamp": "2018-04-17"
            }
        ]
    }))
})

app.use('/apis/mandarin/admin/modifyReaderDeposit', (req, res, next) => {
    // res.json(Mock.mock({
    //     "code": 1,
    //     "data": [
    //         {
    //             "book_period": "2.3",
    //             "timestamp": "2018-04-13"
    //         },
    //         {
    //             "book_period": "1.4",
    //             "timestamp": "2018-04-15"
    //         },
    //         {
    //             "book_period": "2.5",
    //             "timestamp": "2018-04-17"
    //         }
    //     ]
    // }))
    res.json(Mock.mock({
        "code":1,
        "data": [
            {
                "reader_deposit": "2.3",
                "timestamp": "2018-04-13"
            },
            {
                "reader_deposit": "1.4",
                "timestamp": "2018-04-15"
            },
            {
                "reader_deposit": "10000000",
                "timestamp": "2018-04-17"
            }
        ]
    }))
})

app.use('/apis/mandarin/admin/searchLib', (req, res, next) => {
    res.json(Mock.mock({
        "message": "success",
        "code": 1,
        "data":[{
            "librarian_id": "2",
                "librarian_name": "hhhh",
                "auth_admin_name": "admin",
                "librarian_phone": "1779",
                "librarian_email": "1ddd@gmail.com"
        }]
    }
    ))
})
app.listen('8080', () => {
    console.log(`监听8080端口`)
})