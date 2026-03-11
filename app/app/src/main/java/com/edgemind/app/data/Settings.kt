package com.edgemind.app.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "edgemind_settings")

object SettingsKeys {
    val HF_TOKEN = stringPreferencesKey("hf_access_token")

    // Built-in token — used when no user-saved token exists.
    // Users can override this via Settings on the Home screen.
    const val DEFAULT_HF_TOKEN = "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"
}

suspend fun Context.getHfToken(): String {
    val saved = dataStore.data.map { it[SettingsKeys.HF_TOKEN] ?: "" }.first()
    return saved.ifEmpty { SettingsKeys.DEFAULT_HF_TOKEN }
}

suspend fun Context.saveHfToken(token: String) {
    dataStore.edit { it[SettingsKeys.HF_TOKEN] = token }
}
