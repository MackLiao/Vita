# Vita - Personal Medical Assistant

Vita is an intelligent virtual medical assistant designed for healthcare question answering (QA) and appointment scheduling. Built with Java Spring Boot and LangChain4j, Vita provides conversational AI capabilities to help patients with medical guidance and manage their healthcare appointments.

## 🌟 Features

### Medical Question Answering
- **AI-Powered Medical Guidance**: Provides detailed, accurate, and practical medical advice based on clinical practice and research
- **RAG (Retrieval-Augmented Generation)**: Uses vector embeddings and document retrieval to provide contextually relevant medical information
- **AI Triage**: Recommends the most appropriate medical specialty based on patient symptoms and needs

### Appointment Management
- **Check Appointment Availability**: Query available appointment slots by department, date, time, and doctor
- **Schedule Appointments**: Book appointments with automatic availability checking
- **Cancel Appointments**: Cancel existing appointments with validation

### Conversational AI
- **Streaming Responses**: Real-time streaming chat responses for better user experience
- **Conversation Memory**: Persistent chat memory using MongoDB for context-aware conversations
- **Multi-LLM Support**: Supports OpenAI, Ollama, and Alibaba Cloud DashScope models

## 🏗️ Architecture

Vita is built with a modern microservices architecture:

- **Backend**: Spring Boot 3.2.6 with LangChain4j for AI capabilities
- **Frontend**: Vue.js 3 with Element Plus UI components
- **Database**: 
  - MySQL for appointment data persistence
  - MongoDB for chat memory storage
- **Vector Store**: Pinecone for RAG document embeddings
- **API Documentation**: Knife4j (Swagger) for API testing

## 🛠️ Tech Stack

### Backend
- **Java 17**
- **Spring Boot 3.2.6**
- **LangChain4j 1.0.0-beta3** - AI framework
- **MyBatis Plus 3.5.11** - ORM framework
- **Spring Data MongoDB** - NoSQL database integration
- **Spring WebFlux** - Reactive streaming support

### AI/ML
- **LangChain4j** - AI orchestration
- **OpenAI API** - GPT models
- **Alibaba Cloud DashScope** - Qwen models
- **Ollama** - Local LLM support
- **Pinecone** - Vector database for embeddings

### Frontend
- **Vue.js 3.5.13**
- **Element Plus 2.8.4**
- **Vite 5.4.8**
- **Axios 1.7.7**

## 📋 Prerequisites

Before you begin, ensure you have the following installed:

- **Java 17** or higher
- **Maven 3.6+**
- **Node.js 16+** and npm/yarn
- **MySQL 8.0+**
- **MongoDB 4.4+**
- **Ollama** (optional, for local LLM)

### API Keys Required

You'll need API keys for at least one of the following:
- **OpenAI API Key** (for GPT models)
- **Alibaba Cloud DashScope API Key** (for Qwen models)
- **Pinecone API Key** (for vector embeddings)

## 🚀 Installation & Setup

### 1. Clone the Repository

git clone <repository-url>
cd AIChatBot/java-ai-langchain4j### 2. Backend Setup

#### Configure Database

1. **MySQL Setup**:
  
   CREATE DATABASE vita;
   2. **MongoDB Setup**:
   - Ensure MongoDB is running on `localhost:27017`
   - The application will automatically create the `chat_memory_db` database

#### Configure Application Properties

Edit `src/main/resources/application.properties`:
ties
# Set your API keys
OPENAI_API_KEY=your_openai_api_key
DASH_SCOPE_API_KEY=your_dashscope_api_key

# MySQL Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/vita?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&useSSL=false
spring.datasource.username=your_username
spring.datasource.password=your_password

# MongoDB Configuration
spring.data.mongodb.uri=mongodb://localhost:27017/chat_memory_db#### Build and Run Backend

mvn clean install
mvn spring-boot:runThe backend will start on `http://localhost:8080`

### 3. Frontend Setup

cd vita-ui
npm install
npm run devThe frontend will start on `http://localhost:5173` (default Vite port)

### 4. API Documentation

Once the backend is running, access the API documentation at:
- **Swagger UI**: `http://localhost:8080/doc.html`
- **Knife4j**: `http://localhost:8080/doc.html`

## ⚙️ Configuration

### LLM Model Configuration

The application supports multiple LLM providers. Configure in `application.properties`
