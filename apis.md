# apis

#### important
返回的json的形式
- success
```js
{
"code":1,
"message":"success",
"data":{data}
}
```
- fail
```js
"code":0,
"message":{错误的原因}
"data":{data}
```
___

host name：localhost
port:8080
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
data:{}
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
data:{}
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
data:
{
"librarian_id":{librarianID},
"librarian_name":{librarianName},
"librarian_password":{librariabPassword},
"librarian_phone":{librarianPhone},
"librarian_email":{librarianEmail}
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
data:{}
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
data:{}
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
data:{}
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
data:
{"book_fine_value":{bookFineValue}}=>(注释：bookFineValue的type为double)
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
data:{
"modify_success",
"modify_fail"
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
data:
{"book_period":{bookPeriod}}=>(注释：bookPeriod的type为int)
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
data:{}
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
data:
{"reader_deposit":{readerDeposit}=>(注释：readerDeposit的type为double)
```



_____

#### 12  __search librarian__

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

####### server
```js
"data":{
"librarian_name":{libririanN"librarian_id":{librarianID},
"librarian_name":{librarianName},
"librarian_password":{librarianPassword},
"librarian_phone":{librarianPhone},
"librarian_email":{librarianEmail}
}
```

#### 13  __get permission__

```
url:/apis/mandarin/admin/getInfo
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
"admin_name":{adminName}
}
```
____

### new
#### 14 verify E-mail
```shell
url:/apis/mandarin/admin/verifyEmail
```
###### client
```javascript
type:post
dataType:FormData
data:
{"admin_email",{adminEmail}}
```
###### server
```javascript
data:{}
```
#### 15 change password
```shell
url:/apis/mandarin/admin/changePassword
```
###### client
```javascript
type:post
dataType:FormData
data:
{
"admin_password":{adminPassword}
"admin_key":{adminKey}
}
```
