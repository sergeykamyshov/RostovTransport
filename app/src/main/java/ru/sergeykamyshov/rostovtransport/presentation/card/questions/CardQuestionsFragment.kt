package ru.sergeykamyshov.rostovtransport.presentation.card.questions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.card_questions_card_layout.view.*
import kotlinx.android.synthetic.main.card_questions_first_card_layout.view.*
import kotlinx.android.synthetic.main.fragment_card_questions.*
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.data.models.card.CardQuestions
import ru.sergeykamyshov.rostovtransport.presentation.main.MainActivity


class CardQuestionsFragment : Fragment() {

    companion object {
        fun newInstance() = CardQuestionsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_card_questions, container, false)

        val viewModel = ViewModelProviders.of(activity as MainActivity).get(CardQuestionsViewModel::class.java)
        viewModel.getData().observe(this, Observer {
            if (it != null) {
                createLayoutAndFillData(it)

                card_questions_progress.visibility = View.GONE
                ll_card_questions_content.visibility = View.VISIBLE
            }
        })
        viewModel.loadData()

        return view
    }

    /**
     * Создает разметку для всех вопросов/ответов и заполняем их данными
     * questions - список вопросов с ответами
     */
    private fun createLayoutAndFillData(questions: List<CardQuestions.Question>) {
        val mainActivity = activity as MainActivity
        ll_card_questions_content.removeAllViews()
        for (i in 0 until questions.size) {
            if (i == 0) {
                // Для первого вопроса/ответа создаем и заполняем разметку с изображением
                val view = mainActivity.layoutInflater.inflate(R.layout.card_questions_first_card_layout, ll_card_questions_content, true)
                view.tv_card_first_question.text = questions[i].question
                view.tv_card_first_answer.text = questions[i].answer
            } else {
                // Для каждого следующего вопроса/ответа создаем и заполняем новую разметку
                val view = mainActivity.layoutInflater.inflate(R.layout.card_questions_card_layout, ll_card_questions_content, false)
                view.tv_question.text = questions[i].question
                view.tv_answer.text = questions[i].answer
                ll_card_questions_content.addView(view)
            }
        }
    }

}