package developer.abdulahad.valyuta_kurs

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyLog
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import developer.abdulahad.valyuta_kurs.databinding.ActivityMain2Binding
import developer.abdulahad.valyuta_kurs.databinding.BottomItemBinding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    private lateinit var request: RequestQueue
    private lateinit var myAdapter: MyAdapter

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        request = Volley.newRequestQueue(this)
        VolleyLog.DEBUG = true

        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET,
            "https://cbu.uz/uzc/arkhiv-kursov-valyut/json/",
            null,
            {
                val type = object : TypeToken<List<User>>() {}.type
                val list = Gson().fromJson<List<User>>(it.toString(), type)
                myAdapter = MyAdapter(list, object : MyAdapter.MyClick {
                    override fun onClick(user: User, i: Int) {
                        val bottom = BottomSheetDialog(this@MainActivity2)
                        val item = BottomItemBinding.inflate(layoutInflater).apply {
                            id.text = "Id: ${list[i].id}"
                            code.text = "Code: ${list[i].Code}"
                            ccy.text = "Ccy: ${list[i].Ccy}"
                            ccyNmRU.text = "CcyNm_RU: ${list[i].CcyNm_RU}"
                            ccyNmUZ.text = "CcyNm_UZ: ${list[i].CcyNm_UZ}"
                            ccyNmUZC.text = "CcyNm_UZC: ${list[i].CcyNm_UZC}"
                            ccyNmEN.text = "CcyNm_EN: ${list[i].CcyNm_EN}"
                            nominal.text = "Nominal: ${list[i].Nominal}"
                            rate.text = "Rate: ${list[i].Rate}"
                            diff.text = "Diff: ${list[i].Diff}"
                            date.text = "Date: ${list[i].Date}"
                        }
                        bottom.setContentView(item.root)
                        bottom.show()
                    }
                })
                binding.rv.adapter = myAdapter
            }, {
                Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
            }
        )
        request.add(jsonArrayRequest)
    }
}