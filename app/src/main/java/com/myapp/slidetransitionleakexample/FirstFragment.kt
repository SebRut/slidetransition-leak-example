package com.myapp.slidetransitionleakexample

import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        val animatedViews = listOf<View>(
            view.findViewById(R.id.textview_first),
            view.findViewById(R.id.button_first)
        )

        val transitions = TransitionSet()
        var delay: Long = 0
        val delayOffset: Long = 500
        for (view1 in animatedViews) {
            val transition = Slide().addTarget(view).apply {
                duration = 300 + delay
            }

            delay += delayOffset
            transitions.addTransition(transition)
        }

        super.setExitTransition(transitions)
    }
}