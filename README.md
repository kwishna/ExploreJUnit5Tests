## About
This is just a practice project to explore JUnit5 features.  It may also have JUnit5 integration with Allure, Mocking, RestAssured

### Serenity Junit5:
```java
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.extension.ExtendWith;
/**
 *  // https://github.com/serenity-bdd/serenity-junit-starter/blob/master/src/test/java/starter/acceptancetests/WhenSearchingForTerms.java
 *      <dependency>
 *             <groupId>net.serenity-bdd</groupId>
 *             <artifactId>serenity-junit5</artifactId>
 *             <version>${serenity.version}</version>
 *             <scope>test</scope>
 *         </dependency>
 */
@ExtendWith(SerenityJUnit5Extension.class)
public class SerenityJunit5RunnerTest {
}
```

### Resources
Allure Junit5 - https://allurereport.org/docs/junit5/
SWTest - https://www.swtestacademy.com/category/test-automation/testing-frameworks/junit/
