package com.tahakocer.first_app

import Calculator
import android.annotation.SuppressLint
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tahakocer.first_app.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        binding.textView.setMovementMethod(ScrollingMovementMethod())
        binding.button0.setOnClickListener() {
            binding.textView.text = binding.textView.text.toString() + "0"
        }
        binding.button1.setOnClickListener() {
            binding.textView.text = binding.textView.text.toString() + "1"
        }
        binding.button2.setOnClickListener() {
            binding.textView.text = binding.textView.text.toString() + "2"
        }
        binding.button3.setOnClickListener() {
            binding.textView.text = binding.textView.text.toString() + "3"
        }
        binding.button4.setOnClickListener() {
            binding.textView.text = binding.textView.text.toString() + "4"
        }
        binding.button5.setOnClickListener() {
            binding.textView.text = binding.textView.text.toString() + "5"
        }
        binding.button6.setOnClickListener() {
            binding.textView.text = binding.textView.text.toString() + "6"
        }
        binding.button7.setOnClickListener() {
            binding.textView.text = binding.textView.text.toString() + "7"
        }
        binding.button8.setOnClickListener() {
            binding.textView.text = binding.textView.text.toString() + "8"
        }
        binding.button9.setOnClickListener() {
            binding.textView.text = binding.textView.text.toString() + "9"
        }
        binding.buttonTopla.setOnClickListener() {
            binding.textView.text = binding.textView.text.toString() + "+"
        }
        binding.buttonCarp.setOnClickListener() {
            binding.textView.text = binding.textView.text.toString() + "*"
        }
        binding.buttonBol.setOnClickListener() {
            binding.textView.text = binding.textView.text.toString() + "/"
        }
        binding.buttonCikar.setOnClickListener() {
            binding.textView.text = binding.textView.text.toString() + "-"
        }
        binding.buttonAcParantez.setOnClickListener() {
            binding.textView.text = binding.textView.text.toString() + "("
        }
        binding.buttonKapaParantez.setOnClickListener() {
            binding.textView.text = binding.textView.text.toString() + ")"
        }

        binding.buttonSil.setOnClickListener()
        {
            var currentText = binding.textView.text
            if (currentText.isNotEmpty()) {
                binding.textView.text = currentText.substring(0, currentText.length - 1)

            }
        }
        binding.buttonClear.setOnClickListener()
        {
            var currentText = binding.textView.text
            if (currentText.isNotEmpty()) {
                binding.textView.text = null

            }
        }
    }

    fun hesapla (view : View) {
        val calculator  = Calculator()
        val text = binding.textView.text.toString()
        val res = calculator.evaluate(text)
        println(res)
        binding.textViewSonuc.text = "$res = "
    }

}