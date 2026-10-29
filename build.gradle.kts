plugins {
    alias(libs.plugins.blossom)
    alias(libs.plugins.forge.gradle)
    alias(libs.plugins.renamer)
    idea
    java
}

idea.module.excludeDirs.add(projectDir.resolve("runs"))

java.toolchain.languageVersion.set(JavaLanguageVersion.of(25))

sourceSets {
    main {
        blossom {
            javaSources {
                property("id", providers.gradleProperty("id"))
                property("group", group.toString())
            }
        }
    }
}

minecraft {
    mappings("stable", libs.versions.mcp.get())
    runs {
        register("client")
        register("server") {
            args("--nogui")
        }
    }
    useDefaultAccessTransformer()
}

repositories {
    mavenCentral()
    maven(fg.minecraftLibsMaven)
    maven(fg.forgeMaven)
    minecraft.mavenizer(this)
}

dependencies {
    compileOnly(libs.jspecify)
    annotationProcessor(libs.jabel)
    compileOnly(libs.jabel)
    implementation(minecraft.dependency(libs.forge))
}

tasks.compileJava {
    sourceCompatibility = "21"
    options.release.set(8)
}

afterEvaluate {
    tasks.named<JavaExec>("runClient") {
        javaLauncher.set(
            javaToolchains.launcherFor {
                languageVersion.set(JavaLanguageVersion.of(8))
            }
        )
    }
    tasks.named<JavaExec>("runServer") {
        javaLauncher.set(
            javaToolchains.launcherFor {
                languageVersion.set(JavaLanguageVersion.of(8))
            }
        )
    }
}
