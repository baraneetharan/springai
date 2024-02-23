# Spring AI

**Spring AI** aims to simplify AI application development by providing abstractions that serve as the foundation for creating AI applications. These abstractions have multiple implementations, allowing easy component swapping with minimal code changes. Key features include:

1. **Model Support**: Spring AI supports major model providers like OpenAI, Microsoft, Amazon, Google, and Huggingface. It covers Chat and Text-to-Image models.
2. **Portable API**: Developers can use a consistent API across AI providers for Chat and Embedding models. Both synchronous and stream APIs are supported.
3. **Mapping to POJOs**: Spring AI maps AI model outputs to Plain Old Java Objects (POJOs).
4. **Vector Database Support**: It integrates with major vector database providers, including Azure Vector Search, Chroma, Milvus, Neo4j, PostgreSQL/PGVector, PineCone, Redis, and Weaviate.
5. **Vector Store API**: Spring AI offers a portable SQL-like metadata filter API for vector stores.
6. **Function Calling**: Developers can call functions within the framework.
7. **Spring Boot Integration**: Auto configuration and starters are available for AI models and vector stores.
8. **ETL Framework**: Spring AI includes an ETL framework for data engineering.

  

Spring AI simplifies AI development across programming languages by providing a versatile set of tools and abstractions.

  

### AI Concepts

#### Models

\- AI models process and generate information, mimicking human cognitive functions.

\- They learn patterns from data to make predictions or generate outputs.

\- Various types of AI models exist for different use cases.

\- Spring AI initially focuses on language processing models like OpenAI + Azure OpenAI.

\- Embeddings, numerical representations of text, support advanced use cases.

  

#### Prompts

\- Prompts guide AI models to produce specific outputs.

\- Crafting effective prompts is essential for quality outputs.

\- Effective prompts require an understanding of human-like interaction.

\- Prompt Engineering is a discipline focusing on prompt optimization.

\- Well-crafted prompts significantly impact output quality.

  

#### Prompt Templates

\- Templates establish request context and fill placeholders with user-specific data.

\- Spring AI uses StringTemplate for prompt creation, similar to Spring MVC's view layer.

\- Templates vary in complexity and data format, evolving from simple strings to multi-message structures.

  

#### Embeddings

\- Embeddings convert text to numerical vectors for AI processing.

\- They facilitate tasks like text classification and semantic search.

\- Understanding their role is crucial for integrating AI into applications.

  

#### Tokens

\- Tokens are the basic units of AI model processing.

\- They represent portions of text converted to numerical form.

\- Token usage affects model charges and processing limits.

\- Models have token limits, impacting the amount of text processed in one go.

  

#### Output Parsing

\- Models output data as strings, necessitating parsing for application integration.

\- Specialized prompts and parsing are essential for extracting usable data.

\- OpenAI Functions facilitate precise output formatting.

  

#### Bringing Your Data to the AI model

\- Customizing models involves fine-tuning, prompt stuffing, or function calling.

\- Retrieval Augmented Generation incorporates external data into prompts.

\- RAG involves data preprocessing and leveraging vector databases for relevance.

  

#### Function Calling

\- Allows large language models access to external data in real-time.

\- Simplifies function invocation and integration with Spring AI.

\- Multiple functions can be defined and referenced in a single prompt.

  

#### Evaluating AI responses

\- Effective evaluation ensures accuracy and usefulness of AI applications.

\- Metrics like relevance, coherence, and factual correctness gauge response quality.

\- Leveraging vector database information enhances evaluation.

  

#### ChatClient

  

```typescript
public interface ChatClient extends ModelClient<Prompt, ChatResponse> {

	default String call(String message) {// implementation omitted
	}

    @Override
	ChatResponse call(Prompt prompt);
}
```

  

![](https://t9002023706.p.clickup-attachments.com/t9002023706/94ee1369-ac2b-426e-b387-183fa20044d3/image.png)

  

#### Create Spring Boot Project [https://start.spring.io/](https://start.spring.io/)

  

![](https://t9002023706.p.clickup-attachments.com/t9002023706/bf91037b-82db-4850-bb5b-1d647f288fe7/image.png)

  

To use the Milestone and Snapshot version, you need to add references to the Spring Milestone and/or Snapshot repositories in your build file.

  

```xml
  <repositories>
    <repository>
      <id>spring-milestones</id>
      <name>Spring Milestones</name>
      <url>https://repo.spring.io/milestone</url>
      <snapshots>
        <enabled>false</enabled>
      </snapshots>
    </repository>
    <repository>
      <id>spring-snapshots</id>
      <name>Spring Snapshots</name>
      <url>https://repo.spring.io/snapshot</url>
      <releases>
        <enabled>false</enabled>
      </releases>
    </repository>
  </repositories>
```

  

#### Spring AI Bill of Materials (BOM)

  

Using the BOM from your application’s build script avoids the need for you to specify and maintain the dependency versions yourself

  

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.ai</groupId>
            <artifactId>spring-ai-bom</artifactId>
            <version>{project-version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

  

```xml
<dependency>
	<groupId>org.springframework.ai</groupId>
	<artifactId>spring-ai-openai-spring-boot-starter</artifactId>
	<version>0.8.0-SNAPSHOT</version>
</dependency>
```

  

#### ChatController

  

```java
package com.leads.springai.controller;

import java.util.Map;

import org.springframework.ai.chat.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @RequestMapping("/chat")
public class ChatController {
    private final ChatClient chatClient;


    @Autowired
    public ChatController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    
    @SuppressWarnings("rawtypes")
    @GetMapping("/chat")
    public Map generate(@RequestParam(value = "message", defaultValue = "TOP 5 AI initiative in india") String message) {
        return Map.of("generation", chatClient.call(message));
    }
}


```

  

**Class Definition:**

*   `ChatController` class annotated with `@RestController` makes it a RESTful endpoint.
*   `private ChatClient chatClient`: Injected dependency for interacting with the chat bot.

**Constructor:**

*   The constructor takes a `ChatClient` object as an argument and injects it using `@Autowired`.

**`generate`** **method:**

*   This method is annotated with `@GetMapping("/chat")`, making it accessible via a GET request to `/chat`.
*   It takes a message parameter (`String message`) with a default value of "TOP 5 AI initiative in india".
*   It uses the injected `chatClient` to call the bot with the provided message and stores the response in a map.
*   The map is then returned with the key "generation" and the bot's response as the value.

  

#### Run your Project

```plain
mvn spring-boot:run
```

  

#### Test our API endpoint

  

![](https://t9002023706.p.clickup-attachments.com/t9002023706/1f1bca98-a8ba-4b7f-8c17-b2acbd5dd503/image.png)

  

#### Source Code GitHub Link

[https://github.com/baraneetharan/springai](https://github.com/baraneetharan/springai)


#### How to use this project
Clone this repository in your system and run mvn spring-boot:run 
http://localhost:8080/swagger-ui/index.html
