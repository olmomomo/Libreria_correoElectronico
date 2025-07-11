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


public class correo {

    private String remitente;
    private String contraseña;
    private String destinatario;
    private String asunto;
    private String cuerpo;
    private List<File> archivosAdjuntos = new ArrayList<>();

    /**
     *
     */
    public correo() {}

    /**
     *
     * @param correo
     * @param contrasena
     */
    public void setRemitente(String correo, String contrasena) {
        this.remitente = correo;
        this.contraseña = contrasena;
    }

    /**
     *
     * @param correo
     */
    public void setDestinatario(String correo) {
        this.destinatario = correo;
    }

    /**
     *
     * @param asunto
     * @param cuerpo
     */
    public void setContenido(String asunto, String cuerpo) {
        this.asunto = asunto;
        this.cuerpo = cuerpo;
    }

    // ✅ Agrega varios archivos

    /**
     *
     * @param rutaArchivo
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
     *
     * @param rutaArchivo
     * @throws IOException
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

    // ✅ Inserta imágenes y PDFs en el cuerpo
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
     *
     * @throws MessagingException
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

    // Ejemplo de uso

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
       
        
    }
}
