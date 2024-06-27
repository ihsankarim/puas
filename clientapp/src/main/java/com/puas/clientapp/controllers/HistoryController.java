package com.puas.clientapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.puas.clientapp.models.History;
import com.puas.clientapp.models.dto.request.HistoryRequest;
import com.puas.clientapp.services.HistoryService;

@Controller
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @GetMapping
    public String history(Model model) {
        List<History> histories = historyService.getAllHistoryByUser();
        model.addAttribute("histories", histories);
        model.addAttribute("isActive", "riwayat");
        return "history/history";
    }

    @GetMapping("/{id}")
    public String getByIdHistory(@PathVariable Integer id, Model model) {
        History historyResponse = historyService.getByIdWithDTO(id);
        model.addAttribute("history", historyResponse);
        model.addAttribute("isActive", "riwayat");
        return "history/history-detail";
    }

    @GetMapping("/admin")
    public String historyAdmin(Model model) {
        List<History> histories = historyService.getAllHistoriesForAdmin();
        model.addAttribute("histories", histories);
        model.addAttribute("isActive", "riwayat");
        return "dashboard/riwayat";
    }

    @GetMapping("/update/{id}")
    public String updateHistoryView(@PathVariable Integer id, Model model) {
        History historyResponse = historyService.getByIdWithDTO(id);

        // Menyiapkan objek HistoryRequest untuk form
        HistoryRequest historyRequest = new HistoryRequest();
        historyRequest.setDate(historyResponse.getDate());
        historyRequest.setNote(historyResponse.getNote());
        historyRequest.setStatusId(historyResponse.getStatus().getId());

        // Menambahkan ke model
        model.addAttribute("history", historyResponse);
        model.addAttribute("historyRequest", historyRequest);

        return "history/history-update";
    }

    // Metode untuk memproses form update
    @PutMapping("/update/{id}")
    public String updateHistory(@PathVariable Integer id, HistoryRequest historyRequest) {
        historyService.updateHistory(id, historyRequest);
        return "redirect:/dashboad/riwayat";
    }

    // Metode untuk menghapus history
    @GetMapping("/delete/{id}")
    public String deleteHistory(@PathVariable Integer id) {
        historyService.deleteHistory(id);
        return "redirect:/dashboard/riwayat";
    }
}
