package com.sample.searchrestaurant.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.sample.searchrestaurant.models.Category

object MyExtension {

    /*
    Get last world from string
     */
    fun String.resCity() = this.split(",").last()

    /*
    Combine all menu items
     */
    fun List<Category>.menuNames(): String {
        val names = StringBuilder();
        for (cat in this) {
            for (menu in cat.menuItems) {
                if (names.isEmpty()) names.append(menu.name) else {
                    names.append(", ")
                    names.append(menu.name)
                }
            }
        }
        return names.toString();
    }

    /*
    Dismiss keyboard
     */
    fun View.dismissKeyboard(context: Context) = try {
        val inputMethodManager: InputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}