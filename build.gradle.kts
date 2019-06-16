import com.jfrog.bintray.gradle.BintrayExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-library`
    `maven-publish`
    id("com.jfrog.bintray") version "1.8.4"
    id("com.gradle.build-scan") version "2.3"
    kotlin("jvm") version "1.3.31"
}

group = "net.besttoolbars"
version = "0.4.0"

repositories {
    mavenCentral()
}


val junit = "5.4.0"
val jackson = "2.9.8"

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.fasterxml.jackson.core:jackson-databind:$jackson")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jackson")
    implementation("org.apache.httpcomponents:httpclient:4.5.7")
    implementation("commons-io:commons-io:2.6")
    implementation("com.fasterxml:aalto-xml:1.2.0")

    testImplementation("org.junit.jupiter:junit-jupiter-api:$junit")
    testImplementation("org.mockito:mockito-junit-jupiter:2.25.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junit")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks {
    javadoc {
        if (JavaVersion.current().isJava9Compatible) {
            (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
        }
    }

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }

    withType(JavaCompile::class) {
        options.compilerArgs.add("-parameters")
    }

    named<Test>("test") {
        useJUnitPlatform()
    }

    register<Jar>("sourcesJar") {
        from(sourceSets.main.get().allSource)
        archiveClassifier.set("sources")
    }

    register<Jar>("javadocJar") {
        from(javadoc)
        archiveClassifier.set("javadoc")
    }
}

publishing {
    publications {
        create<MavenPublication>("admitad") {
            from(components["java"])
            artifact(tasks["sourcesJar"])
            artifact(tasks["javadocJar"])
        }
    }
}


bintray {
    user = System.getenv("BINTRAY_USER")
    key = System.getenv("BINTRAY_KEY")
    publish = true
    override = true
    setPublications("admitad")
    pkg(delegateClosureOf<BintrayExtension.PackageConfig> {
        repo = "repo"
        name = project.name
        userOrg = "besttoolbars"
        websiteUrl = "https://github.com/Besttoolbars/$name"
        githubRepo = "besttoolbars/$name"
        vcsUrl = "https://github.com/Besttoolbars/$name"
        description = "Admitad jvm connector"
        setLabels("kotlin", "java", "admitad")
        setLicenses("Apache-2.0")
    })
}

buildScan {
    termsOfServiceUrl = "https://gradle.com/terms-of-service"
    setTermsOfServiceAgree("yes")
}
