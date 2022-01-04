drop table if exists banner_info;
drop table if exists banner;
drop table if exists pds_item;

create table pds_item (
	uuid varchar(100) primary key,
	uploadPath varchar(200) not null,
	fileName varchar(100) not null,
	-- 1(배너), 2(PC:리스트이미지), 3(PC:상품대표이미지-4개), 4(PC:메인노출이미지),
	-- 5(Mobile:리스트이미지), 6(Mobile:상품대표이미지-4개), 7(Mobile:메인노출이미지)
	fileType varchar(2) default "1",
	fileSize int,
	dcount int default 0,
	gid int,	-- bid, pid
	createdAt datetime default current_timestamp,
	modifiedAt datetime default current_timestamp on update current_timestamp
);

/*
 * 배너 종류(kind) : 1(GNB), 2(메인), 3(스타일숍 리스트), 4(오픈숍 리스트),
 * 		5(서브 메뉴), 6(커뮤니티 리스트), 7(고객센터), 8(상품 주문 완료)
 * 배너 위치(position) : 1(상단), 2(오른쪽), 3(왼쪽), 4(하단)
 * 노출방식(location) : 1(슬라이더), 2(랜덤), 3(로그인 전/후), 4(노출하지 않음)
 */
create table banner (
	bid int primary key auto_increment,
	kind int not null,
	position int not null,
	location int not null,
	createdAt datetime default current_timestamp,
	modifiedAt datetime default current_timestamp on update current_timestamp
);

-- 배너 종류와 배너 위치에 따라 배너를 정의
create index idx_banner on banner(kind, position);

create table banner_info (
	info_id int primary key auto_increment,
	bid int not null,
	url varchar(100),
	alt varchar(100),
	target varchar(10),
	loginBefore int default 0,
	createdAt datetime default current_timestamp,
	modifiedAt datetime default current_timestamp on update current_timestamp,
	foreign key (bid) references banner(bid)
);

select * from banner;
select * from banner_info;
select * from pds_item;

create table member(
	id varchar(10) primary key,
	pwd varchar(60) not null,
	name varchar(20) not null,
	mobile varchar(15),
	email varchar(20) not null,
	marketing varchar(2) default 'n',	-- 마케팅 정보 제공 동의
	rcv varchar(2) default 'y',	-- 메일 수신 여부 : 'n'(no), 'y'(yes)
	grade int default 1,	-- 회원등급 : 1~5
	visited int default 0,	-- 방문 수
	pursuit int default 0,  -- 구매금액 : 100원 단위
	accum int default 0,	-- 적립금
	createdAt datetime default current_timestamp,
	modifiedAt datetime default current_timestamp on update current_timestamp
);

drop table member;
delete from member;

select * from member;
update member set grade=3 where id='admin3';

create table category (
	name varchar(30) not null,
	code varchar(20) not null primary key,
	expose boolean default true,
	gnb boolean default true,
	step int not null,	-- 1(1차) 또는 2(2차)
	seq int not null,	-- 순서 1~
	parent varchar(20),	-- 1차 일경우 null, 2차 일 경우 1차에 대한 code 저장
	createdAt datetime default current_timestamp,
	modifiedAt datetime default current_timestamp on update current_timestamp
);

select * from category;
drop table category;

drop table product_info;
drop table product_option;
drop table product_history;
drop table product;

create table product (		-- 상품(product) 테이블
	pid int primary key auto_increment,	-- 상품 아이디
	category1 varchar(20) not null,	-- 1차 상품 카테고리
	category2 varchar(20) not null,	-- 2차 상품 카테고리
	name varchar(60) not null,		-- 상품 이름
	price int not null,				-- 정상 가격
	saleprice int not null,			-- 판매가격
	maxpurchase int default 10,		-- 최대 구매 가능 수
	deposit int default 0,			-- 적립포인트 : -1(기본 포인트), 0(포인트 없음), >0(개별 포인트 %)
	delivery int default 0,			-- 배송비 : -1(기본 배송비), 0(무료 배송), >0(개별 배송비)
	newp boolean default false,		-- 상품 특성 : 신상품
	best boolean default false,		-- 상품 특성 : Best
	discount boolean default false,	-- 상품 특성 : 할인 
	info boolean default false,		-- 상품 정보 : true(사용), false(미사용)
	opt boolean default false,		-- 옵션 : true(사용), false(미사용)
	userid varchar(10) not null,	-- 상품등록자
	readcount int default 0,		-- 조회수
	introduction varchar(100) not null,	-- 상품 소개
	pc_detail text not null,		-- 상세설명(PC)
	mobile_detail text not null,	-- 상세설명(Mobile)
	pc_delivery text,				-- 배송 안내(PC) : null(공통), 그외(개별)
	mobile_delivery text,			-- 배송 안내(Mobile) : null(공통), 그외(개별)
	pc_exchange text,				-- 교환 및 반품 안내(PC) : null(공통), 그외(개별)
	mobile_exchange text,			-- 교환 및 반품 안내(Mobile) : null(공통), 그외(개별)
	memo varchar(300),				-- 상품 메모
	expose varchar(10) not null,	-- 상품 노출 여부 : show(진열), out(품절), hide(숨김)
	createdAt datetime default current_timestamp,
	modifiedAt datetime default current_timestamp on update current_timestamp
);

