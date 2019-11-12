Kod klasy `packageOne.HelloWorld` z polem typu `printer` i luźnymi zależnościami podawanymi z zewnątrz:

```java
package pl.hit.spring.core.warmup;

import pl.hit.spring.core.components.ConsolePrinter;
import pl.hit.spring.core.components.DialogPrinter;
import pl.hit.spring.core.components.FilePrinter;
import pl.hit.spring.core.components.Printer;

public class packageOne.HelloWorld {

    private Printer printer;

    public packageOne.HelloWorld(Printer printer) {
        this.printer = printer;
    }

    public void setPrinter(Printer printer) {
        this.printer = printer;
    }

    public void sayHello() {
        printer.print("Hello, world");
    }

    public static void main(String[] args) {
        packageOne.HelloWorld firstHello = new packageOne.HelloWorld(new ConsolePrinter());
        firstHello.sayHello();

        packageOne.HelloWorld secondHello = new packageOne.HelloWorld(new FilePrinter());
        secondHello.sayHello();

        packageOne.HelloWorld thirdHello = new packageOne.HelloWorld(new DialogPrinter());
        thirdHello.sayHello();
    }
}
```