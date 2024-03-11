-- jsp_bbs 테이블 생성
-- BBS(Bulletin Board System : 전자 게시판)

create table jsp_bbs(
	board_no int(7) primary key,			-- 게시판 글 번호
	board_writer varchar(50) not null,		-- 게시판 글 작성자
	board_title varchar(200) not null,		-- 게시판 글 제목
	board_cont varchar(2000) not null,		-- 게시판 글 내용
	board_pwd varchar(50) not null,			-- 게시판 글 비밀번호	
	board_hit int(5) default 0,				-- 게시판 글 조회수
	board_date datetime not null,			-- 게시판 글 작성일
	board_update datetime,					-- 게시판 글 수정일
	board_group int(7),						-- 게시판 글 그룹
	board_step int(5),						-- 게시판 글 답변 단계
	board_indent int(5)						-- 게시판 답변글 들여쓰기,

);

-- jsp_bbs 테이블에 데이터 추가하기
insert into jsp_bbs values(1, '홍길동', '제목1', '내용1', '1111', default, now(), null, 1, 0, 0);
insert into jsp_bbs values(2, '이순신', '장군 글', '장군 글입니다.', '1111', default, now(), null, 2, 0, 0);
insert into jsp_bbs values(3, '유관순', '열사', '유관순 열사 글입니다.', '1111', default, now(), null, 3, 0, 0);
insert into jsp_bbs values(4, '김유신', '화랑도', '화랑도 글입니다.', '1111', default, now(), null, 4, 0, 0);
insert into jsp_bbs values(5, '김연아', '연아', '내용5', '1111', default, now(), null, 5, 0, 0);
