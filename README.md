
I'm running an JasperReports application on tomcat 9 which is deploying fine and working good
but it's not working on Jboss EAP 7.

I can get info logs in server.log but I can't see the error logs

Changing the log level to DEBUG in JBoss EAP 7 
```
<root-logger>
    <level name="DEBUG"/>
    <handlers>
        <handler name="CONSOLE"/>
        <handler name="FILE"/>
    </handlers>
</root-logger>
```

so i can see this:
```
2020-08-11 13:05:45,188 DEBUG [net.sf.jasperreports.engine.xml.BaseSaxParserFactory] (default task-1) Error setting Xerces grammar pool of type org.apache.xerces.util.XMLGrammarPoolImpl: net.sf.jasperreports.engine.JRRuntimeException: Could not load class org.apache.xerces.util.XMLGrammarPoolImpl.
        at net.sf.jasperreports.engine.util.ClassUtils.instantiateClass(ClassUtils.java:67)
        at net.sf.jasperreports.engine.xml.BaseSaxParserFactory.setGrammarPoolProperty(BaseSaxParserFactory.java:227)
        at net.sf.jasperreports.engine.xml.BaseSaxParserFactory.enableSchemaCaching(BaseSaxParserFactory.java:186)
        at net.sf.jasperreports.engine.xml.BaseSaxParserFactory.configureParser(BaseSaxParserFactory.java:162)
        at net.sf.jasperreports.engine.xml.BaseSaxParserFactory.createParser(BaseSaxParserFactory.java:108)
        at net.sf.jasperreports.engine.xml.JRXmlDigesterFactory.createParser(JRXmlDigesterFactory.java:1596)
        at net.sf.jasperreports.engine.xml.JRXmlDigesterFactory.createDigester(JRXmlDigesterFactory.java:1565)
        at net.sf.jasperreports.engine.xml.JRXmlLoader.load(JRXmlLoader.java:263)
        at net.sf.jasperreports.engine.xml.JRXmlLoader.load(JRXmlLoader.java:219)
        at net.sf.jasperreports.engine.xml.JRXmlLoader.load(JRXmlLoader.java:194)
        at net.sf.jasperreports.engine.xml.JRXmlLoader.load(JRXmlLoader.java:185)
        at com.rock.example.JasperTest.generateReport(JasperTest.java:63)
        at com.rock.example.JasperTest.processRequest(JasperTest.java:31)
        at com.rock.example.JasperTest.doGet(JasperTest.java:105)
        at javax.servlet.http.HttpServlet.service(HttpServlet.java:503)
        at javax.servlet.http.HttpServlet.service(HttpServlet.java:590)
        at io.undertow.servlet.handlers.ServletHandler.handleRequest(ServletHandler.java:74)
        at io.undertow.servlet.handlers.security.ServletSecurityRoleHandler.handleRequest(ServletSecurityRoleHandler.java:62)
        at io.undertow.servlet.handlers.ServletChain$1.handleRequest(ServletChain.java:68)
        at io.undertow.servlet.handlers.ServletDispatchingHandler.handleRequest(ServletDispatchingHandler.java:36)
        at org.wildfly.extension.undertow.security.SecurityContextAssociationHandler.handleRequest(SecurityContextAssociationHandler.java:78)
        at io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43)
        at io.undertow.servlet.handlers.RedirectDirHandler.handleRequest(RedirectDirHandler.java:68)
        at io.undertow.servlet.handlers.security.SSLInformationAssociationHandler.handleRequest(SSLInformationAssociationHandler.java:132)
        at io.undertow.servlet.handlers.security.ServletAuthenticationCallHandler.handleRequest(ServletAuthenticationCallHandler.java:57)
        at io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43)
        at io.undertow.security.handlers.AbstractConfidentialityHandler.handleRequest(AbstractConfidentialityHandler.java:46)
        at io.undertow.servlet.handlers.security.ServletConfidentialityConstraintHandler.handleRequest(ServletConfidentialityConstraintHandler.java:64)
        at io.undertow.security.handlers.AuthenticationMechanismsHandler.handleRequest(AuthenticationMechanismsHandler.java:60)
        at io.undertow.servlet.handlers.security.CachedAuthenticatedSessionHandler.handleRequest(CachedAuthenticatedSessionHandler.java:77)
        at io.undertow.security.handlers.NotificationReceiverHandler.handleRequest(NotificationReceiverHandler.java:50)
        at io.undertow.security.handlers.AbstractSecurityContextAssociationHandler.handleRequest(AbstractSecurityContextAssociationHandler.java:43)
        at io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43)
        at org.wildfly.extension.undertow.security.jacc.JACCContextIdHandler.handleRequest(JACCContextIdHandler.java:61)
        at io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43)
        at org.wildfly.extension.undertow.deployment.GlobalRequestControllerHandler.handleRequest(GlobalRequestControllerHandler.java:68)
        at io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43)
        at io.undertow.servlet.handlers.ServletInitialHandler.handleFirstRequest(ServletInitialHandler.java:269)
        at io.undertow.servlet.handlers.ServletInitialHandler.access$100(ServletInitialHandler.java:78)
        at io.undertow.servlet.handlers.ServletInitialHandler$2.call(ServletInitialHandler.java:133)
        at io.undertow.servlet.handlers.ServletInitialHandler$2.call(ServletInitialHandler.java:130)
        at io.undertow.servlet.core.ServletRequestContextThreadSetupAction$1.call(ServletRequestContextThreadSetupAction.java:48)
        at io.undertow.servlet.core.ContextClassLoaderSetupAction$1.call(ContextClassLoaderSetupAction.java:43)
        at org.wildfly.extension.undertow.security.SecurityContextThreadSetupAction.lambda$create$0(SecurityContextThreadSetupAction.java:105)
        at org.wildfly.extension.undertow.deployment.UndertowDeploymentInfoService$UndertowThreadSetupAction.lambda$create$0(UndertowDeploymentInfoService.java:1541)
        at org.wildfly.extension.undertow.deployment.UndertowDeploymentInfoService$UndertowThreadSetupAction.lambda$create$0(UndertowDeploymentInfoService.java:1541)
        at org.wildfly.extension.undertow.deployment.UndertowDeploymentInfoService$UndertowThreadSetupAction.lambda$create$0(UndertowDeploymentInfoService.java:1541)
        at org.wildfly.extension.undertow.deployment.UndertowDeploymentInfoService$UndertowThreadSetupAction.lambda$create$0(UndertowDeploymentInfoService.java:1541)
        at io.undertow.servlet.handlers.ServletInitialHandler.dispatchRequest(ServletInitialHandler.java:249)
        at io.undertow.servlet.handlers.ServletInitialHandler.handleRequest(ServletInitialHandler.java:174)
        at io.undertow.server.handlers.HttpContinueReadHandler.handleRequest(HttpContinueReadHandler.java:79)
        at io.undertow.server.handlers.PathHandler.handleRequest(PathHandler.java:91)
        at org.wildfly.extension.undertow.Host$OptionsHandler.handleRequest(Host.java:399)
        at io.undertow.server.handlers.HttpContinueReadHandler.handleRequest(HttpContinueReadHandler.java:79)
        at org.wildfly.extension.undertow.Host$AcmeResourceHandler.handleRequest(Host.java:421)
        at io.undertow.server.Connectors.executeRootHandler(Connectors.java:376)
        at io.undertow.server.HttpServerExchange$1.run(HttpServerExchange.java:830)
        at org.jboss.threads.ContextClassLoaderSavingRunnable.run(ContextClassLoaderSavingRunnable.java:35)
        at org.jboss.threads.EnhancedQueueExecutor.safeRun(EnhancedQueueExecutor.java:1982)
        at org.jboss.threads.EnhancedQueueExecutor$ThreadBody.doRunTask(EnhancedQueueExecutor.java:1486)
        at org.jboss.threads.EnhancedQueueExecutor$ThreadBody.run(EnhancedQueueExecutor.java:1348)
        at java.lang.Thread.run(Thread.java:748)

Caused by: java.lang.ClassNotFoundException: org.apache.xerces.util.XMLGrammarPoolImpl from [Module "deployment.demo.war" from Service Module Loader]
        at org.jboss.modules.ModuleClassLoader.findClass(ModuleClassLoader.java:255)
        at org.jboss.modules.ConcurrentClassLoader.performLoadClassUnchecked(ConcurrentClassLoader.java:410)
        at org.jboss.modules.ConcurrentClassLoader.performLoadClass(ConcurrentClassLoader.java:398)
        at org.jboss.modules.ConcurrentClassLoader.loadClass(ConcurrentClassLoader.java:116)
        at java.lang.Class.forName0(Native Method)
        at java.lang.Class.forName(Class.java:348)
        at net.sf.jasperreports.engine.util.JRClassLoader.loadClassForRealName(JRClassLoader.java:174)
        at net.sf.jasperreports.engine.util.JRClassLoader.loadClassForName(JRClassLoader.java:114)
        at net.sf.jasperreports.engine.util.ClassUtils.instantiateClass(ClassUtils.java:55)
        ... 61 more
```

Add dependency on jboss-deployment-structure.xml
```
jboss-deployment-structure.xml

<jboss-deployment-structure xmlns="urn:jboss:deployment-structure:1.0">
        <deployment>
                <exclusions>
                        <module name="org.apache.commons.logging" />
                        <module name="org.apache.log4j" />
                        <module name="org.slf4j" />
                </exclusions>
                <dependencies>
                        <module name="org.apache.xerces" />
                </dependencies>
        </deployment>
</jboss-deployment-structure>

```

good tutorial by "http://vikasjavablogs.blogspot.com/2013/07/jasper-report-from-servlet-and-mysql_15.html"