# RestAssured Demo Framework — The Simpsons API

![Java](https://img.shields.io/badge/Java-17-blue)
![JUnit5](https://img.shields.io/badge/JUnit-5-green)
![RestAssured](https://img.shields.io/badge/RestAssured-API--Testing-orange)
![Maven](https://img.shields.io/badge/Build-Maven-lightgrey)
![License](https://img.shields.io/badge/License-MIT-blue)

---

This project demonstrates a **Java + RestAssured** testing framework that validates REST API endpoints using **JUnit 5** and **Maven**.  
It is built around **[The Simpsons API](https://thesimpsonsquoteapi.glitch.me/)**, which provides paginated character data.

---

## 🚀 Features

- Modular orchestration with `BaseTest` and reusable request specs  
- Environment configuration with `.env` files  
- Reusable HTTP constants, helpers, and numerical values  
- JSON Schema validation for responses  
- Parametrized data-driven tests  
- Dynamic environment loader for endpoint switching  
- Optional **Allure** reporting integration  

---

## 🧱 Project Structure

```bash
restassured-poc
├── .idea/
├── src
│   └── test
│       ├── java
│       │   ├── base
│       │   ├── config
│       │   ├── constants
│       │   ├── http
│       │   ├── tests
│       │   └── utils
│       └── resources
├── pom.xml
├── .gitignore
└── README.md
```

---

## ⚙️ Environment Configuration

All environment variables are defined in a `.env` file and dynamically loaded by `Environment.java`.

Example:
```env
BASE_URL=https://thesimpsonsquoteapi.glitch.me
TIMEOUT=5000
```

You can easily switch `BASE_URL` to another API for test reuse without changing code.

---

## 🚀 Quick Start

Follow these steps to set up and run the project locally:

### 1️⃣ Clone the repository
```bash
git clone https://github.com/refnando/restassured-poc.git
cd restassured-poc
```

### 2️⃣ Verify Java and Maven installation
```bash
java -version
mvn -v
```
Make sure you have **Java 17** and **Maven 3.9+** installed.

### 3️⃣ Install dependencies
```bash
mvn clean install
```

### 4️⃣ Run the tests
```bash
mvn test
```

### 5️⃣ (Optional) Generate Allure Report
```bash
allure serve allure-results
```

> 💡 Tip: You can switch the `BASE_URL` in your `.env` file to target a different API without modifying the code.

---

## 🧪 Example Test — `SimpsonsTest.java`

```java
@Test
@DisplayName("GET /characters returns metadata and 20 results")
void validateCharacterList() {
    given()
        .spec(RequestSpecFactory.getRequestSpec())
    .when()
        .get(CHARACTERS)
    .then()
        .assertThat()
        .statusCode(HttpStatus.OK)
        .body("info.count", greaterThan(Numbers.ZERO))
        .body("results.size()", equalTo(Numbers.RESULT_LIMIT));
}
```

---

## 🧰 Build & Run

### 1️⃣ Install Dependencies
Make sure you have **Java 17** and **Maven 3.9+** installed.

```bash
mvn clean install
```

### 2️⃣ Run Tests
```bash
mvn clean test
```

### 3️⃣ (Optional) Generate Allure Report
```bash
allure serve allure-results
```

> Developed and tested on macOS using IntelliJ IDEA 2025.1

---

## 📡 API Reference

| Endpoint | Description | Example |
|-----------|--------------|----------|
| `/characters` | List of characters (paginated) | `GET /api/characters` |
| `/character/:id` | Fetch single character | `GET /api/character/5` |
| `/quotes` | Random character quotes | `GET /api/quotes` |

---

## 📦 Example Response Schema

<details>
<summary>Click to view sample JSON</summary>

```json
{
  "id": 1,
  "name": "Homer Simpson",
  "gender": "Male",
  "image": "https://example.com/homer.png",
  "quote": "D'oh!"
}
```
</details>

---

## 🧠 Technologies Used

| Category | Tool |
|-----------|------|
| Language | Java 17 |
| Test Framework | JUnit 5 |
| HTTP Client | RestAssured |
| Build Tool | Maven |
| Environment Loader | dotenv-java |
| Assertions | Hamcrest |
| Reporting | Allure (optional) |

---

## 👨‍💻 Author

**Fernando Campos**  
*QA Automation Engineer / SDET*  
📍 Guadalajara, México  
🔗 [GitHub Profile](https://github.com/refnando)

> Specialized in API and UI test automation using Java (RestAssured, Selenium) and TypeScript (Pactum, Playwright), with strong experience in building modular frameworks and integrating tests into CI/CD pipelines.

---

## 🔮 Future Improvements

- Add negative test cases and error handling  
- Extend schema validation for `/character/:id`  
- Implement GitHub Actions CI/CD pipeline  
- Add data-driven tests for pagination  

---

## 🪪 License

This project is open-source and intended for educational and demo purposes.  
You are free to **clone**, **modify**, and **use** it for non-commercial or learning projects.

---
