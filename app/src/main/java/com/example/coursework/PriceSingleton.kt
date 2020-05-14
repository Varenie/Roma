package com.example.coursework

class PriceSingleton {
    var id: Array<Int?> = arrayOfNulls(500)
    var name: Array<String?> = arrayOfNulls(500)
    var description: Array<String?> = arrayOfNulls(500)
    var price: Array<String?> = arrayOfNulls(500)

    companion object{
        private var instance = PriceSingleton()

        fun getInstance(): PriceSingleton{
            if(instance == null){
                instance = PriceSingleton()
                instance.id = arrayOfNulls(500)
                instance.name = arrayOfNulls(500)
                instance.description = arrayOfNulls(500)
                instance.price = arrayOfNulls(500)
            }
            return instance
        }
    }
}