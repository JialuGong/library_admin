# rule-apis
host name:localhost
port:8080

### get
##### 1. get reader deposit
```
url:apis/mandarin/admin/getReaderDeposit
```
###### client

```js
type:get
```
###### server(最新三条[最新-倒数第三新])
```js
dataType:json
data:
{
"reader_deposit":{readerDeposit},
"timestamp":{timestamp}
}

```

### modify

##### 1.modify reader deposit
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
"timestamp":{'yy-MM-dd'}
}
```
###### server
```js
dataType:String
data:
"modify_success",
"modify_fail"
```