package com.rick.and.morty.buildsrc

object Libs {

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:1.7.0"
        const val appCompat = "androidx.appcompat:appcompat:1.4.1"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.3"

        object Navigation {
            val navVersion = "2.4.2"
            val fragmentKtx = "androidx.navigation:navigation-fragment-ktx:$navVersion"
            val uiKtx = "androidx.navigation:navigation-ui-ktx:$navVersion"

            object Test {
                val navigation = "androidx.navigation:navigation-testing:$navVersion"
            }

        }

    }

    object Hilt {
        val hiltVersion = "2.38.1"
        val android = "com.google.dagger:hilt-android:$hiltVersion"
        val androidCompiler = "com.google.dagger:hilt-android-compiler:$hiltVersion"
    }

    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
        const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:2.9.0"
    }

    object Kotlinx {
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0"
    }
    
    object Google {
        const val material = "com.google.android.material:material:1.5.0"
        const val androidPlayCore = "com.google.android.play:core:1.10.3"
        const val androidPlayCoreKtx = "com.google.android.play:core-ktx:1.8.1"
    }

    object Test {
        const val junit = "junit:junit:4.13.2"
        const val espressoCore = "androidx.test.espresso:espresso-core:3.4.0"

        object Ext {
            const val junit = "androidx.test.ext:junit:1.1.3"
        }
    }
}