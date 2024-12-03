package com.example.security.controller;

import com.example.security.model.*;
import com.example.security.repository.TemporalEmailsRepository;
import com.example.security.service.EmailSenderService;
import com.example.security.service.GlobalUpcBarcodesService;
import com.example.security.service.ProductService;
import com.example.security.service.SalesService;
import com.example.security.service.TemporalEmailsService;

import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/api/v1/demo-controller")
public class DemoControllers {

    // Service instances
    private final EmailSenderService emailSenderService;
    @Autowired
    private final GlobalUpcBarcodesService globalUpcBarcodesService;
    @Autowired
    private final TemporalEmailsService temporalEmailsService;


    //Instances of repositories
    @Autowired
    private TemporalEmailsRepository temporalEmailsRepository;
    //private final SoldItemsRepository;


    public DemoControllers(EmailSenderService emailSenderService,
                           TemporalEmailsService temporalEmailsService,
                           SalesService salesService,
                           GlobalUpcBarcodesService globalUpcBarcodesService,
                           ProductService productService
    ) {
        this.emailSenderService = emailSenderService;
        this.temporalEmailsService = temporalEmailsService;
        this.globalUpcBarcodesService = globalUpcBarcodesService;
    }


    //used for testing
    @GetMapping("/api/v1/demo-controller")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from secured endpoint");
    }


    //Save Global upc barcodes
    @PostMapping("/barcodes/saveGlobalUpcBarcodes")
    public ResponseEntity saveGlobalBarcodes(@RequestBody List<GlobalUpcBarcodes> barcodes) {
        this.globalUpcBarcodesService.save(barcodes);
        return ResponseEntity.ok("Items saved");
    }

    @GetMapping("/barcodes/getGlobalUpcBarcodes")
    public List<GlobalUpcBarcodes> getGlobalUpcBarcodes() {
        return this.globalUpcBarcodesService.getGlobalUpcBarcodes();
    }


    //save the emails with temporary verification codes
    @PostMapping("/temporal-email/save")
    public ResponseEntity saveTemporaryEmail(@RequestBody TemporalEmails temporalEmail) {
        int code = this.temporalEmailsService.save(temporalEmail);
        String message = "Добро пожаловать в Марту!" + "\n\nВаш временный пароль: "
                + Integer.toString(code) + "\nВы можете использовать код в ближайшие пол часа.\n\nС уважением,\nКоманда Marta Software";
        this.emailSenderService.sendEmail(temporalEmail.getEmail(), "Ваш временный пароль", message);
        return ResponseEntity.ok("Email saved temporary");
    }

    //Get temporary verification codes
    @GetMapping("/temporal-email/getTempCodes/{email}")
    public List<TemporalEmails> getAllTempCodes(@PathVariable
                                                @Pattern(
                                                        regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
                                                        message = "Invalid email format"
                                                ) String email) {
        return this.temporalEmailsService.getAllTemporaryCodes(email);
    }
}

