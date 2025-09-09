package com.example.authdemo.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton
private val Context.dataStore by preferencesDataStore("app_prefs")

@Singleton
class DataStoreManager @Inject constructor(
    @ApplicationContext private val context: Context
){

    companion object {
        val USER_TOKEN = stringPreferencesKey("user_token")
    }
    suspend fun saveUserToken(token : String){
         context.dataStore.edit {
            it[USER_TOKEN] = token
        }
    }

    suspend fun getUserToken() : Flow<String?>{
        return context.dataStore.data.map { prefs->
            prefs[USER_TOKEN]
        }
    }

    suspend fun clearUserData() {
        context.dataStore.edit { preferences ->
            preferences.remove(USER_TOKEN) // if you store email/username
        }
    }

}