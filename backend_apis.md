# apis from back_end

___
host name：localhost

port:8080
___

##### 1. log in
```
url:apis/mandarin/admin/login
```
###### client

```js
type:post
dataType:formdata
data:
{
"admin_name":{adminName},
"admin_password":{adminePassword}
}
```
###### server
```js
dataType:json
data:{如下}
```
```js
    {
        "code": 0,
        "data": null,
        "message": "name_not_found"
    }
```
```js
    {
        "code": 0,
        "data": null,
        "message": "password_error"
    }
```
```js
    {
        "code": 1,
        "data": null,
        "message": "success"
    }
```
```js
    {
        "code": 0,
        "data": null,
        "message": "login_fail"
    }
```
##### 2. register new librarian
```
url:apis/mandarin/admin/registerLib
```
###### client

```js
type:post
dataType:formdata
data:
{
"librarian_id":{librarianID},
"librarian_name":{librarianName},
"librarian_password":{librarianPassword},
"librarian_phone":{librarianPhone},
"librarian_email":{librarianEmail}
}
```
###### server
```js
dataType:json
data:{如下}
```
```js
    {
        "code": 1,
        "data": null,
        "message": "success"
    }
```
```js
    {
        "code": 0,
        "data": null,
        "message": "name_existed"
    }
```
```js
    {
        "code": 0,
        "data": null,
        "message": "register_fail"
    }
```
##### 3. get librarian list
```
url:apis/mandarin/admin/getAllLib
```
###### client

```js
type:get
```
###### server
```js
dataType:json
data:{如下}
```
```js
{
    "code": 1,
    "data": [
      {
        "librarian_name": "test",
        "librarian_phone": "test",
        "librarian_id": 1,
        "librarian_password": "test",
        "librarian_email": "test"
      },
      {
        "librarian_name": "test123",
        "librarian_phone": "test",
        "librarian_id": 3,
        "librarian_password": "9",
        "librarian_email": "test"
      },
      {
        "librarian_name": "test1223456",
        "librarian_phone": "test",
        "librarian_id": 4,
        "librarian_password": "9",
        "librarian_email": "test"
      }
    ],
    "message": "success"
}
```
##### 4. edit librarian
```
url:apis/mandarin/admin/editLib
```
###### client

```js
type:post
dataType:formdata
data:
{
"librarian_id":{librarianID},
"librarian_name":{librarianName},
"librarian_password":{librarianPassword},
"librarian_phone":{librarianPhone},
"librarian_email":{librarianEmail}
}
```
###### server
```js
dataType:json
data:{如下}
```
```js
    {
        "code": 0,
        "data": null,
        "message": "name_existed"
    }
```
```js
    {
        "code": 1,
        "data": null,
        "message": "success"
    }
```
```js
    {
        "code": 0,
        "data": null,
        "message": "edit_fail"
    }
```
##### 5.delete librarian
```
url:apis/mandarin/admin/deleteLib
```
###### client

```js
type:post
dataType:formdata
data:
{
"librarian_name":{librarianName},
}
```
###### server
```js
dataType:Json
data:{如下}
```
```js
    {
        "code": 0,
        "data": null,
        "message": "name_not_found"
    }
```
```js
    {
        "code": 0,
        "data": null,
        "message": "delete_fail"
    }
```
```js
    {
        "code": 1,
        "data": null,
        "message": "success"
    }
```
##### 6.modify book fine value
```
url:apis/mandarin/admin/modifyBookFine
```
###### client

```js
type:post
dataType:formdata
data:
{
"book_fine_value":{bookFineValue}=>(注释：bookFineValue的type为double)
}
```
###### server
```js
dataType:json
data:{如下}
```
```js
    {
        "code": 1,
        "data": null,
        "message": "success"
    }
```
```js
    {
        "code": 0,
        "data": null,
        "message": "modify_fail"
    }
```
##### 7. get book fine value
```
url:apis/mandarin/admin/getBookFine
```
###### client

```js
type:get
```
###### server
```js
dataType:json
data:{如下}
```
```js
{
    "code": 1,
    "data": [
      {
        "book_fine_value": 1.0,
        "timestamp": null
      },
      {
        "book_fine_value": 112.0,
        "timestamp": null
      },
      {
        "book_fine_value": 112.0,
        "timestamp": "hfoesaoheohfoe"
      }
    ],
    "message": "success"
}
```
```js
    {
        "code": 0,
        "data": null,
        "message": "acquisition_failed"
    }
```
##### 8.modify book period
```
url:apis/mandarin/admin/modifyBookPeriod
```
###### client

```js
type:post
dataType:formdata
data:
{
"book_period":{bookPeriod}=>(注释：bookPeriod的type为int)
}
```
###### server
```js
dataType:String
data:{如下}
```
```js
    {
        "code": 1,
        "data": null,
        "message": "success"
    }
```
```js
    {
        "code": 0,
        "data": null,
        "message": "modify_fail"
    } 
```
##### 9. get book period
```
url:apis/mandarin/admin/getBookPeriod
```
###### client

```js
type:get
```
###### server
```js
dataType:json
data:{如下}
```
```js
  {
    "code": 1,
    "data": [
      {
        "book_period": 30.0,
        "timestamp": null
      },
      {
        "book_period": 9897.0,
        "timestamp": null
      },
      {
        "book_period": 8.6776868E7,
        "timestamp": null
      },
      {
        "book_period": 34567.0,
        "timestamp": "hfoesaoheohfoe"
      }
    ],
    "message": "success"
  }
```
```js
    {
        "code": 0,
        "data": null,
        "message": "acquisition_failed"
    }
```
##### 10.modify reader deposit
```
url:apis/mandarin/admin/modifyReaderDeposit
```
###### client

```js
type:post
dataType:formdata
data:
{
"reader_deposit":{readerDeposit}=>(注释：readerDeposit的type为double)
}
```
###### server
```js
dataType:String
data:{如下}
```
```js
    {
        "code": 1,
        "data": null,
        "message": "success"
    }
```
```js
    {
        "code": 0,
        "data": null,
        "message": "modify_fail"
    } 
```

##### 11. get reader deposit
```
url:apis/mandarin/admin/getReaderDeposit
```
###### client

```js
type:get
```
###### server
```js
dataType:json
data:{如下}
```
```js
  {
    "code": 1,
    "data": [
      {
        "reader_deposit": 300.0,
        "timestamp": null
      },
      {
        "reader_deposit": 139743.0,
        "timestamp": null
      },
      {
        "reader_deposit": 39274.0,
        "timestamp": null
      },
      {
        "reader_deposit": 3782648.0,
        "timestamp": null
      },
      {
        "reader_deposit": 234.0,
        "timestamp": null
      },
      {
        "reader_deposit": 234342.0,
        "timestamp": "hfoesaoheohfoe"
      }
    ],
    "message": "success"
  }
```
```js
    {
        "code": 0,
        "data": null,
        "message": "acquisition_failed"
    }
```

_____
### new add apis

- __search librarian__

```
url:/apis/mandarin/admin/searchLib
```
###### client
```js
type:post
dataType:FormData
data:{
"librarian_name":{librarianName}
}
```
###### server
```js
"data":{
"librarian_name":{libririanN"librarian_id":{librarianID},
"librarian_name":{librarianName},
"librarian_password":{librarianPassword},
"librarian_phone":{librarianPhone},
"librarian_email":{librarianEmail}
}
```
____
### maybe?
- __get permission__
```
url:/apis/mandarin/admin/info
```
###### client
```js
type:get
```
###### server
```js
dataType:json
data:
{
}
```

