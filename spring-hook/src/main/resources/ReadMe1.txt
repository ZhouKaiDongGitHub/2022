Spring作为一个容器，我们一般使用只需要直接启动，用如下2种方式
1. ApplicationContext context = new AnnotationApplicationContext();
    context.register(AppConfig.class)
    context.refresh();
    context.start();
   ApplicationContext context = new XmlApplicationContext();
   context.scan(application.xml);
   context.refresh();
   context.start();

    SpringMVC : 核心对象DispatcherServlet
    DispatcherServlet servlet = new DispatcherServlet();
    servlet.addApplicationContext(context);

    http://localhost:8080/monitor.jsp

    本地开启一个TCP端口 8080, Tomcat容器/服务器。服务器是一个开启的一个一直监听硬件某一个端口的程序。
    Catalina catalina = new Catalina();
    ....
    catalina.addContext();
    Tomcat会找，找实现Servlet接口的实现类，发现了这个玩意 DispatcherServlet。所以我们说Tomcat是Servlet容器

    while(true){
        Catalina catalina = new Catalina();
            ....
        catalina.addContext();
        {
            DispatcherServlet servlet = new DispatcherServlet();
                servlet.addApplicationContext(context);
                ApplicationContext context = new XmlApplicationContext();
                   context.scan(application.xml);
                   context.refresh();
                   context.start();
        }
    }
2. SpringApplication.run(SpringBootApplication.class)
   Enbad --> Catalina
   Catalina catalina = new Catalina();
   帮你Tomcat的事情做了。所以直接启动了

所以说Spring启动的过程我们是无感知的，不需要使用的程序员进行参与的。这完全满足我们正常启动一个项目的调用。但是但是...
当我们想用Spring管理另一个Jar包中的类的时候，让Spring管理根本是不现实的。Spring不会给你扩展的。这时候就需要使用Spring提供的扩展点