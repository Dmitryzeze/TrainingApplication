package com.dmitryzeze.trainingapplication

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.dmitryzeze.trainingapplication.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.button.setOnClickListener {
            val newFragment = TimePickerFragment()
            newFragment.show(supportFragmentManager, "Time Picker")
        }

    }


    class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {

        private lateinit var calendar: Calendar

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            calendar = Calendar.getInstance()

            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            return TimePickerDialog(
                activity, // Установка контекста диалога
                android.R.style.Theme_Material_Dialog_Presentation, // Тема оформления
                this, // TimePickerDialog.OnTimeSetListener
                hour, // Установка значения часов
                minute, // Установка значения минут
                false // Использование 12/24 формата (false - AM/PM)
            )
        }

        override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
            val tv: TextView = activity?.findViewById(R.id.textView) as TextView
            tv.text = "Hour : Minute\n${getHourAMPM(hourOfDay)}:$minute ${getAMPM(hourOfDay)}"
        }

        override fun onCancel(dialog: DialogInterface) {
            Toast.makeText(activity, "Picker Canceled.", Toast.LENGTH_SHORT).show()
            super.onCancel(dialog)
        }

        private fun getAMPM(hour: Int): String {
            return if (hour > 11) "PM" else "AM"
        }

        private fun getHourAMPM(hour: Int): Int {
            var modifiedHour = if (hour > 11) hour - 12 else hour
            if (modifiedHour == 0) {
                modifiedHour = 12
            }
            return modifiedHour
        }
    }
}