package com.ej.keyboardtest

import android.graphics.Color
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ej.keyboardtest.databinding.FragmentBlankBinding


class BlankFragment : Fragment() {
    lateinit var binding : FragmentBlankBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate(inflater,R.layout.fragment_blank,container,false)
        binding.lifecycleOwner = this.viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkKeyboardActivation()
    }

    private fun checkKeyboardActivation(){
        val rootView = binding.layoutRoot
        rootView.viewTreeObserver.addOnGlobalLayoutListener {
            val r = Rect()
            rootView.getWindowVisibleDisplayFrame(r)
            val screenHeight = rootView.rootView.height
            val btnHeight = binding.nextBtn.height
            val keyboardHeight = screenHeight - r.bottom - btnHeight

            if (keyboardHeight > 0) {
                // 키보드가 열린 상태
                // 여기에 키보드 열림에 따른 동작을 추가합니다.
                binding.nextBtn.y = (screenHeight - r.bottom+btnHeight - getStatusBarHeight()).toFloat()
                binding.nextBtn.visibility =  View.VISIBLE

            } else {
                // 키보드가 닫힌 상태
                // 여기에 키보드 닫힘에 따른 동작을 추가합니다.
                binding.nextBtn.visibility =  View.GONE
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = BlankFragment()
    }



    // 상태 표시 줄의 높이를 가져오는 함수
    private fun getStatusBarHeight(): Int {
        val resources = resources
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        return if (resourceId > 0) {
            resources.getDimensionPixelSize(resourceId)
        } else {
            0
        }
    }
}