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

## 📁 Estructura del Proyecto

📦 proyecto-correo
┣ 📄 correo.java // Lógica de envío del correo
┣ 📄 pruebaCorreoVisual.java // Interfaz gráfica para llenar los campos
┣ 📄pruebaCorreo.java // con método main para ejecutar una prueba sin manera visual
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

