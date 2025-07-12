/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elementos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.mail.Authenticator;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author ayele
 */


public class Correo {

    private String remitente;
    private String contraseña;
    private String destinatario;
    private String asunto;
    private String cuerpo;
    private List<File> archivosAdjuntos = new ArrayList<>();

    /**
     *
     */
    public Correo() {}

    /**
 * Establece el correo y la contraseña del remitente.
 * Estos datos se usarán para autenticar al momento de enviar el correo.
 *
 * @param correo Dirección de correo electrónico del remitente.
 * @param contrasena Contraseña del correo (se refiere a una contraseña de aplicación).
 */
    public void setRemitente(String correo, String contrasena) {
        this.remitente = correo;
        this.contraseña = contrasena;
    }

    /**
 * Define la dirección de correo electrónico del destinatario.
 *
 * @param correo Correo electrónico del destinatario que recibirá el mensaje.
 */
    public void setDestinatario(String correo) {
        this.destinatario = correo;
    }

   /**
 * Define el asunto y el cuerpo principal del mensaje de correo.
 *
 * @param asunto Título del mensaje que aparecerá como asunto.
 * @param cuerpo Contenido principal del correo, puede ser texto plano o HTML.
 */
    public void setContenido(String asunto, String cuerpo) {
        this.asunto = asunto;
        this.cuerpo = cuerpo;
    }


   /**
 * Agrega un archivo al mensaje como adjunto.
 * Si el archivo no existe, se mostrará un mensaje de error en consola.
 *
 * @param rutaArchivo Ruta completa al archivo que se desea adjuntar.
 */
    public void agregarArchivo(String rutaArchivo) {
        File archivo = new File(rutaArchivo);
        if (archivo.exists()) {
            archivosAdjuntos.add(archivo);
        } else {
            System.err.println("❌ Archivo no encontrado: " + rutaArchivo);
        }
    }

   /**
 * Crea un archivo HTML simple con la información del mensaje enviado.
 * Este archivo puede usarse como registro o comprobante del envío.
 *
 * @param rutaArchivo Ruta donde se guardará el archivo HTML.
 * @throws IOException Si ocurre un error al escribir el archivo.
 */
    public void crearHTML(String rutaArchivo) throws IOException {
        FileWriter fw = new FileWriter(rutaArchivo);
        fw.write("<html><body>");
        fw.write("<h2>Correo enviado</h2>");
        fw.write("<p><strong>De:</strong> " + remitente + "</p>");
        fw.write("<p><strong>Para:</strong> " + destinatario + "</p>");
        fw.write("<p><strong>Asunto:</strong> " + asunto + "</p>");
        fw.write("<p><strong>Mensaje:</strong> " + cuerpo + "</p>");
        fw.write("</body></html>");
        fw.close();
        System.out.println("✅ Archivo HTML generado.");
    }

/**
 * Crea un cuerpo HTML para el correo incluyendo texto y vista previa de los archivos adjuntos.
 * Las imágenes se insertan embebidas y los PDFs se integran con etiquetas <embed>.
 *
 * @return Parte del cuerpo del mensaje con contenido HTML y recursos incrustados.
 * @throws MessagingException Si ocurre un error al construir la parte HTML.
 */
    private MimeBodyPart crearCuerpoConArchivos() throws MessagingException {
        MimeBodyPart cuerpoHtml = new MimeBodyPart();
        StringBuilder html = new StringBuilder();

        html.append("<html><body>");
        html.append("<h2>Correo enviado</h2>");
        html.append("<p><strong>De:</strong> ").append(remitente).append("</p>");
        html.append("<p><strong>Para:</strong> ").append(destinatario).append("</p>");
        html.append("<p><strong>Asunto:</strong> ").append(asunto).append("</p>");
        html.append("<p><strong>Mensaje:</strong><br>").append(cuerpo).append("</p>");

        int contador = 1;
        for (File archivo : archivosAdjuntos) {
            String nombre = archivo.getName().toLowerCase();
            String cid = "archivo" + contador++;

            if (nombre.matches(".*\\.(jpg|jpeg|png|gif|bmp)")) {
                html.append("<p><img src='cid:").append(cid).append("' width='400'/></p>");
            } else if (nombre.endsWith(".pdf")) {
                html.append("<p><embed src='cid:").append(cid).append("' width='600' height='400'></embed></p>");
            } else {
                html.append("<p>Archivo adjunto: ").append(archivo.getName()).append("</p>");
            }
        }

        html.append("</body></html>");
        cuerpoHtml.setContent(html.toString(), "text/html");
        return cuerpoHtml;
    }

  /**
 * Envía el correo electrónico configurado con todos sus archivos adjuntos.
 * Utiliza los parámetros previamente definidos de remitente, destinatario, asunto y cuerpo.
 * Acepta imágenes y PDFs que se incrustan directamente en el cuerpo HTML del mensaje.
 *
 * @throws MessagingException Si ocurre algún problema al enviar el mensaje o conectarse con el servidor SMTP.
 */
    public void enviarCorreo() throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, contraseña);
            }
        });

        Message mensaje = new MimeMessage(session);
        mensaje.setFrom(new InternetAddress(remitente));
        mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
        mensaje.setSubject(asunto);

        Multipart multipart = new MimeMultipart();

        // Agregar cuerpo HTML
        MimeBodyPart cuerpoParte = crearCuerpoConArchivos();
        multipart.addBodyPart(cuerpoParte);

        // Agregar todos los archivos adjuntos
        int contador = 1;
        for (File archivo : archivosAdjuntos) {
            MimeBodyPart adjuntoParte = new MimeBodyPart();
            DataSource fuente = new FileDataSource(archivo);
            adjuntoParte.setDataHandler(new DataHandler(fuente));
            adjuntoParte.setFileName(archivo.getName());

            String nombre = archivo.getName().toLowerCase();
            if (nombre.matches(".*\\.(jpg|jpeg|png|gif|bmp|pdf)")) {
                adjuntoParte.setHeader("Content-ID", "<archivo" + contador + ">");
                adjuntoParte.setDisposition(MimeBodyPart.INLINE);
            } else {
                adjuntoParte.setDisposition(MimeBodyPart.ATTACHMENT);
            }

            multipart.addBodyPart(adjuntoParte);
            contador++;
        }

        mensaje.setContent(multipart);
        Transport.send(mensaje);
        System.out.println("✅ Correo enviado correctamente.");
    }

   /**
 * Método principal de prueba (actualmente vacío).
 * Puedes usar este espacio para realizar pruebas de envío si lo deseas.
 *
 * @param args Argumentos de línea de comandos (no utilizados).
 */
    public static void main(String[] args) {
       
        
    }
}
