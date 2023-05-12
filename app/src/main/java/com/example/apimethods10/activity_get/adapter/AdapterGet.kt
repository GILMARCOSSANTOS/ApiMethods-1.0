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

        var header: TextView? = null
        var userId: TextView? = null
        var title: TextView? = null
        var body: TextView? = null

        init {
            userId = view.findViewById(R.id.txtVw_userId_listItem_id)
            title = view.findViewById(R.id.txtVw_title_listItem_id)
            body = view.findViewById(R.id.txtVw_body_listItem_id)
            header = view.findViewById(R.id.txtVw_header_actvtSee_id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val layoutInflater = LayoutInflater.from(context).inflate(
            R.layout.component_users,
            parent, false
        )
        val holder = UsersViewHolder(layoutInflater)
        var view: View =
            LayoutInflater.from(parent.getContext()).inflate(R.layout.component_users, parent, false)

        return holder
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val dataUser = listUsersObjectJsonItem[position]

        if (dataUser != null) {

            holder.userId?.text = buildString {
                append("▬▬▬ CAPÍTULO ")
                append(dataUser.id.toString().padStart(3,'0'))
                append(" ▬▬▬")
            }

            holder.title?.text = buildString {
                append("• SUB - TÍTULO: ")
                append(dataUser.title.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() })
            }

            holder.body?.text = buildString {
                append("• TEXTO: ")
                append(capitalizeString.capitalize(dataUser.body))
            }
        }
    }

    override fun getItemCount(): Int {
        return listUsersObjectJsonItem.size
    }
}


