package ru.sergeykamyshov.rostovtransport.ui.card

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.ui.card.buy.CardBuyFragment
import ru.sergeykamyshov.rostovtransport.ui.card.deposit.CardDepositFragment
import ru.sergeykamyshov.rostovtransport.ui.card.info.CardInfoFragment
import ru.sergeykamyshov.rostovtransport.ui.card.questions.CardQuestionsFragment

/**
 * Адаптер экрана "Транспортная карта" для формирования вкладок
 */
class TransportCardTabsAdapter(var mContext: Context?, fm: FragmentManager?) : FragmentPagerAdapter(fm) {

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
            0 -> return mContext?.getString(R.string.card_tab_title_info)
            1 -> return mContext?.getString(R.string.card_tab_title_buy)
            2 -> return mContext?.getString(R.string.card_tab_title_deposit)
            3 -> return mContext?.getString(R.string.card_tab_title_questions)
        }
        return super.getPageTitle(position)
    }
}