#SensorMov

[TOCM]

[TOC]

## Introducción
La biblioteca SensorMov se construyó para proporcionar un conjunto de herramientas de desarrollo para la realización de interfaces tangibles de usuario (TUI, por su nombre en inglés), que se conecten con dispositivos móviles Android. Consiste en un conjunto de clases, propiedades y funcionalidades que permiten extender el uso de sensores de los móviles, típicamente subutilizados. Por ejemplo, se proporciona una clase que permite calibrar y utilizar rápidamente el magnetómetro para la identificación de objetos, de acuerdo con su campo magnético estático; controvirtiendo así el típico uso de este sensor, como brújula, para conocer la rotación del celular respecto al campo magnético terrestre.  
Este desarrollo es un producto derivado del proyecto de investigación INV-ING-2981 “Diálogos de saberes en la creación de interfaces físicas artesanales para la interacción con dispositivos móviles”, financiado por la Vicerrectoría de Investigaciones de la Universidad Militar Nueva Granada, Vigencia 2019. En este sentido, los requisitos funcionales y, especialmente los no funcionales, surgieron del diálogo que se dio entre estudiantes de Ingeniería en Multimedia, diseñadores de interacciones, y artesanas textiles de la región Bogotá Sabana en Colombia. Uno de los más significativos es que la biblioteca sea en español, considerando que se desea propiciar, localmente, el desarrollo de interfaces tangibles que aprovechen el extendido uso de dispositivos móviles, y que, a la vez, reconozcan las capacidades locales, como en el caso de las artesanías textiles.  
La biblioteca SensorMov está desarrollada en lenguaje nativo, facilitando la comunicación con el sistema operativo. Esto permite que se pueda combinar con otras funcionalidades, como las de comunicación, manejo de bases de datos, gestión de archivos, entre otras. Así mismo, está exportada en un formato que permitiría incluirla, con algunos ajustes, en otras plataformas de desarrollo como Processing for Android.  
Este documento detalla, técnicamente, la biblioteca: 1. ¿Qué es SensorMov?, 2. ¿Para qué sirve SensorMov?, y 3. Sensores en los dispositivos móviles Android. Así mismo, presenta el procedimiento para instalarla, 4. ¿Cómo se instala? y usarla, 5. ¿Cómo se usa? -Funciones disponibles y Ejemplos-. Para cada funcionalidad se ha desarrollado un ejemplo sencillo que está acompañado por el código fuente y un video disponible en un enlace público. Si este documento se consulta digitalmente, se puede acceder a través del hipervínculo. Si la consulta es en físico, se puede acceder a través del código QR que acompaña cada enlace.



## 1.Qué es SensorMov?

Es una biblioteca de código abierto desarrollada en lenguaje nativo Android-Java, para dispositivos móviles. Esta biblioteca reúne un conjunto de funcionalidades orientado a extender el uso de varios de los sensores del móvil (de allí su nombre), a través de objetos físicos denominados “tangibles”.  
A continuación, se presenta un glosario de términos utilizados con frecuencia en esta biblioteca. 
• **Datos crudos**: Se refiere a datos provenientes de los sensores, sin ningún tipo de preprocesamiento. Por ejemplo, la posición x,y de un toque sobre la pantalla  táctil del móvil.  
• **MainActivity**: Es la actividad principal por defecto de Android. Todos los ejemplos presentados en esta biblioteca son realizados sobre el MainActivity, por lo que acá se refiere a una actividad ejemplo que hace uso de alguna funcionalidad de la biblioteca.  
•** SensorMov**: Es el nombre de la biblioteca. 
• **Tangibles**: Objeto con componentes tanto físicos como digitales. En este sentido, es un objeto físico que sirve como control para modificar el estado digital del sistema, o uno que representa físicamente la información digital.


## 2. ¿Para qué sirve SensorMov?

