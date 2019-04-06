package ru.sergeykamyshov.rostovtransport.presentation.help

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_help.*
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.presentation.base.BaseFragment

class HelpFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_help, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setActionBarTitle(R.string.title_help)

        // Настраиваем переключение по вкладкам
        val viewPager = vp_help
        // Используем childFragmentManager для корректного поведения фрагментов на вкладках,
        // т.к. они находятся внутри фрагмента "Справка"
        viewPager.adapter = HelpTabsAdapter(requireContext(), childFragmentManager)
        val tabLayout = tab_help
        tabLayout?.setupWithViewPager(viewPager)
        // В портретной ориентации все вкладки не вмещаются, поэтому по умолчанию они scrollable,
        // но в горизонтальной ориентации больше места, поэтому меняем настройку только при повороте экрана
        if (Configuration.ORIENTATION_LANDSCAPE == activity?.resources?.configuration?.orientation) {
            tabLayout.tabMode = TabLayout.MODE_FIXED
        }
    }

    companion object {
        fun newInstance() = HelpFragment()
    }

}