package mini_project.spk.service;

import mini_project.spk.dto.AnalysisDTO;
import mini_project.spk.dto.AnalysisResultDTO;
import mini_project.spk.model.Analysis;
import mini_project.spk.model.Customer;

import java.util.List;

public interface AnalysisService {

    List<Analysis> getAll();
    Analysis getById(Integer id);
    void deleteById(Integer id);
    AnalysisResultDTO analyzeLoan(Integer loanId);

}