SensorMov busca facilitar, a los desarrolladores, la creación de aplicaciones que utilicen los sensores que se encuentran embebidos en el dispositivo móvil. En particular, para extender su funcionalidad más allá de los límites físicos del dispositivo, a través de tangibles. Con facilitar, se hace referencia a reducir el tiempo de desarrollo, 
proporcionando funcionalidades ya establecidas, pero facilitando su modificación y permitiendo la inclusión de otras funcionalidades del sistema operativo.  
Así mismo, con esta biblioteca se busca proporcionar un conjunto de funcionalidades que inspiren la diversificación de aplicaciones basadas en tecnologías móviles, en particular, de teléfonos inteligentes. Las funciones que se han desarrollado y se describen en el presente documento técnico, utilizan como tangible el mismo dispositivo móvil o tangibles que se denominan pasivos, por no requerir la alimentación a través de energía eléctrica. Un ejemplo de estos tangibles son imanes. 
Así, se amplían las posibilidades de que desarrolladores que no cuenten con recursos o conocimientos en la implementación de sistemas embebidos, puedan igualmente extender el uso de móviles, a través de objetos físicos.  


## 3. Sensores en los dispositivos móviles

La evolución de los dispositivos móviles permite crear diferentes interacciones, gracias a que la mayoría de estos dispositivos cuenta con sensores que miden el movimiento, la orientación o diversas condiciones ambientales [1]. Estos sensores proporcionan datos crudos con alta precisión, y son útiles para monitorear el dispositivo (su posición o rotación en los tres ejes, cambios ambientales alrededor de él) [1] e incluso proporcionan información para desarrollar interacciones más complejas como el reconocimiento de gestos, identificación o detección de la posición o rotación de objetos, entre otros. Sin embargo, a pesar de la versatilidad de los dispositivos móviles inteligentes, la mayoría de las aplicaciones actuales son limitadas a nivel de interactividad: utilizan máximo dos puntos táctiles de la pantalla, el magnetómetro se usa básicamente como brújula, el micrófono se utiliza solo para voz, etc.
La Tabla 1 presenta los sensores que pueden incluir los dispositivos Android. Al ser un sistema operativo abierto, utilizado por diversos fabricantes de hardware, no se puede generalizar que todos los móviles tengan todos los sensores; es necesario consultar las especificaciones de cada referencia, para saber si se cuenta con un sensor o no. En caso de no tener la referencia exacta, o no contar con la hoja de datos, existen aplicaciones descargables en la tienda *PlayStore*, como *Sensors*, que permiten verificar con cuáles sensores cuenta el dispositivo y cuáles son sus rangos de operación. La Tabla 1, detalla por cada sensor, de los soportados por Android, su nombre, su tipo, una breve descripción y sus usos comunes.

***Tabla 1 Sensores de los dispositivos Android. La tipología puede ser i) Hardware, que refiere a aquellos que son componentes físicos integrados en el móvil y que obtienen los datos directamente de la captura, o ii) Software, también llamados sintéticos o virtuales, son datos que se derivan de la combinación de sensores de hardware, y no son componentes físicos.***

