package mini_project.spk.controller;

import lombok.RequiredArgsConstructor;
import mini_project.spk.dto.AnalysisResultDTO;
import mini_project.spk.model.Analysis;
import mini_project.spk.service.AnalysisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AnalysisController {
    private final AnalysisService analysisService;

    @GetMapping("/analyze/{loanId}")
    public ResponseEntity<AnalysisResultDTO> analyzeLoan(@PathVariable Integer loanId) {
        AnalysisResultDTO result = analysisService.analyzeLoan(loanId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/analysis-show")
    public List<Analysis> showAll(){
        return analysisService.getAll();
    }

    @GetMapping("/analysis-show/{id}")
    public Analysis showById(@PathVariable Integer id){
        return analysisService.getById(id);
    }

    @DeleteMapping("/analysis-delete/{id}")
    public void deleteById(@PathVariable Integer id){
        analysisService.deleteById(id);
    }
}
