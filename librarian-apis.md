# librarian-apis

#### important
server返回的json的形式
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
host name:47.92.141.59
___

##### 1. librarian login in
```
url:apis/mandarin/librarian/login
```
###### client

```javascript
type:post
dataType:formdata
data:
{
"librarian_name":{adminName},
"librarian_password":{adminePassword}
}
```
###### server
```js
dataType:json
data:{}
```
##### 2. get deposit
```
url:apis/mandarin/librarian/getDeposit
```
###### client

```javascript
type:get
```
###### server
```js
dataType:json
data:{
"deposit":{deposit}
}
```
##### 3. get fine value
```
url:apis/mandarin/librarian/getFineValue
```
###### client

```javascript
type:get
```
###### server
```js
dataType:json
data:{
"fine_value":{fineValue}
}
```
##### 4. get return period
```
url:apis/mandarin/librarian/getReturnPeriod
```
###### client

```javascript
type:get
```
###### server
```js
dataType:json
data:{
"return_period":{returnReriod}
}
```