| sensor  | tipo  | descripción  | usos comunes  |
| ------------ | ------------ | ------------ | ------------ |
| TYPE_ACCELEROMETER   | Hardware  | Mide la fuerza de aceleración en m/s2 que se aplica a un dispositivo en los tres ejes físicos (x, y, y z), incluida la fuerza de la gravedad.|Detección de movimiento (sacudir, inclinación, etc.). |
| TYPE_AMBIENT_TEMPERA TURE  | Hardware  | Mide la temperatura ambiente de la habitación en grados Celsius (°C).   |Monitorear la temperatura del aire.   |
| TYPE_GRAVITY   | Software o Hardware  | Mide la fuerza de gravedad en m/s2 que se aplica a un dispositivo en los tres ejes físicos (x, y, y z).  | Detección de movimiento (sacudir, inclinación, etc.).  |
| TYPE_GYROSCOPE   | Hardware  | Mide la velocidad de rotación de un dispositivo en rad/s alrededor de cada uno de los tres ejes físicos (x, y, y z).  |Detección de rotación.  |
| TYPE_LIGHT   | Hardware  | Mide el nivel de luz ambiente (iluminación) en lx.  | Controlar el brillo de la pantalla.  |
| TYPE_LINEAR_ACCELERAT ION  |  Software o Hardware  | Mide la fuerza de aceleración en m/s2 que se aplica a un dispositivo en los tres ejes físicos (x, y, y z), excluyendo la fuerza de la gravedad.  | Monitoreo de la aceleración a lo largo de un solo eje |
| TYPE_MAGNETIC_FIELD   | Hardware  | Mide el campo geomagnético ambiental para los tres ejes físicos (x, y, y z) en μT.  | Crear una brújula.  |
| TYPE_ORIENTATION   | Software  | Mide los grados de rotación que realiza un dispositivo alrededor de los tres ejes físicos (x, y, y z). Funciona a partir del nivel API-3, puede obtener la matriz de inclinación y la matriz de rotación para un dispositivo utilizando el sensor de gravedad y el sensor de campo geomagnético junto con el método getRotationMatrix().  | Determinación de la orientación del dispositivo.  |
| TYPE_PRESSURE   | Hardware  | Mide la presión del aire ambiente en ℎPa o mBar.  | Monitoreo de cambios de presión de aire.  |
| TYPE_PROXIMITY   | Hardware  | Mide la proximidad de un objeto en cm con respecto a la pantalla de visualización de un dispositivo. Este sensor se usa generalmente para determinar sí un auricular se está sosteniendo cerca del oído de una persona.  | Posición del teléfono durante una llamada.  |
| TYPE_RELATIVE_HUMIDITY   | Hardware  | Mide la humedad ambiental relativa en porcentaje (%).  | Monitoreo del punto de rocío, humedad absoluta y relativa.  |
| TYPE_ROTATION_VECTOR   |  Software o Hardware  | Mide la orientación de un dispositivo proporcionando los tres elementos del vector de rotación del dispositivo.  | Detección de movimiento y detección de rotación. |
| TYPE_TEMPERATURE   | Hardware  | Mide la temperatura del dispositivo en grados Celsius (°C). La implementación de este sensor varía según el dispositivo. Este sensor se reemplazó con el sensor TYPE_AMBIENT_TEMPERAT URE en el nivel 14 de API.  | Monitoreo de temperaturas. |



### 3.1 Sistema de coordenadas

El sistema de coordenadas que utilizan los sensores del dispositivo móvil no cambia al mover el dispositivo, es decir, es independiente de su orientación, al estar basado en la posición de la pantalla [1]. Este sistema, presentado en la Figura 1, es de tres ejes (x, y, y z). 

Los sensores que utilizan este sistema de coordenadas son los de aceleración y gravedad, el giroscopio, y el de campo magnético.  

### 3.2 Sensores que usa Sensormov

SensorMov, en su primera versión, permite el uso de los siguientes sensores: 
**-  Acelerómetro **
**-  Giroscopio **
**-  Magnetómetro** 
**-  Vector de rotación **
Adicionalmente, se utiliza la pantalla táctil como medio de captura de información. Es importante señalar que la pantalla no es considerada como un sensor del móvil, pero sí permite el ingreso de datos asociados a puntos táctiles, incluso concurrentes:  posición x,y, id, y según el dispositivo, área y presión.  
A futuro se contempla el uso del sensor de iluminación, de la pantalla táctil en versión extendida (para la identificación y detección de posición y rotación de objetos sobre ella), y el micrófono o los micrófonos en el caso de algunos dispositivos que graban en estéreo.  


## 4. ¿Cómo se instala?

Para instalar la biblioteca SensorMov, se deben seguir los pasos indicados a continuación: 

