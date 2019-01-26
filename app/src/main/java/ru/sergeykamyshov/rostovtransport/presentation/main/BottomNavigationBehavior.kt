package ru.sergeykamyshov.rostovtransport.presentation.main

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar

class BottomNavigationBehavior : CoordinatorLayout.Behavior<BottomNavigationView> {

    constructor() : super()

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun layoutDependsOn(parent: CoordinatorLayout, child: BottomNavigationView,
                                 dependency: View): Boolean {
        if (dependency is Snackbar.SnackbarLayout) {
            updateSnackbar(child, dependency)
        }
        return super.layoutDependsOn(parent, child, dependency)
    }

    private fun updateSnackbar(child: View, snackbarLayout: Snackbar.SnackbarLayout) {
        if (snackbarLayout.layoutParams is CoordinatorLayout.LayoutParams) {
            val params = snackbarLayout.layoutParams as CoordinatorLayout.LayoutParams

            params.anchorId = child.id
            params.anchorGravity = Gravity.TOP
            params.gravity = Gravity.TOP
            snackbarLayout.layoutParams = params
        }
    }

//    override fun onStartNestedScroll(
//            coordinatorLayout: CoordinatorLayout,
//            child: BottomNavigationView,
//            directTargetChild: View,
//            target: View,
//            nestedScrollAxes: Int
//    ): Boolean {
//        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL
//    }

//    override fun onNestedPreScroll(
//            coordinatorLayout: CoordinatorLayout,
//            child: BottomNavigationView,
//            target: View,
//            dx: Int,
//            dy: Int,
//            consumed: IntArray
//    ) {
//        if (dy < 0) {
//            showBottomNavigationView(child)
//        } else if (dy > 0) {
//            hideBottomNavigationView(child)
//        }
//    }
//
//    private fun hideBottomNavigationView(view: BottomNavigationView) {
//        view.animate().translationY(view.height.toFloat())
//    }
//
//    private fun showBottomNavigationView(view: BottomNavigationView) {
//        view.animate().translationY(0f)
//    }
}