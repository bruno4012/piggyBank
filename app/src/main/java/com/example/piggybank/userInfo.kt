package com.example.piggybank

class userInfo {
    var id : Int = 0;
    var prodName : String = "";
    var price : Int = 0;
    var date :String = "";

    constructor(prodName:String, price:Int, date:String){
        this.prodName = prodName;
        this.price = price;
        this.date = date;
    }
}