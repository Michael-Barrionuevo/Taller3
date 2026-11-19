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
    implementation("dev.langchain4j:langchain4j-open-ai:${langchain4jVersion}")

    implementation("ch.qos.logback:logback-classic:1.6.6")
}

configurations.all {
    resolutionStrategy {
        force("com.microsoft.onnxruntime:onnxruntime:1.19.2")
        force("ai.djl.huggingface:tokenizers:0.28.0")
    }
}

tasks.test {
    useJUnitPlatform()
}