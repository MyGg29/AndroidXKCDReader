package com.isen.xkcdreader

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class XKCDFragmentStatePagerAdapter(val fragmentManager: FragmentManager, val xkcds : ArrayList<XKCDItem>)
    : FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCount(): Int {
        return xkcds.size
    }

}