plugins {
    java
}

group = "libvirgo.github.io.item.proto"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

dependencies {
    implementation("io.grpc:grpc-stub:1.53.0")
    implementation("io.grpc:grpc-protobuf:1.53.0")
    if (JavaVersion.current().isJava9Compatible) {
        // Workaround for @javax.annotation.Generated
        // see: https://github.com/grpc/grpc-java/issues/3633
        implementation("javax.annotation:javax.annotation-api:1.3.1")
    }
}

