# 💡 Smart Lamp Control

An Android application developed in Kotlin to control the status and brightness of a smart lamp.

---

## Build & Run Application

### Requirements
- Android Studio Ladybug Feature Drop or later
- Minimum SDK: 26 (Android 8.0)
- Internet access
- MQTT Broker (public or local)


### How to Build
1. Clone the repository:
   ```bash
    git clone https://github.com/SeyyedAliTabatabaei/Take-Home-Task.git
   ```
2. Open the project in Android Studio.
3. Connect a real device or use an emulator.
4. Click Run or use:
    ```bash
    ./gradlew installDebug
   ```
   
### Testing the App with MQTT
**After launching the application to test MQTT, follow the steps below in order:**
1. First, connect your real device or emulator to the internet.
2. Open the application on device.
3. Wait until the connection status at the top of the screen changes to “Connected.”
4. Then, after turning on the lamp, you can adjust its brightness.

**Note:**
To view the MQTT logs, run the app through Android Studio and set the Logcat filter to 'package:seyyed.ali.tabatabaei.take_home MqttManager' so that MQTT logs are displayed for you.


### MQTT Topics Used

| Topic                   | Description                                                                  |
|-------------------------|------------------------------------------------------------------------------|
| `light_bulb_status`     | Sending and receiving the on/off status of the lamp.                         |
| `light_bulb_brightness` | Sending and receiving the brightness level of the lamp when it is turned on. |


### This Project use
- **Kotlin:** A modern, expressive, and concise programming language officially supported for Android development.
- **MQTT:** A lightweight messaging protocol ideal for IoT applications, used to send and receive real-time data like smart lamp status and brightness.
- **Clean Architecture:** A software architecture pattern that separates concerns into layers (e.g., domain, data, presentation) to make the codebase scalable, testable, and maintainable.
- **MVVM (Model-View-ViewModel):** An architectural pattern that separates the UI (View), business logic (ViewModel), and data (Model), allowing for better testability and a cleaner UI logic.
- **Jetpack Compose:** A modern UI toolkit for building native Android user interfaces declaratively with Kotlin.
- **Dependency Injection (Hilt):** A library built on top of Dagger for managing dependencies in Android apps, improving modularity and testability.
- **Coroutines - Flow:** Kotlin’s solution for asynchronous programming; Coroutines simplify background task execution, and Flow enables reactive stream handling.
- **OOP (Object-Oriented Programming):** A programming paradigm based on the concept of objects that encapsulate data and behavior, promoting modularity and reuse.
- **SOLID:** A set of five principles (Single Responsibility, Open/Closed, Liskov Substitution, Interface Segregation, Dependency Inversion) to guide software design for better maintainability and scalability.

---

## 👤 Author

**Seyyed Ali Tabatabaei**  
📧 [SeyyedAliTabatabaei7@gmail.com](mailto:SeyyedAliTabatabaei7@gmail.com)  
🌐 [GitHub Profile](https://github.com/SeyyedAliTabatabaei)

---

🌟 Star this repo to support the project!  
🐛 Found a bug? [Open an issue](https://github.com/SeyyedAliTabatabaei/ZamanakCalendar/issues)