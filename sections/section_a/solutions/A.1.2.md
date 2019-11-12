Kod klasy `packageOne.HelloWorld`:

```java
package pl.honestit.spring.core.warmup;

public class packageOne.HelloWorld {

    public void sayHello() {
        System.out.println("Hello, world!");
    }
}

```


Kod klasy `Application`, w którym tworzymy kontekst, rejestrujemy beana i go pobieramy, aby użyć:

```java
package pl.honestit.spring.core.app;

import org.springframework.context.support.GenericApplicationContext;
import pl.honestit.spring.core.warmup.packageOne.HelloWorld;

public class Application {

    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("helloBean", packageOne.HelloWorld.class);
        context.refresh();

        packageOne.HelloWorld bean = context.getBean("helloBean", packageOne.HelloWorld.class);
        bean.sayHello();
    }
}

```