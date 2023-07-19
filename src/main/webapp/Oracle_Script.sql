
drop table board; 

create table board (
    seq number(5) not null primary key, 
    title varchar2(200) null , 
    write varchar2(20) null, 
    content varchar2(2000) null,
    regdate date default sysdate null , 
    cnt number(5)  default 0 null 
    ) ; 
  
insert into board 
values ( 1, 'MVC M2 게시판 제목', 'admin', 'MVC M2 게시판 내용', default, 0); 
    
insert into board 
values ( 2, 'MVC M2 게시판 제목2', 'user', 'MVC M2 게시판 내용2', default, 0); 
commit; 

-- sql 에서 commit을 하지 않는 경우; 무한정 대기상태에 빠진다. (LOCK) 
insert into board (seq, title, write, content) 
values ( 4 , '글제목 4', 'admin', '글내용 4');




select * from board
order by seq desc ; 
    

create table users (
    id varchar2(8) not null primary key, 
    password varchar2(8) null ,
    name varchar2(20) null, 
    role varchar2(5)
    ) ; 
    
    
-- 더미 데이터 입력     
insert into users 
values ( 'admin', '1234', '관리자' , 'Admin');  

insert into users 
values ( 'user', '1234', '일반사용자', 'Users') ; 
commit ; 
select * from users; 


select nvl(max(seq),0) + 1 from board


-- 제품을 넣는 테이블 : 관리자 (Admin) - insertProducts.jsp   : 로그인후 Admin 롤을 가진 사용자만 접근가능
--                                 getProductList.jsp   : 로그인후 Admin 롤을 가진 사용자만 접근가능
CREATE TABLE products (
	p_code	number(5)		NOT NULL  primary key,
	p_name	varchar2(100)		NULL,
	p_kind	char(1)		NULL,
	p_price	varchar2(10)		NULL,
	p_content	varchar2(1000)		NULL,
	p_quantity	varchar2(5)		NULL,
	indate	date	default sysdate	NULL
);


    