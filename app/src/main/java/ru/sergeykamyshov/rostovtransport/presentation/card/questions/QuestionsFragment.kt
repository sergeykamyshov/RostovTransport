package ru.sergeykamyshov.rostovtransport.presentation.card.questions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_card_questions.view.*
import ru.sergeykamyshov.rostovtransport.R


class QuestionsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_card_questions, container, false)

        view.rv_questions.layoutManager = LinearLayoutManager(activity)
        val adapter = QuestionsAdapter(activity!!)
        view.rv_questions.adapter = adapter

        val questions = activity?.resources?.getStringArray(R.array.card_questions)?.toList()
        val answers = activity?.resources?.getStringArray(R.array.card_answers)?.toList()

        if (questions != null && answers != null && questions.size == answers.size) {
            val items = ArrayList<QuestionAnswer>()
            for ((index, question) in questions.withIndex()) {
                items.add(QuestionAnswer(question, answers[index]))
            }
            adapter.update(items)
        }

        return view
    }

    companion object {
        fun newInstance() = QuestionsFragment()
    }

}

class QuestionAnswer(
        val question: String,
        val answer: String
)