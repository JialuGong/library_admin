# admin module for mandarin  library

###### it's a simple web project use vue-cli and spring booot
> front-end :[Huaiyu Gong](https://github.com/JialuGong)<br>
> back-end :[floatinjianghu](https://github.com/floatinjianghu)


you can view it on [mandarin|admin](http://47.92.141.59)
___

### functions
- [x] login in/out
- [x] register new librarian
- [x] edit/delete librarian
- [x] set/modify fine value
- [x] set/modify book return value
- [x] set/modify reader deposit value
- [x] obtian librarian password
- [x] change admin password

All functions have been finished.
___
- release 2
> origin

- [x] searching libarain account
- [x] setting book fine value

 > now

- [x] searching librarian account
- [x] setting book information
- [x] obtaining librarian password
- [x] setting readers's  deposit

- ralease 3
> oringin

- [x] obtain librarian password
- [x] setting reader's deposit
- [x] change admin password

 > now

- [x] change password
___
- front-end
	- develop mode:
		```js
        npm install
        npm run server
        ```
	- for production mode
	```js
    npm run build
    ```
- back-end
	- run sql-file to create database
	- run this spring-boot projects
	```js
    mvn clean
    mvn install
    mvn spring-boot:run
	```
