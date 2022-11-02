package com.socialmedia.SocialMediaApp.Service;

import com.socialmedia.SocialMediaApp.Model.AppUser;
import com.socialmedia.SocialMediaApp.Model.EmailToken;
import com.socialmedia.SocialMediaApp.Model.Role;
import com.socialmedia.SocialMediaApp.Repo.AppUserRepo;
import com.socialmedia.SocialMediaApp.Repo.EmailTokenRepo;
import com.socialmedia.SocialMediaApp.Repo.RoleRepo;
import com.socialmedia.SocialMediaApp.Util.EmailSenderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDateTime;


@Service
@AllArgsConstructor
public class EmailTokenServiceImpl implements EmailTokenService{

    private final EmailTokenRepo emailTokenRepo;
    private final EmailSenderService emailSender;
    private final AppUserRepo appUserRepo;
    private final RoleRepo roleRepo;

    public void saveEmailToken(EmailToken emailToken){
        emailTokenRepo.save(emailToken);
    }

    public EmailToken getToken(String token){
        return emailTokenRepo.findByToken(token);
    }

    public int setConfirmedAt(String token){
        return emailTokenRepo.updateConfirmedAt(token, LocalDateTime.now());
    }

    @Transactional
    public String confirmToken(String token){
        EmailToken emailToken = getToken(token);

        if(emailToken.getConfirmedAt() != null){
            throw new IllegalStateException("Email already confirmed.");
        }

        LocalDateTime expiredAt = emailToken.getExpiresAt();

        if(emailToken.getConfirmedAt() == null && expiredAt.isBefore(LocalDateTime.now())){
            throw new IllegalStateException("Token expired.");
        }

        setConfirmedAt(token);
        enableAppUser(emailToken.getAppUser().getEmail());
        addRoleToAppUser(emailToken.getAppUser().getEmail(), "ROLE_USER");
        return "User confirmed email successfully!";
    }

    public String sendEmail(AppUser appUser, String emailToken){
        String token = emailToken;
        String link = "http://localhost:8080/api/user/confirm?token="+token;
        emailSender.send(appUser.getEmail(), buildEmail(appUser.getFirstName(), link));

        return emailToken;

    }

    public int enableAppUser(String email){
        return appUserRepo.enableAppUser(email);
    }


    public void addRoleToAppUser(String email, String roleName) {
        AppUser appUser = appUserRepo.findByEmail(email);
        Role role = roleRepo.findByName(roleName);
        appUser.getRoles().add(role);
    }
    private String buildEmail(String name, String link){
        return  "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>Thanks for signing up!</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }


}
