package ru.sergeykamyshov.rostovtransport.presentation.card.questions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_card_questions.*
import ru.sergeykamyshov.rostovtransport.R

class QuestionsFragment : Fragment() {

    private lateinit var adapter: QuestionsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_card_questions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecycler()

        val questions = activity?.resources?.getStringArray(R.array.card_questions)?.toList()
        val answers = activity?.resources?.getStringArray(R.array.card_answers)?.toList()

        if (questions != null && answers != null && questions.size == answers.size) {
            val items = ArrayList<QuestionAnswer>()
            for ((index, question) in questions.withIndex()) {
                items.add(QuestionAnswer(question, answers[index]))
            }
            adapter.update(items)
        }
    }

    private fun setupRecycler() {
        rv_questions.layoutManager = LinearLayoutManager(requireContext())
        adapter = QuestionsAdapter(requireContext())
        rv_questions.adapter = adapter
    }

    companion object {
        fun newInstance() = QuestionsFragment()
    }

}

class QuestionAnswer(
        val question: String,
        val answer: String
)