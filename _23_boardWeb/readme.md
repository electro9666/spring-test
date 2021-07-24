book: springquickstart - rubypaper

spring mvc

# springloaded 추가 (성공)
boardDAO의 수정에서는 에러 발생.
java.lang.IllegalAccessException: Class org.springsource.loaded.ReloadableType can not access a member of class org.springframework.aop.framework.CglibAopProxy$ClassLoaderAwareUndeclaredThrowableStrategy with modifiers "public"

aop가 관련되어 있는것 보니, @Repository("boardDAO")인 경우는 트랜잭션 설정 때문에?

VM arguments에
-javaagent:C:\Users\ysh\.m2\repository\org\springframework\springloaded\1.2.8.RELEASE\springloaded-1.2.8.RELEASE.jar -noverify

# dcevm 테스트
소스변경하고, 화면 새로고침하면 에러....-_-;
java.sql.SQLException: Data source is closed

VM arguments에
-XXaltjvm=dcevm -javaagent:F:\java_apps\hotswap-agent-1.4.1.jar