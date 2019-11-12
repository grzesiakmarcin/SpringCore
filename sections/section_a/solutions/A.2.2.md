
Klasa konfiguracyjna `WarmUpConfiguration`:

```java
package pl.honestit.spring.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.honestit.spring.core.warmup.packageOne.HelloWorld;

@Configuration
public class WarmUpConfiguration {

    @Bean
    public packageOne.HelloWorld helloBean() {
        return new packageOne.HelloWorld();
    }
}

```

Wykorzystanie konfiguracji w metodzie main `Application`:

```java
package pl.honestit.spring.core.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import pl.honestit.spring.core.config.WarmUpConfiguration;
import pl.honestit.spring.core.warmup.packageOne.HelloWorld;

public class Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(WarmUpConfiguration.class);
        context.refresh();

        packageOne.HelloWorld helloBean = context.getBean("helloBean", packageOne.HelloWorld.class);
        helloBean.sayHello();
    }

    private static void classPathWarmUp() {

        // ...
    }

    private static void genericWarmUp() {
        
        // ...
    }
}

```

Automatyczne wykrywanie komponentu z adnotacją `@Component` w klasie `packageOne.HelloWorld`:

```java
package pl.honestit.spring.core.warmup;

import org.springframework.stereotype.Component;

@Component
public class packageOne.HelloWorld {

    public void sayHello() {
        System.out.println("Hello, world!");
    }
}

```

Uzupełnienie metody `main` w klasie `Application` o obsługę automatycznego skanowania:

```java
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(WarmUpConfiguration.class);
        context.scan("pl.honestit.spring.core.warmup");
        context.refresh();

        packageOne.HelloWorld helloBean = context.getBean("helloBean", packageOne.HelloWorld.class);
        helloBean.sayHello();
    }
```


Kod testujący różne sposoby pobierania bean'ów:

```java
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(WarmUpConfiguration.class);
        context.scan("pl.honestit.spring.core.warmup");
        context.refresh();

        packageOne.HelloWorld helloBean = context.getBean("helloBean", packageOne.HelloWorld.class);
        helloBean.sayHello();

        packageOne.HelloWorld helloWorld = context.getBean("helloWorld", packageOne.HelloWorld.class);
        helloWorld.sayHello();

        System.out.println(helloBean == helloWorld);

        packageOne.HelloWorld helloBean2 = context.getBean("helloBean", packageOne.HelloWorld.class);
        packageOne.HelloWorld helloWorld2 = context.getBean("helloWorld", packageOne.HelloWorld.class);

        System.out.println(helloBean == helloBean2);
        System.out.println(helloWorld == helloWorld2);

        // To nie zadziała - nie istnieje taki bean
        packageOne.HelloWorld buzzBuzz = context.getBean("buzzBuzz", packageOne.HelloWorld.class);
        buzzBuzz.sayHello();

        // To też nie zadziała - niejednoznaczna zależność
        packageOne.HelloWorld bean = context.getBean(packageOne.HelloWorld.class);
        bean.sayHello();
    }
```