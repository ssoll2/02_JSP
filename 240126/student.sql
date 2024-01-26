CREATE TABLE student (
    hakbun VARCHAR(20) PRIMARY KEY, 
    irum VARCHAR(30) NOT NULL,
    major VARCHAR(50),
    phone VARCHAR(20) NOT NULL,
    addr VARCHAR(200),
    regdate DATE NOT NULL
);

ALTER TABLE student
CHANGE COLUMN irum name1 VARCHAR(30) NOT NULL;

INSERT INTO student VALUES ('2023_001', '홍길동', '경제학과', '010-1111-1234', '서울 구로구', CURDATE());
INSERT INTO student VALUES ('2023_003', '이순신', '영문학과', '010-3333-1234', '서울 영등포구', CURDATE());
INSERT INTO student VALUES ('2023_004', '강감찬', '체육학과', '010-4444-1234', '경기 의정부시', CURDATE());
INSERT INTO student VALUES ('2023_005', '김유신', '세무학과', '010-5555-1234', '서울 도봉구', CURDATE());
INSERT INTO student VALUES ('2023_006', '이길동', '전산학과', '010-6666-1234', '서울 노원구', CURDATE());
INSERT INTO student VALUES ('2023_007', '박길동', '유아교육학과', '010-7777-1234', '서울 강북구', CURDATE());
INSERT INTO student (hakbun, name1, phone, regdate) VALUES ('2023_008', '최길동', '010-8888-1234', CURDATE());
INSERT INTO student (hakbun, name1, phone, regdate) VALUES ('2023_009', '임길동', '010-9999-1234', CURDATE());
INSERT INTO student (hakbun, name1, phone, regdate) VALUES ('2023_010', '황길동', '010-1234-1234', CURDATE());

commit;

