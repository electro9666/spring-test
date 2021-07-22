book: springquickstart - rubypaper

-- 130p
--mariadb
CREATE TABLE users (
	id nvarchar(8) primary key,
	password nvarchar(8),
	name nvarchar(20),
	role nvarchar(5)
)

5. model2 아키텍처
spring이 아니라 servlet 구현체로 작동함. - if else if else if else if...
톰캣으로 연결해서 실행

# springloaded 추가(성공)
VM arguments에
-javaagent:C:\Users\ysh\.m2\repository\org\springframework\springloaded\1.2.8.RELEASE\springloaded-1.2.8.RELEASE.jar -noverify
