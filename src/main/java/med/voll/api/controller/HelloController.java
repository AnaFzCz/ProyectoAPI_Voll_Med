package med.voll.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public String holaMundo() {
        return "\nHola Mundo - Ana - API REST es una interfaz de programación de aplicaciones que sigue los principios de diseño dell estilo arquitectonico REST";
    }

}
