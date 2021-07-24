book: springquickstart - rubypaper

// 109p
--mariadb
CREATE TABLE board (
	seq int PRIMARY KEY,
	title nvarchar(200),
	writer nvarchar(20),
	content nvarchar(2000),
	regdate DATE DEFAULT NOW(),
	cnt int(5) DEFAULT 0
)

1. bean 실습 
패키지: com.springbook.biz.polymorphism

2. jdbc 실습
패키지: com.springbook.biz.board

3. jdbcTemplate
패키지: com.springbook.biz.springJdbc

4. jdbcTemplate + transaction(aop)
패키지: com.springbook.biz.transaction