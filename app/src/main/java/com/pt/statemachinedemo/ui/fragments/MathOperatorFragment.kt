package com.pt.statemachinedemo.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pt.statemachinedemo.R
import common.*
import common.ui.BaseStateFragment
import kotlinx.android.synthetic.main.fragment_math_operator.*
import java.lang.Exception

class MathOperatorFragment : BaseStateFragment() {
    private var operator: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments()
        operator = arguments!!.getInt(OPERATOR)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_math_operator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        edNumber1.requestFocus()

        textOperator.text = "OperatorSideEffect ${operator!!.toOperatorText()}"

        btnCalculate.setOnClickListener {
            fun updateResult(result: Double) {
                textResult.text = "Result: $result"
            }

            val numbers = getNumbers()
            when (operator) {
                ADDITION -> updateResult(numbers.first + numbers.second)
                SUBTRACTION -> updateResult(numbers.first - numbers.second)
                MULTIPLICATION -> updateResult(numbers.first * numbers.second)
                DIVISION -> updateResult(numbers.first / numbers.second)
            }
        }

        btnAbout.setOnClickListener {
            changeStateWithEvent(State.AboutState, Event.EmptyEvent)
        }
    }

    private fun getNumbers(): Pair<Double, Double> {
        val number1 = try {
            edNumber1.text.toString().toDouble()
        } catch (e: Exception) {
            0.0
        }
        val number2 = try {
            edNumber2.text.toString().toDouble()
        } catch (e: Exception) {
            0.0
        }
        return Pair(number1, number2)
    }

    companion object {
        private const val OPERATOR = "OPERATOR"
        fun newInstance(operator: Int) =
            MathOperatorFragment().apply {
                arguments = Bundle().apply {
                    putInt(OPERATOR, operator)
                }
            }
    }
}
