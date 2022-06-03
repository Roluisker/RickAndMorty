package com.rick.and.morty.data

import android.util.Log
import javax.inject.Inject

class CharactersRepository @Inject constructor() {
    fun testFunction() {
        Log.d("Aloha", "characters repository")
    }
}