# AuthDemoApp

# 🔐 Auth Demo App (Android)

An **Android authentication demo app** built with **Kotlin, Jetpack Compose, Hilt, Clean Architecture, Retrofit, and DataStore**.  
This app connects to an **existing Django + PostgreSQL backend** for authentication and user management.

---

## 🚀 Features

- ✅ **Sign Up / Sign In** via Django REST backend
- ✅ **User authentication** with JWT token
- ✅ **Secure token storage** using DataStore
- ✅ **Form validation** (first name, last name, phone, email, password match)
- ✅ **Clean Architecture** (MVVM pattern)
- ✅ **Hilt Dependency Injection**
- ✅ **Jetpack Compose UI**
- ✅ **Retrofit networking**
- ✅ **Reactive UI with LiveData / StateFlow**

---

## 🛠️ Tech Stack

- **Language:** Kotlin
- **UI:** Jetpack Compose
- **Architecture:** MVVM + Clean Architecture
- **Dependency Injection:** Hilt
- **Networking:** Retrofit + OkHttp Interceptor
- **Local Storage:** Preferences DataStore
- **Backend:** Django REST API + PostgreSQL (provided separately)

---

data/ # API services, repositories, DataStore
domain/ # Use cases, business logic
ui/ # Compose screens (Login, Signup, Home)
di/ # Hilt modules
util/ # Resource, validators


---

## 🔧 Setup Instructions

### Backend
- This project consumes an **existing Django REST API** (with PostgreSQL).  
- You don’t need to run your own backend, but you must configure the `BASE_URL`.

### Android App

1. Clone this repo:
   ```bash
   git clone https://github.com/ferdoushasan69/AuthDemoApp.git


Open in Android Studio (Giraffe or newer).

In ApiService.kt, set the base URL:

private const val BASE_URL = ""


Build & Run 🚀

📱 Screens (Demo)

Sign Up → User details (first name, last name, phone, email, password, retype password)

Login → Authenticate and receive JWT token

Home → Fetch user profile (protected route with token)

✅ Roadmap

 User authentication with JWT

 DataStore for token storage

 Forgot Password

 Dark mode support

 CI/CD (GitHub Actions)

📜 License

This project is licensed under the MIT License.


---

## ✅ Repo Setup Steps for You
1. Create repo → `authDemoApp`.  
2. Push your Android project.  
3. Add:  
   - `README.md` (use template above).  
   - `.gitignore` (`Android.gitignore` from GitHub template).  
   - `LICENSE` file (MIT).  
4. Add screenshots if possible.  

---

⚡Pro tip: In your **README intro line**, add:  
> “This project is a demo client app for consuming a Django + PostgreSQL authentication backend.”  

👉 That way it’s clear you are **not building backend**, just **consuming APIs**.  


