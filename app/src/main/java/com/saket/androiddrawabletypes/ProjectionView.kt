package com.saket.androiddrawabletypes

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatImageView

/**
 * A custom view that uses custom attributes to set its drawables. The
 * background resource used for this view in xml is stateListDrawable.
 * Ref url:
 * https://bterczynski.medium.com/android-drawable-with-custom-states-c6ebdcc58205
 */
class ProjectionView constructor(
    context: Context,
    attributeSet: AttributeSet
) : AppCompatImageView(context, attributeSet) {
    constructor(context: Context, attributeSet: AttributeSet, defStyle: Int = 0) : this(
        context,
        attributeSet
    )

    /*
    Enum is a class that can hold types which extend it.
    In kotlin, Sealed classes are an alternative to enums as they are less constrained.
     */
    enum class ProjectionMode {
        //These are classes of type ProjectionMode
        CARPLAY, ANDROIDAUTO, PHONE;

        private lateinit var currentMode: ProjectionMode

        fun setValue(value: Int) {
            if (value == 1)  {
                currentMode = CARPLAY
            }
            if (value == 2) {
                currentMode = ANDROIDAUTO
            }
            if (value == 3) {
                currentMode = PHONE
            }
        }
        fun getValue(): ProjectionMode = currentMode
    }

    var currentProjection: ProjectionMode = ProjectionMode.PHONE

    private val clickListener = OnClickListener { _ -> //Current projection mode
        //Update projection mode
        if (currentProjection == ProjectionMode.PHONE) {
            currentProjection = ProjectionMode.CARPLAY
            setImageState(intArrayOf(R.attr.projection_carplay), true)
            return@OnClickListener
        }
        if (currentProjection == ProjectionMode.CARPLAY) {
            currentProjection = ProjectionMode.ANDROIDAUTO
            setImageState(intArrayOf(R.attr.projection_aa), true)
            return@OnClickListener
        }
        if (currentProjection == ProjectionMode.ANDROIDAUTO) {
            currentProjection = ProjectionMode.PHONE
            setImageState(intArrayOf(R.attr.projection_phone), true)
        }
    }

    init {
        /*
        By convention custom view and declare-sytelable should have the same name.
        Various editor features rely on this convention
         */
        val typedArray =
            context.obtainStyledAttributes(attributeSet, R.styleable.ProjectionView, 0, 0)
        if (typedArray.indexCount > 0) {
            currentProjection.setValue(
                if (typedArray.getBoolean(R.styleable.ProjectionView_projection_aa, false)) {
                    1
                } else if (typedArray.getBoolean(
                        R.styleable.ProjectionView_projection_carplay,
                        false
                    )
                ) {
                    2
                } else {
                    3
                }
            )
        }
        typedArray.recycle()
        setOnClickListener(clickListener)
    }
}
