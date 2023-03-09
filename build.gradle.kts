import com.google.protobuf.gradle.*

plugins {
    java
    id("com.google.protobuf") version "0.9.2"
}

group = "libvirgo.github.io"

repositories {
    mavenCentral()
}
allprojects {
    if (project.name == "proto") {
        apply(plugin = "java")
        apply(plugin = "com.google.protobuf")
        repositories {
            mavenCentral()
        }
        dependencies {
            protobuf(files("$projectDir/proto"))
        }
        protobuf {
            protoc {
                artifact = "com.google.protobuf:protoc:3.21.12"
            }
            plugins {
                id("grpc") {
                    artifact = "io.grpc:protoc-gen-grpc-java:1.53.0"
                }
            }
            generateProtoTasks {
                all().configureEach {
                    group = "proto"
                    plugins {
                        id("grpc") {
                            outputSubDir = "java"
                        }
                    }
                    val lang = listOf("go", "rust", "java")
                    builtins {
                        remove("java")
                        lang.forEach {
                            id(it)
                        }
                    }
                    doLast {
                        lang.forEach {
                            copy {
                                from("$generatedFilesBaseDir/main/$it")
                                into("$projectDir/src/main/$it")
                            }
                        }
                        delete(files(generatedFilesBaseDir))
                    }
                }
            }
        }
    }
}
