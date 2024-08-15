package mini_project.spk.service;

import mini_project.spk.dto.AnalysisDTO;
import mini_project.spk.model.Analysis;
import mini_project.spk.model.Customer;

import java.util.List;

public interface AnalysisService {

    List<Analysis> getAll();
    Analysis getById(Integer id);
    Analysis create(AnalysisDTO request);
    Analysis updateById(Integer id, AnalysisDTO updateReq);
    void deleteById(Integer id);

}
