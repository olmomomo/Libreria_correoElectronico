# Libreria_correoElectronico

# Envío de Correos con Archivos Adjuntos en Java (Swing + JavaMail)

Este proyecto permite enviar correos electrónicos desde una aplicación Java con una interfaz gráfica amigable creada con Swing. Puedes adjuntar **múltiples archivos** (PDFs, imágenes, etc.), y los archivos compatibles (como imágenes y PDFs) se mostrarán **dentro del cuerpo del mensaje en formato HTML**.

---

## Características

- Interfaz gráfica con Java Swing
- Envío de correos usando SMTP (Gmail)
- Soporte para múltiples archivos adjuntos
- Imágenes y PDFs incrustados en el cuerpo del mensaje
- Generación de archivo HTML con vista previa del mensaje
- Manejo de errores y confirmación visual

---

## Requisitos

- Java 8 o superior
- Librerías:
  - `javax.mail.jar`
  - `activation.jar`
- Conexión a internet

> ⚠️ Si usas Gmail, debes activar la **verificación en dos pasos** y generar una **contraseña de aplicación** desde:
> [https://myaccount.google.com/apppasswords](https://myaccount.google.com/apppasswords)

---
🔽 **Ejemplo de cómo se ve la configuración del proyecto con librerías:**

![Librerías añadidas al proyecto](https://i.ibb.co/XfLNjVwJ/librerias-necesarias.png)

---

## 📷 Capturas del uso del sistema

### 1. Interfaz principal

Al ejecutar la clase `PruebaCorreoVisual`, se muestra una ventana donde puedes llenar todos los campos:

- Correo del remitente
- Contraseña de aplicación (para Gmail)
- Correo del destinatario
- Asunto y mensaje
- Adjuntar uno o varios archivos

![Interfaz principal](https://i.ibb.co/qFCXJQjn/interfaz-principal.png)

---

### 2. Llenado de campos y selección de archivos

Puedes adjuntar imágenes, PDFs u otros archivos. Los archivos seleccionados se muestran en una lista:

![Llenado de campos y selección de archivos](https://i.ibb.co/wZ66hrqf/elegir-archivo.png)

---

### 3. Archivos adjuntos añadidos correctamente

Los archivos seleccionados se muestran en pantalla y están listos para enviarse:


---

### 4. Enviando el correo

Haz clic en el botón “Enviar Correo” para enviar el mensaje:


---

### 5. Mensaje de confirmación

Si todo es correcto, verás un mensaje de confirmación:

![Confirmación de envío](https://i.ibb.co/KzzP7gxr/notificacion-de-correo-enviado.png)

---

### 6. Vista del correo recibido (en Gmail)

Así se ve el correo en la bandeja de entrada, con imágenes incrustadas y archivos adjuntos:

![Correo recibido en Gmail](https://i.ibb.co/WppdHqqj/prueba-de-que-el-correo-se-envia.png)

---

### 7. Archivo HTML generado

El sistema genera automáticamente un archivo `correo_generado.html` con una vista previa del mensaje enviado.


---

## 📁 Estructura del Proyecto

📦 proyecto-correo

┣ 📄 Correo.java // Lógica de envío del correo

┣ 📄 PruebaCorreoVisual.java // Interfaz gráfica para llenar los campos

┣ 📄PruebaCorreo.java // con método main para ejecutar una prueba sin manera visual

┗ 📄 correo_generado.html // Se genera al enviar el correo


## 🚀 Instrucciones para ejecutar

1. Abre el proyecto en NetBeans, IntelliJ o tu IDE favorito.
2. Asegúrate de agregar las librerías `javax.mail.jar` y `activation.jar` al classpath del proyecto.
3. Ejecuta la clase `pruebaCorreoVisual`.
4. Llena los campos solicitados:
   - Correo remitente (Gmail)
   - Contraseña de aplicación
   - Correo destinatario
   - Asunto
   - Cuerpo del mensaje
5. Adjunta los archivos deseados (PDFs, imágenes).
6. Haz clic en **Enviar correo**.
7. El mensaje se enviará y se generará un archivo HTML de respaldo.

---

## 📌 Notas importantes

- ⚠️ **No funciona con Hotmail/Outlook**, ya que Microsoft bloquea la autenticación básica. Usa cuentas Gmail o Zoho con contraseña de aplicación.
- Asegúrate de que los archivos adjuntos existan y no estén en uso por otro programa.
- Puedes agregar mejoras visuales como barra de progreso o íconos fácilmente con Swing.

---

## Autor

**Ittay Ayelen Olmos Reyes**
**Flor Estephany Bustamante Rios**
Proyecto con fines académicos y educativos.

---

##  Licencia

Este proyecto puede ser modificado y reutilizado para fines personales, educativos o demostrativos.

