package com.example.spring.controller;

import com.example.spring.service.InvoicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invoices")
public class InvoicesController {
    @Autowired
    private InvoicesService invoicesService;

    @GetMapping("/generate/{invoice_id}")
    public void generateInvoice(@PathVariable("invoice_id") Long invoiceId) throws Exception{
//        invoicesService.generatePdf(param, "invoice");
    }
}
