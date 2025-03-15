## Simple Object Serialization/Deserialization

Scenario: Convert a non-generic (raw) object to/from JSON.
```java
import com.google.gson.Gson;

class Person {
    private String name;
    private int age;
    // Constructor, getters, setters
}

public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();

        // Serialization
        Person person = new Person("Alice", 30);
        String json = gson.toJson(person); // {"name":"Alice","age":30}

        // Deserialization
        Person deserialized = gson.fromJson(json, Person.class);
    }
}
```
2. Raw Collections (Without Generics)

Scenario: Serialize/deserialize a raw List (no type safety).
```java
import java.util.List;
import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();

        // Serialization
        List rawList = List.of("A", 123, true);
        String json = gson.toJson(rawList); // ["A",123,true]

        // Deserialization (returns List<Object> with LinkedTreeMap for nested objects)
        List deserialized = gson.fromJson(json, List.class);

        // Accessing elements (may require casting)
        String first = (String) deserialized.get(0); // "A"
    }
}
```
3. Generic Collections (With Type Safety)

Scenario: Handle List<T>, Map<K,V>, etc., using TypeToken to retain generic type information.
```java
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();

        // Serialization
        List<String> names = List.of("Alice", "Bob");
        String json = gson.toJson(names); // ["Alice","Bob"]

        // Deserialization for List<String>
        Type listType = new TypeToken<List<String>>() {}.getType();
        List<String> deserialized = gson.fromJson(json, listType);
    }
}
```
4. Generic Classes

Scenario: Serialize/deserialize a generic class (e.g., Response<T>).
```java
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

class Response<T> {
    T data;
    int status;
    // Constructor, getters, setters
}

public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();

        // Serialization
        Response<String> response = new Response<>("Success", 200);
        String json = gson.toJson(response); // {"data":"Success","status":200}

        // Deserialization
        Type responseType = new TypeToken<Response<String>>() {}.getType();
        Response<String> deserialized = gson.fromJson(json, responseType);
    }
}
```
5. Nested Generics

Scenario: Handle complex types like Map<String, List<Person>>.
```java
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.Map;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();

        // Serialization
        Map<String, List<Person>> peopleByCity = Map.of(
            "New York", List.of(new Person("Alice", 30), new Person("Bob", 25))
        );
        String json = gson.toJson(peopleByCity);

        // Deserialization
        Type mapType = new TypeToken<Map<String, List<Person>>>() {}.getType();
        Map<String, List<Person>> deserialized = gson.fromJson(json, mapType);
    }
}
```
6. Arrays vs. Collections

Scenario: Convert between JSON arrays and Java arrays/collections.
```java
import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();

        // Array Serialization
        String[] arr = {"A", "B", "C"};
        String json = gson.toJson(arr); // ["A","B","C"]

        // Array Deserialization
        String[] deserializedArr = gson.fromJson(json, String[].class);

        // Collection Deserialization (to List)
        Type listType = new TypeToken<List<String>>() {}.getType();
        List<String> list = gson.fromJson(json, listType);
    }
}
```
7. Polymorphic Types (Inheritance)

Scenario: Serialize/deserialize objects of different subclasses using RuntimeTypeAdapterFactory.
```java
import com.google.gson.Gson;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;

abstract class Animal {
    String name;
}

class Dog extends Animal {
    String breed;
}

class Cat extends Animal {
    boolean hasClaws;
}

public class Main {
    public static void main(String[] args) {
        // Register subtypes
        TypeAdapterFactory animalAdapter = RuntimeTypeAdapterFactory.of(Animal.class, "type")
            .registerSubtype(Dog.class, "dog")
            .registerSubtype(Cat.class, "cat");

        Gson gson = new GsonBuilder()
            .registerTypeAdapterFactory(animalAdapter)
            .create();

        // Serialization
        Animal dog = new Dog();
        dog.name = "Buddy";
        ((Dog) dog).breed = "Golden Retriever";
        String json = gson.toJson(dog); // {"type":"dog","name":"Buddy","breed":"Golden Retriever"}

        // Deserialization
        Animal deserialized = gson.fromJson(json, Animal.class);
    }
}
```

## Complex Nested Objects

Scenario: An e-commerce Order containing nested Customer, Product, and Address objects.
Java Classes
```java
class Order {
    String orderId;
    Customer customer;
    List<Product> products;
    LocalDateTime orderDate;
}

class Customer {
    String id;
    String name;
    Address shippingAddress;
}

class Product {
    String sku;
    String name;
    BigDecimal price;
}

class Address {
    String street;
    String city;
    String country;
}
```
Serialization/Deserialization
```java
Gson gson = new Gson();

// Serialize
Order order = buildOrder(); // Assume this creates a populated Order
String json = gson.toJson(order);

// Deserialize
Order deserializedOrder = gson.fromJson(json, Order.class);
```

2. Paginated API Response with Generics

