package com.best.hello.controller.RCE;

import groovy.lang.GroovyShell;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class GroovyVAl {

 
    public void vAl(String cmd) {
        GroovyShell shell = new GroovyShell();
        shell.evaluate(cmd);
    }


    @GetMapping("/safe")
    public void safe(String cmd) {
      
        List<String> safeCodeList = Arrays.asList("\"id\".execute()", "\"whoami\".execute()");

        if (!safeCodeList.contains(cmd)) {
            throw new RuntimeException("unsafe code detected: " + cmd);
        }

        GroovyShell shell = new GroovyShell();
        shell.evaluate(cmd);
    }

}
