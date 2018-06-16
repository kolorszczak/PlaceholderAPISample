package eu.mihau.placeholderapisample.utils.manager

import android.databinding.BindingAdapter
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.widget.TextView

import eu.mihau.placeholderapisample.utils.list.SeparatorItemDecorator

object DataBindingUtils {

    @JvmStatic
    @BindingAdapter("itemDecorator")
    fun setItemDecorator(view: RecyclerView, drawable: Int?) {
        if (view.itemDecorationCount > 0) {
            for (i in 0 until view.itemDecorationCount) {
                view.removeItemDecorationAt(i)
            }
        }
        view.addItemDecoration(SeparatorItemDecorator(ContextCompat.getDrawable(view.context, drawable!!)!!))
    }

    @JvmStatic
    @BindingAdapter("android:text")
    fun setLong(view: TextView, value: Long?) {
        if (value == null)
            return
        view.text = value.toString()
    }
}