1. Descargar el archivo sensormov-release de la dirección [http://https://drive.google.com/file/d/1PZAZRbi3HIECWTlmwgaefcNieD94jaov/view?usp= sharing ](http://https://drive.google.com/file/d/1PZAZRbi3HIECWTlmwgaefcNieD94jaov/view?usp= sharing )

2. En file, seleccionar la opción proyect structure…

3. Posteriormente, se desplegará una nueva ventana cuyo menú a la izquierda contará con una opción llamada dependencias, la cual debe ser seleccionada.

4. En la barra superior aparecerá un símbolo más (+), el cual desplegará dos opciones, de las cuales la segunda debe ser elegida.

5. A continuación, hará acto de presencia una nueva ventana que pide la ubicación donde se encuentra alojada la biblioteca, para dar dicha información solo basta con copiar y pegar el lugar donde se encuentra y seleccionar la opción ok.

6. Una vez aparece la librería, se selecciona, se da click en la opción applay y luego en ok.

7. Listo, lo siguiente es escribir la siguiente línea de código: 
*import com.umng.sensormov. *
Después del punto se puede escoger la clase que se desea usar.


## 5. ¿Cómo se usa?
SensorMov es integra un conjunto de herramientas de fácil uso, que requieren extender la Actividad en la se use. En esta sección se presentan las funciones disponibles y se detallan ejemplos de cada función.  

### 5.1 Clases disponibles

La clase principal siempre será *Datos Crudos Extiende Activity*


### 5.2 Ejemplos

Esta subsección presenta algunos ejemplos sencillos de cómo utilizar las diferentes funcionalidades para cada una de las clases.

Todos los ejemplos requieren una modificación mínima en el apartado *acrtivity_main* para funcionar de manera correcta.

El id del texto presente en la pantalla blanca tiene que llamarse testTouch, testLatitud, testMagnetismo, etc. Dependiendo del tipo de ejercicio. Para esto es necesario seleccionarlo mediante un click y escribirlo en el cuadro de la esquina superior derecha al lado del apartado id.


#### 5.2.1 Datos crudos

Este ejemplo despliega en un campo de texto el valor de latitud brindada por el GPS. Nota: *Recuerde revisar si la aplicación creada si tiene los permisos necesarios para acceder a la ubicación del dispositivo.*

```java
package com.example.datos_crudos1;

import android.content.Context;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.umng.sensormov.DatosCrudos;
import androidx.annotation.RequiresApi;
public class MainActivity extends DatosCrudos {
    TextView Ubicacion;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocationManager lm=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        setContentView(R.layout.activity_main);
        Ubicacion = findViewById(R.id.testLatitud);
        String mes = "";
        mes = "Latitud: " + gps.dgps.latitud;
        Ubicacion.setText(mes);

    }
}

```

#### 5.2.2 Datos Crudos Acelerómetro

Este ejemplo despliega en un campo de texto el valor del campo magnético en el eje x.

```java
package com.example.datos_crudos2acelerometro;

import android.hardware.SensorEventListener;
import android.os.Build;
import android.os.Bundle;
import android.hardware.SensorEvent;
import android.widget.TextView;

import com.umng.sensormov.SensorDatos;
import androidx.annotation.RequiresApi;
public class MainActivity extends SensorDatos implements SensorEventListener {
    TextView Acelerometro;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sensor=0; //magnetometro o brujula
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Acelerometro = findViewById(R.id.testAcelerometro);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        super.onSensorChanged(event);
        String mes = "";
        mes = "X: " + datosSensor.x;
        Acelerometro.setText(mes);
    }
}

```

#### 5.2.3 Datos Pantalla

Este ejemplo despliega en un campo de texto los valores de la posición x,y al momento de  realizar un touch en la pantalla.

```java
package com.example.datos_pantalla1;

import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

import com.umng.sensormov.DatosPantalla;
import androidx.annotation.RequiresApi;
public class MainActivity extends DatosPantalla {
    TextView toques;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toques = findViewById(R.id.testTouch);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        Log.i("Main", "Action "+ event.getActionMasked()+": "+event.getActionIndex());
        String mes="";
        for(int t=0;t<TOTAL_PUNTEROS;t++)
            if(puntosTactiles[t].id!=-1)
                mes=mes+" "+puntosTactiles[t].id+ ": x="+puntosTactiles[t].x+",y="+puntosTactiles[t].y+", tam: "+puntosTactiles[t].tam+"\n";
        toques.setText(mes);
        return true;
    }
}

```

#### 5.2.4 Sensor Datos

Este ejemplo despliega en un campo de texto el valor del campo magnético en el eje x.

```java
package com.example.datos_pantalla_magnetico;
import android.hardware.SensorEventListener;
import android.os.Build;
import android.os.Bundle;
import android.hardware.SensorEvent;
import android.widget.TextView;

import com.umng.sensormov.SensorDatos;
import androidx.annotation.RequiresApi;
public class MainActivity extends SensorDatos implements SensorEventListener {
    TextView Magnetismo;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sensor=2; //magnetometro o brujula
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Magnetismo = findViewById(R.id.testMagnetismo);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        super.onSensorChanged(event);
        String mes = "";
        mes = "X: " + datosSensor.x;
        Magnetismo.setText(mes);
    }
}

```

#### 5.2.5 GPS

Este ejemplo despliega en un campo de texto el valor de latitud brindada por el GPS. Nota: *Recuerde revisar si la aplicación creada si tiene los permisos necesarios para acceder a la ubicación del dispositivo.*

```java
package com.example.datos_pantallagps;

import android.content.Context;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.umng.sensormov.GPS;
import androidx.annotation.RequiresApi;
public class MainActivity extends GPS {
    TextView gps;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocationManager lm=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        setContentView(R.layout.activity_main);
        gps = findViewById(R.id.testGPS);
        String mes = "";
        mes = "Latitud: " + dgps.latitud;
        gps.setText(mes);
    }
}

```
#### 5.2.6 Gestos

A continuación, se muestra una aplicación que permite obtener el valor del ángulo en grados en el eje z del celular.

```java
package com.example.datos_pantallagestos;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.umng.sensormov.Gestos;
import androidx.annotation.RequiresApi;
public class MainActivity extends Gestos implements SensorEventListener {
    TextView anguloz;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anguloz = findViewById(R.id.testGestos);
        calibrar();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        super.onSensorChanged(event);
        String mes = "";
        mes = "yawZ: " + yawZ;
        anguloz.setText(mes);
    }
}

```

#### 5.2.7 Objetos Magnéticos

A continuación, se muestra una aplicación que permite obtener enseñarle a reconocer imanes y mostrar una imagen de acuerdo con cada uno de ellos.

```java
package com.umng.tangibles; 
import android.hardware.SensorEvent; 
import android.hardware.SensorEventListener; 
import android.os.Bundle; 
import android.util.Log; 
import android.view.View; 
import android.widget.Button; 
import android.widget.ImageView; 
import com.umng.sensormov.ObjetosMagneticos; 
import static android.view.View.*; 
public class MainActivity extends ObjetosMagneticos implements SensorEventListener {  ImageView jim; 
 ImageView tom; 
 ImageView hercules; 
 Button enseñarBoton; 
 Button calibrarBoton; 
 Button identificarBoton; 
 Button elimnarBoton; 
 @Override 
 protected void onCreate(Bundle savedInstanceState) { 
 super.onCreate(savedInstanceState); 
 setContentView(R.layout.activity_main); 
 jim = findViewById(R.id.jimid); 
 tom = findViewById(R.id.tomid); 
 hercules = findViewById(R.id.herculesid); 
 enseñarBoton= (Button)findViewById(R.id.enseñarid); 
 calibrarBoton= (Button) findViewById(R.id.calibrarid); 
 identificarBoton=(Button)findViewById(R.id.indentificarid); 
 elimnarBoton=(Button)findViewById(R.id.eliminarid); 
 enseñarBoton.setOnClickListener(new OnClickListener() { 
 @Override 
 public void onClick(View v) { 
 enseñar(); 
 } 
 }); 
 calibrarBoton.setOnClickListener(new OnClickListener() { 
 @Override 
 public void onClick(View v) { 
 calibrar(); 
 }


 }); 
 identificarBoton.setOnClickListener(new OnClickListener() { 
 @Override 
 public void onClick(View v) { 
 identificar(); 
 } 
 }); 
 elimnarBoton.setOnClickListener(new OnClickListener() { 
 @Override 
 public void onClick(View v) { 
 eliminar(); 
 } 
 }); 
 } 
 @Override 
 public void onSensorChanged(SensorEvent event) { 
 super.onSensorChanged(event); 
 Log.i("MAIN: ","Ultimo Objeto"+ultimoObjeto); 
 if (ultimoObjeto==0){ 
 jim.setVisibility(VISIBLE); 
 tom.setVisibility(INVISIBLE); 
 hercules.setVisibility(INVISIBLE); 
 } else { 
 if (ultimoObjeto==1){ 
 jim.setVisibility(INVISIBLE); 
 tom.setVisibility(VISIBLE); 
 hercules.setVisibility(INVISIBLE); 
 } else { 
 if (ultimoObjeto==2) { 
 jim.setVisibility(INVISIBLE); 
 tom.setVisibility(INVISIBLE); 
 hercules.setVisibility(VISIBLE); 
 } 
 } 
 } 
 } 
}

```

#### 5.2.8 Posicionador Magnético

A continuación, se muestra una aplicación que permite obtener enseñarle a reconocer la posición de unos imanes y mostrarla en un cuadro de texto.

```java
package com.umng.tangibles; 
import android.hardware.SensorEvent; 
import android.hardware.SensorEventListener; 
import android.os.Bundle; 
import android.view.View; 
import android.widget.Button; 
import android.widget.TextView; 
import com.umng.sensormov.PosicionadorMagnetico; 
import static android.view.View.*; 
public class MainActivity extends PosicionadorMagnetico implements SensorEventListener { 
 Button enseñarBoton; 
 Button calibrarBoton; 
 Button identificarBoton; 
 TextView texto; 
 @Override 
 protected void onCreate(Bundle savedInstanceState) { 
 super.onCreate(savedInstanceState); 
 setContentView(R.layout.activity_main); 
 enseñarBoton= (Button)findViewById(R.id.enseñarid); 
 calibrarBoton= (Button) findViewById(R.id.calibrarid); 
 identificarBoton=(Button)findViewById(R.id.identificarid); 
 texto=(TextView)findViewById(R.id.textid); 
 enseñarBoton.setOnClickListener(new OnClickListener() { 
 @Override 
 public void onClick(View v) { 
 enseñar(); 
 } 
 }); 
 calibrarBoton.setOnClickListener(new OnClickListener() { 
 @Override 
 public void onClick(View v) { 
 calibrar(); 
 } 
 }); 
 identificarBoton.setOnClickListener(new OnClickListener() { 
 @Override 
 public void onClick(View v) { 
 identificar(); 
 } 
 }); 
 } 
 @Override 
 public void onSensorChanged(SensorEvent event) { 
 super.onSensorChanged(event); 
 // Log.i("MAIN: ","Ultimo Objeto"+ultimoObjeto); 
 if (ultimoObjeto==1){ 
 texto.setText("posicion 1"); 
 } else { 
 if (ultimoObjeto==2){ 
 texto.setText("posicion 2"); 
 } else { 
 if (ultimoObjeto==3) { 
 texto.setText("posicion 3"); 
 }else{ 
 texto.setText("no identificada"); 
 } 
 } 
 } 
 } 
}

```

# Referencias
