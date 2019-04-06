package ru.sergeykamyshov.rostovtransport.presentation.card.questions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_questions_card_layout.view.*
import kotlinx.android.synthetic.main.card_questions_first_card_layout.view.*
import ru.sergeykamyshov.rostovtransport.R

class QuestionsAdapter(
        private var context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val MAIN_TYPE = 1
    private val DEFAULT_TYPE = 2
    private val layoutInflater = LayoutInflater.from(context)
    private var items: List<QuestionAnswer> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            MAIN_TYPE -> MainViewHolder(layoutInflater.inflate(R.layout.card_questions_first_card_layout, parent, false))
            else -> DefaultViewHolder(layoutInflater.inflate(R.layout.card_questions_card_layout, parent, false))
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        when (holder) {
            is MainViewHolder -> {
                holder.question.text = item.question
                holder.answer.text = item.answer
            }
            is DefaultViewHolder -> {
                holder.question.text = item.question
                holder.answer.text = item.answer
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) MAIN_TYPE else DEFAULT_TYPE
    }

    fun update(items: List<QuestionAnswer>) {
        this.items = items
        notifyDataSetChanged()
    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val question: TextView = itemView.tv_card_first_question
        val answer: TextView = itemView.tv_card_first_answer
    }

    class DefaultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val question: TextView = itemView.tv_question
        val answer: TextView = itemView.tv_answer
    }

}