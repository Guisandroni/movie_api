
package dev.guisandroni.movieapi.Service;

import dev.guisandroni.movieapi.Entity.Movie;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public void sendMoviesEmail(String to, List<Movie> movies) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");

        Context context = new Context();
        context.setVariable("movies", movies);

        String htmlContent = templateEngine.process("movies-email", context);

        helper.setTo(to);
        helper.setSubject("Sua Lista de Filmes");
        helper.setText(htmlContent, true);

        mailSender.send(mimeMessage);
    }
}
