# apis
host name：localhost:8080
##### 1. log in
```
url:/mandarin/admin/login
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
dataType:String
data:
"password_error"
"name_not_found"
"login_success"
```
##### 2. register new librarian
```
url:/mandarin/admin/registerLib
```
###### client

```js
type:post
dataType:formdata
data:
{
"librarian_name":{librarianName},
"librarian_password":{librarianPassword},
"librarian_phone":{librarianPhone},
"librarian_email":{librarianEmail}
}
```
###### server
```js
dataType:String
data:
"name_existed"
"register_success"
```

##### 3. get librarian list
```
url:/mandarin/admin/getAllLib
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
"librarian_id":{librarianId},
"librarian_name":{librarianName},
"librarian_password":{librariabPassword},
"librarian_phone":{librarianPhone},
"librarian_email":{librarianEmail}
}
```
##### 4. edit librarian
```
url:/mandarin/admin/editLib
```
###### client

```js
type:post
dataType:formdata
data:
{
"librarian_name":{librarianName},
"librarian_password":{librarianPassword},
"librarian_phone":{librarianPhone},
"librarian_email":{librarianEmail}
}
```
###### server
```js
dataType:String
data:
"edit_success"
"edit_fail"
```
##### 5.delete librarian
```
url:/mandarin/admin/deleteLib
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
dataType:String
data:
"delete_success"
"delete_fail"
"name_not_found"
```
##### 6.modify book fine value
```
url:/mandarin/admin/modifyBookFine
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
dataType:String
data:
"modify_success",
"modify_faile"
```
##### 7. get book fine value
```
url:/mandarin/admin/getBookFine
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
url:/mandarin/admin/modifyBookPeriod
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
data:
"modify_success",
"modify_faile"
```
##### 9. get book period
```
url:/mandarin/admin/getBookPeriod
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
url:/mandarin/admin/modifyReaderDeposit
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
data:
"modify_success",
"modify_faile"
```

##### 11. get book fine value
```
url:/mandarin/admin/getReaderDeposit
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