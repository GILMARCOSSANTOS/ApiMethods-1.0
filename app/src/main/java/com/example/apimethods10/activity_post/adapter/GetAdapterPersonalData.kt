package com.example.apimethods10.activity_post.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apimethods10.R
import com.example.apimethods10.activity_post.model.ModelGetPersonalData
import com.example.apimethods10.activity_post.model.ModelPost
import com.example.apiregisteruser_10.utils.Capitalize
import java.util.*

class GetAdapterPersonalData(
    private val context: Context,
    private var listPersonalData: MutableList<ModelGetPersonalData>
) : RecyclerView.Adapter<GetAdapterPersonalData.GetViewHolderPersonalData>() {

    private var capitalizeString = Capitalize()

    class GetViewHolderPersonalData(view: View) : RecyclerView.ViewHolder(view) {
        var id: TextView? = null
        var title: TextView? = null
        var body: TextView? = null

        init {
            id = view.findViewById(R.id.txtVw_userId_componentGetPersonalData_id)
            title = view.findViewById(R.id.txtVw_title_componentGetPersonalData_id)
            body = view.findViewById(R.id.txtVw_body_componentGetPersonalData_id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GetViewHolderPersonalData {
        val layoutInflater =
            LayoutInflater.from(context).inflate(R.layout.component_get_personaldata, parent, false)

        val holder = GetViewHolderPersonalData(layoutInflater)
        LayoutInflater.from(parent.context)
            .inflate(R.layout.component_get_personaldata, parent, false)
        return holder
    }

    override fun onBindViewHolder(holder: GetViewHolderPersonalData, position: Int) {
        val dataPersonal = listPersonalData[position]

        if (dataPersonal != null) {
            holder.id?.text = buildString {
                append("▬▬▬ CAPÍTULO ")
                append(dataPersonal.userId.toString().padStart(3, '0'))
                append(" ▬▬▬")
            }

            holder.title?.text = buildString {
                append("• SUB - TÍTULO: ")
                append(dataPersonal.title.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                })
            }

            holder.body?.text = buildString {
                append("• TEXTO: ")
                append(capitalizeString.capitalize(dataPersonal.body))
            }
        }
    }

    override fun getItemCount(): Int {
        return listPersonalData.size
    }

    fun addPersonalData(newData:MutableList<ModelGetPersonalData>) {
        val insertIndex = listPersonalData.size
        listPersonalData.addAll(newData)
        notifyItemRangeInserted(insertIndex, newData.size)
    }
}