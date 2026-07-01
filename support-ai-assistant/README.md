# AI-Powered Support Knowledge Assistant

## Project Overview

AI-Powered Support Knowledge Assistant is an enterprise support automation platform designed to help support engineers resolve customer issues faster by leveraging Artificial Intelligence.

The assistant analyzes support tickets, application logs, and historical resolutions to recommend possible root causes and suggested fixes.

---

## Business Problem

Support engineers currently spend significant time:

- Manually searching previous tickets
- Looking through emails and documentation
- Depending on Subject Matter Experts (SMEs)
- Repeating the same investigations

This project reduces investigation time by providing AI-assisted recommendations.

---

## Features

### Phase 1 (Completed)

- Spring Boot REST API
- Ollama Integration
- Llama 3 Integration
- AI Chat Endpoint
- GitHub Integration

### Phase 2 (In Progress)

- AI Log Analyzer
- Error Summary Generation
- Root Cause Identification
- Resolution Recommendation

### Future Phases

- Support Ticket Search
- Knowledge Repository
- Vector Database (Qdrant)
- Retrieval-Augmented Generation (RAG)
- Digiprise Integration

---

## Technology Stack

| Layer | Technology |
|--------|------------|
| Backend | Java 17 |
| Framework | Spring Boot |
| AI Framework | Spring AI |
| LLM | Llama 3 (Ollama) |
| REST API | Spring Web |
| Build Tool | Maven |
| Version Control | Git |
| Repository | GitHub |
| Future Vector DB | Qdrant |

---

## Architecture

```
Support Engineer
        │
        ▼
 Spring Boot REST API
        │
        ▼
 Spring AI
        │
        ▼
 Ollama
        │
        ▼
 Llama 3
```

Future Architecture

```
Support Engineer
        │
        ▼
Spring Boot API
        │
        ├──────────────► Qdrant
        │                   │
        ▼                   │
     Spring AI ◄────────────┘
        │
        ▼
     Llama 3
```

---

## Project Structure

```
src
 └── main
      ├── controller
      ├── service
      ├── serviceimpl
      ├── dto
      ├── config
      └── ai
```

---

## Current Progress

✅ Phase 1 Completed

- Spring Boot Setup
- Ollama Integration
- AI Chat API
- GitHub Repository

🚧 Phase 2

- AI Log Analyzer

---

## Author

Maheswar Mopuru