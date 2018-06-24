package ru.sergeykamyshov.rostovtransport.ui.card.questions

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import ru.sergeykamyshov.rostovtransport.MainActivity
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.data.network.model.card.CardQuestions


class CardQuestionsFragment : Fragment() {

    private lateinit var firstQuestion: TextView
    private lateinit var firstAnswer: TextView
    private lateinit var contentLayout: LinearLayout

    companion object {
        fun newInstance() = CardQuestionsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_card_questions, container, false)

        val progressBar = view.findViewById<ProgressBar>(R.id.card_questions_progress)

        contentLayout = view.findViewById(R.id.sv_card_questions_content)

        firstQuestion = view.findViewById(R.id.tv_card_first_question)
        firstAnswer = view.findViewById(R.id.tv_card_first_answer)

        val viewModel = ViewModelProviders.of(activity as MainActivity).get(CardQuestionsViewModel::class.java)
        viewModel.getData().observe(this, Observer {
            if (it != null) {
                createLayoutAndfillData(it)

                progressBar.visibility = View.GONE
                contentLayout.visibility = View.VISIBLE
            }
        })
        viewModel.loadData()

        return view
    }

    /**
     * Создает разметку для всех вопросов/ответов (кроме первого) и заполняем их данными
     * questions - список вопросов с ответами
     */
    private fun createLayoutAndfillData(questions: List<CardQuestions.Question>) {
        for (i in 0 until questions.size) {
            if (i == 0) {
                // Первый вопрос/ответ всегда заполняет поля заголовока (тот что с картинкой)
                firstQuestion.text = questions.get(i).question
                firstAnswer.text = questions.get(i).answer
            } else {
                // Для каждого следующего вопроса/ответа создаем и заполняем новую разметку
                val mainActivity = activity as MainActivity
                val view = mainActivity.layoutInflater.inflate(R.layout.item_card_questions, contentLayout, false)

                val question = view.findViewById<TextView>(R.id.tv_question)
                val answer = view.findViewById<TextView>(R.id.tv_answer)

                question.text = questions.get(i).question
                answer.text = questions.get(i).answer

                contentLayout.addView(view)
            }
        }
    }

}