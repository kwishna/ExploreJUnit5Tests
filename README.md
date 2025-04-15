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
Allure Junit5 - https://allurereport.org/docs/junit5
SWTest - https://www.swtestacademy.com/category/test-automation/testing-frameworks/junit
Properties - https://junit.org/junit5/docs/snapshot/user-guide/#writing-tests-parallel-execution-config-properties

### Notes
| Feature | Class | Type  |
| ------ | ------ |-------|
| Represents | Non-generic/raw types | All types (including generics) |
| Subtypes | None (concrete class) | ParameterizedType, TypeVariable, etc. |
| Use Case | Runtime type checks, reflection | Handling generics, libraries |
| Convertibility | Class is a Type| Not all Type are Class|

- Use Class for simple types and Type for generics. They are not directly interchangeable, but Class is a subset of Type.

