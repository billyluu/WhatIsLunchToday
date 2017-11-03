package com.billy.whatislunchtoday.activity

import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.billy.whatislunchtoday.R
import com.billy.whatislunchtoday.bean.Lunch
import com.billy.whatislunchtoday.model.FireBaseModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private val TAG = javaClass.simpleName

    private lateinit var myAdapter : MyAdapter

    private lateinit var recycleView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycleView = findViewById(R.id.main_recyclerView)
        setUpToolbar()
        setUpFab()

        FireBaseModel().readLunchData("main", object : FireBaseModel.FireBaseCallBack2{
            override fun onGetData(list: List<Lunch>) {
                Log.i(TAG, "" + list.size)

                myAdapter = MyAdapter(this@MainActivity, list)
                recycleView.layoutManager = GridLayoutManager(this@MainActivity, 1)
                recycleView.adapter = myAdapter
            }

            override fun onNullData(msg: String) {
                Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun setUpFab() {
        main_fab.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                var view = LayoutInflater.from(this@MainActivity).inflate(R.layout.lunch_layout, null)
                showDialog(view)
            }
        })
    }

    private fun showDialog(view : View) {
        AlertDialog.Builder(this)
                .setView(view)
                .setPositiveButton("送出", object : DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        var nickname_edt = view.findViewById<EditText>(R.id.nickname_editText)
                        var food_edt = view.findViewById<EditText>(R.id.food_editText)
                        var drink_edt = view.findViewById<EditText>(R.id.drink_editText)

                        var nickname = nickname_edt.text.toString()
                        var food = food_edt.text.toString()
                        var drink = drink_edt.text.toString()

                        if (nickname.isEmpty()){
                            Toast.makeText(this@MainActivity, "不填名字怎麼知道誰吃什麼？", Toast.LENGTH_SHORT).show()
                            return
                        }

                        if (food.isEmpty()){
                            Toast.makeText(this@MainActivity, "不吃是要做神仙？", Toast.LENGTH_SHORT).show()
                            return
                        }

                        if (drink.isEmpty()){
                            drink = "不喝"
                        }


                        insertLunchInfo(nickname, food, drink)
                    }
                })
                .setNegativeButton("取消", null)
                .show()
    }

    private fun insertLunchInfo(nickname : String, food : String, drink : String) {
        var map = HashMap<String, String>();
        map.put("nickname", nickname)
        map.put("food", food)
        map.put("drink", drink)
        FireBaseModel().saveMapData("main", map)
    }

    class MyAdapter(context : Context, list: List<Lunch>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
        private var mContext : Context
        private var lunchList : List<Lunch>

        init {
            mContext = context
            lunchList = list
        }

        class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
            var main_nickname : TextView
            var main_food : TextView
            var main_drink : TextView

            init {
                main_nickname = itemView!!.findViewById(R.id.main_name)
                main_food = itemView!!.findViewById(R.id.main_food)
                main_drink = itemView!!.findViewById(R.id.main_drink)
            }

        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyAdapter.ViewHolder {
            var view = LayoutInflater.from(parent!!.context).inflate(R.layout.main_list_item, parent, false)
            var viewHolder = ViewHolder(view)

            return viewHolder
        }

        override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
            var lunch = lunchList.get(position)
            holder!!.main_nickname.setText(lunch.getNickName())
            holder!!.main_food.setText(lunch.getFood())
            holder!!.main_drink.setText(lunch.getDrink())


        }

        override fun getItemCount(): Int {

            return lunchList.size
        }
    }
}
