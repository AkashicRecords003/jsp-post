#### Jsp 게시판 만들기

## 오라클 12C 사용자 생성
```sql
alter session set "_ORACLE_SCRIPT"=true;  
CREATE USER apple IDENTIFIED BY bitc5600;

GRANT CREATE SESSION TO apple;
GRANT CREATE TABLESPACE TO apple;
GRANT CREATE TABLE TO apple;
GRANT CREATE SEQUENCE TO apple;
alter user apple default tablespace users quota unlimited on users;
```

## 테이블
```sql
CREATE TABLE member(
	id number primary key,
    username varchar2(100) not null unique,
    password varchar2(100) not null,
    email varchar2(100) not null,
    createDate timestamp
) ;

CREATE TABLE post(
	id number primary key,
    memberId number,
    title varchar2(100) not null,
    content clob,
    createDate timestamp,
    foreign key (memberId) references member (id)
);
```

## 시퀀스
```sql
CREATE SEQUENCE MEMBER_SEQ
  START WITH 1
  INCREMENT BY 1;
  
CREATE SEQUENCE POST_SEQ
  START WITH 1
  INCREMENT BY 1;