Scenario: A paginated API response containing a list of items and metadata.
Java Classes
```java
class ApiResponse<T> {
    int page;
    int totalPages;
    List<T> data;
}

class User {
    String userId;
    String email;
}
```
Handling Generics with TypeToken
```java
// Deserialize ApiResponse<User> from JSON
String jsonResponse = "{'page':1, 'totalPages':5, 'data':[{'userId':'123','email':'a@test.com'}]}";

Type responseType = TypeToken.getParameterized(ApiResponse.class, User.class).getType();
ApiResponse<User> response = gson.fromJson(jsonResponse, responseType);

// Serialize back to JSON
String serialized = gson.toJson(response, responseType);
```
3. Polymorphic Types (Payment Methods)

Scenario: Handle different payment methods (CreditCard, PayPal) in a list.
Java Classes
```java
abstract class PaymentMethod {
    String type;
}

class CreditCard extends PaymentMethod {
    String cardNumber;
    String expiry;
}

class PayPal extends PaymentMethod {
    String email;
    String transactionId;
}
```
Using RuntimeTypeAdapterFactory
```java
// Add Gson extras dependency for RuntimeTypeAdapterFactory
RuntimeTypeAdapterFactory<PaymentMethod> adapterFactory =
RuntimeTypeAdapterFactory.of(PaymentMethod.class, "type")
    .registerSubtype(CreditCard.class, "credit_card")
    .registerSubtype(PayPal.class, "paypal");

Gson gson = new GsonBuilder()
    .registerTypeAdapterFactory(adapterFactory)
    .create();

// Serialize
List<PaymentMethod> payments = List.of(new CreditCard(), new PayPal());
String json = gson.toJson(payments);
// Output: [{"type":"credit_card","cardNumber":...}, {"type":"paypal","email":...}]

// Deserialize
Type listType = new TypeToken<List<PaymentMethod>>() {}.getType();
List<PaymentMethod> deserialized = gson.fromJson(json, listType);
```
4. Custom Date Handling

Scenario: Parse ISO 8601 dates in a Transaction object.
Java Class
```java
class Transaction {
    String id;
    Date timestamp; // Custom format: "yyyy-MM-dd'T'HH:mm:ssZ"
}
```
Configure Gson with Custom Date Format
```java
Gson gson = new GsonBuilder()
    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ") // ISO 8601
    .create();

// Serialize/Deserialize
Transaction transaction = new Transaction();
transaction.timestamp = new Date();

String json = gson.toJson(transaction); // {"timestamp":"2023-10-25T14:30:00+0000"}
Transaction deserialized = gson.fromJson(json, Transaction.class);
```
5. Optional Fields and Null Handling

Scenario: Skip null fields and handle optional values in a Profile object.
Java Class
```java
class Profile {
    @Expose(serialize = true, deserialize = true) // Include only non-null fields
    String name;

    @Expose(serialize = false) // Exclude this field during serialization
    String temporaryToken;
    
    String phone; // Nullable field (not annotated)
}
```
Configure Gson to Skip Nulls
```java
Gson gson = new GsonBuilder()
    .excludeFieldsWithoutExposeAnnotation() // Only serialize fields with @Expose
    .serializeNulls() // Include null fields (default is to skip them)
    .create();

Profile profile = new Profile();
profile.name = "Alice";
// phone is null

String json = gson.toJson(profile); // {"name":"Alice"}
```
6. Custom Serialization/Deserialization

Scenario: Convert a Money object to a JSON string like "100.50 USD".
Java Class
```java
class Money {
    BigDecimal amount;
    Currency currency; // Enum: USD, EUR, etc.
}
```
Custom Adapter
```java
public class MoneyAdapter implements JsonSerializer<Money>, JsonDeserializer<Money> {
    @Override
    public JsonElement serialize(Money src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.amount + " " + src.currency.name());
    }

    @Override
    public Money deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        String[] parts = json.getAsString().split(" ");
        return new Money(new BigDecimal(parts[0]), Currency.valueOf(parts[1]));
    }
}

// Register the adapter
Gson gson = new GsonBuilder()
    .registerTypeAdapter(Money.class, new MoneyAdapter())
    .create();

// Serialize
Money money = new Money(new BigDecimal("100.50"), Currency.USD);
String json = gson.toJson(money); // "100.50 USD"

// Deserialize
Money deserialized = gson.fromJson(json, Money.class);
```
Best Practices for Complex JSON

Use TypeToken for Generics.

Avoid runtime type erasure issues by capturing parameterized types:
```java
Type listType = new TypeToken<List<Map<String, User>>>() {}.getType();
```
Handle Edge Cases

Use `@SerializedName` to map JSON keys to Java fields:
```java
@SerializedName("user_id") String userId;
```
Use `JsonElement` for partial parsing if the schema is dynamic.

Test with Real-World Data
Validate against actual API responses/requests to catch mismatches.

Use `GsonBuilder` for Customization
Configure `date formats`, `null` handling, and pretty printing:
```java
Gson gson = new GsonBuilder()
        .setPrettyPrinting()
        .disableHtmlEscaping()
        .create();
```
By combining these techniques, you can handle even the most complex JSON structures in real-world applications! 🚀
