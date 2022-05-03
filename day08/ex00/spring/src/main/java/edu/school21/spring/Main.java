package edu.school21.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

//    public static void main(String[] args) {
//            PreProcessor preProcessor = new PreProcessorToUpperImpl();
//            Renderer renderer = new RendererErrImpl(preProcessor);
//            PrinterWithPrefixImpl printer = new PrinterWithPrefixImpl(renderer);
//            printer.setPrefix("Prefix");
//            printer.printWith("Hello!");
//    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        Printer printer = context.getBean("printerBean", Printer.class);
        printer.printWith("Hello!");
    }
}
