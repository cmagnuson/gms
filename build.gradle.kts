plugins {
	id("org.springframework.boot") version "2.3.5.RELEASE"
	id("io.spring.dependency-management") version "1.0.10.RELEASE"
	id("org.siouan.frontend-jdk11") version "4.0.1"
	id("com.github.spotbugs") version "4.6.0"
	pmd
	jacoco
	java
}

group "com.carlmagnuson"
version "0.0.1-SNAPSHOT"

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
	developmentOnly
	runtimeClasspath {
		extendsFrom(configurations.developmentOnly.get())
	}
}

pmd {
	maxFailures.set(Integer.MAX_VALUE)
	reportsDir = file("$project.buildDir/reports/pmd")
	toolVersion = "6.20.0"
}

spotbugs {
	ignoreFailures.set(true)
	reportsDir.set(file("$project.buildDir/reports/findbugs"))
	effort.set(com.github.spotbugs.snom.Effort.MAX)
}

jacoco {
	toolVersion = "0.8.5"
}

frontend {
	nodeVersion.value("14.15.0")
	assembleScript.value("run build")
	nodeInstallDirectory.set(file("${projectDir}/build/node"))
	yarnInstallDirectory.set(file("${projectDir}/build/yarn"))
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-batch")
	implementation("org.springframework.boot:spring-boot-starter-jooq")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-data-rest")
	implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity5")
	compileOnly("org.projectlombok:lombok")
	runtimeOnly("com.h2database:h2")
	annotationProcessor("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group="org.junit.vintage", module="junit-vintage-engine")
	}
	testImplementation("org.springframework.batch:spring-batch-test")
	testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
	systemProperty("spring.profiles.active", "dev")
}
