package Config;

import com.resend.services.emails.model.CreateEmailOptions;
import io.github.cdimascio.dotenv.Dotenv;
import com.resend.*;
import com.resend.core.exception.ResendException;

public class ResendConfig {

    Resend resend;
    private static final Dotenv dotenv = Dotenv.load();
    private static final String RESEND_API_KEY = dotenv.get("RESEND_API_KEY");
    private static final String RESEND_DOMAIN = dotenv.get("RESEND_DOMAIN");

    public ResendConfig() {
        this.resend = new Resend(RESEND_API_KEY);
    }

    public boolean sendEmail(String email, String userName, String animalName, String destination) {
        String htmlContent = "<html>"
                + "<head>"
                + "<style>"
                + "body {"
                + "  font-family: Arial, sans-serif;"
                + "  background-color: #f4f4f9;"
                + "  color: #333;"
                + "  margin: 0;"
                + "  padding: 20px;"
                + "}"
                + ".email-container {"
                + "  background-color: #ffffff;"
                + "  border-radius: 8px;"
                + "  padding: 30px;"
                + "  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);"
                + "}"
                + "h1 {"
                + "  color: #4CAF50;"
                + "}"
                + "p {"
                + "  font-size: 16px;"
                + "  line-height: 1.5;"
                + "}"
                + ".footer {"
                + "  font-size: 12px;"
                + "  color: #888888;"
                + "  margin-top: 20px;"
                + "}"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div class='email-container'>"
                + "<h1>¡Gracias, " + userName + "!</h1>"
                + "<p>Nos alegra saber que has decidido adoptar a " + animalName + ". ¡Gracias por darle una nueva oportunidad a un ser querido!</p>"
                + "<p>El animal será enviado a: <strong>" + destination + "</strong>. Nos aseguraremos de que llegue de forma segura y te mantendremos informado durante todo el proceso.</p>"
                + "<p>Recuerda que siempre estamos aquí para apoyarte en el proceso. Si tienes alguna pregunta, no dudes en contactarnos.</p>"
                + "<p>Saludos cordiales,</p>"
                + "<p>El equipo de adopciones de mascotas</p>"
                + "<div class='footer'>"
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";

        CreateEmailOptions params = CreateEmailOptions.builder()
                .from(RESEND_DOMAIN)
                .to(email)
                .subject("Gracias por adoptar a " + animalName)
                .text("Gracias por adoptar a " + animalName)
                .html(htmlContent)
                .build();

        try {
            resend.emails().send(params);
            return true;
        } catch (ResendException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
