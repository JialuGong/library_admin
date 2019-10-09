# admin module for mandarin  library

###### it's a simple web project use vue-cli and spring booot
> front-end :[Huaiyu Gong](https://github.com/JialuGong)<br>
> back-end :[floatinjianghu](https://github.com/floatinjianghu)

### how to run
###### addtional remarks
front-end port:8001
back-end portt:8080
（请注意这两个端口有无被占用）
###### front-end
1. install node(推荐使用nvm)
	- windows 安装[nvm](https://github.com/coreybutler/nvm-windows/releases)
	- 安装node(v12.10.0)
	```shell
    nvm install 12.10.0
    ```
    - 使用node(v12.10.0)
    ```shell
    nvm use 12.10.0
    ```
2. run the front-end project
	进入front-end项目根目录：/front-end/mandarin_admin
    ```shell
    npm install
    npm run serve:randy
    ```
3. run the mockserver
	进入mockserver项目根目录：/mock
    ```shell
    npm install
    node apis.js
    ```
- ps：若npminstall较慢，请切换到淘宝源
- pss：对于nvm出现无法安装的问题：请在nvm的setting.txt中添加淘宝源

###### back-end(need to install [idea](https://www.jetbrains.com/))
todo : )====>(need floadinginJianghu to complete)

### the GUI
>login

![avatar](images/login.png)
>home

![avatar](images/home.png)

>fineValue

![avatar](images/deposit.png)

>mangeLirarian

![avatar](images/librarian.png)

### TODO
- resolve error
For login.已经接受到消息，但是无法登陆，catch到的error为undefined.（查看是否为拦截器问题）
- test
