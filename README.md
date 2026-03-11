# EdgeMind

**EdgeMind** is an Android application built with Kotlin and Jetpack Compose that runs **on-device Large Language Model (LLM) inference**. It leverages Google's **LiteRT LM SDK** to provide zero-latency, private, and offline conversational AI experiences directly on the user's mobile device without relying on any cloud backend.

---

## 🚀 Features

- **100% On-Device Inference:** All processing happens locally (CPU/GPU). No internet connection required after initial model download.
- **Multi-Modal Support:** Handles text, images (Gemma 3 vision models) and audio (with supported models).
- **Auto-Hardware Acceleration:** Automatically probes for OpenCL support and falls back to CPU if the GPU is unsupported or fails during generation.
- **Streaming Responses:** Provides smooth, real-time token streaming in the chat UI.
- **Dynamic Model Management:** Download and manage models (e.g., Gemma, Qwen, Phi, DeepSeek) formatted for LiteRT LM directly within the app.

---

## 🛠️ Prerequisites

To build and run EdgeMind locally, you will need:

1. **Android Studio** (Jellyfish or newer recommended).
2. **Java Development Kit (JDK) 17+** (included with modern Android Studio).
3. **Android SDK** (API Level 24+ for the app, though higher is usually needed for modern LLMs due to memory constraints).
4. **Physical Android Device** (Emulators will work but only support slow CPU-based inference. A physical device with 6GB+ RAM and a modern GPU like Adreno or Mali is strongly recommended).

---

## ⚙️ Getting Started

Follow these steps to build and run the project:

### 1. Clone the Repository

```bash
git clone https://github.com/NeetuPara/EdgeMind.git
cd EdgeMind
```

### 2. Open in Android Studio

1. Open Android Studio.
2. Select **File > Open** and choose the `EdgeMind/app` directory (where the root `build.gradle.kts` is located).
3. Wait for Gradle to sync the project dependencies.

### 3. Build and Run

- Connect your Android device via USB debugging or Wireless debugging.
- Click the **Run 'app'** button (▶) in Android Studio, or use the terminal:

```bash
# Set your JAVA_HOME if necessary
# $env:JAVA_HOME = "C:\Program Files\Android\Android Studio\jbr"

# To build a debug APK:
./gradlew assembleDebug
```

---

## 🏗️ Architecture Overview

The app is architected with a clean separation of concerns, primarily centering around the **LiteRT LM SDK** (`com.google.ai.edge.litertlm`).

### Core Inference Components

- **`LlmChatModelHelper.kt`**: The heart of the inference engine. It manages the complete lifecycle of the LLM:
  - Initializes the `Engine` and `Conversation` using a downloaded `.litertlm` model file.
  - Dictates hardware backend selection (GPU vs CPU) dynamically based on `System.loadLibrary("OpenCL")` availability.
  - Handles the asynchronous token streaming (`sendMessageAsync`).
  - Cleans up and frees memory.

- **LiteRT LM Models (`.litertlm`)**: The app uses specialized, pre-quantized (int4/q8) model bundles provided by Google. These contain weights, tokenizers, and the inference graph. They are downloaded directly from Hugging Face into the app's secure storage.

### Data Flow

1. **User Input:** User sends text/images via the Jetpack Compose UI.
2. **ViewModel:** `LlmChatViewModel` processes the input and forwards it to the Model Helper.
3. **Inference:** `LlmChatModelHelper` passes the input (as `Content` objects) to the active `Conversation` session.
4. **Streaming:** The SDK triggers `onMessage` callbacks with partial tokens, which are bubbled back up to the ViewModel to update the UI instantly.

---

## 📦 Supported Models

The app supports various models including, but not limited to:
- **Gemma-3n-E2B-it / E4B-it** (Text, Vision, Audio)
- **Gemma3-1B-IT** (Text)
- **Qwen2.5-1.5B-Instruct**
- **Phi-4-mini-instruct**
- **DeepSeek-R1-Distill-Qwen-1.5B**

Models are downloaded via a background `WorkManager` job and stored locally.

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).
