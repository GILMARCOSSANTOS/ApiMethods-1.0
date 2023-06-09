package com.example.apiregisteruser_10.activity_see.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apimethods10.R
import com.example.apiregisteruser_10.activity_see.model.ModelGet
import com.example.apiregisteruser_10.utils.Capitalize
import java.util.*

class AdapterGet(
    private val context: Context,
    private val listUsersObjectJsonItem: List<ModelGet>
) : RecyclerView.Adapter<AdapterGet.UsersViewHolder>() {

    private var capitalizeString = Capitalize()

    class UsersViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var id: TextView? = null
        var title: TextView? = null
        var body: TextView? = null

        init {
            id = view.findViewById(R.id.txtVw_Id_componentGet_id)
            title = view.findViewById(R.id.txtVw_title_componentGet_id)
            body = view.findViewById(R.id.txtVw_body_componentGet_id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val layoutInflater = LayoutInflater.from(context).inflate(
            R.layout.component_api,
            parent, false
        )
        val holder = UsersViewHolder(layoutInflater)
        LayoutInflater.from(parent.context).inflate(R.layout.component_api, parent, false)
        return holder
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val dataUser = listUsersObjectJsonItem[position]

        if (dataUser != null) {

            holder.id?.text = buildString {
                append("▬▬▬▬▬ CAPÍTULO ")
                append(dataUser.id.toString().padStart(3, '0'))
                append(" ▬▬▬▬▬")
            }

            holder.title?.text = buildString {
                append("▬▬▬ TÍTULO: \n")
                append(dataUser.title.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() })
            }

            holder.body?.text = buildString {
                append("▬▬▬ PARÁGRAFO: \n")
                append(capitalizeString.capitalize(dataUser.body))
            }
        }
    }

    override fun getItemCount(): Int {
        return listUsersObjectJsonItem.size
    }
}


