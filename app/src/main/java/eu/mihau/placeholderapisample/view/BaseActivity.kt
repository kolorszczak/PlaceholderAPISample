package eu.mihau.placeholderapisample.view

import android.app.AlertDialog
import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.SearchView
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import dagger.android.support.DaggerAppCompatActivity
import eu.mihau.placeholderapisample.R

open class BaseActivity : DaggerAppCompatActivity(){

    override fun onBackPressed() {
        // Check if no view has focus:
        val view = this.currentFocus
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        if (view != null && !imm.hideSoftInputFromWindow(view.windowToken, 0) && isTaskRoot) {
            if (view is EditText) {
                view.clearFocus()
            }
            showExitDialog()
        } else if (isTaskRoot) {
            showExitDialog()
        } else {
            super.onBackPressed()
        }
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        hideKeyboard(event, currentFocus)
        return super.dispatchTouchEvent(event)
    }

    private fun hideKeyboard(event: MotionEvent, currentFocus: View?) {
        if (event.action == MotionEvent.ACTION_DOWN) {
            if (currentFocus is EditText || currentFocus is SearchView) {
                val outRect = Rect()
                currentFocus.getGlobalVisibleRect(outRect)
                //check if user has not clicked on currently focus view
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    // clear focus on currently focused view if clicked outside
                    currentFocus.clearFocus()

                    val newFocus = findViewAt(findViewById(R.id.coordinator), event.rawX.toInt(), event.rawY.toInt())
                    // hide keyboard if newly focused view is not keyboard owner
                    if (newFocus == null || !(newFocus is EditText || currentFocus is SearchView)) {
                        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(currentFocus.windowToken, 0)
                    }
                }
            }
        }
    }

    private fun findViewAt(viewGroup: ViewGroup, x: Int, y: Int): View? {
        for (i in 0 until viewGroup.childCount) {
            val child = viewGroup.getChildAt(i)
            if (child is ViewGroup) {
                val foundView = findViewAt(child, x, y)
                if (foundView != null && foundView.isShown) {
                    return foundView
                }
            } else {
                val location = IntArray(2)
                child.getLocationOnScreen(location)
                val rect = Rect(location[0], location[1], location[0] + child.width, location[1] + child.height)
                if (rect.contains(x, y)) {
                    return child
                }
            }
        }

        return null
    }

    private fun showExitDialog() {
        AlertDialog.Builder(this)
                .setTitle(getString(R.string.confirmQuit))
                .setMessage(R.string.confirmQuitContent)
                .setPositiveButton(R.string.quit, { dialogInterface, i ->
                    dialogInterface.dismiss()
                    finish()
                })
                .setNegativeButton(R.string.cancel, { dialogInterface, i -> dialogInterface.dismiss() }).show()
    }
}
