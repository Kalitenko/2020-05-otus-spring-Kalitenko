import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring.service.TestingServiceImpl;

@ComponentScan("ru.otus.spring")
public class TestingApp {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestingApp.class);

        System.out.println("context = " + context);

        TestingServiceImpl testingService = context.getBean("testingService", TestingServiceImpl.class);
        testingService.toTest();

    }
}
