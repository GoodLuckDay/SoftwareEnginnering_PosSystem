# SoftwareEnginnering_PosSystem
SoftwareEnginnering Term Project
* **조장 : 조현욱**
* 조원 : 임대영, 정윤수

## Introduction
<pre>1. 결코 master에 pullRequest를 날리지 마세요. (새로이 브랜치를 만들고 작업을 할것)
2. commit 내용은 어떤 내용을 수정을 했는지 알수 있어야 합니다.
3. 자신의 작업을 할 때 어느 곳을 건드리겠다고 이야기를 해주세요.
4. 아래에 DB용 외부 라이브러리에 대해서 문의가 있다면 언제 든지 이야기 해주세요. (전 거의 일어나 있으니......)
5. git add *로 모든 파일들을 stage에 올려 pull request를 보내지 마세요 자신이 수정한 파일만 올려야 충돌이 일어나지 않습니다.
6. 기능 하나 구현 할 때 마다 pull request를 보내주세요</pre>

## 쉽게 할수있는 MySQL 셋팅!!!!!!
1. MySQL 실행법 : mysql -u root -p 입력후 자신이 맨 처음 설정한 비밀번호 입력해 주세요.
2. mySQL이 실행이 되면 먼저 Database를 생성을 하여야 함으로 create database jdbcTestServer; 명령어를 입력해 주세요
3. Database가 생성이 되었다면 use jdbcTestServer; 명령어로 해당 데이터 베이스로 이동
4. 그 후 아래에 명시 되어 있는 테이블들을 생성을 해주세요. (아래에 있는것들 복붙 하시면 됩니다.)
5. 마지막으로 DBCP 패키지의 DBConnectionPoolMgr 클래스에서 주석이 붙어있는곳을 건드려 주시면 됩니다.


## Apache common DBCP
>**1. 설치 라이브러리** 
>   * commons-dpcp2-2.1.1.jar<br>
>   * commons-logging-1.2.jar<br>
>   * commons-pool2-2.4.3.jar<br> 

## MySQL
> **1. JDBC 드라이버**
>   * mysql-connector-java5.1.6
>
> **2. Item Table**
>   * create table item(item_name varchar(10), item_price int, item_stock int primary key(item_name)); <br>
>
> **3. saledItem**
>   * create talbe saledItem(paymentTime datetime, saledItem_name, saledItem_count, saledItem_price); 

