# LiteRT LM SDK — What You Can & Cannot Control

## ✅ Parameters You CAN Control

### Sampling Parameters (`SamplerConfig`)
| Parameter | Type | Default | Range | Effect |
|---|---|---|---|---|
| `temperature` | Double | 1.0 | 0.0–2.0 | Randomness. 0 = deterministic, higher = more creative |
| `topK` | Int | 64 | 1–1000 | Limits token selection to top K candidates |
| `topP` | Double | 0.95 | 0.0–1.0 | Nucleus sampling — cumulative probability cutoff |

**Code location:** [`LlmChatModelHelper.kt`](file:///D:/Android_App/EdgeMind/app/app/src/main/java/com/edgemind/app/data/LlmChatModelHelper.kt)

```kotlin
SamplerConfig(
    topK = model.defaultTopK,
    topP = model.defaultTopP.toDouble(),
    temperature = model.defaultTemperature.toDouble(),
)
```

### Engine Configuration (`EngineConfig`)
| Parameter | Type | Effect |
|---|---|---|
| `backend` | Backend.CPU / Backend.GPU | Hardware acceleration |
| `visionBackend` | Backend.GPU / null | Image processing backend |
| `maxNumTokens` | Int | Max context window (1024–4096) |
| `modelPath` | String | Path to `.litertlm` file |

**Code location:** [`LlmChatModelHelper.kt`](file:///D:/Android_App/EdgeMind/app/app/src/main/java/com/edgemind/app/data/LlmChatModelHelper.kt)

### Conversation & Messaging
| Feature | How | Code |
|---|---|---|
| System prompt | Send as first message | `Content.Text("You are a helpful assistant...")` |
| Multi-turn chat | Reuse `Conversation` object | `conversation.sendMessageAsync(...)` |
| New session | Create new conversation | `engine.createConversation(config)` |
| Image input | Send image bytes | `Content.ImageBytes(byteArray)` |
| Streaming output | Use callback | `MessageCallback.onMessage()` |
| Stop generation | Close/reset conversation | `conversation.close()` |

### Model Selection
| Control | Details |
|---|---|
| Which models to offer | Edit `model_allowlist.json` |
| Add custom models | Upload `.litertlm` + add JSON entry |
| Per-model defaults | Set in `defaultConfig` in allowlist JSON |

---

## ❌ Parameters You CANNOT Control

| Feature | Status | Workaround |
|---|---|---|
| Stop sequences | Not exposed | Post-process output, trim at stop token |
| Frequency penalty | Not in API | None (use temperature instead) |
| Presence penalty | Not in API | None |
| Raw logit access | Internal | Use llama.cpp backend for this |
| Token probabilities | Not exposed | Use llama.cpp backend |
| KV cache control | Internal | None |
| Tokenizer config | Baked into model | Must change at conversion time |
| Quantization | Fixed in `.litertlm` | Choose at conversion: int4 / int8 / fp32 |
| Batch inference | Not supported | One conversation at a time |
| Embedding extraction | Not exposed | Use separate embedding library |

---

## 📁 Key Files for Configuration

| File | Purpose |
|---|---|
| [`model_allowlist.json`](file:///D:/Android_App/EdgeMind/app/app/src/main/assets/model_allowlist.json) | Model registry — names, URLs, default params |
| [`LlmChatModelHelper.kt`](file:///D:/Android_App/EdgeMind/app/app/src/main/java/com/edgemind/app/data/LlmChatModelHelper.kt) | Engine init, inference, backend selection |
| [`Model.kt`](file:///D:/Android_App/EdgeMind/app/app/src/main/java/com/edgemind/app/data/Model.kt) | Data classes with defaults |
| [`ChatViewModel.kt`](file:///D:/Android_App/EdgeMind/app/app/src/main/java/com/edgemind/app/viewmodel/ChatViewModel.kt) | Chat logic, message handling |

---

## 🔓 License

LiteRT LM SDK is **Apache 2.0** — full source at [github.com/google-ai-edge/LiteRT-LM](https://github.com/google-ai-edge/LiteRT-LM). You can fork, modify, and use commercially.

## 🔮 Future: Dual Backend

For full low-level control (logits, penalties, stop sequences), integrate **llama.cpp** as a second backend for `.gguf` models. See conversation notes for architecture plan.
