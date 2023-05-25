package com.example.apimethods10.activity_post.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apimethods10.R
import com.example.apimethods10.activity_post.model.ModelPostApi
import com.example.apiregisteruser_10.utils.Capitalize
import java.util.*

class AdapterPostGeneralData(
    private val context: Context,
    private var listGeneralData: MutableList<ModelPostApi>
) : RecyclerView.Adapter<AdapterPostGeneralData.GetViewHolderPersonalData>() {

    private var capitalizeString = Capitalize()

    class GetViewHolderPersonalData(view: View) : RecyclerView.ViewHolder(view) {
        var id: TextView? = null
        var title: TextView? = null
        var body: TextView? = null

        init {
            id = view.findViewById(R.id.txtVw_Id_componentGet_id)
            title = view.findViewById(R.id.txtVw_title_componentGet_id)
            body = view.findViewById(R.id.txtVw_body_componentGet_id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GetViewHolderPersonalData {
        val layoutInflater =
            LayoutInflater.from(context).inflate(R.layout.component_get, parent, false)

        val holder = GetViewHolderPersonalData(layoutInflater)
        LayoutInflater.from(parent.context)
            .inflate(R.layout.component_get, parent, false)
        return holder
    }

    override fun onBindViewHolder(holder: GetViewHolderPersonalData, position: Int) {
        val dataPersonal = listGeneralData[position]

        if (dataPersonal != null) {
            holder.id?.text = buildString {
                append("▬▬▬▬▬ CAPÍTULO ")
                append(dataPersonal.id.toString().padStart(3, '0'))
                append(" ▬▬▬▬▬")
            }

            holder.title?.text = buildString {
                append("▬▬▬ TÍTULO: \n")
                append(dataPersonal.title.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() })
            }

            holder.body?.text = buildString {
                append("▬▬▬ PARÁGRAFO: \n")
                append(capitalizeString.capitalize(dataPersonal.body))
            }
        }
    }

    override fun getItemCount(): Int {
        return listGeneralData.size
    }

    fun addPersonalData(newData:MutableList<ModelPostApi>) {
        val insertIndex = listGeneralData.size
        listGeneralData.addAll(newData)
        notifyItemRangeInserted(insertIndex, newData.size)
    }
}