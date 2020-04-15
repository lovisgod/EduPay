package com.edupay.edupay.model

data class FeeItem (
    var fee_class: String,
    var amount: String,
    var category: String
)

data class FeeItemHistory (
    var student_name: String,
    var amount: String,
    var category: String,
    var payment_date: String
)