package com.ds.norris.ui

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ds.norris.R

class ViewDialog {
    fun showDialog(context: Context, title: String, imageUrl: String) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.random_joke)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val okButton = dialog.findViewById<Button>(R.id.okButton)
        val titleText = dialog.findViewById<TextView>(R.id.title)
        val image = dialog.findViewById<ImageView>(R.id.norrisImage)

        titleText.text = title
        Glide.with(context)
            .load(imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(image)

        okButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}