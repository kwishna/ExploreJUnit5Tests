1. Simple Object (No Generics)

```java
@Data // Lombok (or add getters/setters manually)
public class Person {
    private String name;
    private int age;
}
```

Gson:
```java
Gson gson = new Gson();
Person person = new Person("Alice", 30);

// Serialize
String json = gson.toJson(person);

// Deserialize
Person deserialized = gson.fromJson(json, Person.class);
```
Jackson:
```java
ObjectMapper mapper = new ObjectMapper();
Person person = new Person("Alice", 30);

// Serialize
String json = mapper.writeValueAsString(person);

// Deserialize
Person deserialized = mapper.readValue(json, Person.class);
```
2. Raw Collections (No Generics)

Gson:
```java
List rawList = List.of("A", 123, true);
String json = gson.toJson(rawList);
List deserialized = gson.fromJson(json, List.class);
```
Jackson:
```java
List rawList = List.of("A", 123, true);

// Serialize
String json = mapper.writeValueAsString(rawList);

// Deserialize (returns List<Object>)
List<?> deserialized = mapper.readValue(json, List.class);

// Access elements (may require type checking)
String first = (String) deserialized.get(0);
```
3. Generic Collections (e.g., List<String>)

Gson:
```java
Type listType = new TypeToken<List<String>>() {}.getType();
List<String> names = gson.fromJson(json, listType);
```
Jackson:
```java
// Deserialize
List<String> names = mapper.readValue(
    json,
    new TypeReference<List<String>>() {}
);

// Serialize
String json = mapper.writeValueAsString(names);
```
4. Generic Class (e.g., Response<T>)


```java
@Data
public class Response<T> {
    private T data;
    private int status;
}
```
Gson:
```java
Type responseType = new TypeToken<Response<String>>() {}.getType();
Response<String> response = gson.fromJson(json, responseType);
```
Jackson:
```java
Response<String> response = mapper.readValue(
    json,
    new TypeReference<Response<String>>() {}
);
```
5. Nested Generics (e.g., Map<String, List<Person>>)

Gson:
```java
Type mapType = new TypeToken<Map<String, List<Person>>>() {}.getType();
Map<String, List<Person>> deserialized = gson.fromJson(json, mapType);
```
Jackson:
```java
Map<String, List<Person>> deserialized = mapper.readValue(
    json,
    new TypeReference<Map<String, List<Person>>>() {}
);
```
6. Polymorphic Types (Inheritance)


```java
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    property = "type"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Dog.class, name = "dog"),
    @JsonSubTypes.Type(value = Cat.class, name = "cat")
})
@Data
public abstract class Animal {
    private String name;
}

@Data
public class Dog extends Animal {
    private String breed;
}

@Data
public class Cat extends Animal {
    private boolean hasClaws;
}
```
Gson:
```java
RuntimeTypeAdapterFactory<Animal> factory = ...;
Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();
```
Jackson:
```java
ObjectMapper mapper = new ObjectMapper();

// Serialize/Deserialize
List<Animal> animals = List.of(new Dog(), new Cat());
String json = mapper.writeValueAsString(animals);

List<Animal> deserialized = mapper.readValue(
    json,
    new TypeReference<List<Animal>>() {}
);
```
7. Custom Date Handling


```java
@Data
public class Transaction {
    private LocalDateTime timestamp; // Java 8 time
}
```
Gson:
```java
Gson gson = new GsonBuilder()
    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
    .create();
```
Jackson:
```java
ObjectMapper mapper = new ObjectMapper()
    .registerModule(new JavaTimeModule()) // Handles Java 8 dates
    .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
```
8. Optional Fields and Null Handling


```java
@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // Skip nulls
public class Profile {
    private String name;
    
    @JsonIgnore // Exclude field
    private String temporaryToken;
}
```
Gson:
```java
Gson gson = new GsonBuilder()
    .excludeFieldsWithoutExposeAnnotation()
    .create();
```
Jackson:
```java
ObjectMapper mapper = new ObjectMapper()
    .setSerializationInclusion(JsonInclude.Include.NON_NULL);
```
9. Custom Serialization/Deserialization


```java
@Data
public class Money {
    private BigDecimal amount;
    private Currency currency;
}
```
Gson:
```java
Gson gson = new GsonBuilder()
.registerTypeAdapter(Money.class, new MoneyAdapter())
.create();
```
Jackson:
```java
SimpleModule module = new SimpleModule();
module.addSerializer(Money.class, new MoneySerializer());
module.addDeserializer(Money.class, new MoneyDeserializer());
ObjectMapper mapper = new ObjectMapper().registerModule(module);
```
Key Takeaways

1. Annotations: Jackson uses annotations like @JsonTypeInfo, @JsonInclude, and @JsonIgnore.
2. Type References: Replace Gson’s TypeToken with Jackson’s TypeReference.
3. Modules: Jackson requires modules (e.g., JavaTimeModule) for advanced features.
4. Configuration: Use ObjectMapper to customize serialization/deserialization behavior.

