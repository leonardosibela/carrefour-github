@file:Suppress("unused")

package com.hikarisource.carrefourgithub.core.extensions

import android.R
import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun Fragment.popBackStack() = findNavController().popBackStack()

fun Fragment.navigate(directions: NavDirections) = findNavController().navigate(directions)

fun Fragment.displayToastShort(string: String) = displayToast(string, Toast.LENGTH_SHORT)

fun Fragment.displayToastLong(string: String) = displayToast(string, Toast.LENGTH_LONG)

@Suppress("unused")
fun Fragment.displayToast(any: Any) {
    displayToast(any.toString(), Toast.LENGTH_LONG)
}

fun Fragment.displayToastShort(@StringRes stringRes: Int) = displayToast(
    getString(stringRes), Toast.LENGTH_SHORT
)

fun Fragment.displayToastLong(@StringRes stringRes: Int) = displayToast(
    getString(stringRes), Toast.LENGTH_LONG
)

private fun Fragment.displayToast(@StringRes stringRes: Int, duration: Int) = displayToast(
    getString(stringRes), duration
)

private fun Fragment.displayToast(string: String, duration: Int) = Toast.makeText(
    requireContext(), string, duration
).show()

fun Fragment.launchWhenCreated(block: suspend CoroutineScope.() -> Unit) {
    lifecycleScope.launch {
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
            this.block()
        }
    }
}

@Suppress("FunctionName")
fun Fragment.VerticalItemDecoration(): RecyclerView.ItemDecoration {
    return DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
}

@SuppressLint("Recycle")
@Suppress("FunctionName")
fun Fragment.VerticalLine(): ImageView {
    val listDivider = intArrayOf(R.attr.listDivider)
    val styledAttributes = requireContext().obtainStyledAttributes(listDivider)
    val dividerDrawable = styledAttributes.getDrawable(0)
    return ImageView(requireContext()).apply {
        setImageDrawable(dividerDrawable)
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
        )
    }
}

fun Fragment.HorizontalLineDrawable(): Drawable {
    val listDivider = intArrayOf(R.attr.listDivider)
    val styledAttributes = requireContext().obtainStyledAttributes(listDivider)
    return styledAttributes.getDrawable(0) ?: throw IllegalStateException()
}
