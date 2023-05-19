package com.example.apimethods10.activity_post.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apimethods10.R
import com.example.apimethods10.activity_post.model.ModelPost
import com.example.apiregisteruser_10.utils.Capitalize
import java.util.*

class AdapterPost(
    private val dataList: List<ModelPost>, private
    val editTextData: ModelPost
) : RecyclerView.Adapter<AdapterPost.PostViewHolder>() {


    private val capitalizeString = Capitalize()

    class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView? = null
        var body: TextView? = null

        init {
            title = view.findViewById(R.id.txtVw_subTitle_componentPost_id)
            body = view.findViewById(R.id.txtVw_text_componentPost_id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.component_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val dataUser = dataList[position]

        holder.title?.text = buildString {
            append("• SUB - TÍTULO: ")
            append(dataUser.title.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() })
        }

        holder.body?.text = buildString {
            append("• TEXTO: ")
            append(capitalizeString.capitalize(dataUser.body))
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateData(post: List<ModelPost>) {
        dataList = post
        notifyDataSetChanged()
    }
}