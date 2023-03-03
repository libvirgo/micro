import com.google.protobuf.gradle.*

plugins {
    java
    idea
    id("org.springframework.boot") version "3.0.2"
    id("io.spring.dependency-management") version "1.1.0"
    id("com.google.protobuf") version "0.9.2"
}

group = "libvirgo.github.io"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.protobuf:protobuf-java:3.22.0")
    implementation("io.grpc:grpc-stub:1.53.0")
    implementation("io.grpc:grpc-protobuf:1.53.0")
    implementation("org.springframework.boot:spring-boot-starter")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation(kotlin("script-runtime"))
}

sourceSets {
    main {
        proto {
            srcDir("src/main")
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}


protobuf {
    protoc {
        path = "/nix/store/9gj7wqvm91i7kd7h1xpk558cvdh095vj-protobuf-3.21.12/bin/protoc"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.53.0"
        }
    }
    generateProtoTasks {
        all().configureEach {
            builtins {
                id("go") {
                    doLast {
                        delete("$rootDir/proto/go")
//                        copy {
//                            from("$buildDir/generated/source/proto/main/go")
//                            into("$rootDir/proto/go")
//                        }
                    }
                }
                id("rust") {
                    doLast {
                        delete("$rootDir/proto/rust")
//                        copy {
//                            from("$buildDir/generated/source/proto/main/rust")
//                            into("$rootDir/proto/rust")
//                        }
                    }
                }
//                remove("grpc")
            }
        }
        ofSourceSet("main").forEach {
            it.plugins {
//               id("grpc") {
//               }
            }
        }
    }
}
