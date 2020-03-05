package com.isen.xkcdreader
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.ortiz.touchview.TouchImageView

class XKCDFragment: Fragment() {
    private lateinit var title : String
    private lateinit var altText : String
    private lateinit var img : Bitmap


    companion object {
        fun newInstance(item : XKCDItem): XKCDFragment {
            val newFragment = XKCDFragment()
            newFragment.title = item.title
            newFragment.altText = item.alttext
            newFragment.img = item.img
            return newFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.xkcd_fragment, container, false)
        val title : TextView = view.findViewById(R.id.xkcd_title)
        val altText : TextView = view.findViewById(R.id.xkcd_alt_text)
        val img : TouchImageView = view.findViewById(R.id.xkcd_img)
        title.text = this.title
        altText.text = this.altText
        img.setImageBitmap(this.img)


        return view
    }
}