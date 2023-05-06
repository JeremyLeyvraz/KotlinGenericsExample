package com.lj.coroutines

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lj.coroutines.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val manager = CoroutineManager()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //region Fire & Forget buttons

        binding.buttonFireForget.setOnClickListener {
            manager.runFireAndForget{
                manager.simulateAction()
            }
        }
        binding.buttonFireForgetTimeout.setOnClickListener {
            manager.runFireAndForgetWithTimeout{
                manager.simulateAction()
            }
        }
        binding.buttonFireForgetTimeoutCallback.setOnClickListener {
            manager.runFireAndForgetWithTimeoutWithCallbackWhenError(
                {manager.simulateAction()},
                {manager.simulateAction()}
            )
        }

        //endregion Fire & Forget buttons

        //region Fire and Wait buttons

        binding.buttonWait.setOnClickListener {
            manager.runFireAndWait {
                manager.simulateAction()
            }
        }
        binding.buttonWaitResult.setOnClickListener {
            val result = manager.runFireAndWaitResult {
                val name = "jerem"
                val number = "24"
                "$name $number"
            }
            manager.log("Result: ${result}")
        }
        binding.buttonWaitResultWithFireAndForgetCallback.setOnClickListener {
            var name = "jerem"
            var age = 24
            manager.runFireAndWaitActionWithFireAndForgetCallback(
                {
                    name = "lola"
                    age = 29
                },
                {
                    manager.log("callback: ${name} ${age}")
                }
            )
        }

        //endregion Fire & Wait buttons
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}