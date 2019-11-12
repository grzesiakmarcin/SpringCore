import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import packageOne.HelloWorld;

public class Application {
    public static void main (String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(WarmUpConfiguraiton.class);
        context.scan("packageOne");
        context.refresh();



        HelloWorld helloBean = context.getBean("helloBean", HelloWorld.class);  // ten obiekt jest tworzony w WarmUpConfiguration
        helloBean.sayHello();

        HelloWorld helloWorld = context.getBean("helloWorld", HelloWorld.class);// Ten jest tworzony w helloWorld jezeli nie ma w HelloWorld adnotacji @Component to apka tutaj się wysypie
        helloWorld.sayHello();

        System.out.println(helloWorld==helloBean);
        System.out.println(helloWorld);
        System.out.println(helloBean);


        HelloWorld helloBean2 = context.getBean("helloBean", HelloWorld.class);
        HelloWorld helloWorld2 = context.getBean("helloWorld", HelloWorld.class);

        System.out.println(helloBean == helloBean2);
        System.out.println(helloWorld == helloWorld2);


    }

    private static void classPathWarmUp () {
        GenericApplicationContext genericApplicationContext = new GenericApplicationContext();
        genericApplicationContext.registerBean("helloBean", HelloWorld.class);
        genericApplicationContext.refresh();


        HelloWorld bean = genericApplicationContext.getBean("helloBean", HelloWorld.class); // wyciągamy obiekt  z geneticApplicationContext
        bean.sayHello();
    }
}
