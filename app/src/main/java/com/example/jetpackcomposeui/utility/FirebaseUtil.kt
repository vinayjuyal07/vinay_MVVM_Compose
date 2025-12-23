package com.example.jetpackcomposeui.utility

import android.util.Log
import com.example.jetpackcomposeui.model.ChatMessage
import com.example.jetpackcomposeui.model.LoginResponse
import com.example.jetpackcomposeui.model.UserModel
import com.example.jetpackcomposeui.network.base.ErrorEntity
import com.example.jetpackcomposeui.network.base.ResultState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

object FirebaseUtil{
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db = FirebaseDatabase.getInstance()
    suspend fun loginFirebaseAuth(
        email: String,
        password: String
    ): ResultState<LoginResponse> {

        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            val firebaseUser = result.user
            if (firebaseUser != null) {
                saveUserProfile(firebaseUser)
                // Prepare your response data
                val successResponse = LoginResponse(
                    accessToken = firebaseUser.uid,
                    refreshToken = firebaseUser.displayName?:"NA",
                    expiresIn = System.currentTimeMillis()
                )
                ResultState.Success(successResponse)
            } else {
                ResultState.Error(
                    ErrorEntity.Error(
                        errorCode = 401,
                        errorMessage = "Login failed! User not found."
                    )
                )
            }
        } catch (e: Exception) {
            ResultState.Error(
                ErrorEntity.Error(
                    errorCode = 401,
                    errorMessage = e.message ?: "Firebase login failed."
                )
            )
        }
    }

    suspend fun saveUserProfile(firebaseUser: FirebaseUser) {
        val userModel = UserModel(
            uid = firebaseUser.uid,
            name = firebaseUser.email?.substringBefore("@") ?: "Anonymous ",
            email = firebaseUser.email ?: ""
        )
        Log.d("vinay", "saveUserProfile: "+userModel.name)
        Log.d("vinay", "saveUserProfile: "+userModel.uid)
        Log.d("vinay", "saveUserProfile: "+userModel.email)
        db.reference
            .child("users")
            .child(firebaseUser.uid)
            .setValue(userModel)
    }

    fun observeUsersList(): Flow<ResultState<List<UserModel>>> = callbackFlow {
        val currentUid = auth.currentUser?.uid.orEmpty()
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<UserModel>()
                for (child in snapshot.children) {
                    val user = child.getValue(UserModel::class.java)
                    if (user != null && user.uid != currentUid) {
                        list.add(user)
                    }
                }
                trySend(ResultState.Success(list))
            }
            override fun onCancelled(error: DatabaseError) {
                trySend(
                    ResultState.Error(
                        ErrorEntity.Error(
                            errorCode = 401,
                            errorMessage = error.message ?: "Firebase user list failed"
                        )
                    )
                )
            }
        }
        db.reference.child("users").addValueEventListener(listener)
        awaitClose {
            db.reference.child("users").removeEventListener(listener)
        }
    }


    fun getChatId(uid1: String, uid2: String): String {
        return listOf(uid1, uid2).sorted().joinToString("_")
    }
    fun observeMessages(
        receiverId: String,
        onResult: (List<ChatMessage>) -> Unit
    ) {
        val senderId = auth.currentUser!!.uid
        val chatId = getChatId(senderId, receiverId)
        db.reference
            .child("chats")
            .child(chatId)
            .child("messages")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val messages = snapshot.children
                        .mapNotNull { it.getValue(ChatMessage::class.java) }
                    onResult(messages)
                }

                override fun onCancelled(error: DatabaseError) {}
            })
    }
    fun sendMessage(receiverId: String, text: String) {
        val senderId = FirebaseAuth.getInstance().currentUser!!.uid
        val chatId = getChatId(senderId, receiverId)
        val message = ChatMessage(
            senderId = senderId,
            receiverId = receiverId,
            message = text
        )
        db.reference
            .child("chats")
            .child(chatId)
            .child("messages")
            .push()
            .setValue(message)
    }


}