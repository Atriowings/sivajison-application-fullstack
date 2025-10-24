package com.TechGhuru.SivajiAndSons.controller;
import java.util.List;
import com.TechGhuru.SivajiAndSons.service.TwilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/whatsapp")
// @CrossOrigin(origins = "https://admin.sivajison.com")
@CrossOrigin(origins = "*")
public class WhatsAppController {

    @Autowired
    private TwilioService twilioService;

    @PostMapping("/send")
    public String sendWhatsAppMessage(@RequestParam String to, @RequestParam String message) {
        String sid = twilioService.sendWhatsAppMessage(to, message);
        return "Message sent successfully. SID: " + sid;
    }
}

