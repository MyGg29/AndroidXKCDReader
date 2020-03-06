package com.isen.xkcdreader
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class XKCDFragmentStatePagerAdapter(val fragmentManager: FragmentManager, val xkcds : MutableList<XKCDItem>)
    : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return XKCDFragment.newInstance(xkcds[position])
    }

    override fun getCount(): Int {
        return xkcds.size
    }


}