package com.omx1000.util
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder


object AppPreference {

    const val PREF_NAME = "OMX1000"
    const val KEY_DEVICE_INFO = "DEVICE_INFO"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    private fun getEditor(context: Context): SharedPreferences.Editor {
        return getPreferences(context).edit()
    }

    fun writeBooleanValue(context: Context,key: String?,value: Boolean)
    {
        getEditor(context).putBoolean(key, value).commit()
    }

    fun getBooleanValue(context: Context, key: String?,defValue: Boolean): Boolean
    {
        return getPreferences(context).getBoolean(key, defValue)
    }

    fun writeIntegerValue(context: Context,key: String?,value: Int)
    {
        getEditor(context).putInt(key, value).commit()
    }

    fun getIntegerValue(context: Context,key: String?,defValue: Int): Int
    {
        return getPreferences(context).getInt(key, defValue)
    }

    fun writeStringValue(context: Context,key: String?,value: String?)
    {
        getEditor(context).putString(key, value).commit()
    }

    fun getStringValue(context: Context,key: String?): String?
    {
        return getPreferences(context).getString(key, null)
    }

    /**
     * Saves object into the Preferences.
     *
     * @param object Object of model class (of type [T]) to save
     * @param key Key with which Shared preferences to
     **/
    fun <T> writeObjectValue(context: Context, key: String,obj: T) {
        //Convert object to JSON String.
        val jsonString = GsonBuilder().create().toJson(obj)
        getEditor(context).putString(key, jsonString).commit()
    }

    /**
     * Used to retrieve object from the Preferences.
     *
     * @param key Shared Preference key with which object was saved.
     **/
    inline fun <reified T> getObjectValue(mContext: Context, key: String): T? {
        //We get JSON String which was saved.
        val pref = mContext.getSharedPreferences(PREF_NAME, 0)
        val value = pref.getString(key, null)
        //JSON String was found which means object can be get.
        //We convert this JSON String to model object. Parameter "c" (of
        //type Class < T >" is used to cast.
        return GsonBuilder().create().fromJson(value, T::class.java)
    }

    fun clearAllPreference(context: Context)
    {
        getEditor(context).clear().commit()
    }
    fun clearPreference(context: Context,key: String)
    {
        getEditor(context).remove(key).commit()
    }
}