## Custom Adapters/Serializers

Scenario: Serialize/deserialize a Money class to a custom string format like "100.50 USD".
Gson
```java
// Custom Adapter (implements JsonSerializer and JsonDeserializer)
public class MoneyAdapter implements JsonSerializer<Money>, JsonDeserializer<Money> {

    @Override
    public JsonElement serialize(Money src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getAmount() + " " + src.getCurrency());
    }

    @Override
    public Money deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        String[] parts = json.getAsString().split(" ");
        return new Money(new BigDecimal(parts[0]), parts[1]);
    }
}

// Usage
Gson gson = new GsonBuilder()
    .registerTypeAdapter(Money.class, new MoneyAdapter())
    .create();

// Serialize
Money money = new Money(new BigDecimal("100.50"), "USD");
String json = gson.toJson(money); // "100.50 USD"

// Deserialize
Money deserialized = gson.fromJson(json, Money.class);
```
Jackson
```java
// Custom Serializer
public class MoneySerializer extends StdSerializer<Money> {
    public MoneySerializer() {
        super(Money.class);
    }

    @Override
    public void serialize(Money value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(value.getAmount() + " " + value.getCurrency());
    }
}

// Custom Deserializer
public class MoneyDeserializer extends StdDeserializer<Money> {
    public MoneyDeserializer() {
    super(Money.class);
    }

    @Override
    public Money deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String[] parts = p.getText().split(" ");
        return new Money(new BigDecimal(parts[0]), parts[1]);
    }
}

// Usage
ObjectMapper mapper = new ObjectMapper();
SimpleModule module = new SimpleModule();

module.addSerializer(Money.class, new MoneySerializer());
module.addDeserializer(Money.class, new MoneyDeserializer());

mapper.registerModule(module);

// Serialize
String json = mapper.writeValueAsString(money); // "100.50 USD"

// Deserialize
Money deserialized = mapper.readValue(json, Money.class);
```
2. Parsers (Manual JSON Tree Navigation)

Scenario: Parse a JSON string manually to extract data.
Gson
```java
String json = "{'name':'Alice','age':30,'pets':['Dog','Cat']}";

// Parse into a JSON tree
JsonElement root = JsonParser.parseString(json);
JsonObject obj = root.getAsJsonObject();

// Extract values
String name = obj.get("name").getAsString(); // "Alice"
JsonArray pets = obj.get("pets").getAsJsonArray();
String firstPet = pets.get(0).getAsString(); // "Dog"
```
Jackson
```java
String json = "{'name':'Alice','age':30,'pets':['Dog','Cat']}";

// Parse into a JSON tree
JsonNode root = mapper.readTree(json);

// Extract values
String name = root.get("name").asText(); // "Alice"
JsonNode pets = root.get("pets");
String firstPet = pets.get(0).asText(); // "Dog"
```
3. JsonArray Handling

Scenario: Create and manipulate a JSON array.
Gson
```java
// Create a JsonArray
JsonArray jsonArray = new JsonArray();
    jsonArray.add("Apple");
    jsonArray.add(123);
    jsonArray.add(true);

// Iterate
for (JsonElement elem : jsonArray) {
    if (elem.isJsonPrimitive()) {
        System.out.println(elem.getAsString());
    }
}

// Convert to JSON string
String json = new Gson().toJson(jsonArray); // ["Apple",123,true]
```
Jackson
```java
// Create an ArrayNode
ArrayNode arrayNode = mapper.createArrayNode();
    arrayNode.add("Apple");
    arrayNode.add(123);
    arrayNode.add(true);

// Iterate
arrayNode.forEach(elem -> System.out.println(elem.asText()));

// Convert to JSON string
String json = mapper.writeValueAsString(arrayNode); // ["Apple",123,true]
```
4. Combined Example: Complex JSON

Scenario: Build a JSON object with nested arrays and objects.
Gson
```java
JsonObject user = new JsonObject();
    user.addProperty("name", "Alice");

JsonArray pets = new JsonArray();
    pets.add("Dog");
    pets.add("Cat");
    user.add("pets", pets);

// Serialize
String json = new Gson().toJson(user);
// {"name":"Alice","pets":["Dog","Cat"]}
```
Jackson
```java
ObjectNode user = mapper.createObjectNode();
    user.put("name", "Alice");

ArrayNode pets = mapper.createArrayNode();
    pets.add("Dog");
    pets.add("Cat");
    user.set("pets", pets);

// Serialize
String json = mapper.writeValueAsString(user);
```
Key Differences
Feature	Gson	Jackson
Tree Model	JsonElement, JsonObject, JsonArray	JsonNode, ObjectNode, ArrayNode
Custom Adapters	JsonSerializer/JsonDeserializer	StdSerializer/StdDeserializer
Parsing	JsonParser.parseString()	ObjectMapper.readTree()
Null Handling	.serializeNulls() in GsonBuilder	@JsonInclude(Include.NON_NULL)
Dependencies	Single JAR (lightweight)	Requires jackson-databind, jackson-core