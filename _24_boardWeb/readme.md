book: springquickstart - rubypaper

spring mvc + mybatis

# springloaded 테스트
서버 실행될 때, 빈 생성에서 에러 발생해서 서버가 올라가지 않음.
org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'boardDAO' defined in file [F:\workspace2020\spring-test\.metadata\.plugins\org.eclipse.wst.server.core\tmp4\wtpwebapps\_24_boardWeb\WEB-INF\classes\com\springbook\biz\board\impl\BoardDAO.class]: Instantiation of bean failed; nested exception is java.lang.ExceptionInInitializerError

Caused by: java.lang.NullPointerException
	at org.springsource.loaded.TypeRegistry.getReloadableType(TypeRegistry.java:1903)
	at com.springbook.biz.board.impl.BoardDAO.<clinit>(BoardDAO.java)
	
VM arguments에
-javaagent:C:\Users\ysh\.m2\repository\org\springframework\springloaded\1.2.8.RELEASE\springloaded-1.2.8.RELEASE.jar -noverify

# dcevm 테스트
빈을 찾지 못함.(같은 빈이 2개라서? 뭔가 빈을 찾는 규격이 다른듯... mybatis가 추가되면서 빈이 2개가 된듯...)
화면에 나오는 에러.. 
Message: Error creating bean with name 'boardService': Injection of autowired dependencies failed; nested exception is org.springframework.beans.factory.BeanCreationException: Could not autowire field: private com.springbook.biz.board.impl.BoardDAOMybatis com.springbook.biz.board.impl.BoardServiceImpl.boardDAO; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'boardDAOMybatis': Injection of autowired dependencies failed; nested exception is org.springframework.beans.factory.BeanCreationException: Could not autowire field: private org.mybatis.spring.SqlSessionTemplate com.springbook.biz.board.impl.BoardDAOMybatis.mybatis; nested exception is org.springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type [org.mybatis.spring.SqlSessionTemplate] is defined: expected single matching bean but found 2: org.mybatis.spring.SqlSessionTemplate#0,org.mybatis.spring.SqlSessionTemplate#1

소스를 수정하고, dcevm이 갱신하면, 동일한 에러..(spring bean이 누적되는듯-_-;)
expected single matching bean but found 3:
expected single matching bean but found 4:
...

헤결
그래서 bean id를 추가했고,
applicationContext.xml의 ${jdbc.url}를 읽지 못해서, xml에 하드코딩함.
Message: Could not open JDBC Connection for transaction; nested exception is org.apache.commons.dbcp.SQLNestedException: Cannot load JDBC driver class '${jdbc.driver}'

에러는 안나는데, 소스 변경이 반영되지 않는다.

VM arguments에
-XXaltjvm=dcevm -javaagent:F:\java_apps\hotswap-agent-1.3.0.jar
-XXaltjvm=dcevm -javaagent:F:\java_apps\hotswap-agent-1.3.0.jar=autoHotswap=true // 이렇게 해야 작동하네.
-XXaltjvm=dcevm -javaagent:F:\java_apps\hotswap-agent-1.3.0.jar=autoHotswap=true -XX:TraceRedefineClasses=1
=autoHotswap=true 대신, hotswap-agent.properties에서 설정하기
// -XX:TraceRedefineClasses=1은 get feedback about reloaded classes.
최종
-XXaltjvm=dcevm -javaagent:F:\java_apps\hotswap-agent-1.3.0.jar -XX:TraceRedefineClasses=1
-XXaltjvm=dcevm -javaagent:F:\java_apps\hotswap-agent-1.4.1.jar -XX:TraceRedefineClasses=1

>>>>>>
controller, service, dao 소스변경사항 반영되는 것 확인함.


# mybatis xml 변경시 리로드
단순히 mapper.xml을 wtpwebapps로 복사해준다고 끝나는 것이 아니라
mapper.xml를 읽어서 객체로 만드는 부분을 갱신해야 한다.
dcevm이 작동중이라면,
RefreshableSqlSessionFactoryBean 껍데기를 만들어서
RefreshableSqlSessionFactoryBean을 변경하고 저장하면 된다.
다른 블로그에는 타이머를 작동시키지만, 굳이 그래야 하나?

HotswapAgent의 properties설정이 가능하다.
http://hotswapagent.org/mydoc_quickstart.html
https://github.com/HotswapProjects/HotswapAgent/blob/master/hotswap-agent-core/src/main/resources/hotswap-agent.properties

- 성능에 영향이 있으므로 해당하는 케이스는
exclude하기

# mybatis refresh 추가 (timer)
RefreshableSqlSessionFactoryBean

# RefreshableSqlSessionFactoryBean 대신
watchService를 이용해서, spring init시에 외부에서 xml을 갱신시켜주면 안될까?


---

# mybatis에 mapper class로 설정
MyMapper.java를 사용해봤지만
추가: <mybatis-spring:scan base-package="com.springbook.mybatis.mapper"/>
src/main/java 폴더내에
	com.springbook.mybatis.mapper폴더에 MyMapper.java 파일 생성
src/main/resources폴더 내에
	com.springbook.mybatis.mapper폴더에 MyMapper.xml 파일 생성
		namespace를 com.springbook.mybatis.mapper.MyMapper로 설정
결과: xml 변경해도 dcevm이 적용되지 않는듯;;

참고: 
https://github.com/HotswapProjects/HotswapAgent/issues/86
https://github.com/HotswapProjects/HotswapAgent/blob/master/plugin/hotswap-agent-mybatis-plugin/src/test/java/org/hotswap/agent/plugin/mybatis/MyBatisPluginTest.java#L90
https://kimvampa.tistory.com/59