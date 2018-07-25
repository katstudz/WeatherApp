package com.example.katarzyna.weatherapp.utils

class CloudType {
    companion object {
        private val CL = "CL"	//Clear	Cloud coverage is 0-7% of the sky.
        private val FW = "FW"	//Fair/Mostly sunny	Cloud coverage is 7-32% of the sky
        private val SC = "CL"	//	Partly cloudy	Cloud coverage is 32-70% of the sky.
        private val BK = "BK"	//Mostly Cloudy	Cloud coverage is 70-95% of the sky.
        private val OV = "OV"

        fun getCovarageInProcent(key: String):Double{
            when(key){
                CL -> return 0.07
                FW -> return 0.32
                SC -> return 0.70
                BK -> return 0.90
                OV -> return 1.0
            }
            return 0.0
        }
    }
}