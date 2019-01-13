package ru.sergeykamyshov.rostovtransport.presentation.card

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.presentation.card.buy.CardBuyFragment
import ru.sergeykamyshov.rostovtransport.presentation.card.deposit.CardDepositFragment
import ru.sergeykamyshov.rostovtransport.presentation.card.info.CardInfoFragment
import ru.sergeykamyshov.rostovtransport.presentation.card.questions.CardQuestionsFragment

/**
 * Адаптер экрана "Транспортная карта" для формирования вкладок
 */
class TransportCardTabsAdapter(
        var context: Context?,
        fm: FragmentManager
) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return CardInfoFragment.newInstance()
            1 -> return CardBuyFragment.newInstance()
            2 -> return CardDepositFragment.newInstance()
            3 -> return CardQuestionsFragment.newInstance()
        }
        return CardInfoFragment.newInstance()
    }

    override fun getCount() = 4

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return context?.getString(R.string.card_tab_title_info)
            1 -> return context?.getString(R.string.card_tab_title_buy)
            2 -> return context?.getString(R.string.card_tab_title_deposit)
            3 -> return context?.getString(R.string.card_tab_title_questions)
        }
        return super.getPageTitle(position)
    }
}