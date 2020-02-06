package com.bantulabtech.active

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

data class ClassRoom(
  private var _updating: Boolean = false,
  private var _gpsLatitude: String,
  private var _gpsLongitude: String,
  private var _gpsAccuracy: String,
  private var _gpsSpeed: String,
  private var _gpsAltitude: String,
  private var _gpsProvider: String
 ): BaseObservable(){

 var updating: Boolean
  @Bindable get() = _updating
  set(value){
   _updating = value
   notifyPropertyChanged(BR.updating)
  }

 var gpsLatitude: String
  @Bindable get() = _gpsLatitude
  set(value){
   _gpsLatitude = value
   notifyPropertyChanged(BR.gpsLatitude)
  }

 var gpsLongitude: String
  @Bindable get() = _gpsLongitude
  set(value){
   _gpsLongitude = value
   notifyPropertyChanged(BR.gpsLongitude)
  }

 var gpsAccuracy: String
  @Bindable get() = _gpsAccuracy
  set(value){
   _gpsAccuracy = value
   notifyPropertyChanged(BR.gpsAccuracy)
  }

 var gpsSpeed: String
  @Bindable get() = _gpsSpeed
  set(value){
   _gpsSpeed = value
   notifyPropertyChanged(BR.gpsSpeed)
  }

 var gpsAltitude: String
  @Bindable get() = _gpsAltitude
  set(value){
   _gpsAltitude = value
   notifyPropertyChanged(BR.gpsAltitude)
  }

 var gpsProvider: String
  @Bindable get() = _gpsProvider
  set(value){
   _gpsProvider = value
   notifyPropertyChanged(BR.gpsProvider)
  }
}