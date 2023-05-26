package com.caruso.pcbuilderproject.user

import com.caruso.pcbuilderproject.componentsclasses.*
import java.time.LocalDate

data class Order(
    val orderId: Int,
    val date: LocalDate,
    val totalCost: Float,
    val cpu: Cpu?,
    val motherboard: Motherboard?,
    val ram: Ram?,
    val gpu: Gpu?,
    val storage: Storage?,
    val psu: Psu?
) {
    override fun toString(): String {
        return "Order #$orderId: {" +
                "\nDate: " + date.toString() +
                "\nTotal cost: " + totalCost.toString() +
                "\n" + cpu.toString() +
                "\n" + motherboard.toString() +
                "\n" + ram.toString() +
                "\n" + gpu.toString() +
                "\n" + storage.toString() +
                "\n" + psu.toString() +
                "\n}"
    }
}
