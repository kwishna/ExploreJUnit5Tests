1. Complex Nested Objects

Scenario: Serialize/deserialize an Order with nested Customer, Product, and Address.

```java
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data; // Lombok for getters/setters (or add manually)

@Data
class Order {
    private String orderId;
    private Customer customer;
    private List<Product> products;
    private LocalDateTime orderDate;
}

@Data
class Customer {
    private String id;
    private String name;
    private Address shippingAddress;
}

@Data
class Product {
    private String sku;
    private String name;
    private BigDecimal price;
}

@Data
class Address {
    private String street;
    private String city;
    private String country;
}
```
Serialization/Deserialization
```java
import com.fasterxml.jackson.databind.ObjectMapper;

ObjectMapper mapper = new ObjectMapper();
mapper.registerModule(new JavaTimeModule()); // For Java 8 date/time

// Serialize
Order order = buildOrder();
String json = mapper.writeValueAsString(order);

// Deserialize
Order deserializedOrder = mapper.readValue(json, Order.class);
```
2. Paginated API Response with Generics

Scenario: Deserialize  `ApiResponse<User>` using `TypeReference`.

```java
@Data
class ApiResponse<T> {
    private int page;
    private int totalPages;
    private List<T> data;
}

@Data
class User {
    private String userId;
    private String email;
}
```
Handling Generics
```java

String jsonResponse = "{'page':1, 'totalPages':5, 'data':[{'userId':'123','email':'a@test.com'}]}";

// Deserialize using TypeReference
ApiResponse<User> response = mapper.readValue(
    jsonResponse,
    new TypeReference<ApiResponse<User>>() {}
);

// Serialize
String serialized = mapper.writeValueAsString(response);
```
3. Polymorphic Types (Payment Methods)

Scenario: Handle CreditCard and PayPal using @JsonTypeInfo.

```java
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    property = "type",
    visible = true // Include "type" field in JSON
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = CreditCard.class, name = "credit_card"),
    @JsonSubTypes.Type(value = PayPal.class, name = "paypal")
})

@Data
abstract class PaymentMethod {
    private String type;
}

@Data
class CreditCard extends PaymentMethod {
    private String cardNumber;
    private String expiry;
}

@Data
class PayPal extends PaymentMethod {
    private String email;
    private String transactionId;
}
```
Serialize/Deserialize
```java
List<PaymentMethod> payments = List.of(new CreditCard(), new PayPal());

// Serialize
String json = mapper.writeValueAsString(payments);
// Output: [{"type":"credit_card","cardNumber":...}, ...]

// Deserialize
List<PaymentMethod> deserialized = mapper.readValue(
    json,
    new TypeReference<List<PaymentMethod>>() {}
);
```
4. Custom Date Handling

Scenario: Parse ISO 8601 dates in a Transaction object.

```java
@Data
class Transaction {
    private String id;
    private Date timestamp;
}
```
Configure ObjectMapper
```java
ObjectMapper mapper = new ObjectMapper()
    .registerModule(new JavaTimeModule()) // For Java 8 dates
    .setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ"));
```
5. Optional Fields and Null Handling

Scenario: Skip null fields and ignore certain fields.

```java

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // Skip nulls globally
class Profile {
    private String name;

    @JsonIgnore // Exclude from serialization
    private String temporaryToken;

    private String phone; // Nullable
}
```
Configure ObjectMapper
```java
ObjectMapper mapper = new ObjectMapper()
        .setSerializationInclusion(JsonInclude.Include.NON_NULL);
```
6. Custom Serialization/Deserialization

Scenario: Convert Money to/from a custom string like "100.50 USD".

```java
@Data
class Money {
    private BigDecimal amount;
    private Currency currency;
}
```
Custom Adapter
```java
public class MoneySerializer extends JsonSerializer<Money> {
    @Override
    public void serialize(Money value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(value.getAmount() + " " + value.getCurrency().name());
    }
}
    
public class MoneyDeserializer extends JsonDeserializer<Money> {
    @Override
    public Money deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String[] parts = p.getText().split(" ");
        return new Money(new BigDecimal(parts[0]), Currency.valueOf(parts[1]));
    }
}
```
Register Adapters
```java
SimpleModule mod = new SimpleModule();
mod.addSerializer(Money.class, new MoneySerializer());
mod.addDeserializer(Money.class, new MoneyDeserializer());

ObjectMapper mapper = new ObjectMapper().registerModule(module);
    
// Serialize/Deserialize
Money money = new Money(new BigDecimal("100.50"), Currency.USD);
String json = mapper.writeValueAsString(money); // "100.50 USD"
Money deserialized = mapper.readValue(json, Money.class);
```
Key Differences Between Gson and Jackson

* Annotations: Jackson relies heavily on annotations (e.g., @JsonTypeInfo, @JsonInclude).
* Type Handling: Use TypeReference instead of Gson’s TypeToken.
* Modules: Jackson uses modules (e.g., JavaTimeModule) for advanced features.
* Configuration: Jackson’s ObjectMapper is more configurable (e.g., SerializationFeature, DeserializationFeature).