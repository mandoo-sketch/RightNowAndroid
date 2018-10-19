package com.sketch.mandoo.rightnow.main.listener

import com.sketch.mandoo.rightnow.main.MainViewModel

interface Listener {

    interface SelectBusListener {
        fun searchItemBusSelectEvent(item:MainViewModel)
    }
}