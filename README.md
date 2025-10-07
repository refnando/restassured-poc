# 🧪 RestAssured Demo Framework — The Simpsons API

This project demonstrates a **Java + RestAssured** testing framework that validates REST API endpoints using **JUnit 5** and **Maven**.  
It is built around the [The Simpsons API](https://thesimpsonsapi.com/api/characters), which provides paginated character data.

---

## 🚀 Features

- ✅ Modular architecture using `BaseTest` and reusable request specs  
- ✅ Environment configuration with `.env` files  
- ✅ Constants for HTTP codes, endpoints, and reusable numeric values  
- ✅ Response validator utility for clean and readable assertions  
- ✅ Pagination and single-resource validation  
- ✅ JSON schema-ready structure for contract validation  
- ✅ Optional Allure reporting integration  

---

## 📂 Project Structure

```bash
📁 restassured-poc
├── 📄 pom.xml
├── 📄 .gitignore
├── 📄 .env
│
├── 📁 src
│   └── 📁 test
│       ├── 📁 java
│       │   ├── 📁 base
│       │   │   └── BaseTest.java
│       │   ├── 📁 config
│       │   │   └── Environment.java
│       │   ├── 📁 constants
│       │   │   ├── ApiConstants.java
│       │   │   ├── HttpStatus.java
│       │   │   └── Numbers.java
│       │   ├── 📁 http
│       │   │   └── RequestSpecFactory.java
│       │   ├── 📁 models
│       │   ├── 📁 tests
│       │   │   └── SimpsonsTest.java
│       │   └── 📁 utils
│       │       ├── CorrelationFilter.java
│       │       └── ResponseValidator.java
│       │
│       └── 📁 resources
│           ├── 📁 data
│           ├── 📁 schemas
│           └── 📄 .env
│
├── 📁 allure-results
│   └── (auto-generated Allure report data)
│
└── 📁 .mvn
    └── (Maven wrapper configuration)
```

---

## ⚙️ Environment Configuration

All environment variables are defined in `.env` and dynamically loaded by `Environment.java`.

```properties
BASE_URL=https://thesimpsonsapi.com/api
TIMEOUT_MS=5000
```

> 💡 Tip: You can switch the `BASE_URL` to another API for fast reuse without changing code.

---

## 🧩 Example Test — `SimpsonsTest.java`

```java
@Test
@DisplayName("GET /characters returns metadata and 20 results")
void getCharacters_ok() {
    Response response = given()
            .spec(RequestSpecFactory.getDefaultSpec())
    .when()
            .get(ApiConstants.CHARACTERS)
    .then()
            .extract().response();

    ResponseValidator.status(response, HttpStatus.OK);

    response.then()
            .body("count", greaterThan(Numbers.ZERO))
            .body("results.size()", equalTo(20))
            .body("results[0].name", not(emptyOrNullString()));
}
```

---

## 🧰 Build & Run

### 1️⃣ Install Dependencies
Make sure you have **Java ≥ 17** and **Maven ≥ 3.9** installed:
```bash
java -version
mvn -version
```

### 2️⃣ Run Tests
```bash
mvn clean test
```

### 3️⃣ (Optional) Generate Allure Report
```bash
mvn allure:serve
```

---

## 🧠 API Reference

| Endpoint | Description | Example |
|-----------|--------------|----------|
| `/characters` | List of characters (20 per page) | [GET /api/characters](https://thesimpsonsapi.com/api/characters) |
| `/characters?page=2` | Fetch second page | [GET /api/characters?page=2](https://thesimpsonsapi.com/api/characters?page=2) |
| `/characters/{id}` | Single character details | [GET /api/characters/1](https://thesimpsonsapi.com/api/characters/1) |
| `/quotes` | Random character quotes | [GET /api/quotes](https://thesimpsonsapi.com/api/quotes) |

---

## 🧾 Example Response Schema

```json
{
  "count": 1182,
  "next": "https://thesimpsonsapi.com/api/characters?page=2",
  "prev": null,
  "pages": 60,
  "results": [
    {
      "id": 1,
      "age": 39,
      "birthdate": "1956-05-12",
      "gender": "Male",
      "name": "Homer Simpson",
      "occupation": "Safety Inspector",
      "portrait_path": "/character/1.webp",
      "phrases": [
        "Doh!",
        "Woo-hoo!",
        "Stupid Flanders!"
      ],
      "status": "Alive"
    }
  ]
}
```

---

## 🧱 Technologies Used

| Category | Tool |
|-----------|------|
| Language | Java 21 |
| Test Framework | JUnit 5 |
| HTTP Client | RestAssured |
| Build Tool | Maven |
| Environment Loader | dotenv-java |
| Assertions | Hamcrest |
| Reporting | Allure (optional) |

---

## 🧑‍💻 Author

**Fernando Campos**  
QA Automation Engineer / SDET  
📍 Guadalajara, Jalisco  
💼 Focused on API testing, automation frameworks, and CI/CD integration.

---

## 🏁 Future Enhancements

- [ ] JSON Schema validation for `/characters`  
- [ ] Extend to `/episodes` and `/quotes` endpoints  
- [ ] Add GitHub Actions CI pipeline  


---

## 📄 License

This project is open-sourced for educational and demo purposes.  
You are free to clone, modify, and extend it for non-commercial use.
