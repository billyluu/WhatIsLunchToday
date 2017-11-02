package com.billy.whatislunchtoday.bean

/**
 * Created by billylu on 2017/11/3.
 */
class Lunch {

    private lateinit var nickName : String
    private lateinit var food : String
    private lateinit var drink : String

    fun setNickName(nickName : String){
        this.nickName = nickName
    }

    fun getNickName() : String{
        return this.nickName
    }

    fun setFood(food: String) {
        this.food = food
    }

    fun getFood() : String {
        return this.food
    }

    fun setDrink(drink: String) {
        this.drink = drink
    }

    fun getDrink() : String {
        return this.drink
    }
}