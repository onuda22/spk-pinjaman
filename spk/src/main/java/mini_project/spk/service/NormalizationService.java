package mini_project.spk.service;

import mini_project.spk.model.NormalizationParameter;

import java.util.List;

public interface NormalizationService {

    List<NormalizationParameter> getAll();
    NormalizationParameter create(NormalizationParameter request);
    NormalizationParameter updateById(Integer id, NormalizationParameter updateReq);
    void deleteById(Integer id);
}
