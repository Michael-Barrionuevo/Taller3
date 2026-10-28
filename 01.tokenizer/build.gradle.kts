plugins {
    id("java")
}

group = "com.taller3"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.knuddels:jtokkit:1.1.0")

}

tasks.test {
    useJUnitPlatform()
}