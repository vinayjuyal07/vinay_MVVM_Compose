package com.example.jetpackcomposeui.utility

import com.example.jetpackcomposeui.model.MessageType

object CommonUtils {
    var ids = 1100
    fun getMessageDummyList(): List<MessageType> {
        val imageList = arrayListOf<String>()
        imageList.add("https://api.slingacademy.com/public/sample-photos/27.jpeg")
        imageList.add("https://api.slingacademy.com/public/sample-photos/28.jpeg")
        imageList.add("https://api.slingacademy.com/public/sample-photos/11.jpeg")
        imageList.add("https://api.slingacademy.com/public/sample-photos/6.jpeg")
        imageList.add("https://api.slingacademy.com/public/sample-photos/7.jpeg")
        imageList.add("https://api.slingacademy.com/public/sample-photos/8.jpeg")
        imageList.add("https://api.slingacademy.com/public/sample-photos/9.jpeg")
        imageList.add("https://api.slingacademy.com/public/sample-photos/10.jpeg")
        imageList.add("https://api.slingacademy.com/public/sample-photos/12.jpeg")
        imageList.add("https://api.slingacademy.com/public/sample-photos/13.jpeg")
        imageList.add("https://api.slingacademy.com/public/sample-photos/39.jpeg")
        imageList.add("https://api.slingacademy.com/public/sample-photos/36.jpeg")
        val list = arrayListOf<MessageType>()
        repeat(10) { index ->
            ids++
            list.add(
                MessageType(
                    id = ids,
                    title = "Dummy Title $ids",
                    body = "This is a dummy message body for item $ids.",
                    image = imageList[index]
                )
            )
        }
        return list
    }
}