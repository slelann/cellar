package com.mycellar.conditions.measure

class OpenWeatherMapMeasure extends Measure {

     Integer pressure
     Float   temp_max
     Float   temp_min
     Integer cloudsCover
     String  weatherDescription
     String  weatherMain
     Float   windSpeed
     Integer windDegree
     String  weatherIconUrl    
    
    static constraints = {
          cloudsCover(range:0..100)
          windDegree(range:0..100)
    }
    
/*     { "clouds" : { "all" : 0 },
          "cod" : 200,
          "coord" : { "lat" : 48.180481,
               "lon" : -1.63829
            },
          "date" : "2013-02-21 08:30:00",
          "dt" : 1361435400,
          "id" : 3032965,
          "main" : { "pressure" : 1018,
               "temp" : 0,
               "temp_max" : 0,
               "temp_min" : -3.7599999999999998
            },
          "name" : "Betton",
          "sys" : { "country" : "FR",
               "id" : 5638
            },
          "url" : "http://openweathermap.org/city/3032965",
          "weather" : [ { "description" : "Sky is Clear",
                 "icon" : "01d",
                 "id" : 800,
                 "main" : "Clear"
               } ],
          "wind" : { "deg" : 80,
               "speed" : 5.7000000000000002
            }
       }
*/
     }