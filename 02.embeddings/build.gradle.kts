plugins {
    id("java")
}

group = "com.taller3"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val langchain4jVersion = "1.23.0"

dependencies {
    implementation("dev.langchain4j:langchain4j:${langchain4jVersion}")
    implementation("dev.langchain4j:langchain4j-embeddings:${langchain4jVersion}-beta33")

    // Opcional: quita la advertencia de SLF4J
    runtimeOnly("org.slf4j:slf4j-simple:2.0.16")
}

// Fuerza una versión de ONNX Runtime compatible con tu macOS
configurations.all {
    resolutionStrategy {
        force("com.microsoft.onnxruntime:onnxruntime:1.19.2")
        force("ai.djl.huggingface:tokenizers:0.28.0")
    }
}

tasks.test {
    useJUnitPlatform()
}