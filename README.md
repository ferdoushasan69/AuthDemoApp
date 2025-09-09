AuthDemoApp 🔐

Auth Demo App (Android)
A demo Android authentication app built with Kotlin, Jetpack Compose, Hilt, Clean Architecture, Retrofit, and DataStore.
This app consumes an existing Node.js backend (e.g., Express + MongoDB) for authentication and user management.

🚀 Features

✅ Sign Up / Sign In via Node.js REST backend

✅ User authentication with JWT token

✅ Secure token storage using DataStore

✅ Form validation (first name, last name, phone, email, password match)

✅ Clean Architecture using MVVM pattern

✅ Hilt Dependency Injection

✅ Jetpack Compose UI

✅ Retrofit networking

✅ Reactive UI with StateFlow

🛠️ Tech Stack

Language: Kotlin

UI: Jetpack Compose

Architecture: MVVM + Clean Architecture

Dependency Injection: Hilt

Networking: Retrofit + OkHttp Interceptor

Local Storage: Preferences DataStore

Backend: Node.js REST API (Express) + MongoDB (provided separately)

📂 Project Structure
data/    # API services, repositories, DataStore
domain/  # Use cases, business logic
ui/      # Compose screens (Login, Signup, Home)
di/      # Hilt modules
util/    # Resource class, validators

🔧 Setup Instructions
Backend

The app consumes an existing Node.js REST API with MongoDB.

You do not need to run your own backend, but you must configure the BASE_URL.

Android App

Clone the repo:

git clone https://github.com/ferdoushasan69/AuthDemoApp.git


Open the project in Android Studio (Giraffe or newer).

Set the backend URL in ApiService.kt:

private const val BASE_URL = "<YOUR_NODEJS_BACKEND_URL>"


Build & Run the app 🚀

📱 Screens (Demo)

Sign Up: Enter user details (first name, last name, phone, email, password, retype password)

Login: Authenticate and receive JWT token

Home: Fetch user profile (protected route with token)

✅ Roadmap

User authentication with JWT

Secure token storage with DataStore

Forgot Password feature

Dark mode support

CI/CD via GitHub Actions

Apk link : https://drive.google.com/drive/folders/1uy_sEvE3vlm_7AyckYBvkcvoCFkDJEK6

📜 License

This project is licensed under the MIT License.
