package com.example.apimethods10.activity_post.controller

import com.example.apimethods10.activity_post.model.ModelPostData
import com.example.apimethods10.activity_post.model.ModelPostPersonalData
import com.example.apimethods10.activity_post.service.PostServiceData
import com.example.apimethods10.activity_post.service.PostServicePersonalData
import com.example.apimethods10.service.ApiConnection
import retrofit2.Callback

class ControllerPostPersonalData {

    suspend fun fetchPostPersonalData(id: Int, title: String, body: String, personalData: String) {

        val modelPost = ModelPostPersonalData(id, title, body, personalData)
        val postService = ApiConnection().createService(PostServicePersonalData::class.java)

//       postService.createPostPersonalData(modelPost).enqueue(object : Callback<Post> {
//            override fun onResponse(call: Call<Post>, response: Response<Post>) {
//                if (response.isSuccessful) {
//                    val createdPost = response.body()
//
//                    // Adiciona o post criado à lista de posts exibidos no RecyclerView
//                    postsWithExtraInfo.add(
//                        Post(
//                            id = createdPost.id,
//                            userId = createdPost.userId,
//                            title = createdPost.title,
//                            body = createdPost.body,
//                            extraInfo = text
//                        )
//                    )
//
//                    // Notifica o RecyclerView que houve uma mudança nos dados
//                    postAdapter.notifyDataSetChanged()
//                } else {
//                    // Trata erros de resposta da API
//                }
//            }
//
//            override fun onFailure(call: Call<Post>, t: Throwable) {
//                // Trata erros de comunicação com a API
//            }
//        })
    }
}