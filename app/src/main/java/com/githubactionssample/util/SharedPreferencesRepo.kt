package com.githubactionssample.util

import android.content.Context
import android.content.SharedPreferences
import com.githubactionssample.dto.UserInfo
import java.util.*

class SharedPreferencesRepo(context: Context) {

    companion object {
        const val SHARED_PREFERENCE_NAME = "MY_APP_PREF"
        const val KEY_NAME = "key_name"
        const val KEY_DOB = "key_dob_millis"
        const val KEY_EMAIL = "key_email"
    }

    private val preferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
    private val editor = preferences.edit()

    /**
     * Saves the given {@link UserInfo} that contains the user's settings to
     * {@link SharedPreferences}.
     *
     * @param userInfo contains data to save to {@link SharedPreferences}.
     * @return {@code true} if writing to {@link SharedPreferences} succeeded. {@code false}
     *         otherwise.
     */
    fun saveUserInfo(userInfo: UserInfo): Boolean {
        editor.putString(KEY_NAME, userInfo.mName.toString())
        editor.putLong(KEY_DOB, userInfo.mDateOfBirth.timeInMillis)
        editor.putString(KEY_EMAIL, userInfo.mEmail.toString())
        return editor.commit()
    }

    /**
     * Retrieves the {@link UserInfo} containing the user's personal information from
     * {@link SharedPreferences}.
     *
     * @return the Retrieved {@link UserInfo}.
     */
    fun getUserInfo(): UserInfo {
        val name = preferences.getString(KEY_NAME, "")
        val dobMillis = preferences.getLong(KEY_DOB, Calendar.getInstance().timeInMillis)
        val dateOfBirth: Calendar = Calendar.getInstance()
        dateOfBirth.timeInMillis = dobMillis
        val email = preferences.getString(KEY_EMAIL, "")
        return UserInfo(name, dateOfBirth, email)
    }

    fun clearData() {
        editor.clear()
        editor.commit()
    }

}