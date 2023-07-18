package com.salman.roomtask.ui.util

import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.salman.roomtask.model.Category

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 7/18/2023.
 */
fun ChipGroup.submitList(
    list: List<Category>,
    onClick: (Category) -> Unit = {},
) {
    list.forEach { category ->
        val chip = Chip(context)
        chip.text = category.title
        chip.setOnClickListener {
            onClick(category)
        }
        addView(chip)
    }
}