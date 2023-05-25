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

class AdapterPostGeneralData_Primary(
    private val context: Context,
    private var listGeneralData: MutableList<ModelPostApi>
) : RecyclerView.Adapter<AdapterPostGeneralData_Primary.ViewHolderGeneralData>() {

    private var capitalizeString = Capitalize()

    class ViewHolderGeneralData(view: View) : RecyclerView.ViewHolder(view) {
        var idTextView: TextView? = null
        var titleTextView: TextView? = null
        var bodyTextView: TextView? = null

        init {
            idTextView = view.findViewById(R.id.txtVw_Id_componentGet_id)
            titleTextView = view.findViewById(R.id.txtVw_title_componentGet_id)
           bodyTextView = view.findViewById(R.id.txtVw_body_componentGet_id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderGeneralData {
        val layoutInflater =
            LayoutInflater.from(context).inflate(R.layout.component_api, parent, false)

        val holder = ViewHolderGeneralData(layoutInflater)
        LayoutInflater.from(parent.context)
            .inflate(R.layout.component_api, parent, false)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolderGeneralData, position: Int) {
        val generalData = listGeneralData[position]

        if (generalData != null) {
            holder.idTextView?.text = buildString {
                append("▬▬▬▬▬ CAPÍTULO ")
                append(generalData.id.toString().padStart(3, '0'))
                append(" ▬▬▬▬▬")
            }

            holder.titleTextView?.text = buildString {
                append("▬▬▬ TÍTULO: \n")
                append(generalData.title.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() })
            }

            holder.bodyTextView?.text = buildString {
                append("▬▬▬ PARÁGRAFO: \n")
                append(capitalizeString.capitalize(generalData.body))
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