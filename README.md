# SwitchAI Backend (v1)

SwitchAI is a backend system for managing railcar data and predicting yard track assignments using a machine learning service.

---

## 🚀 Tech Stack

- Java 17+
- Spring Boot
- MongoDB
- Python (Flask) – ML Service
- REST APIs

---

## 🏗️ Architecture

```
Frontend (Angular)
        ↓
Spring Boot API (Java)
   ↙            ↘
MongoDB      ML Service (Python Flask)
```

---

## 📦 Project Structure

```
com.switchai.switchai_backend
│
├── config
│   └── AppConfig
│
├── railcar
│   ├── Railcar
│   ├── RailcarController
│   ├── RailcarService
│   ├── RailcarRepository
│   ├── Classification
│   ├── ClassificationRepository
│   ├── PredictionRequest
│   └── PredictionResponse
│
├── exception
│   └── GlobalExceptionHandler
│
└── SwitchaiBackendApplication
```

---

## 🔌 API Endpoints

### Railcars

#### Create Railcar
```
POST /railcars
```

#### Get All / Filter
```
GET /railcars
GET /railcars?destination=ATL
GET /railcars?trainId=Q123
GET /railcars?destination=ATL&trainId=Q123
```

#### Get by ID
```
GET /railcars/{id}
```

---

### Predictions

#### Predict Track (via ML service)
```
POST /railcars/predict
```

Body:
```json
{
  "railcarId": "mongo_id_here"
}
```

Response:
```json
{
  "railcarId": "mongo_id_here",
  "predictedTrack": 61
}
```

---

### Classifications

#### Get All / Filter
```
GET /railcars/classifications
GET /railcars/classifications?railcarId=...
```

---

## 🤖 ML Service (Python)

Location:
```
/ml-service/app.py
```

Run:
```bash
python app.py
```

Runs on:
```
http://localhost:5000
```

Endpoint:
```
POST /predict
```

---

## 🗄️ MongoDB

Default connection:
```
mongodb://localhost:27017
```

Collections:
- railcars
- classifications

---

## ✅ Features Implemented

- Railcar CRUD (basic)
- Filtering (destination, trainId)
- ML integration via REST call
- Classification persistence
- Validation (input constraints)
- Global exception handling
- Clean layered architecture

---

## ⚠️ Validation Rules

- trainId, origin, destination, carType → required
- weight, length → must be positive

---

## 🧪 Running the System

### 1. Start MongoDB

### 2. Start ML Service
```bash
cd ml-service
python app.py
```

### 3. Start Spring Boot App

Run:
```
SwitchaiBackendApplication
```

---

## 📌 Next Steps (Planned)

- Track capacity logic (yard simulation)
- Pagination
- Authentication
- Docker setup
- Microservices split (API / ML / Data)

---

## 🧠 Notes

- ML service is currently mocked (hash-based prediction)
- Designed to evolve into microservices architecture

---

## 👨‍💻 Author

SwitchAI Project