select count(*) from product where name like '%코%';
select count(*) from product where name like '%코%'
	and category1='스타일 숍' and category2='0020010'
	and (salePrice between 90000 and 110000) and
	(date(createdAt) between '2021-11-30' and '2021-12-15')
	and (expose='show' or expose='hide' or expose='out');
	
select count(*) from product where name like '%코%' 
	and category1='스타일 숍' and
	date(createdAt) between '2021-11-30' and '2021-12-15';

select count(*) from product where name like '%코트%'
	and category1='스타일 숍' and category2='Outer'
	and (salePrice between 90000 and 110000)
	and (date(createdAt) between '2021-12-01' and '2021-12-16')
	and (expose='show' or expose='out' or expose='hide');
	
select count(*) from product where name like '%코트%' and category1='스타일 숍' and category2='Outer' and (salePrice between 90000 and 110000) and (date(createdAt) between '2021-12-01' and '2021-12-15') and (expose='show' or expose='out' or expose='hide');

create table product_info (		-- 상품 정보 테이블
	pi_id int primary key auto_increment,	-- 식별자
	name varchar(30) not null,			-- 항목 명
	description varchar(60) not null,	-- 설명
	pid int not null,					-- 상품 아이디
	createdAt datetime default current_timestamp,
	modifiedAt datetime default current_timestamp on update current_timestamp,
	foreign key (pid) references product(pid)
);

create table product_option (	-- 상품 옵션 테이블
	po_id int primary key auto_increment,	-- 식별자
	gid int not null,					-- 그룹 ID : 옵션 묶음
	name varchar(30) not null,			-- 옵션명
	description varchar(60) not null,	-- 설명
	price int not null,					-- 가격
	pid int not null,
	createdAt datetime default current_timestamp,
	modifiedAt datetime default current_timestamp on update current_timestamp,
	foreign key (pid) references product(pid)
);

create table product_history (		-- 상품 이력 테이블
	hid int primary key auto_increment,
	item int not null,				-- 1(등록), 2(수정), 3(삭제)
	timeAt datetime default current_timestamp,	-- 날자/시간 : yyyy-MM-dd HH:mm:ss
	userid varchar(10) not null, 	-- 관리자 아이디
	pid int not null,
	foreign key (pid) references product(pid),
	foreign key (userid) references member(id)
);

select * from information_schema.table_constraints;
alter table product_history drop foreign key product_history_ibfk_1;
select * from product;
select * from product_info where pid=5;
select * from product_option where pid=5;
select * from pds_item;
delete from pds_item where dcount=0;
update product set category2='Skirt' where pid=6;
update product set category2='Top' where pid=3;
update product set category2='Bottom' where pid=4;
update product set expose='진열' where pid=1;

create table terms (				-- 약관 관리 테이블
	tid int primary key auto_increment,	-- 아이디
	title varchar(100) not null,	-- 제목
	content text not null,			-- 내용
	expose boolean default false,	-- 노출 여부 : true(사용), false(사용 안함)
	mandatory boolean default false,-- 필수 여부 : true(필수), false(선택)
	createdAt datetime default current_timestamp,
	modifiedAt datetime default current_timestamp on update current_timestamp
);

select * from terms;
drop table terms;

create table policy (
	code int not null,				-- 코드 : 데이터에 따라 고유한 값을 가진다.
	category varchar(60) not null,	-- 분류 : 사용되는 기능에 연관
	name varchar(100) not null,		-- 이름 : 사용되는 파라미터 종류(이름으로 구분)
	value text not null,			-- 값 : 가지는 값 (여러가지 데이터 유형을 가질 수 있으나 문자열로 저장)
	createdAt datetime default current_timestamp,
	modifiedAt datetime default current_timestamp on update current_timestamp
);

select * from policy;
drop table policy;