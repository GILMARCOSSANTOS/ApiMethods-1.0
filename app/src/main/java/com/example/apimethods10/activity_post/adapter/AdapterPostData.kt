package com.example.apimethods10.activity_post.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apimethods10.R
import com.example.apimethods10.activity_post.model.ModelPostData
import com.example.apimethods10.activity_post.model.ModelPostPersonalData
import com.example.apiregisteruser_10.utils.Capitalize
import java.util.*

class AdapterPostData(){

}

//class AdapterPostData(
//    private val listPersonalData: List<ModelPostData>
//) :
//    RecyclerView.Adapter<AdapterPostData.ViewHolderPersonalData>() {
//
//    val capitalizeString = Capitalize()
//
//    class ViewHolderPersonalData(view: View) : RecyclerView.ViewHolder(view) {
//        var numberChapter: TextView? = null
//        var subTitle: TextView? = null
//        var textContext: TextView? = null
//
//        init {
//            numberChapter = view.findViewById(R.id.txtVw_chapter_componentPost_id)
//            subTitle = view.findViewById(R.id.txtVw_subTitle_componentPost_id)
//            textContext = view.findViewById(R.id.txtVw_text_componentPost_id)
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPersonalData {
//        val itemView =
//            LayoutInflater.from(parent.context).inflate(R.layout.activity_post, parent, false)
//        return ViewHolderPersonalData(itemView)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolderPersonalData, position: Int) {
//        val personalData = listPersonalData[position]
//
//        holder.numberChapter?.text = buildString {
//            append("▬▬▬ CAPÍTULO ")
//            append(personalData.id.toString().padStart(3, '0'))
//            append(" ▬▬▬")
//        }
//
//        holder.subTitle?.text = buildString {
//            append("• SUB - TÍTULO: ")
//            append(personalData.tile.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() })
//        }
//
//        holder.subTitle?.text = buildString {
//            append(capitalizeString.capitalize(personalData.body))
//        }
//
//        holder.textContext?.text = buildString {
//            append("• TEXTO: ")
//            append(capitalizeString.capitalize(personalData.body))
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return listPersonalData.size
//    }
//}
