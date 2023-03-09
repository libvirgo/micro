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
            protobuf(files("$projectDir/main"))
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
                    builtins {
                        remove("java")
                        listOf("go", "rust", "java").forEach {
                            id(it) {
                                doLast {
                                    copy {
                                        from("$buildDir/generated/source/proto/main/$it")
                                        into("$projectDir/proto/    $it")
                                    }
                                }
                            }
                        }
                    }
                }
                ofSourceSet("main").forEach {
                    it.plugins {
                        id("grpc") {}
                    }
                }
            }
        }
    }
}
