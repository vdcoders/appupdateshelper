import com.vanniktech.maven.publish.SonatypeHost
import org.gradle.jvm.tasks.Jar

plugins {
    alias(libs.plugins.ksp)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}
version = "0.0.2"

android {
    namespace = "com.inhot.appupdateshelper"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.play.app.update)
    implementation(libs.play.app.update.ktx)
}

fun getVersionName(): String = project.version.toString()
fun getGroupId(): String = "io.github.vdcoders"

val sourceJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(android.sourceSets["main"].java.srcDirs)
}

afterEvaluate {
    mavenPublishing {
        publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
        signAllPublications()

        coordinates(
            getGroupId(),
            "appupdateshelper",
            getVersionName()
        )

        pom {
            name.set("appupdateshelper Android library")
            description.set("The mpv library used by mpvKt.")
            inceptionYear.set("2025")
            url.set("https://github.com/vdcoders/appupdateshelper")

            licenses {
                license {
                    name.set("MIT License")
                    url.set("https://opensource.org/license/mit/")
                    distribution.set("repo")
                }
            }

            developers {
                developer {
                    id.set("vdcoders")
                    name.set("vCoderz")
                    url.set("https://github.com/vdcoders")
                }
            }

            scm {
                url.set("https://github.com/vdcoders/appupdateshelper")
                connection.set("scm:git:git://github.com/vdcoders/appupdateshelper.git")
                developerConnection.set("scm:git:ssh://git@github.com/vdcoders/appupdateshelper.git")
            }
        }
    }
}