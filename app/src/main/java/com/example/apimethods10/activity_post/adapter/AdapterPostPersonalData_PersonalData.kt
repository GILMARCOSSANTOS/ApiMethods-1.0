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

class AdapterPostPersonalData_PersonalData(
    private val context: Context,
    private var listPersonalData: MutableList<ModelPostApi>
) : RecyclerView.Adapter<AdapterPostPersonalData_PersonalData.ViewHolderPostPersonalData>() {

    private var capitalizeString = Capitalize()

    class ViewHolderPostPersonalData(view: View) : RecyclerView.ViewHolder(view) {
        var idTextView: TextView? = null
        var titleTextView: TextView? = null
        var bodyTextView: TextView? = null

        init {
            idTextView = view.findViewById(R.id.txtVw_Id_componentGet_id)
            titleTextView = view.findViewById(R.id.txtVw_title_componentGet_id)
            bodyTextView = view.findViewById(R.id.txtVw_body_componentGet_id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPostPersonalData {
        val layoutInflater =
            LayoutInflater.from(context).inflate(R.layout.component_api, parent, false)

        val holder = ViewHolderPostPersonalData(layoutInflater)
        LayoutInflater.from(parent.context)
            .inflate(R.layout.component_api, parent, false)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolderPostPersonalData, position: Int) {
        val dataPersonal = listPersonalData[position]

        if (dataPersonal != null) {
            holder.idTextView?.text = buildString {
                append("▬▬▬▬▬ CAPÍTULO ")
                append(dataPersonal.id.toString().padStart(3, '0'))
                append(" ▬▬▬▬▬")
            }

            holder.titleTextView?.text = buildString {
                append("▬▬▬ TÍTULO: \n")
                append(dataPersonal.title.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() })
            }

            holder.bodyTextView?.text = buildString {
                append("▬▬▬ PARÁGRAFO: \n")
                append(capitalizeString.capitalize(dataPersonal.body))
            }
        }
    }

    override fun getItemCount(): Int {
        return listPersonalData.size
    }
}