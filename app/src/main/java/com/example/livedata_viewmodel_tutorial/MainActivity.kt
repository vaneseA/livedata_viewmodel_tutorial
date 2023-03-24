package com.example.livedata_viewmodel_tutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.livedata_viewmodel_tutorial.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG: String = "로그"
    }

    private lateinit var  binding: ActivityMainBinding

    lateinit var mynumberViewModel: MynumberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        mynumberViewModel = ViewModelProvider(this).get(MynumberViewModel::class.java)
        mynumberViewModel.currentValue.observe(this, Observer {
            Log.d(TAG, "MainActivity - mynumberViewModel - currentValue 라이브 데이터 값 변경 : $it")
            binding.numberTextView.text = it.toString()
        })
        binding.plusBtn.setOnClickListener(this)
        binding.minusBtn.setOnClickListener(this)
        // 자동 생성된 뷰바인딩 클래스에서의 inflate 메서드 활용
        // -> 액티비티에서 사용할 바인딩 클래스의 인스턴스 생성

        // -> 생성된 뷰를 액티비티에 표시

    }

    override fun onClick(view: View?) {

        val userInput : Int = binding.userInputEditText.text.toString().toInt()

        //뷰모델에 라이브데이터 값을 변경하는 메소드 실ㅇ행
        when(view){
            binding.plusBtn -> mynumberViewModel.updateValue(actionType = ActionType.PLUS, userInput)
            binding.minusBtn -> mynumberViewModel.updateValue(actionType = ActionType.MINUS, userInput)
        }
    }
}